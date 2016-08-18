package com.ning.demosky.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ning.demosky.R;

/**
 * Created by wy on 2016/8/18.
 */
public class CrashTextActivity extends AppCompatActivity{

    String string;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mvp);

        string.equals("a");

    }
}
