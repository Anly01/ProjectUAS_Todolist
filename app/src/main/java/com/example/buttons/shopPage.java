package com.example.buttons;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class shopPage extends AppCompatActivity {
    private ImageView background;
    RelativeLayout shopP1, shopP2, shopP3;
    int action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);
        shopP1 = (RelativeLayout) findViewById(R.id.shopP1);
        shopP2 = (RelativeLayout) findViewById(R.id.shopP2);
        shopP3 = (RelativeLayout) findViewById(R.id.shopP3);
        background = (ImageView) findViewById(R.id.background);


        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action = settings.getInt("ACTION", 1);
        background.setImageResource(getResources().getIdentifier("background"+action, "drawable", getPackageName()));

        shopP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 1;

                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("ACTION", action);
                editor.commit();
                background.setImageResource(getResources().getIdentifier("background"+action, "drawable", getPackageName()));
            }
        });
        shopP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 2;

                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("ACTION", action);
                editor.commit();
                background.setImageResource(getResources().getIdentifier("background"+action, "drawable", getPackageName()));
            }
        });
        shopP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 3;

                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("ACTION", action);
                editor.commit();
                background.setImageResource(getResources().getIdentifier("background"+action, "drawable", getPackageName()));
            }
        });







    }
}
