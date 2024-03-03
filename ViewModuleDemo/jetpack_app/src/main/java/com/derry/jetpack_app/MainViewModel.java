package com.derry.jetpack_app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends /*ViewModel*/ AndroidViewModel {

    // 传统方式的 数据
    // private String phoneInfo = "";  // 目前无法实现感应功能

    // 实现感应功能  LiveData + DataBinding
    private MutableLiveData<String> phoneInfo;

    // 环境
    private Context context;

    public MainViewModel(Application application) {
        super(application);
        context = application;
    }

    // 把数据暴露出去 给布局用
    public MutableLiveData<String> getPhoneInfo() {
        if (phoneInfo == null) {
            phoneInfo = new MutableLiveData<>();

            // 设置默认值
            phoneInfo.setValue("");
        }
        return phoneInfo;
    }


    /**
     * 输入
     *
     * @param number
     */
    public void appendNumber(String number) {
        phoneInfo.setValue(phoneInfo.getValue() + number);
    }

    /**
     * 删除
     */
    public void backspaceNumber() {
        int length = phoneInfo.getValue().length();
        if (length > 0) {
            phoneInfo.setValue(phoneInfo.getValue().substring(0, phoneInfo.getValue().length() - 1));

        }
    }

    /**
     * 清空
     */
    public void clear() {
        phoneInfo.setValue("");
    }

    /**
     * 拨打
     */
    public void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneInfo.getValue()));

        // 非Activity启动拨号 或者是 非Activity启动任何的 startActivity 都会奔溃
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }

    // 例如：9000行代码，代码越多 问题越大
    // ...

}
