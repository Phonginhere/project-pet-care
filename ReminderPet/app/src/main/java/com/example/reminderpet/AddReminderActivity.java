package com.example.reminderpet;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reminderpet.models.Reminder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class AddReminderActivity extends AppCompatActivity {

    private int click_days = 0;
    private int hour, minute;

    private ArrayList<Reminder> reminders;
    private ArrayList<Boolean> dayOfWeek = new ArrayList<Boolean>();

    private Calendar calendar;

    TextView tv_medicine_time;

    CheckBox cb_every_day;
    Button dv_sunday;
    Button dv_monday;
    Button dv_tuesday;
    Button dv_wednesday;
    Button dv_thursday;
    Button dv_friday;
    Button dv_saturday;

    EditText edit_med_name;

    FloatingActionButton cancel_button;
    FloatingActionButton confirm_button;

    TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_medicine);

        dv_sunday = findViewById(R.id.dv_sunday);
        dv_monday = findViewById(R.id.dv_monday);
        dv_tuesday = findViewById(R.id.dv_tuesday);
        dv_wednesday = findViewById(R.id.dv_wednesday);
        dv_thursday = findViewById(R.id.dv_thursday);
        dv_friday = findViewById(R.id.dv_friday);
        dv_saturday = findViewById(R.id.dv_saturday);
        cb_every_day = findViewById(R.id.every_day);

        cancel_button = (FloatingActionButton) findViewById(R.id.cancel_button);
        confirm_button = (FloatingActionButton) findViewById(R.id.confirm_button);

        tv_medicine_time = findViewById(R.id.tv_medicine_time);

        edit_med_name = findViewById(R.id.edit_med_name);

        setCurrentTime();

        dv_sunday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int xx = 0;
                int currentTag = dv_sunday.getTag() == null ? 0 :
                        (int)dv_sunday.getTag();
                currentTag ++;
                dv_sunday.setTag(currentTag);
                if(currentTag % 2 == 0) {
                    dv_sunday.setBackgroundColor(Color.parseColor("#c217a5"));
                    click_days--; //loại bỏ
                    dayOfWeek.add(0, false);

                } else {
                    dv_sunday.setBackgroundColor(Color.parseColor("#7214c9"));
                    click_days++; //thêm vào
                    dayOfWeek.add(0, true);
                }
                displayCheckBoxAfterCheckingAll();
            }
        });

        //btn monday
        dv_monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int xx = 0;
                int currentTag = dv_monday.getTag() == null ? 0 :
                        (int)dv_monday.getTag();
                currentTag ++;
                dv_monday.setTag(currentTag);
                if(currentTag % 2 == 0) {
                    dv_monday.setBackgroundColor(Color.parseColor("#c217a5"));
                    click_days--;
                    dayOfWeek.add(1, false);

                } else {
                    dv_monday.setBackgroundColor(Color.parseColor("#7214c9"));
                    click_days++;
                    dayOfWeek.add(1, true);
                }
                displayCheckBoxAfterCheckingAll();
            }
        });

        //btn tuesday
        dv_tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int xx = 0;
                int currentTag = dv_tuesday.getTag() == null ? 0 :
                        (int)dv_tuesday.getTag();
                currentTag ++;
                dv_tuesday.setTag(currentTag);
                if(currentTag % 2 == 0) {
                    dv_tuesday.setBackgroundColor(Color.parseColor("#c217a5"));
                    click_days--;
                    dayOfWeek.add(2, false);
                } else {
                    dv_tuesday.setBackgroundColor(Color.parseColor("#7214c9"));
                    click_days++;
                    dayOfWeek.add(2, true);
                }
                displayCheckBoxAfterCheckingAll();
            }
        });

        //btn wednesday
        dv_wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int xx = 0;
                int currentTag = dv_wednesday.getTag() == null ? 0 :
                        (int)dv_wednesday.getTag();
                currentTag ++;
                dv_wednesday.setTag(currentTag);
                if(currentTag % 2 == 0) {
                    dv_wednesday.setBackgroundColor(Color.parseColor("#c217a5"));
                    click_days--;
                    dayOfWeek.add(3, false);
                } else {
                    dv_wednesday.setBackgroundColor(Color.parseColor("#7214c9"));
                    click_days++;
                    dayOfWeek.add(3, true);
                }
                displayCheckBoxAfterCheckingAll();
            }
        });

        //btn thursday
        dv_thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int xx = 0;
                int currentTag = dv_thursday.getTag() == null ? 0 :
                        (int)dv_thursday.getTag();
                currentTag ++;
                dv_thursday.setTag(currentTag);
                if(currentTag % 2 == 0) {
                    dv_thursday.setBackgroundColor(Color.parseColor("#c217a5"));
                    click_days--;
                    dayOfWeek.add(4, false);
                } else {
                    dv_thursday.setBackgroundColor(Color.parseColor("#7214c9"));
                    click_days++;
                    dayOfWeek.add(4, true);
                }
                displayCheckBoxAfterCheckingAll();
            }
        });

        //btn friday
        dv_friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int xx = 0;
                int currentTag = dv_friday.getTag() == null ? 0 :
                        (int)dv_friday.getTag();
                currentTag ++;
                dv_friday.setTag(currentTag);
                if(currentTag % 2 == 0) {
                    dv_friday.setBackgroundColor(Color.parseColor("#c217a5"));
                    click_days--;
                    dayOfWeek.add(5, false);
                } else {
                    dv_friday.setBackgroundColor(Color.parseColor("#7214c9"));
                    click_days++;
                    dayOfWeek.add(5, true);
                }

                displayCheckBoxAfterCheckingAll();
            }
        });

        //btn saturday
        dv_saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int xx = 0;
                int currentTag = dv_saturday.getTag() == null ? 0 :
                        (int)dv_saturday.getTag();
                currentTag ++;
                dv_saturday.setTag(currentTag);
                if(currentTag % 2 == 0) {
                    dv_saturday.setBackgroundColor(Color.parseColor("#c217a5"));
                    click_days--;
                    dayOfWeek.add(6, false);
                } else {
                    dv_saturday.setBackgroundColor(Color.parseColor("#7214c9"));
                    click_days++;
                    dayOfWeek.add(6, true);
                }
                displayCheckBoxAfterCheckingAll();
            }
        });

        cb_every_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int xx = 0;
                int currentTag = cb_every_day.getTag() == null ? 0 :
                        (int)cb_every_day.getTag();
                currentTag++;
                cb_every_day.setTag(currentTag);

                if(currentTag % 2 == 0) {
                    dv_saturday.setBackgroundColor(Color.parseColor("#c217a5"));
                    dv_sunday.setBackgroundColor(Color.parseColor("#c217a5"));
                    dv_monday.setBackgroundColor(Color.parseColor("#c217a5"));
                    dv_tuesday.setBackgroundColor(Color.parseColor("#c217a5"));
                    dv_wednesday.setBackgroundColor(Color.parseColor("#c217a5"));
                    dv_thursday.setBackgroundColor(Color.parseColor("#c217a5"));
                    dv_friday.setBackgroundColor(Color.parseColor("#c217a5"));
                    dayOfWeek.add(0, false);
                    dayOfWeek.add(1, false);
                    dayOfWeek.add(2, false);
                    dayOfWeek.add(3, false);
                    dayOfWeek.add(4, false);
                    dayOfWeek.add(5, false);
                    dayOfWeek.add(6, false);
                } else {
                    dv_saturday.setBackgroundColor(Color.parseColor("#7214c9"));
                    dv_sunday.setBackgroundColor(Color.parseColor("#7214c9"));
                    dv_monday.setBackgroundColor(Color.parseColor("#7214c9"));
                    dv_tuesday.setBackgroundColor(Color.parseColor("#7214c9"));
                    dv_wednesday.setBackgroundColor(Color.parseColor("#7214c9"));
                    dv_thursday.setBackgroundColor(Color.parseColor("#7214c9"));
                    dv_friday.setBackgroundColor(Color.parseColor("#7214c9"));
                    dayOfWeek.add(0, true);
                    dayOfWeek.add(1, true);
                    dayOfWeek.add(2, true);
                    dayOfWeek.add(3, true);
                    dayOfWeek.add(4, true);
                    dayOfWeek.add(5, true);
                    dayOfWeek.add(6, true);
                }


            }
        });


        tv_medicine_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTime();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(AddReminderActivity.this, MainActivity.class);
                AddReminderActivity.this.startActivity(myIntent);
            }
        });

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String medicineName = edit_med_name.getText().toString();
                if(edit_med_name.getText().toString().trim().length() > 0){
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    reminders.add(new Reminder(medicineName, dayOfWeek, calendar));
                    String json = gson.toJson(reminders);
                    editor.putString("reminder_data", json);
                    editor.apply();
                }else{
                    Toast.makeText(getApplicationContext(), "Please input medicine name", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void displayCheckBoxAfterCheckingAll(){
            if(click_days == 7){
                cb_every_day.setChecked(true);
                dayOfWeek.add(0, true);
                dayOfWeek.add(1, true);
                dayOfWeek.add(2, true);
                dayOfWeek.add(3, true);
                dayOfWeek.add(4, true);
                dayOfWeek.add(5, true);
                dayOfWeek.add(6, true);
            }else{
                cb_every_day.setChecked(false);
                dayOfWeek.add(0, false);
                dayOfWeek.add(1, false);
                dayOfWeek.add(2, false);
                dayOfWeek.add(3, false);
                dayOfWeek.add(4, false);
                dayOfWeek.add(5, false);
                dayOfWeek.add(6, false);
            }
    }

    public void chooseTime(){
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                tv_medicine_time.setText(String.format(Locale.getDefault(), "%d:%d", selectedHour, selectedMinute));
            }
        }, hour, minute, false);//No 24 hour time
        mTimePicker.setTitle("Select Time");

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,(minute));
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);


        mTimePicker.show();
    }
    private void setCurrentTime() {
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        tv_medicine_time.setText(String.format(Locale.getDefault(), "%d:%d", hour, minute));
    }

}
