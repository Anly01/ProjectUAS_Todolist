package com.example.buttons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.buttons.R;
import com.example.buttons.Utils.mySQLiteDBHandler;

public class calendarPage extends AppCompatActivity {

    private mySQLiteDBHandler dbHandler;
    private EditText editText;
    private CalendarView calendarView;
    private String selectedDate;
    private SQLiteDatabase sqLiteDatabase;
    private ImageView background;
    private ImageButton save;
    int action;
    int nomorlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        //wallpaper
        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action = settings.getInt("ACTION", 1);
        background = (ImageView) findViewById(R.id.background);
        background.setImageResource(getResources().getIdentifier("background"+action, "drawable", getPackageName()));
        //save
        final SharedPreferences buttons = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        nomorlogo = buttons.getInt("NOMOR", 1);
        save = (ImageButton) findViewById(R.id.updatebtn) ;
        save.setBackgroundResource(getResources().getIdentifier("save"+nomorlogo, "drawable", getPackageName()));
        editText = findViewById(R.id.editText);
        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = Integer.toString(year) + month + dayOfMonth;
                ReadDatabase(view);
            }
        });
        try {
            dbHandler = new mySQLiteDBHandler(this, "CalendarDatabase", null, 1);
            sqLiteDatabase = dbHandler.getWritableDatabase();
            sqLiteDatabase.execSQL("CREATE TABLE EventCalendar(Date TEXT, Event TEXT)");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void insertDatabase (View view){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date",selectedDate);
        contentValues.put("Event",editText.getText().toString());
        sqLiteDatabase.insert("EventCalendar",null,contentValues);
    }
    public void ReadDatabase(View view){
        String query = "Select Event from EventCalendar where Date =" +selectedDate;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(query,null);
            cursor.moveToLast();
            editText.setText(cursor.getString(0));
        }
        catch (Exception e){
            e.printStackTrace();
            editText.setText("");
        }
    }
}