package com.example.jetpack_kotlin_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack_kotlin_project.databinding.ActivityMainBinding
import kotlin.concurrent.thread

/**
 * TODO 传统方式的
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
 *
 *
 *
 * TODO JetPack重构方式
 *  单元原则：只一件事情，管理绑定工作
 */

// 角色：MainActivity是管理者
class MainActivity : AppCompatActivity() {

    // 定义ViewModel
    private var vm: MainViewModel? = null

    // 定义我们的DataBinding
    private var dataBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 新版本的写法，和旧版本不同
        vm = ViewModelProvider(viewModelStore, ViewModelProvider.AndroidViewModelFactory(application)).get(MainViewModel::class.java);

        // vm  和 DataBinding 绑定起来
        dataBinding!!.vm = vm

        //  DataBinding 感应  vm  <-----> 布局控件  计时改变
        dataBinding?.setLifecycleOwner(this)

        /*val name: String
        name.let {  }

        thread {

        }*/
    }
}
