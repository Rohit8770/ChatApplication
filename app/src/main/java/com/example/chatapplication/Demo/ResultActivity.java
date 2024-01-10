package com.example.chatapplication.Demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chatapplication.R;

public class ResultActivity extends AppCompatActivity {

    ImageView imgData;
    TextView tx_data;
    Button btn_Finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imgData = findViewById(R.id.imgData);
        btn_Finish = findViewById(R.id.btn_Finish);



        Intent intent = getIntent();
        String imageUriString = intent.getStringExtra("image_view_Result");

        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            imgData.setImageURI(imageUri);
        }

        btn_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, ImageActivity.class));
            }
        });
    }
}