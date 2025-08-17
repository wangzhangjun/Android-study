package com.example.binderdemo1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.example.servicedemo.ICalculator;

public class MyService extends Service {

    private IBinder calulator;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        calulator = new Calulator();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return calulator;
    }

    public class Calulator extends ICalculator.Stub {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public int sub(int a, int b) throws RemoteException {
            return a-b;
        }
    }
}
