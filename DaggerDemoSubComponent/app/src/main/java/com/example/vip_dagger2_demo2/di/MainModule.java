package com.example.vip_dagger2_demo2.di;



import com.example.vip_dagger2_demo2.object.A;
import com.example.vip_dagger2_demo2.object.B;
import com.example.vip_dagger2_demo2.object.ObjectForMainModule;
import com.example.vip_dagger2_demo2.object.User;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    public String name;
    public String pwd;

    public MainModule(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Provides
    public ObjectForMainModule providerObjectForMainModule(){
        return new ObjectForMainModule();
    }


    @Provides
    public B providerB(){
        return new B();
    }
    @Provides
    public A providerA(B b){
        return new A(b);
    }

    @Named("key1")   // 用Named做为限定符，在注入的时候，可以选择根据注解来表明注入的是哪个
    @Provides
    public User providerUser(){
        return new User(name,pwd);
    }
    @Named("key2")
    @Provides
    public User providerUser2(){
        return new User("jett","456");
    }

}














