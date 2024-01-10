package com.example.chatapplication.User;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.chatapplication.R;
import com.example.chatapplication.Utils.Tools;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    String currentPhotoPath = "";
    private File currentPhotoFile;
    private static final int REQUEST_CAMERA_PERMISSION = 101;
    ActivityResultLauncher<Intent> cameraLauncher = null;
    LinearLayout ly_back;
    TextInputEditText et_Username,et_Email,et_mobileNumber,et_Password;
    Button btn_register;
    CircleImageView cameraivProfileCamera,cameraivProfileUser;
    Tools tools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tools=new Tools(this);
        ly_back = findViewById(R.id.ly_back);
        et_Username = findViewById(R.id.et_userName);
        et_Email = findViewById(R.id.et_email);
        et_mobileNumber = findViewById(R.id.et_mobileNumber);
        et_Password = findViewById(R.id.et_password);
        btn_register = findViewById(R.id.btn_register);
        cameraivProfileCamera = findViewById(R.id.cameraivProfileCamera);
        cameraivProfileUser = findViewById(R.id.cameraivProfileUser);

        cameraivProfileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    currentPhotoPath = "";
                    openGallery();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void openGallery() {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryLauncher.launch(galleryIntent);
            }

            ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Uri selectedImageUri = result.getData().getData();
                    currentPhotoPath = Tools.getRealPathFromURI(RegisterActivity.this, selectedImageUri);
                    Tools.displayImage(RegisterActivity.this, cameraivProfileUser, currentPhotoPath);
                } else {
                    Toast.makeText(RegisterActivity.this, "Gallery selection canceled", Toast.LENGTH_SHORT).show();
                }
            });
        });
        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Tools.displayImage(RegisterActivity.this, cameraivProfileUser, currentPhotoPath);
            } else {
                Toast.makeText(this, "Not", Toast.LENGTH_SHORT).show();
            }
        });
        cameraivProfileCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    currentPhotoPath = "";
                    if (checkCameraPermission()) {
                        openCamera();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




        ly_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = et_Email.getText().toString();
                String username = et_Username.getText().toString();
                String mobile = et_mobileNumber.getText().toString();
                String password = et_Password.getText().toString();

                if (email.isEmpty()){
                    et_Email.setError("Select email address");
                } else if (username.isEmpty()) {
                    et_Username.setError("Select userName");
                }else if (mobile.isEmpty()) {
                    et_mobileNumber.setError("Select Mobile Number");
                }else if (password.isEmpty()) {
                    et_Password.setError("Select password");
                }else {

                }


            }
        });
    }
    private boolean checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            return false;
        }
        return true;
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.chatapplication",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                cameraLauncher.launch(takePictureIntent);
            }
        }
    }

    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        currentPhotoFile = image;
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


}