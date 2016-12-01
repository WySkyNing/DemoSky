package com.ning.demosky.view.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.ning.demosky.R;
import com.ning.demosky.view.db.DbActivity;
import com.ning.demosky.view.mvp.Base.BaseActivity;
import com.ning.demosky.view.permission.PermissionActivity;
import com.ning.demosky.view.photo.SelectPhotoActivity;
import com.ning.demosky.view.photo.apps.activity.AlbumsActivity;
import com.ning.demosky.view.provider.ProviderActivity;
import com.ning.demosky.view.thread.HandlerActivity;
import com.ning.demosky.view.upapp.DownloadService;
import com.ning.demosky.view.upapp.UpDataManager;
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

//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setNavigationBarColor(Color.TRANSPARENT);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }else {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

//        Window window = getWindow();
//        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

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


        UpDataManager manager = new UpDataManager(this);
        manager.checkUpData();

//        DownloadService.startDownload(this,
//                "http://api.ocarlife.cn/car/versionType/car_owner_apk/loveCar-release(20161124).apk");
    }
}
