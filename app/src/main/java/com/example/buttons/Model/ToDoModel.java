package com.example.buttons.Model;

// defines the structure for individual tasks to connect it with database.

public class ToDoModel {
    /**
     * Variables
     */
    private int id, status, score;
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
