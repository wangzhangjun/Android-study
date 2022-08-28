LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE            := libtest
LOCAL_SRC_FILES         := libtest.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := main3.out
LOCAL_C_INCLUDES += \
	../tools/
LOCAL_SRC_FILES := \
	main.cpp \
	../tools/tools.cpp \

LOCAL_LDFLAGS += -L./ -llog
LOCAL_SHARED_LIBRARIES += libtest

include $(BUILD_EXECUTABLE)

