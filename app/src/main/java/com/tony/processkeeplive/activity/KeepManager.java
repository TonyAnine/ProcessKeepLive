package com.tony.processkeeplive.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

/**
 * Created by TonyYao on 2019/3/18.
 */
public class KeepManager {
    private static final KeepManager ourInstance=new KeepManager();

    public static KeepManager getOurInstance() {
        return ourInstance;
    }
    private KeepManager(){

    }

    private KeepReceiver mKeepReceiver;

    private WeakReference<Activity> mKeepAct;

    public void setKeep(KeepActivity keep){
        mKeepAct=new WeakReference<Activity>(keep);
    }

    /**
     * 注册 关屏 开屏
     */
    public void registerKeep(Context context){
        IntentFilter filter=new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        mKeepReceiver=new KeepReceiver();
        context.registerReceiver(mKeepReceiver,filter);
    }
    /**
     * 注销 关屏 开屏
     */
    public void unRegisterKeep(Context context){
        if(null!=mKeepReceiver){
            context.unregisterReceiver(mKeepReceiver);
        }
    }

    /**
     * 开启activity
     */
    public void startKeep(Context context){
        Intent intent=new Intent(context,KeepActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 销毁activity
     */
    public void finishKeep(){
        if(null!=mKeepAct){
            Activity activity=mKeepAct.get();
            if(null!=activity){
                activity.finish();
            }
        }
    }
}
