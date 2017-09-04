package com.zhangwx.keepalive.grayservice;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;


/**
 * Created by zhangwx on 2017/5/31.
 * 灰色保活
 */

public class GrayService extends Service {
    public static final String TAG = "GrayService";
    public static final int GRAY_SERVICE_ID = -1001;
    public static final int GRAY_WAKE_REQUEST = 6666;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            startForeground(GRAY_SERVICE_ID, new Notification());//API < 18
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) {
            Intent innerIntent = new Intent(this, InnerGrayService.class);
            startService(innerIntent);
            startForeground(GRAY_SERVICE_ID, new Notification());
        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent();
        alarmIntent.setAction(WakeReceiver.GARY_WAKE_ACTION);
        PendingIntent operation = PendingIntent.getBroadcast(
                this, GRAY_WAKE_REQUEST, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, operation);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class InnerGrayService extends Service {
        public static final String TAG = "InnerGrayService";

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.e(TAG, "onStartCommand");
            startForeground(GRAY_SERVICE_ID, new Notification());
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}
