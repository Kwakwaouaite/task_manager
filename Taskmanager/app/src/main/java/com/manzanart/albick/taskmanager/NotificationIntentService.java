package com.manzanart.albick.taskmanager;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 4223 on 08/12/2017.
 */

public class NotificationIntentService extends IntentService {

    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_START = "ACTION_START";
    private static final String ACTION_DELETE = "ACTION_DELETE";

    public NotificationIntentService() {
        super(NotificationIntentService.class.getSimpleName());
    }

    public static Intent createIntentStartNotificationService(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_START);
        return intent;
    }

    public static Intent createIntentDeleteNotification(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_DELETE);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
        try {
            String action = intent.getAction();
            if (ACTION_START.equals(action)) {
                if (intent.getExtras() != null) {
                   int hash= intent.getIntExtra("Task", 0);
                    processStartNotification(hash);
                }
            }
            if (ACTION_DELETE.equals(action)) {
                processDeleteNotification(intent);
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    private void processDeleteNotification(Intent intent) {
        // Log something?
    }

    private void processStartNotification(int hash) {
        // Do something. For example, fetch fresh data from backend to create a rich notification?
        ArrayList<Task> liste = DisplayTask.Read(getApplicationContext());

        int i = 0;
        Task taskfound = null;

        while (taskfound == null && i < liste.size()) {
            if (liste.get(i).getId() == hash) {
                taskfound = liste.get(i);
            }
            i++;
        }
        if (taskfound == null) {
            System.out.println("tache non retrouvée!");
        } else {
            final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle(taskfound.getName())
                    .setAutoCancel(true)
                    .setColor(getResources().getColor(R.color.colorAccent))
                    .setContentText(taskfound.getDescription())
                    .setSmallIcon(R.drawable.notification);

            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    NOTIFICATION_ID,
                    new Intent(this, DisplayTask.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this));

            final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(NOTIFICATION_ID, builder.build());
        }
    }
}
