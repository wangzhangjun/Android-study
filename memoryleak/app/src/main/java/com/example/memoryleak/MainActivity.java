package com.example.memoryleak;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private static final List<Object> leakedObjects = new ArrayList<>();
    private Handler handler = new Handler();
    private Runnable memoryInfoUpdater;
    private TextView tvMemoryInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvMemoryInfo = findViewById(R.id.tvMemoryInfo);
        button = findViewById(R.id.button_1);
        button.setOnClickListener(v -> {
            // Do something
            createMemoryLeak();
            updateMemoryInfo();
        });

        // 定期更新内存信息
        memoryInfoUpdater = new Runnable() {
            @Override
            public void run() {
                updateMemoryInfo();
                handler.postDelayed(this, 1000); // 每秒更新一次
            }
        };
        handler.post(memoryInfoUpdater);
    }

    private void createMemoryLeak() {
        // 创建一个1MB的数组
        Object[] largeArray = new Object[262144]; // 约1MB
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = new Object(); // 填充对象，确保内存被实际分配
        }

        // 创建内存泄漏对象
        LeakedClass leakedInstance = new LeakedClass(largeArray);
        // 将实例添加到静态列表中
        leakedObjects.add(largeArray);
    }

    private void updateMemoryInfo() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        long maxMemory = runtime.maxMemory() / 1024 / 1024;

        String memoryInfo = String.format(Locale.getDefault(),
                "已用内存: %dMB\n总内存: %dMB\n最大内存: %dMB\n泄漏对象数: %d",
                usedMemory, totalMemory, maxMemory, leakedObjects.size());

        tvMemoryInfo.setText(memoryInfo);
    }

    // 内部类，持有外部Activity的引用
    private class LeakedClass {
        private final Object[] data;
        private final Context context;

        public LeakedClass(Object[] data) {
            this.data = data;
            this.context = MainActivity.this;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(memoryInfoUpdater);
    }
}
