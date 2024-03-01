package com.example.vip_dagger2_demo1;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vip_dagger2_demo1.di.DaggerPresenterComponent;
import com.example.vip_dagger2_demo1.di.Presenter;
import com.example.vip_dagger2_demo1.object.HttpObject;
import javax.inject.Inject;

public class SecActivity extends AppCompatActivity {
    @Inject
    HttpObject httpObject;

    @Inject
    Presenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        DaggerMyComponent.builder()
                .httpModule(new HttpModule())
                .databaseModule(new DatabaseModule())
                .presenterComponent(DaggerPresenterComponent.create())
                .build()
                //上面的代码初始化了module和component
                .injectSecActivity(this);
        /*
        * 这个是为了测试，httpObject我们在前面生成了单例。
          但是到第二个页面再注入进来的HttpObject，hashcode就变了
          * 所以说，我们在module和component中的 @Singleton 本身是一个局部单例，跨activity就不行了
        * */
        Log.i("jett",httpObject.hashCode()+"-sec1");

        /*
         全局单例, 都使用全局单例，才能保证一致。也就是main和sec中都使用全局的方式去注入。
         不能一个使用局部单例，一个使用全局单例。这样看到的也是两个
        */
        ((MyApplication)getApplication()).getMyComponent().injectSecActivity(this);
        Log.i("jett2-quanju",httpObject.hashCode()+"-sec");
        Log.i("jett2-quanju",presenter.hashCode()+"-sec-presenter");
    }
}
