package com.example.buttons;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ImageView background;
    private ImageView logo;
    private ImageButton tdl, calendar, theme, help;
    int nomorlogo;
    int action;
    @Override

/**
* display to set layout from activity_main.xml
* @param savedInstanceState*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //wallpaper
        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action = settings.getInt("ACTION", 1);
        background = (ImageView) findViewById(R.id.background);
        background.setImageResource(getResources().getIdentifier("background"+action, "drawable", getPackageName()));

        //buttons
        logo = (ImageView) findViewById(R.id.logosImage);
        tdl = (ImageButton) findViewById(R.id.btnTodo);
        calendar = (ImageButton) findViewById(R.id.btnCalendar);
        theme = (ImageButton) findViewById(R.id.btnShop);
        help= (ImageButton) findViewById(R.id.btnHelp);
        final SharedPreferences buttons = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        nomorlogo = buttons.getInt("NOMOR", 1);
        logo.setImageResource(getResources().getIdentifier("circlelogo"+nomorlogo, "drawable", getPackageName()));
        tdl.setBackgroundResource(getResources().getIdentifier("hmpg_tdlbtn"+nomorlogo, "drawable", getPackageName()));
        calendar.setBackgroundResource(getResources().getIdentifier("hmpage_clndrbtn"+nomorlogo, "drawable", getPackageName()));
        theme.setBackgroundResource(getResources().getIdentifier("hmpg_shopbtn"+nomorlogo, "drawable", getPackageName()));
        help.setBackgroundResource(getResources().getIdentifier("hmpage_helpbtn"+nomorlogo, "drawable", getPackageName()));
        /**
 * Button Todolist pada Homepage
 */
        ImageButton todoButton = (ImageButton) findViewById(R.id.btnTodo);
        todoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, todoPage.class);
                startActivity(pindah);
            }
        });
/**
 * Button Calendar pada Homepage
 */
        ImageButton calendarButton = (ImageButton) findViewById(R.id.btnCalendar);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, calendarPage.class);
                startActivity(pindah);
            }
        });

/**
* Button Shop pada Homepage
*/
        ImageButton shopButton = (ImageButton) findViewById(R.id.btnShop);
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, themePage.class);
                startActivity(pindah);
            }
        });
/**
 * Button Todolist pada Homepage
 */
        ImageButton helpButton = (ImageButton) findViewById(R.id.btnHelp);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, helpPage.class);
                startActivity(pindah);
            }
        });
    }




    /**
     * Make a yes or no pop up when back navigation is pressed
     */
    public void onBackPressed() {
        if(isTaskRoot()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        } else {
            super.onBackPressed();
        }
    }

    public void onStart() {
        super.onStart();
        //wallpaper
        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action = settings.getInt("ACTION", 1);
        background = (ImageView) findViewById(R.id.background);
        background.setImageResource(getResources().getIdentifier("background" + action, "drawable", getPackageName()));
        //logo
        //logo
        logo = (ImageView) findViewById(R.id.logosImage);
        final SharedPreferences buttons = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        nomorlogo = buttons.getInt("NOMOR", 1);
        logo.setImageResource(getResources().getIdentifier("circlelogo"+nomorlogo, "drawable", getPackageName()));
        tdl.setBackgroundResource(getResources().getIdentifier("hmpg_tdlbtn"+nomorlogo, "drawable", getPackageName()));
        calendar.setBackgroundResource(getResources().getIdentifier("hmpage_clndrbtn"+nomorlogo, "drawable", getPackageName()));
        theme.setBackgroundResource(getResources().getIdentifier("hmpg_shopbtn"+nomorlogo, "drawable", getPackageName()));
        help.setBackgroundResource(getResources().getIdentifier("hmpage_helpbtn"+nomorlogo, "drawable", getPackageName()));

    }


    /**
     * Prints out a message as a toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}