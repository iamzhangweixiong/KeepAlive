package com.zhangwx.keepalive.common;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.zhangwx.keepalive.syncadater.SyncUtil;

/**
 * Created by zhangwx
 * on 2017/7/7.
 */

public class CommonService extends Service {

    public static final String ACTION_CREATE = "com.zhangwx.createsync";
    public static final String ACTION_REMOVE = "com.zhangwx.removesync";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        switch (action) {
            case ACTION_CREATE:
                SyncUtil.createSyncAccount(this);
                break;
            case ACTION_REMOVE:
                SyncUtil.removeSyncAccount(this);
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
