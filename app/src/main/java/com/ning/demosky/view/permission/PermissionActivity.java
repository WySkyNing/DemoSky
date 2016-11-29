package com.ning.demosky.view.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by wy on 2016/11/28.
 *
 */

public class PermissionActivity extends AppCompatActivity {

    private final int MY_PERMISSION_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        setContentView(textView);

        textView.setText("Permission Demo");
        textView.setGravity(Gravity.CENTER);


       PermissionUtils.requestPermission(this, PermissionUtils.CODE_CALL_PHONE, permissionGrant);

       // checkSelfPermission();

    }


    PermissionUtils.PermissionGrant permissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {

            if (PermissionUtils.CODE_CALL_PHONE == requestCode){

                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "10086");
                intent.setData(data);
                startActivity(intent);

                Log.e("wy__","wy___");
            }

        }
    };

    /**
     *  checkSelfPermission 检查是否拥有这个权限,如果已经开启则直接执行想要做的事情
     *  requestPermissions 请求权限，一般会弹出一个系统对话框，询问用户是否开启这个权限。
     * */

    private void checkSelfPermission(){

        if (ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(PermissionActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSION_REQUEST_CALL_PHONE);

        } else {

            callPhone();
        }
    }

    private void callPhone(){

        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }



    /**
     * 请求权限的回调
     * */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == MY_PERMISSION_REQUEST_CALL_PHONE){

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                callPhone();
            }else {
                Toast.makeText(PermissionActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, permissionGrant);
    }



}
