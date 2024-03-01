package com.example.vip_dagger2_demo1;
import android.app.Application;

import com.example.vip_dagger2_demo1.di.DaggerPresenterComponent;

public class MyApplication extends Application {
    private MyComponent myComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        // 全局单例
        myComponent=DaggerMyComponent.builder()
                .httpModule(new HttpModule())
                .databaseModule(new DatabaseModule())
                .presenterComponent(DaggerPresenterComponent.create())  // new 出来这个子component
                .build();
    }

    public MyComponent getMyComponent() {
        return myComponent;
    }
}
