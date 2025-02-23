package com.example.activitylifecycle.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.activitylifecycle.R;
import com.example.activitylifecycle.databinding.DialogActivityBinding;

public class FragmentActivity extends AppCompatActivity implements  View.OnClickListener{
    private AppBarConfiguration appBarConfiguration;
    private DialogActivityBinding binding;
    Fragment fragmentleft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        fragmentleft = (FragmentLeft) getSupportFragmentManager().findFragmentById(R.id.left_fragment);
        if(fragmentleft != null) {
            Button button = (Button) findViewById(R.id.left_fragment_button);
            button.setOnClickListener(this);
        }
        replaceFragment(new FragmentRight());
    }

    @Override
    public void onClick(View v) {
        if( fragmentleft != null ) {
            //FrameLayout所有的控件默认都会摆放在布局的左上角。由于这里仅需要在布局里放入一个碎片，不需要任何定位，因此非常适合使用FrameLayout。
            replaceFragment(new FragmentAnotherFragment());  //动态替换另一个fragment
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_layout, fragment);
        transaction.addToBackStack(null);  // 加这一句是为了返回时，只返回当前的fragment,没有这一句就直接回到最开始的页面
        transaction.commit();
    }
}
