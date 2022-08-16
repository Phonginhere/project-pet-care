package com.example.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.navigation.ui.models.Image;
import com.example.navigation.ui.models.Pet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class petListNavDerAdapter extends RecyclerView.Adapter<petListNavDerAdapter.ViewHolder> {
    ArrayList<Pet> pets;
    private ArrayList<Image> images;
    Context context;
    private long selectedPet;

    DatabaseReference refPetDelete;

    public petListNavDerAdapter(ArrayList<Pet> pets, ArrayList<Image> images, Context context) {
        this.pets = pets;
        this.images = images;
        this.context = context;
    }

    public petListNavDerAdapter() {
    }

    public Long getSelectedPet() {
        return selectedPet;
    }

    @NonNull
    @Override
    public petListNavDerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person, parent, false);
        return new petListNavDerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.name.setText((CharSequence) pets.get(position)); // set position value vào recycler view.
        holder.name.setText(pets.get(position).getPetName());

        for (Image image : images) {
            if (image.getPetId() == pets.get(position).getPetId()) {
                //Glide with
                Glide.with(context).load(image.getUrl()).apply(new RequestOptions().override(70, 70)).into(holder.imagePet);
                break;
            }
        }

        //int i  = position;
        holder.itemView.setOnLongClickListener((View v) -> {
            selectedPet = pets.get(position).getPetId();
            ViewHolder vh = new ViewHolder(v);
            v.setOnCreateContextMenuListener(vh);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return pets == null ? 0 : pets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView name;
        ImageView imagePet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.first_name);
            imagePet = (ImageView) itemView.findViewById(R.id.iv_pet_id);
            if (context instanceof MainActivity) {
                ((MainActivity) context).registerForContextMenu(itemView);
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Pet Options");
            menu.add(getAdapterPosition(), 101, 0, "Edit it");
            menu.add(getAdapterPosition(), 102, 1, "Remove it");
        }


    }

    public void RemoveSelectedPet() {
        DeletePet(selectedPet);
        this.pets.removeIf(item -> item.equals(selectedPet));

        notifyDataSetChanged();
    }

    List<String> listForeignPetIds = new ArrayList<>();
    List<String> listImageIds = new ArrayList<>();

    private void DeletePet(long selectedPet) {

        FirebaseDatabase.getInstance().getReference().child("Image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    long petFId = (long) snapshot.child("petId").getValue();
                    if(petFId == selectedPet){
                        listForeignPetIds.add(String.valueOf(selectedPet));
                        listImageIds.add(String.valueOf(snapshot.child("imageId").getValue()));
                    }
                }
                for (int i = 0; i < listForeignPetIds.size(); i++) {
                    refPetDelete = FirebaseDatabase.getInstance().getReference();
                    Query removeForeignPetQuery = refPetDelete.child("Image").orderByChild("petId").equalTo(selectedPet);

                    removeForeignPetQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot removeForeignPetSnapshot: dataSnapshot.getChildren()) {
                                removeForeignPetSnapshot.getRef().removeValue();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                    listForeignPetIds.clear();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refPetDelete = FirebaseDatabase.getInstance().getReference("Pets");

        refPetDelete.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getChildren().forEach(pet -> {
                    if (pet.getValue(Image.class).getPetId() == selectedPet) {
                        refPetDelete.child(String.valueOf(pet.getValue(Pet.class).getPetId())).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {

                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void displayListForeignKeyPet(long selectedPet) {

    }
}
