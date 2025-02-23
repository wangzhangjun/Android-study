package com.example.activitylifecycle;

import android.app.Application;
import android.util.Log;

// 必须在Manifest中通过 android:name去指定才能生效
public class MainApplication extends Application {
    public static final String TAG = "MainApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MainApplication onCreate");
    }
}
