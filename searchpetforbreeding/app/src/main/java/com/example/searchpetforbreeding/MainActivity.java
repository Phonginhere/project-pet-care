package com.example.searchpetforbreeding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.searchpetforbreeding.adapter.PetAdapter;
import com.example.searchpetforbreeding.databinding.ActivityMainBinding;
import com.example.searchpetforbreeding.models.Pets;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private RecyclerView rv_pet_id;
    private PetAdapter petAdapter;
    private Spinner spinnerGender;
    private Spinner spinnerSpecies;

    LinearLayout ckAdditionalSearchCheckBox;
    LinearLayout ckAdditionalSearchSpinner;
    LinearLayout showoHideButtonFilter;

    private ArrayList<Pets> petsList;
    private ArrayList<Pets> petsListSelected = new ArrayList<>();
    private ArrayList<String> spinnersSpeciesList = new ArrayList<>();

    // Define ActionBar object
    ActionBar actionBar;

    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = String.valueOf(getTitle());

        ckAdditionalSearchCheckBox= findViewById(R.id.ck_additional_search_checkBox);
        ckAdditionalSearchSpinner= findViewById(R.id.ck_additional_search_spinner);
        showoHideButtonFilter = findViewById(R.id.show_or_hide_additional_search);

        ckAdditionalSearchCheckBox.setVisibility(View.GONE);
        ckAdditionalSearchSpinner.setVisibility(View.GONE);
        showoHideButtonFilter.setVisibility(View.GONE);

        rv_pet_id = findViewById(R.id.rv_pet_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_pet_id.setLayoutManager(linearLayoutManager);

        petsList = new ArrayList<>();
        petAdapter = new PetAdapter(petsList);
        rv_pet_id.setAdapter(petAdapter);

        getListPetsFromRealTimeDataBase();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getListPetsFromRealTimeDataBase(){

        //Real time database

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = database.getReference("Pets");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    Pets pets = dataSnapshot.getValue(Pets.class);
//                    petsList.add(pets);
//                }
//                petAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        //raw data
        petsList.add(new Pets("king", true, "corgi", "dog", 10L, 20L,
                "17/04/2004", "green", true, "don't know ya", 1, 2));
        petsList.add(new Pets("kong", false, "beggie", "cat", 5L, 1L,
                "17/04/2021", "red", false, "...", 2, 1));
        petsList.add(new Pets("cat", false, "beggie", "cat", 5L, 1L,
                "17/04/2021", "red", false, "...", 2, 1));
        petsList.add(new Pets("cat", false, "yellow", "cat", 5L, 1L,
                "17/04/2021", "yellow", false, "...", 2, 1));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //setting action bar
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));
        // Set BackgroundDrawable action bar
        actionBar.setBackgroundDrawable(colorDrawable);

        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search_id);

        SearchView searchView = (SearchView) menuItem.getActionView();

        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                searchView.setQueryHint("Type here to search");

                //button for hide and show after clicking search icon
                Button btnHideAndShowAdditionalSearch = findViewById(R.id.btn_hide_show);
                btnHideAndShowAdditionalSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int xx = 0;
                        int currentTag = btnHideAndShowAdditionalSearch.getTag() == null ? 0 :
                                (int)btnHideAndShowAdditionalSearch.getTag();
                        currentTag ++;
                        btnHideAndShowAdditionalSearch.setTag(currentTag);
                        if(currentTag % 2 == 0) {
                            btnHideAndShowAdditionalSearch.setText("Show");
                            ckAdditionalSearchCheckBox.setVisibility(View.VISIBLE);
                            ckAdditionalSearchSpinner.setVisibility(View.VISIBLE);
                        } else {
                            btnHideAndShowAdditionalSearch.setText("Hide");
                            ckAdditionalSearchCheckBox.setVisibility(View.GONE);
                            ckAdditionalSearchSpinner.setVisibility(View.GONE);
                        }
                    }
                });

                //display after clicking search icon
                ckAdditionalSearchCheckBox.setVisibility(View.VISIBLE);
                ckAdditionalSearchSpinner.setVisibility(View.VISIBLE);
                showoHideButtonFilter.setVisibility(View.VISIBLE);

                //begin display spinner species
                for(Pets petsItem: petsList){
                    spinnersSpeciesList.add(petsItem.getSpecies());
                }
                Set<String> speciesWithoutDuplicate = new LinkedHashSet<String>(spinnersSpeciesList);

                speciesWithoutDuplicate.remove("Species");
                speciesWithoutDuplicate.remove("All");

                spinnersSpeciesList.clear();

                spinnersSpeciesList.add("Species");
                spinnersSpeciesList.add("All");

                for(String i : speciesWithoutDuplicate){
                    spinnersSpeciesList.add(i);
                }
                //end display spinner species

                //begin spinnerSpecies spinner
                spinnerSpecies = (Spinner) findViewById(R.id.speciesFilter);
                ArrayAdapter<String> myadapterSpecies= new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1, spinnersSpeciesList);
                myadapterSpecies.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSpecies.setAdapter(myadapterSpecies);
                spinnerSpecies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = parent.getItemAtPosition(position).toString();

                        if(selectedItem.equals("All"))
                        {
                            petsListSelected.clear();
                            petAdapter = new PetAdapter(petsList);
                            rv_pet_id.setAdapter(petAdapter);
                        }else{
                            petsListSelected.clear();
                            for(Pets petItemOnlySpecies: petsList){
                                if(selectedItem.equals(petItemOnlySpecies.getSpecies())){
                                    petsListSelected.add(petItemOnlySpecies);
                                }
                            }
                            petAdapter = new PetAdapter(petsListSelected);
                            rv_pet_id.setAdapter(petAdapter);
                        }
                        //end spinnerSpecies spinner
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                //begin gender spinner
                spinnerGender = (Spinner) findViewById(R.id.id_gender_spinner);
                ArrayAdapter<String> myadapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gender));
                myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGender.setAdapter(myadapter);

                spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        String selectedItem = parent.getItemAtPosition(position).toString();

                            if(selectedItem.equals("Male"))
                            {
                                petsListSelected.clear();
                                for(Pets petItem: petsList){
                                    if(petItem.isGender() == true){
                                        petsListSelected.add(petItem);
                                    }
                                }

                                petAdapter = new PetAdapter(petsListSelected);
                                rv_pet_id.setAdapter(petAdapter);
                            }else if(selectedItem.equals("Female"))
                            {
                                petsListSelected.clear();

                                for(Pets petItem: petsList){
                                    if(petItem.isGender() == false){
                                        petsListSelected.add(petItem);
                                    }
                                }

                                petAdapter = new PetAdapter(petsListSelected);
                                rv_pet_id.setAdapter(petAdapter);
                            }else{
                                petsListSelected.clear();
                                petAdapter = new PetAdapter(petsList);
                                rv_pet_id.setAdapter(petAdapter);
                            }

                        }
                    //end  gender spinner
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String searchText) {


                        CheckBox colorFilter = findViewById(R.id.colorFilter);
                        CheckBox breedFilter = findViewById(R.id.breedFilter);

                        //check 2: color and breed
                        if((colorFilter.isChecked() == true && breedFilter.isChecked() == true)
                                || (colorFilter.isChecked() == false && breedFilter.isChecked() == false)){
                            colorAbreedFilterList(searchText);
                        }
                        //check 1: color or breed
                        if(colorFilter.isChecked() == true){
                            colorFilter(searchText);
                        }else if(breedFilter.isChecked() == true){
                            breedFilter(searchText);
                        }
                        return true;
                    }

                });
                return true;
            }

            //begin click back icon to close search
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                ckAdditionalSearchCheckBox.setVisibility(View.GONE);
                ckAdditionalSearchSpinner.setVisibility(View.GONE);
                showoHideButtonFilter.setVisibility(View.GONE);
                return MainActivity.super.onCreateOptionsMenu(menu);
            }
            //end click back icon to close search
        });

        return super.onCreateOptionsMenu(menu);
    }


    private void colorAbreedFilterList(String searchText) {
        List<Pets> filteredPet = new ArrayList<>();
        for(Pets item: petsList){

            if(item.getColor().toLowerCase().contains(searchText.toLowerCase())){
                filteredPet.add(item);
            }
        }
        if(filteredPet.isEmpty()){
//            Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show();
            ColorDrawable colorDrawable
                    = new ColorDrawable(Color.parseColor("#D41717"));
            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);
        }else{
            ColorDrawable colorDrawable
                    = new ColorDrawable(Color.parseColor("#0F9D58"));
            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);

            petAdapter.setFilteredList(filteredPet);
        }
    }

    public void breedFilter(String searchText) {
        List<Pets> filteredPet = new ArrayList<>();
        for(Pets item: petsList){

            if(item.getBreed().toLowerCase().contains(searchText.toLowerCase())){
                filteredPet.add(item);
            }
        }
        if(filteredPet.isEmpty()){
//            Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show();
            ColorDrawable colorDrawable
                    = new ColorDrawable(Color.parseColor("#D41717"));
            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);
        }else{
            ColorDrawable colorDrawable
                    = new ColorDrawable(Color.parseColor("#0F9D58"));
            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);

            petAdapter.setFilteredList(filteredPet);
        }
    }

    public void colorFilter(String searchText) {

        List<Pets> filteredPet = new ArrayList<>();
        for(Pets item: petsList){

            if(item.getColor().toLowerCase().contains(searchText.toLowerCase())){
                filteredPet.add(item);
            }
        }
        if(filteredPet.isEmpty()){
//            Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show();

            ColorDrawable colorDrawable
                    = new ColorDrawable(Color.parseColor("#D41717"));
            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);

        }else{
            ColorDrawable colorDrawable
                    = new ColorDrawable(Color.parseColor("#0F9D58"));
            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);

            petAdapter.setFilteredList(filteredPet);
        }
    }
}