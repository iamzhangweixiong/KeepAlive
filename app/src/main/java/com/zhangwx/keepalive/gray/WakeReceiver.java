package com.zhangwx.keepalive.gray;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by zhangwx on 2017/6/1.
 */

public class WakeReceiver extends BroadcastReceiver {

    public static final String TAG = "WakeReceiver";
    public static final String GARY_WAKE_ACTION = "com.zhangwx.graywake";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e(TAG, "onReceive: ACTION = " + action);
        if (GARY_WAKE_ACTION.equals(action)) {
            Intent intent1 = new Intent(context, GrayService.class);
            context.startService(intent1);
        }
    }
}
