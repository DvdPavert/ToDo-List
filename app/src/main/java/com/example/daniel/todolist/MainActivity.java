package com.example.daniel.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> list = new ArrayList<>();
    private ListView listTasks;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(this);

        listTasks = (ListView) findViewById(R.id.listTasks);


        list = db.read();

        listTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Task selectedTask = (Task) listTasks.getItemAtPosition(position);
                Intent intent = new Intent(context, ShowTaskActivity.class);
                intent.putExtra("task", selectedTask);
                startActivity(intent);

            }
        });

        makeTrackAdapter();
    }

    public void makeTrackAdapter()
    {
        ArrayAdapter arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
        assert listTasks != null;
        listTasks.setAdapter(arrayAdapter);
    }

    public void addTask(View view)
    {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

}
