package com.example.usbdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 参考资料：http://www.open-open.com/lib/view/open1418868231839.html
 */
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startListen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastRec);
    }

    public void startListen()
    {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.setPriority(1000);
        intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);
        intentFilter.addAction(Intent.ACTION_MEDIA_SHARED);
        intentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
        intentFilter.addAction(Intent.ACTION_MEDIA_CHECKING);
        intentFilter.addAction(Intent.ACTION_MEDIA_EJECT);
        intentFilter.addAction(Intent.ACTION_MEDIA_NOFS);
        intentFilter.addAction(Intent.ACTION_MEDIA_BUTTON);
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentFilter.addDataScheme("file");
        registerReceiver(broadcastRec, intentFilter);
    }

    private final BroadcastReceiver broadcastRec = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        writeFileLog("MediaAction=" + action);
        Toast.makeText(getApplicationContext(), "MediaAction=" + action, Toast.LENGTH_LONG).show();
        if (action.equals("android.intent.action.MEDIA_MOUNTED"))
        {

        } else if (action.equals(Intent.ACTION_MEDIA_UNMOUNTED)) {

        }else if (action.equals(Intent.ACTION_MEDIA_SCANNER_STARTED)){

        }else if (action.equals(Intent.ACTION_MEDIA_SCANNER_FINISHED)){

        }else if (action.equals(Intent.ACTION_MEDIA_SHARED)){

        }else {
        }
        }
    };

    private static void writeFileLog(String str) {
        String logDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File logFile = new File(logDir);
        if (!logFile.exists()) {
            logFile.mkdirs();
        }
        FileOutputStream trace = null;
        try {
            File file = new File(logDir + "/linklog.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            trace = new FileOutputStream(file, true);
            long timeMillis = System.currentTimeMillis();
            String log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timeMillis)) + ":" + str;
            trace.write((log + "\r\n").getBytes());
            trace.flush();
        } catch (Exception e) {
            e.toString();
        } finally {
            if (trace != null) {
                try {
                    trace.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
