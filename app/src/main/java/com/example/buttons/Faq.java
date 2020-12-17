package com.example.buttons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

public class Faq extends AppCompatActivity {
    private ImageView background;
    int action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_faq);
        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action = settings.getInt("ACTION", 1);
        background = (ImageView) findViewById(R.id.background);
        background.setImageResource(getResources().getIdentifier("background"+action, "drawable", getPackageName()));
    }
}