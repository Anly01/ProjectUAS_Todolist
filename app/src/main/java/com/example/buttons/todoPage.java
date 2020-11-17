package com.example.buttons;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buttons.Adapter.ToDoAdapter;

import java.util.ArrayList;

public class todoPage extends AppCompatActivity {

    private static final String TAG = "todoPage";
    //variables
    private ArrayList<String> mTasksText = new ArrayList<>();

    @Override
    //menampilkan layout dari todolist_layout
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist_layout);
        Log.d(TAG, "onCreate: started.");
        initTaskText();
    }

    //memasukkan teks pada to do list
    private void initTaskText (){
        mTasksText.add("Hello");
        mTasksText.add("Kami");
        mTasksText.add("Kelompok 8 1SIMB");
        mTasksText.add("Ini Apps Kami");
        mTasksText.add("Hehe");

        initRecyclerView();
    }

    //memasukkan semua text pada initTaskText kedalam RecyclerView pada todolist_layout.xml
    private void initRecyclerView (){
        Log.d(TAG, "initRecyclerView: initRecyclerView");

        RecyclerView recyclerView = findViewById(R.id.tasksRecyclerView);
        RecyclerView.Adapter adapter = new ToDoAdapter(this, mTasksText);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }

}


