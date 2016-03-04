package com.test.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.graphics.BitmapFactory;

/**
 * Created by qiaoda.zqd on 2016/3/3.
 */
public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {

        public void startDownload() {
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Notification notification = new Notification(R.drawable.apple_pic,
//                "Notification comes", System.currentTimeMillis());
        Notification.Builder builder = new Notification.Builder(this);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.apple_pic) //设置状态栏里面的图标（小图标）
//                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.apple_pic)) //设置状态栏里面的图标（大图标）
                .setTicker("TickerText!") //设置状态栏的显示的信息
                .setWhen(System.currentTimeMillis()) //设置时间发生时间
                .setAutoCancel(true) //设置可以清除
                .setContentTitle("This is ContentTitle")//设置下拉列表里的标题
                .setContentText("this is ContentText");//设置上下文内容
        Notification notification = builder.getNotification();
        startForeground(1, notification);
        Log.d("MyService", "onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MySercie", "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy executed");
    }
}
