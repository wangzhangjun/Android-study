LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE            := liblog
LOCAL_SRC_FILES         := liblog.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := main2.out
LOCAL_C_INCLUDES += \
	../tools/
LOCAL_SRC_FILES := \
	main.cpp \
	../tools/tools.cpp \

LOCAL_SHARED_LIBRARIES += liblog

include $(BUILD_EXECUTABLE)

