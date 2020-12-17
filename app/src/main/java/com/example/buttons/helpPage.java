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

public class helpPage extends AppCompatActivity {
    private ImageButton contactus;
    private ImageButton faq;
    private ImageView background;
    private ImageView logo;
    int action;
    int nomorlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);
        //wallpaper
        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action = settings.getInt("ACTION", 1);
        background = (ImageView) findViewById(R.id.background);
        background.setImageResource(getResources().getIdentifier("background"+action, "drawable", getPackageName()));
        contactus = (ImageButton)findViewById(R.id.contactUs);
        faq = (ImageButton)findViewById(R.id.faq);
        logo = (ImageView) findViewById(R.id.logosImage);
        //buttons
        final SharedPreferences buttons = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        nomorlogo = buttons.getInt("NOMOR", 1);
        logo.setImageResource(getResources().getIdentifier("circlelogo"+nomorlogo, "drawable", getPackageName()));
        contactus.setBackgroundResource(getResources().getIdentifier("hmpage_contactbtn"+nomorlogo, "drawable", getPackageName()));
        faq.setBackgroundResource(getResources().getIdentifier("hmpage_faqbtn"+nomorlogo, "drawable", getPackageName()));

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }
    public void openActivity3(){
        Intent intent = new Intent(this, Faq.class);
        startActivity(intent);
    }
}