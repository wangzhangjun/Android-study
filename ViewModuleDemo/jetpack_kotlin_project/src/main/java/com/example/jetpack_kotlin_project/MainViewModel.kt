package com.example.jetpack_kotlin_project

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// 管理Activity的数据
// kotlin :    vs  extends implements
// 报错是因为，默认调用构造 ()
class MainViewModel(var context: Application) : AndroidViewModel(context) {

    // Java
    // private String phone; // 显示的号码  UI的　数据

    // ? 代表 全部注意了，这个 phone有可能是nul
    // var phone: String? = null
    // var phone: String by lazy { String() }

    // by lazy  懒加载  用到在加载
    val phone by lazy { MutableLiveData<String>() }

    init {
        phone.value = ""
    }

    /**
     * 输入
     * @param number
     *
     * void  == Unit
     */
    fun appendNumber(number: String ) : Unit {

        // phone.value  你没有赋值  getValue
        // phone.value = 原来你要赋值  setValue
        // Java phone.setValue(phone.getValue() + number)
        phone.value = phone.value + number
    }

    /**
     * 删除
     */
    fun  backspaceNumber() : Unit {

        // phone.value? xxx  如果value是null，我就不执行 ?xxx
        // .length报错的原因：  length拿不到长度，我就给你 -1
        var length: Int = phone.value?.length ?: -1

        if (length > 0) {
            // !! 我自己能够保证  不可能为null    vs  有空指针异常的可能
            phone.value = phone.value?.substring(0, phone!!.value!!.length - 1);
        }
    }

    /**
     * 清空
     */
    fun  clear() : Unit {
        phone.value = "" // set  get
    }

    /**
     * 拨打
     */
    fun callPhone() {
        val intent = Intent();
        intent.action = Intent.ACTION_CALL
        intent.data = Uri.parse("tel:" + phone.value)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK  // 非Activity 启动隐式意图 奔溃， 需要加标记
        context.startActivity(intent);
    }
}