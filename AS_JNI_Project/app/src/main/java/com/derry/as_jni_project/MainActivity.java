package com.derry.as_jni_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

// 生成头文件：javah com.derry.as_jni_project.MainActivity
public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("native-lib");
    }
    public static final int A = 100;
    public String name = "Derry"; // 签名：Ljava/lang/String;
    public static int age = 29; // 签名：I

    // Java 本地方法  实现：native层
    public native String getStringPwd();
    public static native String getStringPwd2();

    // -------------  交互操作 JNI
    public native void changeName();
    public static native void changeAge();
    public native void callAddMethod();

    // 专门写一个函数，给native成调用
    public int add(int number1, int number2) {
        return number1 + number2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        changeName();
        tv.setText(name);
        changeAge();
        tv.setText("" + age);
        callAddMethod();
    }

}

/*
    签名规则：
    Java的boolean  --- Z  注意点
    Java的byte  --- B
    Java的char  --- C
    Java的short  --- S
    Java的int  --- I
    Java的long  --- J     注意点
    Java的float  --- F
    Java的double  --- D
    Java的void  --- V
    Java的引用类型  --- Lxxx/xxx/xx/类名;
    Java的String  --- Ljava/lang/String;
    Java的array  int[]  --- [I
            double[][]  --- [[D
    int add(char c1, char c2) ---- (CC)I
    void a()     ===  ()V

    用命令查看签名：
    AS_JNI_Project/app/build/intermediates/javac/debug/classes/com/derry/as_jni_project
    javap -s -p xxx.class    -s 输出xxxx.class的所有属性和方法的签名，-p 忽略私有公开的所有属性方法全部输出
 */