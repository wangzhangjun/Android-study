package com.example.binder_theory2;

import android.os.Parcel;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*
* aidl自动做的事情，就相当于是把下面的代码自动生成了。
* 本质都是对onTransact和transact的封装
* */
public interface ICalculator {
    // 多态
    public int add(int a, int b) throws android.os.RemoteException;
    public int sub(int a, int b) throws android.os.RemoteException;

    // 这里abstract是抽象类，可以不实现add和sub，由它的子类去实现
    public static abstract class Stub extends android.os.Binder implements ICalculator {
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                int a = data.readInt();
                int b = data.readInt();
                int x = add(a, b); // 这个onTransact是在Myservice的代码中被调用，所以add就调用到了Myservice中的add方法
                reply.writeInt(x);
                return true;
            } else if (code == 2) {
                int a = data.readInt();
                int b = data.readInt();
                int y = sub(a, b);
                reply.writeInt(y);
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }
    }

    // 必须是static的，因为static的内部类不依赖外部类的实现。
    // 而非static得，依赖外部类的new对象，而interface是不能new的。
    public static class Proxy implements ICalculator {
        private android.os.IBinder mRemote;

        public Proxy(android.os.IBinder remote) {
            mRemote = remote;
        }

        @Override
        public int add(int a, int b) throws android.os.RemoteException {
            Parcel data1 = Parcel.obtain();
            data1.writeInt(a);
            data1.writeInt(b);
            Parcel reply1 = Parcel.obtain();
            // 因为mRemote是MyService，所以会调用MyService的onTransact，也就是上面被继承的Stub中的onTransact方法，被myService继承进去了
            mRemote.transact(1, data1, reply1, 0);
            int x = reply1.readInt();
            return x;
        }

        @Override
        public int sub(int a, int b) throws android.os.RemoteException {
            Parcel data1 = Parcel.obtain();
            data1.writeInt(a);
            data1.writeInt(b);
            Parcel reply1 = Parcel.obtain();
            mRemote.transact(2, data1, reply1, 0);
            int y = reply1.readInt();
            return y;
        }
    }
}
