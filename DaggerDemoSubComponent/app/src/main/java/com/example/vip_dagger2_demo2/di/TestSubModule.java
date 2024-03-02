package com.example.vip_dagger2_demo2.di;



import com.example.vip_dagger2_demo2.object.ObjectForTestSubModule;

import dagger.Module;
import dagger.Provides;

@Module
public class TestSubModule {
    @Provides
    public ObjectForTestSubModule providerObjectForTestSubModule(){
        return new ObjectForTestSubModule();
    }
}
