package com.example.chatapplication.Demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chatapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveActivity extends AppCompatActivity {

    LinearLayout ly_back;
    Button btn_save;
    ImageView img_Data;
    TextView txtData;
    private float scaleFactor = 1.0f;
    private ScaleGestureDetector scaleGestureDetector;
    private float offsetX, offsetY;
    private float downX, downY;
    LinearLayout leanearLayout;
    String ResultImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        ly_back = findViewById(R.id.ly_back);
        btn_save = findViewById(R.id.btn_save);
        img_Data = findViewById(R.id.img_Data);
        txtData = findViewById(R.id.txtData);
     //   img_Data1 = findViewById(R.id.img_Data1);
        leanearLayout = findViewById(R.id.ll7);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());


        Intent intent = getIntent();
        String userText = intent.getStringExtra("user_text");
     //   String user_image = getIntent().getStringExtra("user_image");
        int selectedColor = intent.getIntExtra("selected_color", 0);
        int selectedFontStyle = intent.getIntExtra("selected_font_style", 0);

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String imageUriString = preferences.getString("User_image", "");

        if (!imageUriString.isEmpty()) {
            Uri imageUri = Uri.parse(imageUriString);
            Glide.with(this)
                    .load(imageUri)
                    .into(img_Data);
        }

        txtData.setText(userText);

        if (selectedColor == 1)
            txtData.setTextColor(Color.RED);
        else if (selectedColor == 2)
            txtData.setTextColor(Color.GREEN);
        else if (selectedColor == 3)
            txtData.setTextColor(Color.BLACK);
        else if (selectedColor == 4)
            txtData.setTextColor(Color.BLUE);
        else if (selectedColor == 5)
            txtData.setTextColor(Color.YELLOW);


        if (selectedFontStyle == 1)
            txtData.setTypeface(Typeface.SANS_SERIF);
        else if (selectedFontStyle == 2)
            txtData.setTypeface(Typeface.SERIF);
        else if (selectedFontStyle == 3)
            txtData.setTypeface(Typeface.MONOSPACE);
        else if (selectedFontStyle == 4)
            txtData.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        else if (selectedFontStyle == 5)
            txtData.setTypeface(Typeface.DEFAULT_BOLD);
        else if (selectedFontStyle == 6)
            txtData.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));


        ly_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txtData.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouch(event);
                scaleGestureDetector.onTouchEvent(event);

                return true;
            }
        });

      /*  btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bitmap image = getBitMapFromURl(leanearLayout);
                img_Data1.setImageBitmap(image);
                Intent imageIntent = new Intent(SaveActivity.this, ResultActivity.class);
                imageIntent.putExtra("image_view_Result", img_Data1.toString());
                startActivity(imageIntent);

            }
        });*/

       /* btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap image = getBitMapFromURl(leanearLayout);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent imageIntent = new Intent(SaveActivity.this, ResultActivity.class);
                imageIntent.putExtra("image_view_Result", byteArray);
                startActivity(imageIntent);
            }
        });*/

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap image = getBitMapFromURl(leanearLayout);
                File imageFile = saveImageToFile(image);
                Intent imageIntent = new Intent(SaveActivity.this, ResultActivity.class);
                imageIntent.putExtra("image_view_Result", imageFile.toURI().toString());
                startActivity(imageIntent);
            }
        });


    }

    private File saveImageToFile(Bitmap image) {
        File imagesFolder = new File(getFilesDir(), "images");
        if (!imagesFolder.exists()) {
            imagesFolder.mkdirs();
        }

        File imageFile = new File(imagesFolder, "image.png");
        try (FileOutputStream out = new FileOutputStream(imageFile)) {
            image.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageFile;
    }


    private void handleTouch(MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                offsetX = txtData.getX() - event.getRawX();
                offsetY = txtData.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                float newX = event.getRawX() + offsetX;
                float newY = event.getRawY() + offsetY;

                // Set the new position of txtData
                txtData.setX(newX);
                txtData.setY(newY);
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
            txtData.setScaleX(scaleFactor);
            txtData.setScaleY(scaleFactor);
            return true;
        }
    }


    private Bitmap getBitMapFromURl(View view){

       Bitmap returnBitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable!= null){
            bgDrawable.draw(canvas);
        }else{
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnBitmap;

    }
}