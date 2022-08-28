#include <iostream>
#include "tools.h"
#include <android/log.h>

using namespace std;
#define LOG_TAG "zhjwang"

#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)

int main()
{
    LOGI("hello zhjwang-LOGD");
    std::cout << "hello2~" << std::endl;
    myPrint("test");
}