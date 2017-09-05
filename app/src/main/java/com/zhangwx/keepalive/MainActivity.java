package com.zhangwx.keepalive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.zhangwx.keepalive.common.CommonService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

//        AliveHelper.startSchedulerJob(this);
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

}
