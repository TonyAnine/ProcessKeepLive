package com.tony.processkeeplive;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tony.processkeeplive.activity.KeepManager;
import com.tony.processkeeplive.service.LocalService;
import com.tony.processkeeplive.service.RemotService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //通过1像素 activity 进行保活
        KeepManager.getOurInstance().registerKeep(this);

        //前台服务，提高服务优先级
        /*if(Build.VERSION.SDK_INT<Build.VERSION_CODES.O){
            startService(new Intent(this, ForegroundService.class));
        }else{
            startForegroundService(new Intent(this, ForegroundService.class));
        }*/

        //双进程守护
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.O){
            startService(new Intent(this, LocalService.class));
        }else{
            startForegroundService(new Intent(this, LocalService.class));
        }
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.O){
            startService(new Intent(this, RemotService.class));
        }else{
            startForegroundService(new Intent(this, RemotService.class));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepManager.getOurInstance().unRegisterKeep(this);
    }
}
