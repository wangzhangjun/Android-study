package com.example.testactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        //Log.d("SecondActivity", data);
        Button button = (Button)findViewById(R.id.button_2);
        button.setOnClickListener(v -> {
            Intent intent1 = new Intent();
            intent1.putExtra("data_return", "Hello MainActivity");
            setResult(RESULT_OK, intent1);
            finish();
        });
    }
}