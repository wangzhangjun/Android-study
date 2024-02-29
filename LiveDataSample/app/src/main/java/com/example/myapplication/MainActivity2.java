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

        /*
         * 这个是验证循环发送
         * 第一个observe接受到1之后，重新发送2
         * 这个时候所有的都会收到2
         * */
//        liveData2 = new MutableLiveData<>();
//
//        liveData2.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                Log.d("jett", "changed1 :" + s);
//                if (s.equals("1")) {
//                    liveData2.setValue("2");
//                }
//            }
//        });
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
