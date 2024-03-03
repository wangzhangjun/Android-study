package com.example.viewbindingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.viewbindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding; // 这个是gradle插件自动生成的类ActivityMainBinding，用layout中的名字生成的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.tv1.setText("AAA");  // 设置标签了
    }
}