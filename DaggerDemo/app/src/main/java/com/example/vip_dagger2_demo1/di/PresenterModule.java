package com.example.vip_dagger2_demo1.di;

import com.example.vip_dagger2_demo1.scope.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
@AppScope
public class PresenterModule {
    @Provides
    @AppScope
    public Presenter providerPresenter() {
        return new Presenter();
    }
}
