package com.example.buttons.Model;

// defines the structure for individual tasks with the info from database
// to store item data (name etc)

public class ToDoModel {
    /**
     * Variables
     */
    private int id, status;
    private String tasks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }
}
