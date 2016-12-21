package com.ning.mylibrary.view2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.ning.mylibrary.R;
import com.ning.mylibrary.textview.DifferentTextViewActivity;
import com.ning.mylibrary.view3.slidedel.SidleDelListViewActivity;

import com.ning.mylibrary.R;


/**
 * Created by wy on 2016/10/11.
 *
 */
public class CustomViewActivity extends AppCompatActivity{
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

        Button custom_btn_5 = (Button) findViewById(R.id.custom_btn_5);
        custom_btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomViewActivity.this,ViewGroupActivity1.class);
                startActivity(intent);
            }
        });

        Button custom_btn_6 = (Button) findViewById(R.id.custom_btn_6);
        custom_btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomViewActivity.this,ViewGroupActivity2.class);
                startActivity(intent);
            }
        });

        Button custom_btn_7 = (Button) findViewById(R.id.custom_btn_7);
        custom_btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomViewActivity.this,SidleDelListViewActivity.class);
                startActivity(intent);
            }
        });

        Button custom_btn_8 = (Button) findViewById(R.id.custom_btn_8);
        custom_btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomViewActivity.this,DifferentTextViewActivity.class);
                startActivity(intent);
            }
        });

    }
}
