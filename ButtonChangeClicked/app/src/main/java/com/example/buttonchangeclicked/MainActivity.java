package com.example.buttonchangeclicked;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button checkBox = findViewById(R.id.btn_change_color);
//Kiểm tra checked
        if (checkBox.isActivated())
        {
            //Checked
            checkBox.setTextColor(Color.parseColor("#c217a5"));
        }
        else
        {
            //Unchecked
            checkBox.setTextColor(Color.parseColor("#7214c9"));
        }

//Thiết lập trạng thái check
        boolean toi_chon = true;
        checkBox.setActivated(toi_chon);

//Bắt sự kiện thay đổi trạng thái
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                //Code khi trạng thái check thay đổi
//                Toast.makeText(
//                        compoundButton.getContext(),
//                        compoundButton.getText()+"|"+b,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

//Bắt sự kiện Click
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        view.getContext(), "Click!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}