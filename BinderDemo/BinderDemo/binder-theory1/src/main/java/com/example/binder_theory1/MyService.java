package com.example.binder_theory1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyService extends Service {

    private IBinder mCalculator; //这个就是binder对象

    @Override
    public void onCreate() {
        super.onCreate();
        mCalculator = new Calulator();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mCalculator;
    }

    public class Calulator extends Binder {
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                int a = data.readInt();
                int b = data.readInt();
                Log.v("zhjwang", "a = " + a + " b = " + b + " function = onTransact" + " code = 1");
                int x = a + b;
                reply.writeInt(x);
                return true;
            } else if (code == 2) {
                int a = data.readInt();
                int b = data.readInt();
                Log.v("zhjwang", "a = " + a + " b = " + b + " function = onTransact" + " code = 2");
                int y = a - b;
                reply.writeInt(y);
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }
    }

}
