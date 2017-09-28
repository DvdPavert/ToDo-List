package com.example.daniel.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowTaskActivity extends AppCompatActivity {

    private TextView textTitle;
    private TextView textTask;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        textTitle = (TextView) findViewById(R.id.textTitle);
        textTask = (TextView) findViewById(R.id.textTask);

        loadTask();
    }

    private void loadTask()
    {
        task = (Task) getIntent().getSerializableExtra("task");
        textTitle.setText(task.getName());
        textTask.setText(task.getTask());
    }

    public  void completeTask(View view)
    {
        DBHelper db = new DBHelper(this);

        task.setName(task.getName() + " - completed");
        task.setStatus("complete");
        db.update(task);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void deleteTask(View view)
    {
        DBHelper db = new DBHelper(this);
        db.delete(task);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
