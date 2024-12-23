package com.example.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        Button startNormalActivity = (Button) findViewById(R.id.start_normal_activity);
        Button startDialogActivity = (Button) findViewById(R.id.start_dialog_activity);
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
}
