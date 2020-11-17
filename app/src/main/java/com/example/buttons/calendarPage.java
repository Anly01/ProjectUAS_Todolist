package com.example.buttons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class calendarPage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);



        /**
         * Button Back to Homepage
         */
        Button backHomepage = (Button) findViewById(R.id.btnBack);
        backHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(calendarPage.this, MainActivity.class);
                startActivity(pindah);
            }
        });
    }
}
