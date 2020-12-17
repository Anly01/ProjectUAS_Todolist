package com.example.buttons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class buttonPage extends AppCompatActivity {
    private ImageView background;
    private ImageView logo;
    ImageButton btn1, btn2, btn3;
    int action;
    int nomorlogo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_layout);
        btn1 = (ImageButton) findViewById(R.id.brownbtn);
        btn2 = (ImageButton) findViewById(R.id.darkbtn);
        btn3 = (ImageButton) findViewById(R.id.lightbtn);
        background = (ImageView) findViewById(R.id.background);
        logo = (ImageView) findViewById(R.id.logosImage);


        //logo
        final SharedPreferences buttons = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        nomorlogo = buttons.getInt("NOMOR", 1);
        logo.setImageResource(getResources().getIdentifier("circlelogo"+nomorlogo, "drawable", getPackageName()));

        //wallpaper
        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action = settings.getInt("ACTION", 1);
        background.setImageResource(getResources().getIdentifier("background"+action, "drawable", getPackageName()));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomorlogo = 3;

                SharedPreferences.Editor editor = buttons.edit();
                editor.putInt("NOMOR", nomorlogo);
                editor.commit();
                logo.setImageResource(getResources().getIdentifier("circlelogo"+nomorlogo, "drawable", getPackageName()));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomorlogo = 1;
                SharedPreferences.Editor editor = buttons.edit();
                editor.putInt("NOMOR", nomorlogo);
                editor.commit();
                logo.setImageResource(getResources().getIdentifier("circlelogo"+nomorlogo, "drawable", getPackageName()));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomorlogo = 2;
                SharedPreferences.Editor editor = buttons.edit();
                editor.putInt("NOMOR", nomorlogo);
                editor.commit();
                logo.setImageResource(getResources().getIdentifier("circlelogo"+nomorlogo, "drawable", getPackageName()));
            }
        });
    }}

