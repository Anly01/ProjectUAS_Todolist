package com.example.buttons;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buttons.Adapter.ToDoAdapter;
import com.example.buttons.Model.ToDoModel;
import com.example.buttons.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class todoPage extends AppCompatActivity implements DialogCloseListener {

    private static final String TAG = "todoPage";
    //defining variables
    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private List<ToDoModel> tasksList;
    private DatabaseHandler db;
    private ImageButton addBtn;
    private ItemTouchHelper itemTouchHelper;
    private ImageView background;
    int action;
    int nomorlogo;



    @Override
    //menampilkan layout dari todolist_layout.xml
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist_layout);
        //wallpaper
        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action = settings.getInt("ACTION", 1);
        background = (ImageView) findViewById(R.id.background);
        background.setImageResource(getResources().getIdentifier("background"+action, "drawable", getPackageName()));

        //define variabel add new todolist
        addBtn = findViewById(R.id.btnAddTask);
        // ganti buttons
        final SharedPreferences buttons = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        nomorlogo = buttons.getInt("NOMOR", 1);
        addBtn.setBackgroundResource(getResources().getIdentifier("addtask_btn"+nomorlogo, "drawable", getPackageName()));

        //setting the variables
        //database
        db = new DatabaseHandler(this);
        db.openDatabase();

        //Todolist task
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(db, this);
        tasksRecyclerView.setAdapter(tasksAdapter);
        tasksList = new ArrayList<>();

        //todolist scoll helper
        itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);
        //get task from database
        tasksList = db.getAllTasks();
        Collections.reverse(tasksList);
        tasksAdapter.setTasks(tasksList);

        // add function
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
     }

    // set all the tasks list to the adapter
    @Override
    public void handleDialogClose(DialogInterface dialog) {
        tasksList = db.getAllTasks();
        Collections.reverse(tasksList);
        tasksAdapter.setTasks(tasksList);
        tasksAdapter.notifyDataSetChanged();
    }

    public void onStart() {
        super.onStart();
        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action = settings.getInt("ACTION", 1);
        background = (ImageView) findViewById(R.id.background);
        background.setImageResource(getResources().getIdentifier("background" + action, "drawable", getPackageName()));
    }
}


