package io.cmichel.boilerplate;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.content.BroadcastReceiver;
import android.os.BatteryManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.HashMap;
import java.util.Map;

public class Module extends ReactContextBaseJavaModule {

  private static final String DURATION_SHORT_KEY = "SHORT";
  private static final String DURATION_LONG_KEY = "LONG";

  public Module(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "ReactBattery";
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
    constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
    return constants;
  }

  @ReactMethod
  public void show(String message, int duration) {
    Toast.makeText(getReactApplicationContext(), message, duration).show();
  }

  @ReactMethod
  public void isCharging(Callback successCallback) {
    // int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);



    IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    Intent batteryStatus = getReactApplicationContext().registerReceiver(null, ifilter);

    // // Are we charging / charged?
    // int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
    // boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
    //                      status == BatteryManager.BATTERY_STATUS_FULL;

    // How are we charging?
    int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
    //boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
    //boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

    successCallback.invoke(chargePlug == BatteryManager.BATTERY_PLUGGED_USB);
  }

}
