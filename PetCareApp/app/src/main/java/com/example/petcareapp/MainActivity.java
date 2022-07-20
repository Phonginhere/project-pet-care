package com.example.petcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petcareapp.models.FirebaseDB;
import com.example.petcareapp.models.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FirebaseDB.APP_NAME);

        //myRef.setValue("Hello, World!");
        List<Product> products = new ArrayList<>();
        products.add(new Product("iphone 3", 2013));
        products.add(new Product("iphone 4", 2014));
        products.add(new Product("iphone 5", 2015));

        myRef.child(FirebaseDB.TABLE_PRODUCT).setValue(products);
        //when data changed, a function is called?
        //observe changes from DB
        myRef.child(FirebaseDB.TABLE_PRODUCT).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("data changeeeeeee", "djkskidsi");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setContentView(R.layout.activity_main);
        final EditText edit_name = findViewById(R.id.edit_name);
        final EditText edit_position = findViewById(R.id.edit_position);
        Button btn = findViewById(R.id.btn_submit);
        DAOEmployee dao = new DAOEmployee();
        btn.setOnClickListener(view -> {
            Employee emp = new Employee(edit_name.getText().toString(), edit_position.getText().toString());
            dao.add(emp).addOnSuccessListener(suc->
            {
                Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->
            {
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }
}