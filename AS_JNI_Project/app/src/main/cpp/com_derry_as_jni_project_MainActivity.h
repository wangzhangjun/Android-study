#include <jni.h>
#include <string>

// 解决循环Copy的问题 第二次就进不来了
#ifndef _Included_com_derry_as_jni_project_MainActivity // 如果没有定义这个宏
#define _Included_com_derry_as_jni_project_MainActivity // 我就定义这个宏

#ifdef __cplusplus // 如果是C++环境
extern "C" { // 全部采用C的方式 不准你函数重载，函数名一样的问题
#endif
#undef com_derry_as_jni_project_MainActivity_A
#define com_derry_as_jni_project_MainActivity_A 100L

// 函数的声明
JNIEXPORT jstring JNICALL Java_com_derry_as_1jni_1project_MainActivity_getStringPwd
  (JNIEnv *, jobject);


#ifdef __cplusplus // 省略  如果是C++，啥事不干
}

#endif
#endif
