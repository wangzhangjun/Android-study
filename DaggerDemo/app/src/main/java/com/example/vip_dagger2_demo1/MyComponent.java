package com.example.vip_dagger2_demo1;

import com.example.vip_dagger2_demo1.di.PresenterComponent;
import com.example.vip_dagger2_demo1.scope.UserScope;

import javax.inject.Singleton;

import dagger.Component;
/**
 * 第2：存放module的组件，这个是个interface
 */
@UserScope
@Component(modules = {HttpModule.class,DatabaseModule.class}
        ,dependencies = {PresenterComponent.class}) // 出现多个component的时候，必须拼接多个子component成为一个，要不会出现编译错误
public interface MyComponent {
    //注入的位置就写在参数上，不能用多态，是注到哪就是哪
    void injectMainActivity(MainActivity mainActivity);  // 注入到mainActivity上
    void injectSecActivity(SecActivity secActivity);
}
