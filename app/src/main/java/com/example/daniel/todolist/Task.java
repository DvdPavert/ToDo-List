package com.example.daniel.todolist;

import java.io.Serializable;

/**
 * Created by Daniel on 25/09/2017.
 */

public class Task implements Serializable {

    private int ID;
    private String name;
    private String task;
    private String status;

    public Task(String name, String task)
    {
        this.ID = -1;
        this.name = name;
        this.task = task;
        this.status = "incomplete";
    }

    public Task(int id, String name, String task, String status)
    {
        this.ID = id;
        this.name = name;
        this.task = task;
        this.status = status;
    }

    public int getID()
    {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return name;
    }
}

