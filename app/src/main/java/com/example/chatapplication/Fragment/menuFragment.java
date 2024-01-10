package com.example.chatapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chatapplication.Demo.ImageActivity;
import com.example.chatapplication.HomeActivity;
import com.example.chatapplication.R;

public class menuFragment extends Fragment {

    TextView tx_profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        tx_profile = v.findViewById(R.id.tx_profile);

        tx_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ImageActivity.class));
            }
        });

        return v;
    }
}