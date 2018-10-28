package com.smileparser.automator.triggeractionmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.smileparser.automator.App;
import com.smileparser.automator.R;
import com.smileparser.automator.activity.ActivitySplashPage;
import com.smileparser.automator.db_helper.MacroModel;
import com.smileparser.automator.triggers_events.MacroManagerNew;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TriggerService extends Service {

    List<BroadcastReceiver> receiverList = new ArrayList<>();

    public TriggerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT < 26) {
            startForeground(1, getNotification());     //<-- Makes Foreground
        } else {
            startMyOwnForeground();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        fetchAllMacrosFromDb();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Notification getNotification() {

        Intent intent = new Intent(this, ActivitySplashPage.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        NotificationCompat.Builder foregroundNotification = new NotificationCompat.Builder(this);
        foregroundNotification.setOngoing(true);

        foregroundNotification.setContentTitle("Automator")
                .setContentText("Macros enabled. Tap to manage.")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);


        return foregroundNotification.build();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void startMyOwnForeground() {
        String NOTIFICATION_CHANNEL_ID = getApplicationContext().getPackageName();
        String channelName = "Automator";
        NotificationChannel chan = null;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
            chan.setLightColor(Color.BLUE);
            chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            manager.createNotificationChannel(chan);
        }

        Intent intent = new Intent(this, ActivitySplashPage.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Automator")
                .setContentText("Macros enabled. Tap to manage.")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(1, notification);
    }

    void fetchAllMacrosFromDb() {
        Dao<MacroModel, Integer> macroModels = App.getDataBaseHelper().getDao(MacroModel.class);
        try {
            if (macroModels == null) {
                Log.e("Android->", "fetchAllMacrosFromDb: Model null");
            } else {
                List<MacroModel> modelList = macroModels.queryForAll();
                if (modelList != null) {
                    for (MacroModel macro : modelList) {
                        Log.e("Android->", "fetchAllMacrosFromDb: " + new Gson().toJson(macro));
                        new MacroManagerNew(this).registerMacro(macro);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
