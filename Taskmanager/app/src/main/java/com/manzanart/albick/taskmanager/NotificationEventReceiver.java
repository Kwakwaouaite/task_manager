package com.manzanart.albick.taskmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 4223 on 08/12/2017.
 */
public class NotificationEventReceiver extends WakefulBroadcastReceiver {

    private static final String ACTION_START_NOTIFICATION_SERVICE = "ACTION_START_NOTIFICATION_SERVICE";
    private static final String ACTION_DELETE_NOTIFICATION = "ACTION_DELETE_NOTIFICATION";

    public static void setupAlarm(Context context,Task task) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = getStartPendingIntent(context,task);
       //on crée le pendingIntent qu'il faut

       float dateTri=0;


        Calendar calendarEnding = Calendar.getInstance();
        calendarEnding.setTime(task.getEndingDate());
        //calendar.add(Calendar.HOUR, NOTIFICATIONS_INTERVAL_IN_HOURS);
        long cEnding=calendarEnding.getTimeInMillis();


        Calendar calendarBeginning = Calendar.getInstance();
        calendarBeginning.setTime(task.getStartingDate());
        //calendar.add(Calendar.HOUR, NOTIFICATIONS_INTERVAL_IN_HOURS);
        long cStarting=calendarBeginning.getTimeInMillis();

        if(task.getRules().size()>0 && task.getRules().get(0).isProportional())
        {


            dateTri=cStarting+ (cEnding-cStarting) *task.getRules().get(0).getValue()/100;

        }
        else if (task.getRules().size()>0 && !task.getRules().get(0).isProportional())
        {
        dateTri=cEnding-task.getRules().get(0).getValue();
        }
        alarmManager.set(AlarmManager.RTC_WAKEUP,(long)(dateTri),alarmIntent);
                }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        Intent serviceIntent = null;
        if (ACTION_START_NOTIFICATION_SERVICE.equals(action)) {
            Log.i(getClass().getSimpleName(), "onReceive from alarm, starting notification service");
            serviceIntent = NotificationIntentService.createIntentStartNotificationService(context);
        } else if (ACTION_DELETE_NOTIFICATION.equals(action)) {
            Log.i(getClass().getSimpleName(), "onReceive delete notification action, starting notification service to handle delete");
            serviceIntent = NotificationIntentService.createIntentDeleteNotification(context);

        }

        if (serviceIntent != null) {
        int hashValue=intent.getIntExtra("Task",0);
        serviceIntent.putExtra("Task",hashValue);
            startWakefulService(context, serviceIntent);

        }
    }

    private static long getTriggerAt(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        //calendar.add(Calendar.HOUR, NOTIFICATIONS_INTERVAL_IN_HOURS);
        long tm=calendar.getTimeInMillis();
        return tm;
    }

    private static PendingIntent getStartPendingIntent(Context context,Task task) {
        Intent intent = new Intent(context, NotificationEventReceiver.class);
        intent.setAction(ACTION_START_NOTIFICATION_SERVICE);
        intent.putExtra("Task",task.getId());
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static PendingIntent getDeleteIntent(Context context) {
        Intent intent = new Intent(context, NotificationEventReceiver.class);
        intent.setAction(ACTION_DELETE_NOTIFICATION);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
