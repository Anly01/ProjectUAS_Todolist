package com.example.buttons;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class themePage extends AppCompatActivity {
    private ImageView background;
    private ImageView logo;
    private ImageButton wallpaper, button;
    int action;
    int nomorlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_layout);
        //wallpaper
        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action = settings.getInt("ACTION", 1);
        background = (ImageView) findViewById(R.id.background);
        background.setImageResource(getResources().getIdentifier("background"+action, "drawable", getPackageName()));
        //buttons
        logo = (ImageView) findViewById(R.id.logosImage);
        wallpaper = (ImageButton) findViewById(R.id.btnWallpaper);
        button = (ImageButton) findViewById(R.id.btnButtons);

        final SharedPreferences buttons = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        nomorlogo = buttons.getInt("NOMOR", 1);
        logo.setImageResource(getResources().getIdentifier("circlelogo"+nomorlogo, "drawable", getPackageName()));
        wallpaper.setBackgroundResource(getResources().getIdentifier("theme_wallpaper"+nomorlogo, "drawable", getPackageName()));
        button.setBackgroundResource(getResources().getIdentifier("theme_buttons"+nomorlogo, "drawable", getPackageName()));

        wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, shopPage.class);
        startActivity(intent);
    }
    public void openActivity3(){
        Intent intent = new Intent(this, buttonPage.class);
        startActivity(intent);
    }

    public void onStart() {
        super.onStart();
        //wallpaper
        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action = settings.getInt("ACTION", 1);
        background = (ImageView) findViewById(R.id.background);
        background.setImageResource(getResources().getIdentifier("background" + action, "drawable", getPackageName()));
        //logo
        final SharedPreferences buttons = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        nomorlogo = buttons.getInt("NOMOR", 1);
        logo.setImageResource(getResources().getIdentifier("circlelogo"+nomorlogo, "drawable", getPackageName()));
        wallpaper.setBackgroundResource(getResources().getIdentifier("theme_wallpaper"+nomorlogo, "drawable", getPackageName()));
        button.setBackgroundResource(getResources().getIdentifier("theme_buttons"+nomorlogo, "drawable", getPackageName()));
    }
}