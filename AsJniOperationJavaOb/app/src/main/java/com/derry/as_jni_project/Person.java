package com.derry.as_jni_project;

import android.util.Log;

public class Person {

    private static final String TAG = Person.class.getSimpleName();

    public Student student;

    public void setStudent(Student student) {
        Log.d(TAG, "call setStudent student:" + student.toString());
        this.student = student;
    }

    public static void putStudent(Student student) {
        Log.d(TAG, "call static putStudent student:" + student.toString());
    }
}
