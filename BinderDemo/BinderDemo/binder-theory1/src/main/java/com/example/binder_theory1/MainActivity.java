package com.example.binder_theory1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    /*
    * 这个demo是为了说明，binder的通信都是基于transact 和 onTransact的封装。
    * client端调用transact，会调用到server端的onTransact.
    *
    * 那么binderdemo1中的，也就是我们正常使用的例子是做了什么样的封装呢，来看binder-theory2,手写proxy和stub
    * */
    private  void init(){
        Intent intent = new Intent(this, MyService.class);
        this.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IBinder calculator = service;
                Log.v("zhjwang", "service->" + service);
                try {
                    int a = 3;
                    int b = 4;
                    Parcel data1 = Parcel.obtain();
                    data1.writeInt(a);
                    data1.writeInt(b);
                    Parcel reply1 = Parcel.obtain();
                    service.transact(1, data1, reply1, 0);
                    int x = reply1.readInt();
                    Log.v("zhjwang", a + " + " + b + " = " + x);


                    Parcel data2 = Parcel.obtain();
                    data2.writeInt(a);
                    data2.writeInt(b);
                    Parcel reply2 = Parcel.obtain();
                    service.transact(2, data2, reply2, 0);
                    int y = reply2.readInt();
                    Log.v("zhjwang", a + " - " + b + " = " + y);
                } catch (Exception e) {

                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }
}