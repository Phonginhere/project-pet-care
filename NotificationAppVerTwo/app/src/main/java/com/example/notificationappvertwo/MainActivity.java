package com.example.notificationappvertwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

import static com.example.notificationappvertwo.App.CHANNEL_ID;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("myCh", "My Channel", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myCh")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("aa")
                .setContentText("bb");

        notification = builder.build();

        notificationManagerCompat = NotificationManagerCompat.from(this);
    }

    public void showNotification(View v) {
//        EditText etTitle = (EditText) findViewById(R.id.et_textViewTitle);
//        EditText etDesc = (EditText) findViewById(R.id.et_Description);
//        String aa = etTitle.getText().toString().trim();
//        String bb = etDesc.getText().toString().trim();
        notificationManagerCompat.notify(1, notification);
    }
}