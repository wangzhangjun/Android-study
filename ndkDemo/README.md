command:

export NDK_PROJECT_PATH=.
ndk-build-25 NDK_APPLICATION_MK=Application.mk 

in adb shell: export LD_LIBRARY_PATH=/data/ndkdemo/:$LD_LIBRARY_PATH