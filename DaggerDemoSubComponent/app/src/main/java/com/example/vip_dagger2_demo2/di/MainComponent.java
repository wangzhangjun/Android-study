package com.example.vip_dagger2_demo2.di;

import dagger.Component;

@Component(modules = {MainModule.class})
public interface MainComponent {
    //只要返回子组件就可以
    TestSubComponent getTestSubComponent();  // 这是子组件的component
}
