package com.example.chatapplication.User;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chatapplication.HomeActivity;
import com.example.chatapplication.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextView ly_Register;
    Button btn_Skip;
    MaterialButton btn_login;
    TextInputEditText et_Email,et_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ly_Register = findViewById( R.id.ly_Register);
        btn_login = findViewById( R.id.btn_login);
        btn_Skip = findViewById( R.id.btn_Skip);
        et_Email = findViewById( R.id.et_Email);
        et_Password = findViewById( R.id.et_Password);

        ly_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        btn_Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });

    }
}