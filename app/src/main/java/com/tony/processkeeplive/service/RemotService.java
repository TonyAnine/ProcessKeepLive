package com.tony.processkeeplive.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by TonyYao on 2019/3/18.
 */
public class RemotService extends ForegroundService {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(RemotService.this,LocalService.class), mConnection, Service.BIND_IMPORTANT);

        return super.onStartCommand(intent, flags, startId);

    }

    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //断开时
            startService(new Intent(RemotService.this,LocalService.class));
            bindService(new Intent(RemotService.this,LocalService.class), mConnection,Service.BIND_IMPORTANT);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }
}
