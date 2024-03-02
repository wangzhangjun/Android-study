package com.example.vip_dagger2_demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.vip_dagger2_demo2.di.DaggerMainComponent;
import com.example.vip_dagger2_demo2.di.MainModule;
import com.example.vip_dagger2_demo2.object.A;
import com.example.vip_dagger2_demo2.object.B;
import com.example.vip_dagger2_demo2.object.ObjectForMainModule;
import com.example.vip_dagger2_demo2.object.ObjectForTestSubModule;
import com.example.vip_dagger2_demo2.object.User;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import dagger.Lazy;

public class MainActivity extends AppCompatActivity {

    @Inject
    ObjectForMainModule objectForMainModule;
    @Inject
    ObjectForTestSubModule objectForTestSubModule;

    @Named("key1")
    @Inject
    User user1;

    @Named("key2")  // 注入的时候按标签
    @Inject
    User user2;

    @Inject
    A a;
    @Inject
    B b;

    //以下两种都是懒加载使用
    @Inject
    Lazy<A> lazy; //单例
    @Inject
    Provider<A> provider; //不是单例


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainComponent.builder()
                .mainModule(new MainModule("jett","123"))   // 传入参数，带参数的注入
                .build()
                .getTestSubComponent()   // 子component
                .injectMainActivity(this);

        Log.i("jett",objectForMainModule.hashCode()+"");
        Log.i("jett",objectForTestSubModule.hashCode()+"");
        Log.i("jett",user1.pwd+"");
        Log.i("jett",user2.pwd+"");
        Log.i("jett", a.hashCode()+"");
        Log.i("jett",b.hashCode()+"");

        //在调用get的时候，才拿到注入的对象，
        Log.i("jett",lazy.get().hashCode()+"");
        Log.i("jett",lazy.get().hashCode()+"");
        Log.i("jett",provider.get().hashCode()+"");
        Log.i("jett",provider.get().hashCode()+"");
    }
}










