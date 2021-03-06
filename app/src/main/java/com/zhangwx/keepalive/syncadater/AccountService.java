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

public class AccountService extends Service {

    private AccountAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG_SYNC, "AccountService onCreate");
        mAuthenticator = new AccountAuthenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG_SYNC, "AccountService onBind");
        return mAuthenticator.getIBinder();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG_SYNC, "AccountService onDestroy");
        super.onDestroy();
    }

}
