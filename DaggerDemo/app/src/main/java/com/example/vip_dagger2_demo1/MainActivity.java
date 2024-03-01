package com.example.vip_dagger2_demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.vip_dagger2_demo1.di.Presenter;
import com.example.vip_dagger2_demo1.object.DatabaseObject;
import com.example.vip_dagger2_demo1.object.HttpObject;

import javax.inject.Inject;
/*
*
* 使用上一共6步：
* 1.提供用于注入的对象。
* 2.编写module
* 3.编写component
* 4.注入到activity
* 5.rebuild让APT生成需要的文件
* 6.在需要的注入类中使用
* */
public class MainActivity extends AppCompatActivity {
    // 第3：注入
    @Inject
    HttpObject httpObject;
    /*@Inject 执行的就是下面的三步，框架这么做的
     * httpObjectProvider.get();
    //= module.providerHttpObject()
    //= new HttpObject(); 
    */
    @Inject
    HttpObject httpObject2;
    @Inject
    DatabaseObject databaseObject;

    @Inject
    DatabaseObject databaseObject2;

    @Inject
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 这个DaggerMyComponent是生成的，所有在使用的时候，一定要先Build一次。
        // 这个就是注入.这个好处就是:再别的地方使用，就加一句话，就注入了，就可以使用这个对象了
//        DaggerMyComponent.create().injectMainActivity(this);

        // 和上面一句话调用效果是一样的
//        DaggerMyComponent.builder()
//                .httpModule(new HttpModule())
//                .databaseModule(new DatabaseModule())
//                .build() //上面的代码初始化了module和component
//                .injectMainActivity(this);

        //全局单例，和sec中的全局单例进行对比
        ((MyApplication)getApplication()).getMyComponent()
                .injectMainActivity(this);

        Log.i("jett-main-httpobject",httpObject.hashCode()+""); // 当HttpModule成为signalton的时候，这两个hashcode是一样的
        Log.i("jett-main-httpobject",httpObject2.hashCode()+"");

        Log.i("jett-main-database",databaseObject.hashCode()+""); // 不是singaltton，不一样
        Log.i("jett-main-database",databaseObject2.hashCode()+"");

        Log.i("jett-main-present",presenter.hashCode()+"-presenter");
        Log.i("jett1-quanju",httpObject.hashCode()+"");
    }
    public void click(View view) {
        startActivity(new Intent(this, SecActivity.class));
    }
}


















