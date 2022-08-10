package com.example.searchandfilterlist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Shape> shapelist = new ArrayList<>();

    private ListView listView;
    private SearchView searchView;

    private String selectedFilter = "all";
    private String currentSearchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSearchWidget();

    }

    public void initSearchWidget(){
        SearchView searchView = (SearchView) findViewById(R.id.simpleSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                currentSearchText = s;
                ArrayList<Shape> filteredShapes = new ArrayList<Shape>();
                for(Shape shape: shapelist){
                    if(shape.getName.toLowerCase().contains(s.toLowerCase())){
                        if(selectedFilter.equals("all")){
                            filteredShapes.add(shape);
                        }else{
                          if(shape.getName().toLowerCase().contains(selectedFilter)){
                              filteredShapes.add(shape);
                          }
                        }
                    }
                }
                shapeAdapter adapter = new Shaeddkdk(getApplicationContext(), 0, filteredShapes);
                listview.setAdapter(adapter);

                return false;
            }
        });


    }

    private void filterList(String status){
        ArrayList<Shape> filteredShapes = new ArrayList<>();
        for(Shape shape: shapeList){
            if(shape.getName().toLowerCase().contains(status)){

                if(shape.getName().toLowerCase().contains(status)){

                    if(currentSearchText != ""){

                        filteredShapes.add(shape);

                    }else{
                        if(shape.getName().toLowerCase().contains(selectedFilter.toLowerCase())){
                            filteredShapes.add(shape);
                        }
                    }
                }
                filteredShapes.add(shape);
            }
        }
        ShapeAdapter adapter = new Shaeddkdk(getApplicationContext(), 0, filteredShapes);
        listview.setAdapter(adapter);
    }

    public void nameFilterTapped(View view) {
        filterList("name");
    }

    public void ageFilterTapped(View view) {
        filterList("age");
    }

    public void weightFilterTapped(View view) {
        filterList("name");
    }
}