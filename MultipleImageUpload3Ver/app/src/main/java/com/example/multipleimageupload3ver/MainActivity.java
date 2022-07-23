package com.example.multipleimageupload3ver;

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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int IMAGE_CODE = 1;
    Button select_btn;
    RecyclerView recycleViewId;
    ProgressBar progressBar;
    ImageView imageViewTick;

    private List<String> fileNameList;
    private List<String> fileDoneList;

    private UploadListAdapter uploadListAdapter;

    private StorageReference mStorage;

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStorage = FirebaseStorage.getInstance().getReference();

        select_btn = findViewById(R.id.select_btn);
        recycleViewId = findViewById(R.id.recycleViewId);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageViewTick = (ImageView) findViewById(R.id.imageViewTick);

        fileNameList = new ArrayList<>();
        fileDoneList = new ArrayList<>();

        uploadListAdapter = new UploadListAdapter(fileNameList, fileDoneList);

        //recycler view

        recycleViewId.setLayoutManager(new LinearLayoutManager(this));
        recycleViewId.setHasFixedSize(true);
        recycleViewId.setAdapter(uploadListAdapter);


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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        StorageReference fileToUpload;

        if(requestCode == IMAGE_CODE && resultCode == RESULT_OK){
            if(data.getClipData() != null){

                int totalItemSelected = data.getClipData().getItemCount();
                for(int i = 0; i < totalItemSelected; i++){
                    Uri fileUri = data.getClipData().getItemAt(i).getUri();
                    String fileName = getFileName(fileUri);
                    fileNameList.add(fileName);
                    uploadListAdapter.notifyDataSetChanged();
                        fileToUpload = mStorage.child("Images").child(fileName);
                        fileToUpload.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
//                                progressBar.setVisibility(View.GONE);
//                                imageViewTick.setVisibility(View.VISIBLE);
                            }
                        });

                }
//                Toast.makeText(this, "Multiple", Toast.LENGTH_SHORT).show();
            }else if(data.getData() != null){
                Uri fileUri = data.getData();
                String fileName = getFileName(fileUri);
                fileNameList.add(fileName);
                uploadListAdapter.notifyDataSetChanged();
                fileToUpload = mStorage.child("Images").child(fileName);
                fileToUpload.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
//                        progressBar.setVisibility(View.GONE);
//                        imageViewTick.setVisibility(View.VISIBLE);
                    }
                });
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