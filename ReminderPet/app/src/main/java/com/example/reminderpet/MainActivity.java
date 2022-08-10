package com.example.reminderpet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.reminderpet.adapter.ReminderAdapter;
import com.example.reminderpet.models.Reminder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView id_reminder_recycleview;
    ImageButton btn_add_activity;
    // Arraylist for storing data
    private ArrayList<Reminder> reminders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id_reminder_recycleview = findViewById(R.id.id_reminder_recycleview);
        btn_add_activity = findViewById(R.id.btn_add_activity);
        btn_add_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, AddReminderActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        // we are initializing our adapter class and passing our arraylist to it.
        ReminderAdapter reminderAdapter = new ReminderAdapter(this, reminders);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        id_reminder_recycleview.setLayoutManager(linearLayoutManager);
        id_reminder_recycleview.setAdapter(reminderAdapter);

    }
}