package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.util.Log;
import android.view.View;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    * 这个就是最简单的livedata的使用场景：
    * 1. new MutableLiveData
    * 2. 订阅：livedata.observe
    * 3. 发消息：setValue/postValue
    * */
    public static MutableLiveData liveData1;
    public static NonStickyMutableLiveData liveData2;  // 测试粘性，先发消息，在activity2页面中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liveData1 = new MutableLiveData();
        liveData2 = new NonStickyMutableLiveData();

        liveData1.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.i("zhjwang","收到了数据1："+s);
            }
        });
    }

    public void mainClick(View view) {
        liveData1.setValue("mainClick");
        liveData2.setValue("Sticky");
    }

    // 在子线程中用postValue法消息，订阅者也是可以接收到的
    public void threadClick(View view) {
        new Thread() {
            @Override
            public void run() {
                liveData1.postValue("threadClick");  // 子线程中
            }
        }.start();
    }

    // 跳到第二个页面
    public void newActivityClick(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}