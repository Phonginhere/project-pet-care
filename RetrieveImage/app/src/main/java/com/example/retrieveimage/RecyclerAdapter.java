package com.example.retrieveimage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String tag =  "RecyclerView";
    private Context mContext;
    private ArrayList<Message> messages;
    private ArrayList<Message> selectedItems = new ArrayList<>();

    boolean isSelectAll=false;
    boolean isSelectMode = false;


    public RecyclerAdapter(Context mContext, ArrayList<Message> messages) {
        this.mContext = mContext;
        this.messages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(messages.get(position).getFileName());
        //Glide with
        Glide.with(mContext).load(messages.get(position).getUrl()).into(holder.imageView);
// set text on text view

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // check condition
                if (!isSelectMode)
                {
                    // when action mode is not enable
                    // initialize action mode
                    ActionMode.Callback callback=new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                            // initialize menu inflater
                            MenuInflater menuInflater= mode.getMenuInflater();
                            // inflate menu
                            menuInflater.inflate(R.menu.menu,menu);
                            // return true
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                            // when action mode is prepare
                            // set isEnable true
                            isSelectMode=true;
                            // create method
                            ClickItem(holder);
                            // set observer on getText method
//                            mainViewModel.getText().observe((LifecycleOwner) activity
//                                    , new Observer<String>() {
//                                        @Override
//                                        public void onChanged(String s) {
//                                            // when text change
//                                            // set text on action mode title
//                                            mode.setTitle(String.format("%s Selected",s));
//                                        }
//                                    });
                            // return true
                            return true;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                            // when click on action mode item
                            // get item  id
                            int id=item.getItemId();
                            // use switch condition
                            switch(id)
                            {
                                case R.id.menu_delete:
                                    // when click on delete
                                    // use for loop
                                    for(Message s:selectedItems)
                                    {
                                        // remove selected item list
                                        messages.remove(s);
                                    }
                                    // check condition
                                    if(messages.size()==0)
                                    {
                                        // when array list is empty
                                        // visible text view
//                                        tvEmpty.setVisibility(View.VISIBLE);
                                    }
                                    // finish action mode
                                    mode.finish();
                                    break;

                                case R.id.menu_select_all:
                                    // when click on select all
                                    // check condition
                                    if(selectedItems.size()==messages.size())
                                    {
                                        // when all item selected
                                        // set isselectall false
                                        isSelectAll=false;
                                        // create select array list
                                        selectedItems.clear();
                                    }
                                    else
                                    {
                                        // when  all item unselected
                                        // set isSelectALL true
                                        isSelectAll=true;
                                        // clear select array list
                                        selectedItems.clear();
                                        // add value in select array list
                                        selectedItems.addAll(messages);
                                    }
                                    // set text on view model
//                                    mainViewModel.setText(String .valueOf(selectList.size()));
                                    // notify adapter
                                    notifyDataSetChanged();
                                    break;
                            }
                            // return true
                            return true;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode mode) {
                            // when action mode is destroy
                            // set isEnable false
                            isSelectMode=false;
                            // set isSelectAll false
                            isSelectAll=false;
                            // clear select array list
                            selectedItems.clear();
                            // notify adapter
                            notifyDataSetChanged();
                        }
                    };
                    // start action mode
                    ((AppCompatActivity) v.getContext()).startActionMode(callback);
                }
                else
                {
                    // when action mode is already enable
                    // call method
                    ClickItem(holder);
                }
                // return true
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check condition
                if(isSelectMode)
                {
                    // when action mode is enable
                    // call method
                    ClickItem(holder);
                }
                else
                {
                    // when action mode is not enable
                    // display toast
//                    Toast.makeText(,"You Clicked"+arrayList.get(holder.getAdapterPosition()),
//                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        // check condition
        if(isSelectAll)
        {
            // when value selected
            // visible all check boc image
            holder.check_box.setVisibility(View.VISIBLE);
        }
        else
        {
            // when all value unselected
            // hide all check box image
            holder.check_box.setVisibility(View.GONE);
        }
    }

    private void ClickItem(ViewHolder holder) {

        // get selected item value
        Message s=messages.get(holder.getAdapterPosition());
        // check condition
        if(holder.check_box.getVisibility()==View.GONE)
        {
            // when item not selected
            // visible check box image
            holder.check_box.setVisibility(View.VISIBLE);
            // add value in select array list
            selectedItems.add(s);
        }
        else
        {
            // when item selected
            // hide check box image
            holder.check_box.setVisibility(View.GONE);
            // remove value from select arrayList
            selectedItems.remove(s);

        }
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Widgets
        ImageView imageView, check_box;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textViewImg);
            check_box = itemView.findViewById(R.id.check_box);
        }
    }
}
