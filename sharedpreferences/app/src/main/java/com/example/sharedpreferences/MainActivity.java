package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge;
    CheckBox checkBoxRememberMe;
    Button btn;
    SharedPreferences sharedPreferences;
    boolean isRemembered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextPersonName);
        etAge = findViewById(R.id.editTextAge);
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);
        btn = findViewById(R.id.button);

        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false); //default value of checkbox is fals, when
        //value is true we move another activity

        if(isRemembered){
            Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
            startActivity(intent);
            finish();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString().trim());
                boolean checked = checkBoxRememberMe.isChecked();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("NAME", name);
                editor.putInt("AGE", age);
                editor.putBoolean("CHECKBOX", checked);
                editor.apply();

                Toast.makeText(MainActivity.this, "Information Saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}