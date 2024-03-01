package com.example.vip_dagger2_demo1;

import com.example.vip_dagger2_demo1.object.HttpObject;
import com.example.vip_dagger2_demo1.scope.UserScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/**
 * 第1：用来提供对象。
 */
@UserScope // 下面写了单例，这里必须写，这里写了Component也要写
@Module
public class HttpModule {
    @UserScope // 假设需要提供的是一个单例，可以加这个注解
    @Provides  // 提供对象
    public HttpObject providerHttpObject(){
        //........
        return new HttpObject();
    }
}
