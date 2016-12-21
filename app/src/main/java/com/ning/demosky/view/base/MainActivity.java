package com.ning.demosky.view.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.ning.demosky.R;
import com.ning.demosky.view.db.DbActivity;
import com.ning.demosky.view.mvp.Base.BaseActivity;
import com.ning.demosky.view.okhttp.NetRequestResultInter;
import com.ning.demosky.view.okhttp.OkHttpSingleton;
import com.ning.demosky.view.permission.PermissionActivity;
import com.ning.demosky.view.photo.SelectPhotoActivity;
import com.ning.demosky.view.photo.apps.activity.AlbumsActivity;
import com.ning.demosky.view.provider.ProviderActivity;
import com.ning.demosky.view.thread.HandlerActivity;
import com.ning.demosky.view.upapp.DownloadService;
import com.ning.demosky.view.upapp.UpDataManager;
import com.ning.demosky.view.utils.L;
import com.ning.mylibrary.view2.CustomViewActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wy on 2016/10/11.
 *
 */
public class MainActivity extends BaseActivity {

    private Button btn_6;

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

        btn_6 = (Button) findViewById(R.id.main_btn_6);
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


        /**
         * OkHttp
         * */


        postRequest();



    }


    private void getRequset(){

        OkHttpClient mOkHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url("https://www.baidu.com").build();

        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("wy_no",e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.e("wy_ok",response.body().string());
            }
        });
    }

    private void postRequest(){

        L.e("www","www");

        OkHttpClient okHttpClient = new OkHttpClient();

        FormBody formBody = new FormBody.Builder()
                .add("userPhoneNumber","18842606495")
                .add("userPassWord","123")
                .build();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userPhoneNumber","18842606495");
            jsonObject.put("userPassWord","123");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpSingleton.getInstance().startJsonRequest(jsonObject, "http://218.60.28.101/car/API/API_O2_LOGIN_USER"
                , null, new NetRequestResultInter() {
                    @Override
                    public <T> void onNetRequestSuccess(T result, String tag) {

                    }

                    @Override
                    public <T> void onNetRequestError(T errorInfo) {

                    }
                },"");

        MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON,jsonObject.toString());

        Request request = new Request.Builder()
                .post(requestBody)
                .url("http://218.60.28.101/car/API/API_O2_LOGIN_USER")
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("wy_no",e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                Log.e("wy_ok",response.body().string());


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btn_6.setText("sdfsdf");
                    }
                });
            }
        });
    }

}
