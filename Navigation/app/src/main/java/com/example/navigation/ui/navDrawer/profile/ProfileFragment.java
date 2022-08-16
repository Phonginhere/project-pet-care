package com.example.navigation.ui.navDrawer.profile;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.navigation.R;
import com.example.navigation.databinding.AppBarMainBinding;
import com.example.navigation.databinding.FragmentShareBinding;
import com.example.navigation.databinding.NavHeaderMainBinding;

public class ProfileFragment extends Fragment {

    private NavHeaderMainBinding binding;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = NavHeaderMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setMessage("You touch sth");
                alertDialog.show();
            }
        });
        return root;
    }

}