package com.derry.jetpack_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.derry.jetpack_app.databinding.ActivityMainBinding;

/**
 * TODO 传统方式的模式
 *
 * 功能越多，代码越多，下面的缺点就越大
 *
 * 1.Activity 或 Fragment 是大管家，代码脓肿
 *
 * 2.Activity 要处理逻辑
 *
 * 3.Activity 要处理Model数据  UI 数据， 不仅管理了 却又管不好（横竖屏切换 数据丢失）
 *
 * 4.Activity 要实时刷新UI，例如：更新TextView
 *
 * 5.Activity 如果横竖屏切换 会丢失数据（号码数据一定会丢失的）
 *
 * 6.焊死，不能灵活拆卸
 *
 * ...... 等等
 */

// TODO 重构
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding dataBinding; // DataBinding初始化
    private MainViewModel mainViewModel; // MainViewModel初始化

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // 旧版本的写法
        // viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // 下面都是新版本
        // extends ViewModel
        // mainViewModel = new ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

        // extends AndroidViewModel
        mainViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(MainViewModel.class);

        dataBinding.setVm(mainViewModel);  // 关联viewmodel和databinding

        dataBinding.setLifecycleOwner(this); // 建立livedata和databinding 感应
    }

    public  class ONClick {

        public void action() {
            // 随心所欲拿到 MainActivity所有环境
            // Toast
            // ...
        }
    }
}
