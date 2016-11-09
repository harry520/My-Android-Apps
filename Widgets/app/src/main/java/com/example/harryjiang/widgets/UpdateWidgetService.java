package com.example.harryjiang.widgets;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

public class UpdateWidgetService extends Service {

    private static final String LOG = "widget.example";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onStart(Intent intent, int startId) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getApplicationContext());
        int[] allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
        for (int widgetId : allWidgetIds) {
            int number = (new Random().nextInt(100));
            RemoteViews remoteViews = new RemoteViews(this.getApplicationContext().getPackageName(), R.layout.my_widget);
            Log.w("WidgetExample", String.valueOf(number));
            remoteViews.setTextViewText(R.id.update, "Random: " + String.valueOf(number));
            Intent clickIntent = new Intent(this.getApplicationContext(), MyWidget.class);
            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
            stopSelf();
            super.onStart(intent, startId);
        }
    }
}
