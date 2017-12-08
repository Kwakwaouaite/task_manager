package com.manzanart.albick.taskmanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by 4223 on 05/12/2017.
 */


    public class CreateTaskActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_task);
            Button button = (Button) findViewById(R.id.submit);
            final EditText nameTask   = (EditText)findViewById(R.id.editText);
            final Date date = getDateFromDatePicker((DatePicker)findViewById(R.id.datePicker));


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DisplayTask.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("nameTask", nameTask.getText().toString());
                    bundle.putString("date", date.toString());

                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            });
             }
    /**
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

