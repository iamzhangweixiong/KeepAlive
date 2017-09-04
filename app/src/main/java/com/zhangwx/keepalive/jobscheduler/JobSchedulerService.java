package com.zhangwx.keepalive.jobscheduler;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import com.zhangwx.keepalive.common.WeakHandler;

/**
 * Created by zhangwx
 * on 2017/7/7.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {

    private WeakHandler mJobHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            // do something
            jobFinished((JobParameters) msg.obj, false);
            return true;
        }
    });

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = params;
        mJobHandler.sendMessage(msg);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        mJobHandler.removeCallbacksAndMessages(null);
        return false;
    }
}
