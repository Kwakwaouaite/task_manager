package com.manzanart.albick.taskmanager;

import android.app.Notification;

/**
 * Created by 4223 on 08/12/2017.
 */

class NotififRules {
    public boolean isProportional() {
        return isProportional;
    }

    public void setProportional(boolean proportional) {
        isProportional = proportional;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public NotififRules(boolean isProportional, float value) {
        this.isProportional = isProportional;
        this.value = value;
    }

    private boolean isProportional;
    private float value;
    public void createNotification()
    {
   /* Notification.Builder builder = new Notification.Builder(this)
            .setWhen(System.currentTimeMillis())
            .setTicker(notificationTitle)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle(getResources().getString(R.string.notification_title))
            .setContentText(getResources().getString(R.string.notification_desc))
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.play,"Play", PendingIntent.getActivity(getApplicationContext(), 0,
                    getIntent(), 0, null))
            .addAction(R.drawable.pause, "Pause",
                    PendingIntent.getActivity(getApplicationContext(), 0,
                            getIntent(), 0, null));

    Notification notification = new Notification.BigPictureStyle(builder)
            .bigPicture(BitmapFactory.decodeResource(getResources(),
                    R.drawable.zoidberg_android)).build();

        mNotification.notify(NOTIFICATION_ID, notification);
    */
    }
    }
