LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := test
LOCAL_C_INCLUDES += Lib.h
LOCAL_SRC_FILES := Lib.cpp
include $(BUILD_SHARED_LIBRARY)