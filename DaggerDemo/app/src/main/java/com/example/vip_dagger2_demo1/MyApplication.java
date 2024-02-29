package com.example.vip_dagger2_demo1;
import android.app.Application;
public class MyApplication extends Application {
    private MyComponent myComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        // 全局单例
        myComponent=DaggerMyComponent.builder()
                .httpModule(new HttpModule())
                .databaseModule(new DatabaseModule())
                .build();
    }

    public MyComponent getMyComponent() {
        return myComponent;
    }
}
