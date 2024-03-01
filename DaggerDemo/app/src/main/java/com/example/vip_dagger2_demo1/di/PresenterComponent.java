package com.example.vip_dagger2_demo1.di;

import com.example.vip_dagger2_demo1.scope.AppScope;

import dagger.Component;

@Component(modules = {PresenterModule.class})
@AppScope
public interface PresenterComponent {  // 拼接到Mycomponent作为一个component注入
    Presenter providerPresenter();  // 这个返回值必须是和PresenterModule的返回值是一致的。
}
