package com.example.bluetoothtether;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MainActivity extends Activity {

    private Switch mSwitch = null;

    private static final String sClassName = "android.bluetooth.BluetoothPan";
    private BluetoothAdapter mBluetoothAdapter = null;
    Class<?> mBluetoothPanClass = null;
    Constructor<?> mBTPanConstructor = null;
    Object mBTServiceInstance = null;
    Method mIsBTTetheringOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        initBluetoothTether();
    }

    public void initBluetoothTether() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Class noparams[] = {};
        try {
            mBluetoothPanClass = Class.forName("android.bluetooth.BluetoothPan");
            mIsBTTetheringOn = mBluetoothPanClass.getDeclaredMethod("isTetheringOn", noparams);
            mBTPanConstructor = mBluetoothPanClass.getDeclaredConstructor(Context.class, BluetoothProfile.ServiceListener.class);
            mBTPanConstructor.setAccessible(true);
            Thread.sleep(250);
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
