package com.ftninformatika.servisi22termin;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class MyStartedService extends Service {

    public static final int NOTIFICATION_ID = 101;
    public static final String SECONDS_TAG = "seconds";

    public MyStartedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int seconds = intent.getIntExtra(SECONDS_TAG, 1);

        for (int i = 0; i < seconds; i++) {
        countDown(seconds - i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



        happyBday();

        return START_NOT_STICKY;

    }

    private void happyBday(){

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.ic_attach_file_black_24dp);
        builder.setContentTitle("Srecan Rodjendan");
        builder.setContentText("Danas nam je divan dan, divan dan.......");
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());


    }

    private void countDown(int secondsRemaining){

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.ic_sentiment_satisfied_black_24dp);
        builder.setContentTitle("Odbrojavanje");
        builder.setContentText("Ostalo je: " + secondsRemaining + " sekundi");
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());

    }


}
