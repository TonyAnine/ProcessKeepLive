package com.tony.processkeeplive.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * Created by TonyYao on 2019/3/18.
 */
public class KeepReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        if(TextUtils.equals(action,Intent.ACTION_SCREEN_OFF)){
            //屏幕关闭
            KeepManager.getOurInstance().startKeep(context);
        }else if(TextUtils.equals(action,Intent.ACTION_SCREEN_ON)){
            //屏幕打开
            KeepManager.getOurInstance().finishKeep();
        }
    }
}
