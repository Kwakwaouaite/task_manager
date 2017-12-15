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
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
    TaskAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tasks=Read(getApplicationContext());
        if(tasks==null) {
            genererTasks();


            Save();
        }
        NotificationEventReceiver.setupAlarm(getApplicationContext(),tasks.get(0));
        Intent intent = getIntent();
        if(intent != null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null && intent.getStringExtra("date")!=null) {
                String name = intent.getStringExtra("nameTask");
                String description = intent.getStringExtra("descriptionTask");
                String dateStr = intent.getStringExtra("date");
                Date date=null;

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    date = format.parse(dateStr);
                    System.out.println(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Task tasknew=new Task(new ArrayList<Task>(),name,description,Calendar.getInstance().getTime(),date,Color.BLUE,new ArrayList<NotififRules>());
                tasknew.getRules().add(new NotififRules(true,(float)0.5));
                tasks.add(tasknew);
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


        this.adapter = new TaskAdapter(DisplayTask.this, tasks);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                //int itemPosition     = position;

                // ListView Clicked item value
                //String  itemValue    = (String) mListView.getItemAtPosition(position);

                Log.d("tag", view.getTag().getClass().getName());

                Task task = tasks.get((int) id);
                task.switchDisplay();

                String taskTitle = (String) task.getName();
                
                ViewGroup test = (ViewGroup) view;

                /*
                int b = test.getChildCount();
                TextView desc = (TextView) test.getChildAt(1);

                desc.setText("YO !");*/



                //
                /*if (tasks.get((int) id).getDescription() == null) {
                    tasks.get((int) id).setDescription("Descriptionaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
                else{
                tasks.get((int) id).setDescription(null);}*/
                adapter.notifyDataSetChanged();

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+ id +"  ListItem : " + task.isDisplayed()  , Toast.LENGTH_LONG)
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
public static ArrayList<Task> Read(Context ctx)
{
try {
    FileInputStream fis = ctx.openFileInput("tasks");
    ObjectInputStream is = new ObjectInputStream(fis);
    ArrayList<Task> taskis =  (ArrayList<Task>) is.readObject();
    is.close();
    fis.close();
    return taskis;
}
catch (Exception e){
}
return null;
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

        tasks.add(new Task(new ArrayList<Task>(),"Get the F up", "aaaa", Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),
                Color.BLUE,new ArrayList<NotififRules>()));
        tasks.add(new Task(new ArrayList<Task>(),"Finish dat project", "", Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),
                Color.BLUE,new ArrayList<NotififRules>()));
        tasks.get(0).getRules().add(new NotififRules(false,1000));
         tasks.get(1).getRules().add(new NotififRules(true,(float)(0.5)));
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

    }

    public void removeClickHandler(View v){
        LinearLayout vwParentRow = (LinearLayout)v.getParent();
        TextView child = (TextView)vwParentRow.getChildAt(0);
        Button btnChild = (Button)vwParentRow.getChildAt(1);
        int position = Integer.parseInt((String)btnChild.getContentDescription());
        tasks.remove(position);
        Save();
        adapter.notifyDataSetChanged();

    }
}

