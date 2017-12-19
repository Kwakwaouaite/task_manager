package com.manzanart.albick.taskmanager;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import java.security.SecureRandomSpi;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by 4223 on 05/12/2017.
 */


    public class CreateTaskActivity extends AppCompatActivity {

public static int year;
    public static int month;
    public static int day;

public static int hour;
public static int minutes;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_task);
            Button button = (Button) findViewById(R.id.submit);
            final EditText nameTask   = (EditText)findViewById(R.id.editText);
            final EditText descriptionTask = (EditText)findViewById(R.id.editDescription);
            final RadioButton radioPourcent= (RadioButton) findViewById(R.id.notificationRelative);
            final EditText valueRule=(EditText) findViewById(R.id.editText3);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DisplayTask.class);
//TODO mettre une sécurité sur les nombres
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isPercent",radioPourcent.isChecked());
                    bundle.putString("nameTask", nameTask.getText().toString());
                    bundle.putString("descriptionTask", descriptionTask.getText().toString());
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    bundle.putString("date",year+"-"+(month+1)+"-"+day+" "+hour+":"+minutes+":00" );
                    //Log.d("Creation", year+"-"+month+"-"+day+" "+hour+":"+minutes+"00");
                    bundle.putString("valueRule",valueRule.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            });
             }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }    /**
     *
     * @param datePicker
     * @return a java.util.Date
     */
    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
    }

