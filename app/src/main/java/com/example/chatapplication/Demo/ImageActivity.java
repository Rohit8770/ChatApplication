package com.example.chatapplication.Demo;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chatapplication.R;
import com.example.chatapplication.User.RegisterActivity;
import com.example.chatapplication.Utils.SharedPreference;
import com.example.chatapplication.Utils.Tools;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageActivity extends AppCompatActivity {

    private final int GALLERY_REQ_CODE = 1000;
    LinearLayout ly_back;
    ImageView image,img_Edit;
    FloatingActionButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ly_back = findViewById(R.id.ly_back);
        image = findViewById(R.id.image);
        btnAdd = findViewById(R.id.btnAdd);
        img_Edit = findViewById(R.id.img_Edit);

        ly_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });
        img_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ImageActivity.this, EditActivity.class);
             //   i.putExtra("user_image",image.toString());
                startActivity(i);
            }
        });

/*
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String imageUriString = preferences.getString("image_view_Result", "");
        //    String imageUri = getIntent().getStringExtra("image_view_Result");

        if (!imageUriString.isEmpty()) {
            Uri imageUri = Uri.parse(imageUriString);
            Glide.with(this)
                    .load(imageUri)
                    .into(image);
        }*/



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == GALLERY_REQ_CODE){
                if (data != null && data.getData() != null) {
                    Uri imageUri = data.getData();
                    image.setImageURI(imageUri);

                    // Save the image URI to SharedPreferences
                    SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("User_image", imageUri.toString());
                    editor.apply();
                }
            }
        }
    }

}