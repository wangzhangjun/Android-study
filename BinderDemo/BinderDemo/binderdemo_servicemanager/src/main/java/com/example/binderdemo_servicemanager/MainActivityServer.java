package com.example.binderdemo_servicemanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.servicemanager.demo.IServiceManager;
import com.example.servicemanager.demo.ICalculator;

public class MainActivityServer extends AppCompatActivity {

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
    private void init() {
        Intent intent = new Intent("com.example.binderdemo.BIND_SM");
        intent.setPackage("com.example.binderdemo_servicemanager");

        this.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // 先拿到ServiceManager
                IServiceManager serviceManager = IServiceManager.Stub.asInterface(service);
                Log.v("zhjwang", "service->" + service);
                try {
                    /*
                    * 所以说本质上是通过ServiceManager的binder通道把另一个可以对外提供的
                    * binder对象保存来起来，所以我们才可以使用getService("cal")来获取
                    * 这个对象。
                    * */
                    serviceManager.addService("cal", new Calulator());
                } catch (Exception e) {

                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    public class Calulator extends ICalculator.Stub {
        @Override
        public int add(int a, int b) throws RemoteException {
            Log.v("Kobe", "a = " + a + " b = " + b + " function = add");
            return a + b;
        }

        @Override
        public int sub(int a, int b) throws RemoteException {
            Log.v("Kobe", "a = " + a + " b = " + b + " function = sub");
            return a - b;
        }
    }
}