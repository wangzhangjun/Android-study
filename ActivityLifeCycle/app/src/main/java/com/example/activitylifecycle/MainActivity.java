package com.example.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activitylifecycle.fragment.FragmentActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null) {
            String tempData = savedInstanceState.getString("data_key");
            Log.d(TAG, tempData);
        }
        Button startNormalActivity = (Button) findViewById(R.id.start_normal_activity);
        Button startDialogActivity = (Button) findViewById(R.id.start_dialog_activity);
        Button startFragmentActivity = (Button)findViewById(R.id.start_fragment_activity);

        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });
        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
        startFragmentActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    // 当activity变为和用户可交互的时候,调用onResume,一般位于栈顶
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    // 当前的activity被其他activity覆盖时,会调用onPause
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    // 完全变为不可见的时候, 会调用onStop, 在这之前一般要调用onPause. 但是比如你的下一个是dialog的, 就不会调用onStop, 只会调用onPause
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    // 当activity被销毁时, 会调用onDestroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    // 由停止变为运行时, 会调用onRestart, 调用了stop, 再次启动就会调用restart
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    /*
    * 当这个activity被回收的时候，比如我们已经在一些输入框中保存了一些数据，为了避免用户再次进来又得重新填一次，可以保存起来。
    * 然后在 onCreate 的时候去读取Bundle, 在onCreate的时候都会传递Bundle进来，当在onSaveInstanceState存储的时候，onCreate的这个值就不为空。
    *
    * 这里Bundle和Intent一起来联合使用，比如可以把需要传递的数据保存到Bundle对象中，然后将Bundle对象保存到Intent中，到了目标的activity之后，先从Intent中取出来Bundle，然后把Bundle中的数据
    * 取出来
    * */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        String tempData = "something you just typed";
        outPersistentState.putString("data_key", tempData);
    }
}
