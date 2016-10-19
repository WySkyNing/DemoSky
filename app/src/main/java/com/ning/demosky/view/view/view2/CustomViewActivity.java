package com.ning.demosky.view.view.view2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.ning.demosky.R;
import com.ning.demosky.view.mvp.Base.BaseActivity;

/**
 * Created by wy on 2016/10/11.
 *
 */
public class CustomViewActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_view);

        Button custom_btn_1 = (Button) findViewById(R.id.custom_btn_1);
        custom_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomViewActivity.this,CustomActivity1.class);
                startActivity(intent);
            }
        });

        Button custom_btn_2 = (Button) findViewById(R.id.custom_btn_2);
        custom_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomViewActivity.this,CustomActivity2.class);
                startActivity(intent);
            }
        });

        Button custom_btn_3 = (Button) findViewById(R.id.custom_btn_3);
        custom_btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomViewActivity.this,CustomActivity3.class);
                startActivity(intent);
            }
        });

        Button custom_btn_4 = (Button) findViewById(R.id.custom_btn_4);
        custom_btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomViewActivity.this,CustomActivity4.class);
                startActivity(intent);
            }
        });

    }
}
