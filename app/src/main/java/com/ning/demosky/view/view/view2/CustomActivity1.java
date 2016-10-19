package com.ning.demosky.view.view.view2;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ning.demosky.R;
import com.ning.demosky.view.mvp.Base.BaseActivity;

/**
 * Created by wy on 2016/10/17.
 *
 */
public class CustomActivity1 extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_view_1);
    }
}
