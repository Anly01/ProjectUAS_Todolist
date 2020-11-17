package com.example.buttons.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

import com.example.buttons.R;


public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mTasks = new ArrayList<>();
    private Context mContext;

    public ToDoAdapter(Context context, ArrayList<String> tasks) {
        mTasks = tasks;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todolist_task_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.Tasks.setText(mTasks.get(position));
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Tasks;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            Tasks = itemView.findViewById(R.id.todoCheckBox);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}