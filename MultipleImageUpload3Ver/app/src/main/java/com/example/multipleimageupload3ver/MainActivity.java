package com.example.multipleimageupload3ver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.multipleimageupload3ver.models.Image;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    long id = 0;
    String urlToken;

    Image image;

    private static final int IMAGE_CODE = 1;
    Button select_btn;
    RecyclerView recycleViewLoadingImgId;
    ImageView tvProcessCheck;
    ImageView imageViewTick;

    private List<String> fileNameList;
    private List<Uri> fileUriList;
    private List<String> fileDoneList ;
    ArrayList<String> listKey = new ArrayList<>();

    private UploadListAdapter uploadListAdapter;

    private StorageReference mStorage;
    private DatabaseReference mReference;

    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStorage = FirebaseStorage.getInstance().getReference();
        mReference = FirebaseDatabase.getInstance().getReference();

        select_btn = findViewById(R.id.select_btn);
        recycleViewLoadingImgId = findViewById(R.id.recycleViewLoadingImgId);
        tvProcessCheck = (ImageView) findViewById(R.id.tv_process_check);
        imageViewTick = (ImageView) findViewById(R.id.imageViewTick);

        fileNameList = new ArrayList<>();
        fileUriList = new ArrayList<>();
        fileDoneList = new ArrayList<>();

        uploadListAdapter = new UploadListAdapter(fileNameList, fileUriList, fileDoneList);

        //recycler view

        recycleViewLoadingImgId.setLayoutManager(new LinearLayoutManager(this));
        recycleViewLoadingImgId.setHasFixedSize(true);
        recycleViewLoadingImgId.setAdapter(uploadListAdapter);


        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_CODE);

            }
        });

        mStorage = FirebaseStorage.getInstance().getReference("Images");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        StorageReference fileToUpload;

        if(requestCode == IMAGE_CODE && resultCode == RESULT_OK){
            if(data.getClipData() != null){

                int totalItemSelected = data.getClipData().getItemCount();
                for(int i = 0; i < totalItemSelected; i++){
                    mReference = FirebaseDatabase.getInstance().getReference("Image");
                    Uri fileUri = data.getClipData().getItemAt(i).getUri();
                    String fileName = getFileName(fileUri);
                    fileNameList.add(fileName);
                    fileUriList.add(fileUri);
                    fileDoneList.add("uploadingMultipleImage");
                    uploadListAdapter.notifyDataSetChanged();
                        fileToUpload = mStorage.child(fileName);

//                        StorageReference fileUrl = fileToUpload;
                    //begin get token
//                    final StorageReference ImageName = mStorage.child("Image"+ fileUri.getLastPathSegment());
                    final StorageReference ImageName = mStorage.child(fileName);

                    ImageName.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = String.valueOf(uri);
                                    urlToken = url;
                                    mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                int getLatestKey = 0;
                                                for(DataSnapshot keyDataSnapshot : snapshot.getChildren()){
                                                    listKey.add(keyDataSnapshot.getKey());
                                                }
                                                if(listKey.size() == 0){
                                                    getLatestKey = 0;
                                                }else{
                                                    getLatestKey = Integer.parseInt(listKey.get(listKey.size() - 1));
                                                }
//                                id = (snapshot.getChildrenCount());
                                                String[] cutText = fileName.split("\\.");
                                                image = new Image();
                                                image.setImageId(getLatestKey+1);
                                                image.setFileName(cutText[0]);
                                                image.setUrl(urlToken);
                                                mReference.child(String.valueOf(getLatestKey+1)).setValue(image);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                            });
                        }
                    });
                    //end get token


                }
//                Toast.makeText(this, "Multiple", Toast.LENGTH_SHORT).show();
            }
            else if(data.getData() != null){
//                Toast.makeText(MainActivity.this, "Selected Single File", Toast.LENGTH_LONG).show();


                mReference = FirebaseDatabase.getInstance().getReference("Image");
                //begin upload one image
                Uri fileUri = data.getData();
                String fileName = getFileName(fileUri);
                fileNameList.add(fileName);
                fileUriList.add(fileUri);
                fileDoneList.add("uploadingSingleImage");
                uploadListAdapter.notifyDataSetChanged();
                fileToUpload = mStorage.child("Images").child(fileName);
                fileToUpload.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                        fileDoneList.remove("uploadingSingleImage");
                        fileDoneList.add("doneSingleImage");

                        uploadListAdapter.notifyDataSetChanged();
                    }
                });
                //begin get token
                final StorageReference ImageName = mStorage.child("Image"+ fileUri.getLastPathSegment());

                ImageName.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = String.valueOf(uri);
                                urlToken = url;
                                mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            int getLatestKey = 0;
                                            for(DataSnapshot keyDataSnapshot : snapshot.getChildren()){
                                                listKey.add(keyDataSnapshot.getKey());
                                            }
                                            if(listKey.size() == 0){
                                                getLatestKey = 0;
                                            }else{
                                                getLatestKey = Integer.parseInt(listKey.get(listKey.size() - 1));
                                            }
//                                id = (snapshot.getChildrenCount());
                                            String[] cutText = fileName.split("\\.");
                                            image = new Image();
                                            image.setImageId(getLatestKey+1);
                                            image.setFileName(cutText[0]);
                                            image.setUrl(urlToken);
                                            mReference.child(String.valueOf(getLatestKey+1)).setValue(image);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        });
                    }
                });
                //end get token

                //end upload one image
//                Toast.makeText(this, "Single", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @SuppressLint("Range")
    public String getFileName(Uri uri){
        String result = null;
        if(uri.getScheme().equals("content")){
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if(cursor != null && cursor.moveToFirst()){
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }finally {
                cursor.close();
            }
            if(result == null){
                result = uri.getPath();
                int cut = result.lastIndexOf('/');
                if(cut != -1){
                    result = result.substring(cut + 1);
                }
            }
        }
        return result;
     }
}