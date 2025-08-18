package com.example.binderdemo_servicemanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.HashMap;
import com.example.servicemanager.demo.IServiceManager;

/*
* ServiceManager的原理。
* 因为我们在binder通信的时候：
* 一种是用bindService()的形式。
* 另一种可以通过ServiceManager.getService()的形式。
* */
public class MyService extends Service {
    private IBinder mServiceManager;
    @Override
    public void onCreate() {
        super.onCreate();
        mServiceManager = new SerivceManager();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mServiceManager;
    }

    public class SerivceManager extends IServiceManager.Stub {

        // 本质的核心原理就是：通过map保存了各种binder对象。
        private HashMap<String, IBinder> mServiceList = new HashMap<>();
        @Override
        public void addService(String name, IBinder service) throws RemoteException {
            Log.v("zhjwang", "addServie " + " name = " + name + " service = " + service);
            mServiceList.put(name, service);
        }
        @Override
        public IBinder getService(String name) throws RemoteException {
            Log.v("zhjwang", "getService " + " name = " + name);
            return mServiceList.get(name);
        }
    }
}
