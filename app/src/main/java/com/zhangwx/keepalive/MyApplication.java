package com.zhangwx.keepalive;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangwx
 * on 2017/7/6.
 */

public class MyApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
