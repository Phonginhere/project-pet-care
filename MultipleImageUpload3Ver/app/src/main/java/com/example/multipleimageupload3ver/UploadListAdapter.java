package com.example.multipleimageupload3ver;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UploadListAdapter extends RecyclerView.Adapter<UploadListAdapter.MyViewHolder> {

    public List<String> fileNameList;
    public List<Uri> fileUriList;
    public List<String> fileDoneList;

    public UploadListAdapter(List<String> fileNameList, List<Uri> fileUriList, List<String> fileDoneList) {
        this.fileNameList = fileNameList;
        this.fileUriList = fileUriList;
        this.fileDoneList = fileDoneList;
    }

    @NonNull
    @Override
    public UploadListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadListAdapter.MyViewHolder holder, int position) {
        String fileName = fileNameList.get(position);
        holder.fileNameView.setText(fileName);

        holder.fileUriView.setImageURI(fileUriList.get(position));

        String fileDone = fileDoneList.get(position);
        if(fileDone.equals("uploadingMultipleImage")){
            holder.fileDoneView.setImageResource(R.drawable.process_icon);
        }else{
            holder.fileDoneView.setImageResource(R.drawable.successful_icon);
        }

        if(fileDone.equals("uploadingSingleImage")){
            holder.fileDoneView.setImageResource(R.drawable.process_icon);
        }else{
            holder.fileDoneView.setImageResource(R.drawable.successful_icon);
        }

    }

    @Override
    public int getItemCount() {
        return fileNameList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public TextView fileNameView;
        public ImageView fileUriView;
        public ImageView fileDoneView;
        public Button btnRemoveUpload;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            fileNameView = (TextView) mView.findViewById(R.id.upload_filename);
            fileUriView = (ImageView) mView.findViewById(R.id.upload_icon);
            fileDoneView = (ImageView) mView.findViewById(R.id.tv_process_check);
            btnRemoveUpload = (Button) mView.findViewById(R.id.btn_remove_upload);


        }
    }
}
