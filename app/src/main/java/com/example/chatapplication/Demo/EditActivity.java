package com.example.chatapplication.Demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chatapplication.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    LinearLayout ly_back;
    Button btn_add;
    TextInputEditText et_Text;
    AppCompatSpinner tx_color;
    AppCompatSpinner tx_font_style;
    String user_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ly_back = findViewById(R.id.ly_back);
        btn_add = findViewById(R.id.btn_add);
        et_Text = findViewById(R.id.et_Text);
        tx_color = findViewById(R.id.tx_color);
        tx_font_style = findViewById(R.id.tx_font_style);

        ly_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        user_image = getIntent().getStringExtra("user_image");

        //colour
        List<String> list = new ArrayList<>();
        list.add("Select_Color");
        list.add("RED");
        list.add("GREEN");
        list.add("BLACK");
        list.add("BLUE");
        list.add("YELLOW");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
        tx_color.setAdapter(arrayAdapter);
        tx_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1)
                    et_Text.setTextColor(Color.RED);
                else if (position == 2)
                    et_Text.setTextColor(Color.GREEN);
                else if (position == 3)
                    et_Text.setTextColor(Color.BLACK);
                else if (position == 4)
                    et_Text.setTextColor(Color.BLUE);
                else if (position == 5)
                    et_Text.setTextColor(Color.YELLOW);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        // Font
        List<String> fontList = new ArrayList<>();
        fontList.add("Select_Font");
        fontList.add("Sans-serif");
        fontList.add("Serif");
        fontList.add("Monospace");
        fontList.add("Default");
        fontList.add("Bold_Italic");
        fontList.add("Default_Bold");
        fontList.add("Italic");

        ArrayAdapter<String> fontAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, fontList);
        tx_font_style.setAdapter(fontAdapter);
        tx_font_style.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1)
                    et_Text.setTypeface(Typeface.SANS_SERIF);
                else if (position == 2)
                    et_Text.setTypeface(Typeface.SERIF);
                else if (position == 3)
                    et_Text.setTypeface(Typeface.MONOSPACE);
                else if (position == 4)
                    et_Text.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
                else if (position == 5)
                    et_Text.setTypeface(Typeface.DEFAULT_BOLD);
                else if (position == 6)
                    et_Text.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String imageUriString = preferences.getString("user_image", "");

        if (!imageUriString.isEmpty()) {
            Uri imageUri = Uri.parse(imageUriString);
         //   img_Data.setImageURI(imageUri);
       }


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_Text.getText().toString();
                int selectedColor = tx_color.getSelectedItemPosition();
                int selectedFontStyle = tx_font_style.getSelectedItemPosition();


                if (text.isEmpty()) {
                    et_Text.setError("Data is required");
                }

                Intent i = new Intent(EditActivity.this, SaveActivity.class);
                i.putExtra("user_text", text);
                i.putExtra("user_image", imageUriString);
                i.putExtra("selected_color", selectedColor);
                i.putExtra("selected_font_style", selectedFontStyle);
                startActivity(i);
            }
        });

    }
}