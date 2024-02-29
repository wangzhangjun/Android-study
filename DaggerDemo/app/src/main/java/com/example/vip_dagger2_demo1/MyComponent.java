package com.example.vip_dagger2_demo1;

import javax.inject.Singleton;

import dagger.Component;
/**
 * 第2：存放module的组件，这个是个interface
 */
@Singleton
@Component(modules = {HttpModule.class,DatabaseModule.class})
public interface MyComponent {
    //注入的位置就写在参数上，不能用多态，是注到哪就是哪
    void injectMainActivity(MainActivity mainActivity);  // 注入到mainActivity上
    void injectSecActivity(SecActivity secActivity);
}
