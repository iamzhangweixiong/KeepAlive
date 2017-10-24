package com.zhangwx.keepalive;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.zhangwx.keepalive.common.AliveHelper;
import com.zhangwx.keepalive.common.CommonService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_DOZE = 1011;
    public static final String TAG = "MainActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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

    @OnClick(R.id.white)
    void whiteClick() {
        if (AliveHelper.needIgnoreOpti(this, getPackageName())) {
            Intent intent = new Intent();
            String packageName = getPackageName();
            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + packageName));
            startActivityForResult(intent, REQUEST_CODE_DOZE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DOZE) {
            boolean success = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                success = pm.isIgnoringBatteryOptimizations(getPackageName());
            }
            Log.e(TAG, "onActivityResult: success = " + success);
        }
    }
}
