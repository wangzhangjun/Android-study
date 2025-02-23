package com.example.activitylifecycle.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.activitylifecycle.R;

// 从这里来看Framement也就是一些元素的标签之类的，和Activity类似，可能后续的其他生命周期不一样，往后再学
public class FragmentLeft extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_left,container,false);
       return view;
    }
}
