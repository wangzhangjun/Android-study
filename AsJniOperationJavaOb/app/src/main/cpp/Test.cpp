#include <iostream>

// 日志输出
#include <android/log.h>

#define TAG "JNISTUDY"
// __VA_ARGS__ 代表 ...的可变参数
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG,  __VA_ARGS__);
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG,  __VA_ARGS__);
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG,  __VA_ARGS__);

int age = 99; // 实现

void show() { // 实现
    // 5000行代码
    // ...

    LOGI("show run age:%d\n", age);
}


