package com.example.sharedpreferencesarraylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etAge;
    Button btnSave;
    TextView tvSize;
    ArrayList<ModelClass> modelClassArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextPersonName);
        etAge = findViewById(R.id.editTextAge);
        btnSave = findViewById(R.id.btn_submit);
        tvSize = findViewById(R.id.tv_array_list);
        loadData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(etName.getText().toString(), etAge.getText().toString());
            }
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", 0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("student_data", null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();

        modelClassArrayList = gson.fromJson(json, type);
        if(modelClassArrayList == null){
            modelClassArrayList = new ArrayList<>();
            tvSize.setText(""+0);
        }else{
//            tvSize.setText(modelClassArrayList.toString());
             for (int i = 0; i < modelClassArrayList.size(); i++){
                 tvSize.setText(tvSize.getText().toString()+"\n"+modelClassArrayList.get(i).getName()+"\n");
             }
        }
    }

    private void saveData(String name, String age) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        modelClassArrayList.add(new ModelClass(name, Integer.parseInt(age)));
        String json = gson.toJson(modelClassArrayList);
        editor.putString("student_data", json);
        editor.apply();
        tvSize.setText("List Data\n");
        loadData();
    }
}