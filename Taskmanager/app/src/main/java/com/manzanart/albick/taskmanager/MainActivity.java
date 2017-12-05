package com.manzanart.albick.taskmanager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by RoxasIsPoor on 04/12/2017.
 */

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_task);
        mListView = (ListView) findViewById(R.id.list);

        List<Task> tasks = genererTasks();

        TaskAdapter adapter = new TaskAdapter(MainActivity.this, tasks);
        mListView.setAdapter(adapter);
    }

    private List<Task> genererTasks(){
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(new ArrayList<Task>(),"Get the F up", Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),Color.BLUE));
        return tasks;
    }
}