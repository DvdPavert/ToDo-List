package com.example.daniel.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTitle = (EditText) findViewById(R.id.editTitle);
        editTask = (EditText) findViewById(R.id.editTask);
    }

    public void getTask()
    {
        DBHelper db = new DBHelper(this); //// TODO: 27/09/2017 Hoe krijg ik de db hier goed? 

        String title = editTitle.getText().toString();
        String task = editTask.getText().toString();

        Task newTask = new Task(title, task);
        db.create(newTask);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
