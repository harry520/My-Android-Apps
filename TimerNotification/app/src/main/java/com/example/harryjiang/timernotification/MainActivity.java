package com.example.harryjiang.timernotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private MessageHandler messageHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog.setTitle("Counting");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(true);
        messageHandler = new MessageHandler();
    }

    public void startCounter(View v) {
        progressDialog.show();
        Thread thread = new Thread(new Timer());
        thread.start();
    }

    public void doNotidy() {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent intent = PendingIntent.getActivity(this, 100, new Intent(this, BoomActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setSound(sound);
        builder.setContentTitle("Knock knock...");
        builder.setContentText("You've got a delivery.");
        builder.setContentIntent(intent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(100, builder.build());
    }

    public class Timer implements Runnable {

        @Override
        public void run() {
            for (int i = 5; i >= 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("current count", i);
                Message message = new Message();
                message.setData(bundle);
                messageHandler.sendMessage(message);
            }
            progressDialog.dismiss();
        }
    }

    private class MessageHandler extends Handler {

        @Override
        public void handleMessage(Message message) {
            int currentCount = message.getData().getInt("current count");
            progressDialog.setMessage("Please wait in ... " + currentCount);
            if (currentCount == 0)
                doNotidy();
        }
    }
}
