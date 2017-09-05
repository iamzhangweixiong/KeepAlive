package com.zhangwx.keepalive;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zhangwx.keepalive.common.CommonService;
import com.zhangwx.keepalive.jobscheduler.JobSchedulerService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final int JOB_ID = 0;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

//        startSchedulerJob();
    }

    @OnClick(R.id.fab)
    void fabClick() {
//      Intent intent = new Intent(MainActivity.this, GrayService.class);
        Intent intent = new Intent(MainActivity.this, CommonService.class);
        intent.setAction(CommonService.ACTION_CREATE);
        startService(intent);
    }

    @OnClick(R.id.remove)
    void removeClick() {
        Intent intent = new Intent(MainActivity.this, CommonService.class);
        intent.setAction(CommonService.ACTION_REMOVE);
        startService(intent);
    }

    private void startSchedulerJob() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, new ComponentName(getPackageName(), JobSchedulerService.class.getName()));
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
}
