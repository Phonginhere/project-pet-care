package com.example.reminderpet.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.reflect.Type;

import com.example.reminderpet.R;
import com.example.reminderpet.models.Reminder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.Viewholder> {
    private Context context;
    private ArrayList<Reminder> reminders = null;

    // Constructor
    public ReminderAdapter(Context context, ArrayList<Reminder> reminders) {
        this.context = context;
        this.reminders = reminders;
    }

    @NonNull
    @Override
    public ReminderAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_fragment_reminder, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.Viewholder holder, int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("DATA", 0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("student_data", null);
        Type type = new TypeToken<ArrayList<Reminder>>(){}.getType();

        reminders = gson.fromJson(json, type);
        if(reminders == null){
            reminders = new ArrayList<>();
        }else{
            for(Reminder reminder: reminders){
                holder.tv_reminder_name.setText(reminder.getName());
                holder.tv_med_time.setText(reminder.getCalendar().HOUR_OF_DAY+" : "+reminder.getCalendar().MINUTE);
                holder.iv_icon.setImageResource(R.drawable.icon_blister);

                if(reminder.getDayOfWeek().get(0) == true){
                    holder.tv_monday.setTextColor(Color.parseColor("#c217a5"));
                }else{
                    holder.tv_monday.setTextColor(Color.parseColor("#FFFFFFFF"));
                }

                if(reminder.getDayOfWeek().get(1) == true){
                    holder.tv_tuesday.setTextColor(Color.parseColor("#c217a5"));
                }else{
                    holder.tv_tuesday.setTextColor(Color.parseColor("#FFFFFFFF"));
                }

                if(reminder.getDayOfWeek().get(2) == true){
                    holder.tv_wednesday.setTextColor(Color.parseColor("#c217a5"));
                }else{
                    holder.tv_wednesday.setTextColor(Color.parseColor("#FFFFFFFF"));
                }
                if(reminder.getDayOfWeek().get(3) == true){
                    holder.tv_thursday.setTextColor(Color.parseColor("#c217a5"));
                }else{
                    holder.tv_thursday.setTextColor(Color.parseColor("#FFFFFFFF"));
                }
                if(reminder.getDayOfWeek().get(4) == true){
                    holder.tv_friday.setTextColor(Color.parseColor("#c217a5"));
                }else{
                    holder.tv_friday.setTextColor(Color.parseColor("#FFFFFFFF"));
                }
                if(reminder.getDayOfWeek().get(5) == true){
                    holder.tv_saturday.setTextColor(Color.parseColor("#c217a5"));
                }else{
                    holder.tv_saturday.setTextColor(Color.parseColor("#FFFFFFFF"));
                }
                if(reminder.getDayOfWeek().get(6)== true){
                    holder.tv_sunday.setTextColor(Color.parseColor("#c217a5"));
                }else{
                    holder.tv_sunday.setTextColor(Color.parseColor("#FFFFFFFF"));
                }
                holder.tv_med_time.setText(reminder.getCalendar().HOUR_OF_DAY+" : "+reminder.getCalendar().MINUTE);
                holder.iv_icon.setImageResource(R.drawable.icon_blister);
                holder.iv_ignore_med.setImageResource(R.drawable.x_icon);
                holder.iv_edit_med.setImageResource(R.drawable.edit);
                holder.iv_take_med.setImageResource(R.drawable.off_icon);
            }
        }
        // to set data to textview and imageview of each card layout
//        Reminder model = reminders.get(position);



        //model.getCalendar().DAT
        //holder.tv_med_time.setText("" + ());
        //holder.courseIV.setImageResource(model.getCourse_image());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return reminders == null ? 0 : reminders.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView iv_icon, iv_ignore_med, iv_edit_med, iv_take_med;
        private TextView tv_med_time, tv_reminder_name, tv_sunday, tv_monday, tv_tuesday, tv_wednesday, tv_thursday, tv_friday, tv_saturday;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            iv_icon = itemView.findViewById(R.id.iv_icon);
            iv_ignore_med = itemView.findViewById(R.id.iv_ignore_med);
            iv_edit_med = itemView.findViewById(R.id.iv_edit_med);
            iv_take_med = itemView.findViewById(R.id.iv_take_med);
            tv_med_time = itemView.findViewById(R.id.tv_med_time);
            tv_reminder_name = itemView.findViewById(R.id.tv_reminder_name);

            tv_sunday = itemView.findViewById(R.id.tv_sunday);
            tv_monday = itemView.findViewById(R.id.tv_monday);
            tv_tuesday = itemView.findViewById(R.id.tv_tuesday);
            tv_wednesday = itemView.findViewById(R.id.tv_wednesday);
            tv_thursday = itemView.findViewById(R.id.tv_thursday);
            tv_friday = itemView.findViewById(R.id.tv_friday);
            tv_saturday = itemView.findViewById(R.id.tv_saturday);
        }
    }
}
