package com.example.myapplication;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /*
            粘性问题：
         * 这个是测试这个activity2还没有show出来的时候，在MainActivity中不断的更新livedata1
         * 多点几次后，这个onChanged打印啥 ?
         * 结果是多点几次，一定得到的是最后一个版本。这里有个mVersion在控制
         * */
        MainActivity.liveData1.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {  // 而且这个是在onStart之后，onResume之前执行
                s+=s; // mainClickmainClick
                Log.i("zhjwang-1", s);
            }
        });

        // 解决粘性问题之后的测试
        MainActivity.liveData2.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                s+=s; // mainClickmainClick
                Log.i("zhjwang-2", s);  // 第一次进来就不会执行了，只有第二次之后再进来才会执行
            }
        });

        // forever 即使activity没有显示，也可以收到消息，当然第一次得启动下这个activity
        MainActivity.liveData1.observeForever(new Observer<String>() {
            @Override
            public void onChanged(String o) {
                Log.i("zhjwang-3-observeForever", o);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("zhjwang","onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("zhjwang","onResume");
    }
}
