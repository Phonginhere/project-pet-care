package com.example.navigation.ui.share;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.navigation.R;
import com.example.navigation.databinding.FragmentHomeBinding;
import com.example.navigation.databinding.FragmentShareBinding;
import com.example.navigation.ui.home.HomeViewModel;

public class ShareFragment extends Fragment {

//    private ShareViewModel shareViewModel;
    private FragmentShareBinding binding;

    private Toolbar toolbar;
    private MenuItem menuItem;
    private SearchView searchView;

    public static ShareFragment newInstance() {
        return new ShareFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentShareBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        toolbar = root.findViewById(R.id.toolbar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Hahahah");

        Button btnSth = root.findViewById(R.id.btn_sth);
        btnSth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Make this", Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_item, menu);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setIconified(true);
//        SearchManager searchManager = getActivity().getSystemService(Context.SEARCH_SERVICE);
        super.onCreateOptionsMenu(menu, inflater);
    }
}