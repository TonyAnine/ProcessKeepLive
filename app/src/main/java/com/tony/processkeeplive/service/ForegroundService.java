package com.tony.processkeeplive.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.tony.processkeeplive.MainActivity;
import com.tony.processkeeplive.R;

/**
 * Created by TonyYao on 2019/3/18.
 */
public class ForegroundService extends Service {
    private static final int SERVICE_ID = 1;

    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    private class LocalBinder extends Binder{

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.JELLY_BEAN_MR2){
            //4.3
            startForeground(SERVICE_ID,new Notification());
        }else if(Build.VERSION.SDK_INT<Build.VERSION_CODES.O){
            //7.0
            startForeground(SERVICE_ID,new Notification());
            //删除通知栏消息
            startService(new Intent(this,InnerService.class));
        }else{
            //8.0及以上
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                NotificationChannel mNotificationChannel = new NotificationChannel("channel_id",
                        getString(R.string.app_name), NotificationManager.IMPORTANCE_MIN);
                mNotificationChannel.setShowBadge(false);
                // 设置通知出现时不震动
                mNotificationChannel.enableVibration(false);
                mNotificationChannel.setVibrationPattern(new long[]{0});

                notificationManager.createNotificationChannel(mNotificationChannel);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id");
                //构建一个Intent
                Intent resultIntent = new Intent(this, MainActivity.class);
                resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //封装一个Intent
                PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setContentIntent(resultPendingIntent);
                builder.setContentTitle("Is Living");
                //builder.setContentText(getString(R.string.disable_notifi));
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setColor(getResources().getColor(R.color.colorPrimary));
                startForeground(SERVICE_ID, builder.build());
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public static class InnerService extends Service{

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(SERVICE_ID,new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }
}
