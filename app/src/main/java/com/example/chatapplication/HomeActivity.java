package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chatapplication.Fragment.HomeFragment;
import com.example.chatapplication.Fragment.InviteFragment;
import com.example.chatapplication.Fragment.menuFragment;
import com.example.chatapplication.Fragment.myAccountFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frameLayout = findViewById(R.id.frame_layout);
        bottomNavigationView = findViewById(R.id.BottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.home_id){
                    loadFragment(new HomeFragment(),false);

                }else if (itemId == R.id.my_account_id){
                    loadFragment(new myAccountFragment(),false);

                }else if (itemId == R.id.invite_id){
                    loadFragment(new InviteFragment(),false);

                }else if (itemId == R.id.menu_id){
                    loadFragment(new menuFragment(),false);

                }else {
                    loadFragment(new HomeFragment(),true);
                }

                return true;
            }
        });

        loadFragment(new HomeFragment(),true);
    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isAppInitialized){
            fragmentTransaction.add(R.id.frame_layout,fragment);
        }else {
            fragmentTransaction.replace(R.id.frame_layout,fragment);

        }

        fragmentTransaction.commit();
    }
}