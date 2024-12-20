package com.example.testactivity;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button  = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "clicked",
                Toast.LENGTH_SHORT).show();
                // Intent的第一种用法:启动另一个Activity
                // 注意这里启动的时候总是失败,要去看xml中的Theme要修改
                // Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // startActivity(intent);

                /*
                 Intent的第二种用法:也是启动另一个Activity
                 不过这个要和mainifest.xml中的保持一致,START 应该是专用的命令
                 注意:要同时满足action和category的条件才能启动.但是category是default的话,就不用指定
                 通过addCategory()方法添加一个Category来和manifest.xml中的Category进行匹配
                */
                //Intent intent = new Intent("com.example.testactivity.ACTION START");
                //intent.addCategory("com.example.testactivity.MY_CATEGORY");
                //startActivity(intent);

                // Intent的第三种用法:启动其他的非自己创建的Activity
                // 比如下面的例子是启动浏览器并打开百度
                // Intent intent = new Intent(Intent.ACTION_VIEW);// 内置的动作
                // intent.setData(Uri.parse("http://www.baidu.com"));
                // startActivity(intent);

                // Intent的第四种用法:向下一个Activity传递数据
                //Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //intent.putExtra("extra_data", "Hello SecondActivity");
                //startActivity(intent);

                // Intent的第五种用法: 返回一个数据给上一个Activity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // 这个函数也可以启动一个activity,但是等被启动的activity结束的时候,会返回给当前activity一个结果
                startActivityForResult(intent, 1);
            }
        });
    }

    // 在SecondActivity中调用finish()方法的时候,会回调这个方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            String returnedData = data.getStringExtra("data_return");
            Toast.makeText(MainActivity.this, returnedData, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_item) {
            Toast.makeText(this, "click add", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.remove_item){
            Toast.makeText(this, "click remove", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}