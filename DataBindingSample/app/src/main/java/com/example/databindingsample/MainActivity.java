package com.example.databindingsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import android.os.Bundle;

import com.example.databindingsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;  // 这个好像要先编一下，才会出现
    //从model层来的数据
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这一行就是完成XML布局的初始化
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //数据是从网络或是数据库拿来的
        user = new User("zhjwang","123");
        binding.setUser(user);
//        binding.setVariable(BR.name,"jett"); // 这个的作用是什么，这个注释了，好像也没有什么变化
        // 开一个线程，不断的设置这个值，你会发现UI一只在自动的变，这个就是优势
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        user.setName(user.getName()+"1");
                        user.setPwd(user.getPwd()+"2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}