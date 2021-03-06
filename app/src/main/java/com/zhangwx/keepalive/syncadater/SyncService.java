package com.zhangwx.keepalive.syncadater;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import static com.zhangwx.keepalive.common.Logger.TAG_SYNC;

/**
 * Created by zhangwx
 * on 2017/7/3.
 */
public class SyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static SyncAdapter sSyncAdapter = null;

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                Log.e(TAG_SYNC, "SyncService onCreate");
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG_SYNC, "SyncService onBind");
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
