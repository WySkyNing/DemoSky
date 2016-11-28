package com.ning.demosky.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import com.ning.demosky.R;
import com.ning.demosky.view.db.DbActivity;
import com.ning.demosky.view.mvp.Base.BaseActivity;
import com.ning.demosky.view.permission.PermissionActivity;
import com.ning.demosky.view.photo.SelectPhotoActivity;
import com.ning.demosky.view.photo.apps.activity.AlbumsActivity;
import com.ning.demosky.view.provider.ProviderActivity;
import com.ning.demosky.view.thread.HandlerActivity;
import com.ning.mylibrary.view2.CustomViewActivity;

/**
 * Created by wy on 2016/10/11.
 *
 */
public class MainActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_1 = (Button) findViewById(R.id.main_btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DbActivity.class);
                startActivity(intent);
            }
        });

        Button btn_2 = (Button) findViewById(R.id.main_btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ProviderActivity.class);
                startActivity(intent);
            }
        });

        Button btn_3 = (Button) findViewById(R.id.main_btn_3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SelectPhotoActivity.class);
                startActivity(intent);
            }
        });

        btn_3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(MainActivity.this, AlbumsActivity.class);
                startActivity(intent);
                return false;
            }
        });


        Button btn_4 = (Button) findViewById(R.id.main_btn_4);
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CustomViewActivity.class);
                startActivity(intent);
            }
        });

        Button btn_5 = (Button) findViewById(R.id.main_btn_5);
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, HandlerActivity.class);
                startActivity(intent);
            }
        });

        Button btn_6 = (Button) findViewById(R.id.main_btn_6);
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, PermissionActivity.class);
                startActivity(intent);
            }
        });
    }
}
