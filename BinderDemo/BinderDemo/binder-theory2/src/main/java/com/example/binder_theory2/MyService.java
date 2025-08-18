package com.example.binder_theory2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private IBinder mCalculator;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mCalculator;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCalculator = new Calulator();
    }

    //服务端
    public class Calulator extends ICalculator.Stub {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }


        @Override
        public int sub(int a, int b) throws RemoteException {
            return a - b;
        }
    }
}
