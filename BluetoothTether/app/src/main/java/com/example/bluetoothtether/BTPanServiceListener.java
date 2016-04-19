package com.example.bluetoothtether;

import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by qiaoda.zqd on 2016/4/19.
 */
public class BTPanServiceListener implements BluetoothProfile.ServiceListener {
    private final Context context;
    public static boolean state = false;

    public BTPanServiceListener(final Context context) {
        this.context = context;
    }

    @Override
    public void onServiceConnected(final int profile,
                                   final BluetoothProfile proxy) {
        try {
            boolean isTetheringOn = ((Boolean) proxy.getClass().getMethod("isTetheringOn", new Class[0]).invoke(proxy, new Object[0])).booleanValue();
            if (isTetheringOn) {
                proxy.getClass().getMethod("setBluetoothTethering", new Class[]{Boolean.TYPE}).invoke(proxy, new Object[]{Boolean.valueOf(false)});
                Toast.makeText(context, "成功关闭！", Toast.LENGTH_SHORT).show();
                state = false;
            } else {
                proxy.getClass().getMethod("setBluetoothTethering", new Class[]{Boolean.TYPE}).invoke(proxy, new Object[]{Boolean.valueOf(true)});
                Toast.makeText(context, "成功打开！", Toast.LENGTH_SHORT).show();
                state = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(final int profile) {
    }
}
