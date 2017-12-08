package com.manzanart.albick.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DisplayTask extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        genererTasks();
        Read();
        NotificationEventReceiver.setupAlarm(getApplicationContext());
        Intent intent = getIntent();
        if(intent != null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String name = intent.getStringExtra("nameTask");
                String dateStr = intent.getStringExtra("date");
                Date date=null;

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    date = format.parse(dateStr);
                    System.out.println(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                tasks.add(new Task(new ArrayList<Task>(),name,null,Calendar.getInstance().getTime(),date,Color.BLUE));
                Save();
            }
        }
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), CreateTaskActivity.class);
                startActivity(i);
            }
        });
        mListView = (ListView) findViewById(R.id.list);


        final TaskAdapter adapter = new TaskAdapter(DisplayTask.this, tasks);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                //int itemPosition     = position;

                // ListView Clicked item value
                //String  itemValue    = (String) mListView.getItemAtPosition(position);

                String taskTitle = (String) tasks.get((int) id).getName();
                

                //
                if (tasks.get((int) id).getDescription() == null) {
                    tasks.get((int) id).setDescription("Descriptionaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
                else{
                tasks.get((int) id).setDescription(null);}
                adapter.notifyDataSetChanged();

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+ id +"  ListItem : " + taskTitle , Toast.LENGTH_LONG)
                        .show();

            }

        });
    }

public void Save()
{
    try {
        FileOutputStream fos = openFileOutput("tasks", Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(tasks);
        os.close();
        fos.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

}
public void Read()
{
try {
    FileInputStream fis = openFileInput("tasks");
    ObjectInputStream is = new ObjectInputStream(fis);
    this.tasks =  (ArrayList<Task>) is.readObject();
    is.close();
    fis.close();
}
catch (Exception e){
}
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



    private void genererTasks(){
        tasks=new ArrayList<Task>();
        tasks.add(new Task(new ArrayList<Task>(),"Get the F up", null, Calendar.getInstance().getTime(),Calendar.getInstance().getTime(), Color.BLUE));
        tasks.add(new Task(new ArrayList<Task>(),"Finish dat project", "", Calendar.getInstance().getTime(),Calendar.getInstance().getTime(), Color.BLUE));

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

    }
}

