package com.example.myapplication.ui.follow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;

public class FollowFragment extends Fragment {

    private FollowViewModel followViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        followViewModel =
                ViewModelProviders.of(this).get(FollowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_follow, container, false);
        final TextView textView = root.findViewById(R.id.text_follow);
        followViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}