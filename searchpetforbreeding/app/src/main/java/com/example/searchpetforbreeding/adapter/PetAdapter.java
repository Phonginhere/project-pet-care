package com.example.searchpetforbreeding.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchpetforbreeding.R;
import com.example.searchpetforbreeding.models.Pets;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>{
    private List<Pets> mlistPets;
    public PetAdapter(List<Pets> mlistPets) {
        this.mlistPets = mlistPets;
    }

    public void setFilteredList(List<Pets> filteredList){
        this.mlistPets = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_information, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) { //go data
        Pets pets = mlistPets.get(position);
        if(pets == null){
            return;
        }

        holder.tv_name.setText(pets.getPetName());
        holder.tv_species.setText(pets.getSpecies());
        if(pets.isGender() == true){
            holder.tv_gender.setText("male");
        }else{
            holder.tv_gender.setText("female");
        }
    }

    @Override
    public int getItemCount() {
        if(mlistPets != null){
            return mlistPets.size();
        }
        return 0;
    }

    public class PetViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgAvatar;
        private TextView tv_name, tv_species, tv_gender;
        RelativeLayout layout_bar;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatar = itemView.findViewById(R.id.imgAvatar);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_species = itemView.findViewById(R.id.tv_species);
            tv_gender = itemView.findViewById(R.id.tv_gender);

            layout_bar = itemView.findViewById(R.id.layout_bar);

            layout_bar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos_click = getAdapterPosition();
                    openRequestDialog(pos_click, view, Gravity.CENTER);
                }
            });
        }
    }

    private void openRequestDialog(int pos_click, View view, int gravity) {

        final Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_request);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        TextView tv_pet_name = dialog.findViewById(R.id.tv_pet_name);
        TextView tv_pet_gender = dialog.findViewById(R.id.tv_pet_gender);
        TextView tv_pet_breed = dialog.findViewById(R.id.tv_pet_breed);
        TextView tv_pet_species = dialog.findViewById(R.id.tv_pet_species);

        TextView tv_pet_height = dialog.findViewById(R.id.tv_pet_height);
        TextView tv_pet_weight = dialog.findViewById(R.id.tv_pet_weight);

        TextView tv_pet_birthdate = dialog.findViewById(R.id.tv_pet_birthdate);
        TextView tv_pet_color = dialog.findViewById(R.id.tv_pet_color);
        TextView tv_pet_intact = dialog.findViewById(R.id.tv_pet_intact);
        TextView tv_pet_notes = dialog.findViewById(R.id.tv_pet_notes);

        Pets pets = mlistPets.get(pos_click);
        if(pets == null){
            return;
        }
//        holder.imgAvatar.setImageResource(pets);
        tv_pet_name.setText("Name: "+pets.getPetName());

        if(pets.isGender() == true){
            tv_pet_gender.setText("Gender: Male");
        }else{
            tv_pet_gender.setText("Gender: Female");
        }

        tv_pet_breed.setText("Breed: "+pets.getBreed());
        tv_pet_species.setText("Species: "+pets.getSpecies());

        tv_pet_height.setText("Height: "+String.valueOf(pets.getPetHeight()) + " cm");
        tv_pet_weight.setText("Weight: "+String.valueOf(pets.getPetWeight()) + "kg");

        tv_pet_birthdate.setText("Birth Date: "+pets.getBirthDate());
        tv_pet_color.setText("Color: "+pets.getColor());
        if(pets.isIntact() == true){
            tv_pet_intact.setText("Intact: Yes");
        }else{
            tv_pet_intact.setText("Intact: No");
        }
        tv_pet_notes.setText("Notes: "+pets.getNotes());



        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
        Button btn_send = dialog.findViewById(R.id.btn_send);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Hello Javatpoint",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
