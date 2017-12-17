package com.manzanart.albick.taskmanager;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RoxasIsPoor on 04/12/2017.
 */

public class TaskAdapter extends ArrayAdapter<Task> {

        //tasks est la liste des models à afficher
        public TaskAdapter(Context context, List<Task> tasks) {
            super(context, 0, tasks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_task,parent, false);
            }

            TaskViewHolder viewHolder = (TaskViewHolder) convertView.getTag();

            if(viewHolder == null){
                viewHolder = new TaskViewHolder();

                viewHolder.text = (TextView) convertView.findViewById(R.id.task_name);
                viewHolder.description = convertView.findViewById(R.id.description);
                viewHolder.button = convertView.findViewById(R.id.remove_button);
                viewHolder.timeLeft = convertView.findViewById(R.id.time_left);

                //viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(viewHolder);
            }

            //getItem(position) va récupérer l'item [position] de la List<Tasks> tasks
            Task task = getItem(position);
            Log.d("Name", task.getName());

            //il ne reste plus qu'à remplir notre vue
            viewHolder.text.setText(task.getName());
            viewHolder.timeLeft.setText(task.getTimeLeft());
            viewHolder.button.setContentDescription(Integer.toString(position));

            if (task.isDisplayed()) {
                viewHolder.description.setText( task.getDescription());
                viewHolder.button.setVisibility(View.VISIBLE);
            }
            else {
                viewHolder.description.setText(null);
                viewHolder.button.setVisibility(View.GONE);}
            //viewHolder.image.setImageDrawable(new ColorDrawable(task.getColor()));

            return convertView;
        }

        private class TaskViewHolder{

            public TextView text;
            public ImageView image;
            public TextView timeLeft;
            public TextView description;
            public Button button;
        }
    }

