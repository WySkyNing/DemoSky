package com.ning.mylibrary;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.lang.ref.WeakReference;

/**
 * Created by wy on 2016/5/27.
 */
public class ViewActivity extends AppCompatActivity {

    private static boolean isRun = true;

    private MyHandler myHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        myHandler = new MyHandler(this);

        MyThreadRunnable myThread = new MyThreadRunnable();
        Thread thread = new Thread(myThread);
        thread.start();


        Button button = (Button) findViewById(R.id.btn);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isRun = false;
                myHandler = null;
            }
        });
    }

    @Override
    protected void onDestroy() {

        isRun = false;
        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(null);
        }
        myHandler = null;
        super.onDestroy();

    }

    private static class MyHandler extends Handler {

        WeakReference<ViewActivity> activityWeakReference;

        MyHandler(ViewActivity activity) {

            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }

    }

    private class MyThreadRunnable implements Runnable {

        @Override
        public void run() {

            while (isRun) {

                try {
                    Thread.sleep(3000);
                    Log.e("wwwwwyyyyyy", "xn");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (null != myHandler) {
                myHandler.sendEmptyMessage(0);
            }

        }
    }

}
