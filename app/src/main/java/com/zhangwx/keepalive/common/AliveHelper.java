package com.zhangwx.keepalive.common;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import com.zhangwx.keepalive.jobscheduler.JobSchedulerService;

/**
 * Created by zhangweixiong on 2017/9/5.
 */

public class AliveHelper {

    private static final int JOB_ID = 0;

    public static void startSchedulerJob(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, new ComponentName(context.getPackageName(), JobSchedulerService.class.getName()));
            builder.setPeriodic(20 * 60 * 1000);
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
            builder.setRequiresCharging(true);
            builder.setPersisted(true);
            builder.setRequiresDeviceIdle(false);
            if (jobScheduler.schedule(builder.build()) <= 0) {
                //If something goes wrong
            }
        }
    }

    public static boolean needIgnoreOpti(Context context, String packageName) {
        boolean needIgnore = false;
        boolean exists = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            needIgnore = !pm.isIgnoringBatteryOptimizations(packageName);
            Intent intent = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
            exists = intent.resolveActivityInfo(context.getPackageManager(), 0) != null;
        }
        return needIgnore && exists;
    }
}
