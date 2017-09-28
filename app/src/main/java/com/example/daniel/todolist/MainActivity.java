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


    //private ArrayList test = new ArrayList<>();


    private ArrayList<Task> list = new ArrayList<>();
    private ListView listTasks;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(this);

        listTasks = (ListView) findViewById(R.id.listTasks);


//        Task testTask1 = new Task("1 + App maken", "Maak een app die SQLite implementeerd");
//        Task testTask2 = new Task("2 + App maken", "Maak een app die SQLite implementeerd");
//        Task testTask3 = new Task("3 + App maken", "Maak een app die SQLite implementeerd");
//        db.create(testTask1);
//        db.create(testTask2);
//        db.create(testTask3);

        list = db.read(); //// TODO: 28/09/2017 WHY 7 ?!?!?!?! 

        //test.add(testTask1);
        //test.add(testTask2);
        //test.add(list.size() + "");

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
