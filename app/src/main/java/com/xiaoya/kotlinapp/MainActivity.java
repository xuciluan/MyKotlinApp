package com.xiaoya.kotlinapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xiaoya.kotlinapp.customview.ColorTrackTextView;
import com.xiaoya.kotlinapp.customview.QQRun;

public class MainActivity extends AppCompatActivity {

    private QQRun qqRun;
    private ColorTrackTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qqRun = findViewById(R.id.qq);
        qqRun.startAnimation();
        textView = findViewById(R.id.trackView);
        textView.startAnimation();
    }
}
