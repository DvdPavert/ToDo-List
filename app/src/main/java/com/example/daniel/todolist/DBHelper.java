package com.example.daniel.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Daniel on 25/09/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todoDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TASK = "task";
    private static final String KEY_STATUS = "status";
    private static final String TABLE = "todoTable";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DB = "CREATE TABLE " + TABLE + "(" +KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT NOT NULL," + KEY_TASK + " TEXT NOT NULL," + KEY_STATUS + " TEXT NOT NULL)" ;

        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE);
        onCreate(db);
    }

    public void create(Task task)
    {
        SQLiteDatabase db = getWritableDatabase();
        //onUpgrade(db, 1, 1);

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_TASK, task.getTask());
        values.put(KEY_STATUS, task.getStatus());

        db.insert(TABLE, null, values);
        db.close();
    }

    public ArrayList<Task> read()
    {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Task> list = new ArrayList<>();

        String query =  "SELECT " + KEY_ID + ", " +  KEY_NAME + ", " +  KEY_TASK + " , " + KEY_STATUS
                + " FROM " + TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do
            {
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String task = cursor.getString(cursor.getColumnIndex(KEY_TASK));
                String status = cursor.getString(cursor.getColumnIndex(KEY_STATUS));

                Task newTask = new Task(id, name, task, status);
                list.add(newTask);
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }

    public int update(Task task)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_TASK, task.getTask());
        values.put(KEY_STATUS, task.getStatus());

        return db.update(TABLE, values, KEY_ID + " = ? ", new String[] {String.valueOf(task.getID())});

    }

    public void delete(Task task)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, " " + KEY_ID + " = ? ", new String[] {String.valueOf(task.getID())} );
        db.close();
    }
}
