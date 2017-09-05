package com.zhangwx.keepalive.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.zhangwx.keepalive.common.CommonService;

/**
 * Created by zhangweixiong on 2017/9/5.
 */

public class AlarmHelper {

    public static void startAlarm(Context context) {
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, CommonService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
    }
}
