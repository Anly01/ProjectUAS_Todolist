package com.example.buttons.Adapter;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.example.buttons.AddNewTask;
import com.example.buttons.Model.ToDoModel;
import com.example.buttons.R;
import com.example.buttons.Utils.DatabaseHandler;
import com.example.buttons.todoPage;

//recyclerview adapter
public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    private List<ToDoModel> todoList;
    private todoPage activity;
    private DatabaseHandler db;


    public ToDoAdapter(DatabaseHandler db, todoPage activity) {
        this.db = db;
        this.activity = activity;
    }

    //define the ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox tasks;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            tasks = itemView.findViewById(R.id.todoCheckBox);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todolist_task_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //connect ToDoModel, jadi data dari todomodel bisa diambil ke recyclerview
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        db.openDatabase();
        ToDoModel item = todoList.get(position);
        holder.tasks.setText(item.getTasks());
        holder.tasks.setChecked(toBoolean(item.getStatus()));

        //update database when we check or uncheck the checkbox
        holder.tasks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    db.updateStatus(item.getId(), 1);
                }else{
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    // agar recyclerview tau berapa banyak task yang ada
    @Override
    public int getItemCount() {
        return todoList.size(); }

    public Context getContext(){
        return activity;
    }

    //set checkbox to checked if the status !=0
    private boolean toBoolean (int n){
        return n!=0;
    }

    //insert data to todolist
    public void setTasks (List<ToDoModel> todoList){
        this.todoList = todoList;
        notifyDataSetChanged(); }

    //edit text
    public void editItem (int position){
        ToDoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("tasks", item.getTasks());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }






}