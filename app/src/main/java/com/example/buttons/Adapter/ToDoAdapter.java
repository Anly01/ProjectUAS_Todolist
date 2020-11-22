package com.example.buttons.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.example.buttons.Model.ToDoModel;
import com.example.buttons.R;
import com.example.buttons.todoPage;

//recyclerview adapter
public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    private List<ToDoModel> todoList;
    private todoPage activity;

    public ToDoAdapter(todoPage activity) {
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
        ToDoModel item = todoList.get(position);
        holder.tasks.setText(item.getTasks());
        holder.tasks.setChecked(toBoolean(item.getStatus()));
    }
    //set checkbox to checked if the status !=0
    private boolean toBoolean (int n){
        return n!=0;
    }
    //insert dummy data to todolist
    public void setTasks (List<ToDoModel> todoList){
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    // agar recyclerview tau berapa banyak task yang ada
    @Override
    public int getItemCount() {
        return todoList.size();
    }



}