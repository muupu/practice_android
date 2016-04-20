package com.example.bluetoothtether;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MainActivity extends Activity {

    private Switch mSwitch = null;

    private static final String sClassName = "android.bluetooth.BluetoothPan";
    private BluetoothAdapter mBluetoothAdapter = null;
    private Class<?> mBluetoothPanClass = null;
    private Constructor<?> mBTPanConstructor = null;
    private Object mBTServiceInstance = null;
    private Method mIsBTTetheringOnMethod;
    private boolean mIsBTTetheringOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBluetoothTether();
//        checkBTTetheringOn();
        mSwitch = (Switch)findViewById(R.id.bluetooth_switch);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if (!mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.enable(); // 打开蓝牙
                    }
                    Thread.sleep(100); // 等待蓝牙打开
                    startBluetoothTether();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void initBluetoothTether() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Class noparams[] = {};
        try {
            mBluetoothPanClass = Class.forName("android.bluetooth.BluetoothPan");
            mIsBTTetheringOnMethod = mBluetoothPanClass.getDeclaredMethod("isTetheringOn", noparams);
            mBTPanConstructor = mBluetoothPanClass.getDeclaredConstructor(Context.class, BluetoothProfile.ServiceListener.class);
            mBTPanConstructor.setAccessible(true);
            Thread.sleep(250);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkBTTetheringOn() {
        Context MyContext = getApplicationContext();
        try {
            Object BTServiceInstance = mBTPanConstructor.newInstance(MyContext, new BluetoothProfile.ServiceListener() {
                @Override
                public void onServiceConnected(int profile, BluetoothProfile proxy) {
                    try {
                        mIsBTTetheringOn = ((Boolean) proxy.getClass().getMethod("isTetheringOn", new Class[0]).invoke(proxy, new Object[0])).booleanValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onServiceDisconnected(int profile) {
                }
            });
//            mIsBTTetheringOn = (boolean) mIsBTTetheringOnMethod.invoke(BTServiceInstance, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startBluetoothTether() {
        Context MyContext = getApplicationContext();
        try {
            mBTServiceInstance = mBTPanConstructor.newInstance(MyContext, new BTPanServiceListener(MyContext));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
