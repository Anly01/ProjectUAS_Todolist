package com.example.buttons.Utils;
// adding, updating, deleting task from database

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.buttons.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String NAME = "toDoListDataBase";
    private static final String TODO_TABLE ="toDo";
    private static final String ID = "id";
    private static final String TASKS = "tasks";
    private static final String STATUS = "status";
    private static final String CREATE_TODO_TABLE = "CREATE TABLE "+ TODO_TABLE + "(" + ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                                            + TASKS + "TEXT, " + STATUS + " INTEGER)";
    private SQLiteDatabase db;

    public DatabaseHandler(Context context){
        super(context, NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop the older tables
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        //create new tables
        onCreate(db);
    }

    public void openDatabase(){
        db = this.getWritableDatabase();
    }
    //function to inserting new tasks
    public void insertTasks(ToDoModel tasks){
        ContentValues cv = new ContentValues();
        cv.put(TASKS, tasks.getTasks());
        //0 because new task will not be checked
        cv.put(STATUS, 0);
        db.insert(TODO_TABLE, null, cv);
    }

    //function to get all tasks from database an send it to activity as array, and then show it in recycler view
    public List<ToDoModel> getAllTasks(){
        List<ToDoModel> tasksList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        //returning all database data from TODO_TABLE without any criteria
        try{
            cur = db.query(TODO_TABLE, null,null,null,null,null,null, null);
            if (cur != null){
                if (cur.moveToFirst()){
                    //loop that get all task, id and status while the cursor are not empty
                    do {
                        ToDoModel task = new ToDoModel();
                        task.setId(cur.getInt(cur.getColumnIndex(ID)));
                        task.setTasks(cur.getString(cur.getColumnIndex(TASKS)));
                        task.setStatus(cur.getInt((cur.getColumnIndex(STATUS))));
                        tasksList.add(task);
                    }while(cur.moveToNext());
                }
            }
        }finally {
            db.endTransaction();
            cur.close();
        }
        return tasksList;
    }
    //update checked or not
    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        //QUESTION MARK IS FOR FORMATTING
        db.update(TODO_TABLE, cv, ID + "=?", new String[] {String.valueOf(id)});
    }
    //update tasks
    public void updateTasks(int id, String tasks){
        ContentValues cv = new ContentValues();
        cv.put(TASKS, tasks);
        db.update(TODO_TABLE, cv, ID + "=?", new String[] {String.valueOf(id)});
    }

    //delete tasks
    public void deleteTasks(int id){
        db.delete(TODO_TABLE, ID + "=?",new String[] {String.valueOf(id)});
    }
}
