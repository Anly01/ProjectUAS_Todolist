package com.example.buttons;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override

/**
* display to set layout from activity_main.xml
* @param savedInstanceState*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                startActivity(pindah);;
            }
        });

/**
* Button Shop pada Homepage
*/
        ImageButton shopButton = (ImageButton) findViewById(R.id.btnShop);
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: kontol");
                Intent pindah = new Intent(MainActivity.this, shopPage.class);
                startActivity(pindah);;

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


    /**
     * Prints out a message as a toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}