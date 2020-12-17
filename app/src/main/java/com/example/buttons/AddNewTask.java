package com.example.buttons;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.buttons.Model.ToDoModel;
import com.example.buttons.Utils.DatabaseHandler;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;
public class AddNewTask extends BottomSheetDialogFragment {
    // untuk memunculkan dialog di bawah layar, saat addbtn pada todopage ditekan
    public static final String TAG = "ActionBottomDialog";
    private EditText newTaskText;
    private ImageButton newTaskSaveButton;
    private DatabaseHandler db;
    int nomorlogo;


    public static AddNewTask newInstance(){
        return new AddNewTask();
    }
    //make sure that after addbtn on click, munculin layout todolist_newtask.xml dengan dialog style
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Nullable
    @Override
    //menentukan jenis input
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todolist_newtask, container, false);
        Objects.requireNonNull(getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newTaskText = Objects.requireNonNull(getView()).findViewById(R.id.newTaskText);
        newTaskSaveButton = getView().findViewById(R.id.newTaskButton);
        //mengubah button save apabila theme diganti
        SharedPreferences buttons = this.getActivity().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        nomorlogo = buttons.getInt("NOMOR", 1);
        newTaskSaveButton.setBackgroundResource(getResources().getIdentifier("save"+nomorlogo, "drawable", getActivity().getPackageName()));

        // menentukan apakah dialog dibuka karena user update atau membuat list baru
        boolean isUpdate = false;
        final Bundle bundle = getArguments();
        if(bundle != null){
            isUpdate = true;
            String task = bundle.getString("task");
            newTaskText.setText(task); }

        //membuka database
        db = new DatabaseHandler(getActivity());
        db.openDatabase();
        final boolean finalIsUpdate = isUpdate;
        newTaskSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = newTaskText.getText().toString();
                //apabila update
                if(finalIsUpdate){
                    db.updateTasks(bundle.getInt("id"), text);
                }
                //else buat list baru
                else {
                    ToDoModel task = new ToDoModel();
                    task.setTasks(text);
                    task.setStatus(0);
                    db.insertTasks(task);
                }
                dismiss();
            }
        });
    }

    //code untuk menutup dialog
    @Override
    public void onDismiss(@NonNull DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener)
            ((DialogCloseListener)activity).handleDialogClose(dialog);
    }
}

