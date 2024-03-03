package com.derry.jetpack_app_kotlin

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData数据（有感应）
    val phoneInfo by lazy { MutableLiveData<String>() }

    // 先理解是 Java的 构造代码块    {}
    init {
        phoneInfo.value = "" // 设置默认值
    }

    // 定义一个环境
    var context: Context = application

    /**
     * 输入
     */
    fun appendNumber(number: String) {
        phoneInfo.value = phoneInfo.value + number
    }

    /**
     * 删除
     */
    fun backspaceNumber() {
        // 如果 phoneInfo.value 为null，我就不执行后面 ？ 这一段代码
        // ?: 如果实在是拿不到length长度，就用 0 默认值
        var length: Int = phoneInfo.value?.length ?: 0
        if (length > 0) {
            // phoneInfo.value  可能是null，你要补救一下  ？
            // value?.length  可能拿不到长度，你要补救一下  ？
            // !! 我保证一定可以获取到 length
            phoneInfo.value = phoneInfo.value?.substring(0, phoneInfo.value?.length!! - 1)
        }
    }

    /**
     * 清空
     */
    fun clear() {
        phoneInfo.value = ""
    }

    /**
     * 拨号
     */
    fun callPhone() {
        var intent = Intent();
        intent.action = Intent.ACTION_CALL
        intent.data = Uri.parse("tel:" + phoneInfo.value)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}