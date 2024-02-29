package com.example.vip_dagger2_demo1;

import com.example.vip_dagger2_demo1.object.DatabaseObject;
import com.example.vip_dagger2_demo1.object.HttpObject;

import dagger.Module;
import dagger.Provides;

/**
 * 用来提供对象
 */
@Module
public class DatabaseModule {

    @Provides
    public DatabaseObject providerDatabaseObject(){
        //........
        return new DatabaseObject();
    }

}
