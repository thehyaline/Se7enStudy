package com.example.myapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false); //获取Fragment绑定的布局xml，设置根view

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
        });

        final Button button1 = root.findViewById(R.id.home_button1); //获取Button的View对象，并将改View转型为Button
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Button 1", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getActivity(), SettingFragment.class);//显式Intent，需要指明启动哪个活动
                //startActivity(intent); //启动目标活动

            }
        });

        final Button button2 = root.findViewById(R.id.home_button2); //获取Button的View对象，并将改View转型为Button
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Button 2", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("com.example.myapplication.ACTION_START");//隐式Intent，需要指明启动哪个活动
                //Intent intent = new Intent(getActivity(), SettingActivity.class);//显式Intent，需要指明启动哪个活动
                startActivity(intent); //启动目标活动

            }
        });


        return root;
    }
}