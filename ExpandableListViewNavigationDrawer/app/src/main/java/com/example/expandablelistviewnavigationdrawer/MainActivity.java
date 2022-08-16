package com.example.expandablelistviewnavigationdrawer;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private Toolbar mtoolbar;

    private RecyclerView recyclerView;
    personAdapter adapter;

    ArrayList arrayList = new ArrayList<>(Arrays.asList("Home", "Feedback", "Contact Us", "Share us",
            "Version Code", "Live Watch", "Add Items"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler1);

        drawerLayout = findViewById(R.id.drawer_layout);
        mtoolbar = (Toolbar) findViewById(R.id.tool_bar);
        //set support action bar
        mtoolbar.setTitleTextColor(0xFFFFFFFF);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, mtoolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.purple_200));

        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        adapter = new personAdapter(arrayList, MainActivity.this);
        //Conecting Adapter Class with the recycler view
        recyclerView.setAdapter(adapter);
    }
}