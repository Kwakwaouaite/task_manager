package com.manzanart.albick.taskmanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DisplayTask extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CreateTaskActivity.class);
                startActivity(i);
            }
        });
        mListView = (ListView) findViewById(R.id.list);

        List<Task> tasks = genererTasks();

        TaskAdapter adapter = new TaskAdapter(DisplayTask.this, tasks);
        mListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private List<Task> genererTasks(){
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(new ArrayList<Task>(),"Get the F up", Calendar.getInstance().getTime(),Calendar.getInstance().getTime(), Color.BLUE));
        tasks.add(new Task(new ArrayList<Task>(),"Finish dat project", Calendar.getInstance().getTime(),Calendar.getInstance().getTime(), Color.BLUE));
        return tasks;
    }
}
