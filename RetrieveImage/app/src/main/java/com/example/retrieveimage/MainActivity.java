package com.example.retrieveimage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewRetrieveImageId;
    private GridLayoutManager gridLayoutManager;
    RecyclerView.LayoutManager layoutManager;

    private DatabaseReference reference;

    //variables
    private ArrayList<Message> messages;
    private RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewRetrieveImageId = findViewById(R.id.recyclerViewRetrieveImageId);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerViewRetrieveImageId.setLayoutManager(gridLayoutManager);
        recyclerViewRetrieveImageId.setLayoutManager(layoutManager);
        recyclerViewRetrieveImageId.setHasFixedSize(true);

        //Firebase
        reference = FirebaseDatabase.getInstance().getReference();

        //Arraylist
        messages = new ArrayList<>();

        //clear Arraylist
        clearAll();

        //get Data method
        GetDataFromDatabase();


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        //lets refresh this code
//        if(requestCode == PICK_IMAGE && resultCode == )
//    }

    private void GetDataFromDatabase() {
        Query query = reference.child("Image");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearAll();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Message message = new Message();
                    message.setUrl(snapshot.child("url").getValue().toString());
                    message.setFileName(snapshot.child("fileName").getValue().toString());
                    messages.add(message);
                }
                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), messages);
                recyclerViewRetrieveImageId.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void clearAll(){
        if(messages != null){
            messages.clear();
            if(recyclerAdapter != null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        messages = new ArrayList<>();
    }
}