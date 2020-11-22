package com.example.buttons;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buttons.Adapter.ToDoAdapter;
import com.example.buttons.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class todoPage extends AppCompatActivity {

    private static final String TAG = "todoPage";
    //defining variables
    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private List<ToDoModel> tasksList;


    @Override
    //menampilkan layout dari todolist_layout.xml
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist_layout);
        Log.d(TAG, "onCreate: started.");
        //setting the variables
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(this);
        tasksRecyclerView.setAdapter(tasksAdapter);
        tasksList = new ArrayList<>();

        ToDoModel task = new ToDoModel();
        task.setTasks("kontol");

        tasksList.add(task);
        tasksList.add(task);
        tasksList.add(task);
        tasksList.add(task);

        tasksAdapter.setTasks(tasksList);

    }

}


