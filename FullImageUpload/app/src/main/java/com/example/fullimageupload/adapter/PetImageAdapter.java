package com.example.fullimageupload.adapter;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fullimageupload.R;
import com.google.gson.Gson;

import java.util.List;

public class PetImageAdapter extends RecyclerView.Adapter<PetImageAdapter.ViewHolder> {
    public List<String> fileNameList;
    public List<Uri> fileUriList;
    public List<String> fileDoneList;


    public PetImageAdapter(List<String> fileNameList, List<Uri> fileUriList, List<String> fileDoneList) {
        this.fileNameList = fileNameList;
        this.fileUriList = fileUriList;
        this.fileDoneList = fileDoneList;
    }

    @NonNull
    @Override
    public PetImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetImageAdapter.ViewHolder holder, int position) {
        String fileName = fileNameList.get(position);
        holder.fileNameView.setText(fileName);

        holder.fileUriView.setImageURI(fileUriList.get(position));

        String fileDone = fileDoneList.get(position);

        if(fileDone.equals("doneImage")){
            fileNameList.remove(position);
            fileUriList.remove(position);
            fileDoneList.remove(position);
        }
    }

    @Override
    public int getItemCount() {
        if (fileNameList != null) {
            return fileNameList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public TextView fileNameView;
        public ImageView fileUriView;
        public ImageView fileDoneView;
        public Button btnRemoveUpload;
        public Button btnChooseMoreImage;
        public Button btnUpload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            fileNameView = (TextView) mView.findViewById(R.id.upload_filename);
            fileUriView = (ImageView) mView.findViewById(R.id.upload_icon);
            fileDoneView = (ImageView) mView.findViewById(R.id.tv_process_check);
            btnRemoveUpload = (Button) mView.findViewById(R.id.btn_remove_upload);
            btnChooseMoreImage = (Button) mView.findViewById(R.id.btn_choose_more_image);
            btnUpload = (Button) mView.findViewById(R.id.btn_upload);


            btnRemoveUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    fileNameList.remove(position);
                    fileUriList.remove(position);
                    notifyDataSetChanged();

//                    if (fileNameList.isEmpty()) {
//                        //up to local storage
//                        boolean checked = true;
//                        SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences("DATAUPLOAD", 0);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putBoolean("CHECKBOX", checked);
//                        editor.apply();
//                    }


                }
            });
        }
    }

}
