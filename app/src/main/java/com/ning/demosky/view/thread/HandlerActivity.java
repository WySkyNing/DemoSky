package com.ning.demosky.view.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ning.demosky.R;


import java.lang.ref.WeakReference;

/**
 * Created by wy on 2016/11/24.
 *
 */

public class HandlerActivity extends AppCompatActivity implements CustomHandler.HandlerInterface{

    private static boolean isRun = true;

   // private MyHandler myHandler;

    private TextView textView;

    private CustomHandler<HandlerActivity> customHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textView = new TextView(this);
        textView.setText(R.string.main_btn_5_text);
        setContentView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //TODO 方法 1
      //  myHandler = new MyHandler(this);

        MyThreadRunnable myThread = new MyThreadRunnable();
        Thread thread = new Thread(myThread);
        thread.start();
        //TODO 方法 1


        customHandler = new CustomHandler<>(this);
    }

    @Override
    public void mHandleMessage(Message message) {

        textView.setText(R.string.main_btn_5_text);
    }


    //TODO 方法 1
//    private static class MyHandler extends Handler {
//
//        WeakReference<HandlerActivity> activityWeakReference;
//
//        MyHandler(HandlerActivity activity) {
//
//            activityWeakReference = new WeakReference<>(activity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            HandlerActivity handlerActivity = activityWeakReference.get();
//            handlerActivity.textView.setText(R.string.main_btn_5_text);
//
//        }
//
//    }

    //TODO 方法 1
    private class MyThreadRunnable implements Runnable {

        @Override
        public void run() {

            while (isRun) {

                try {
                    Thread.sleep(50);

                } catch (InterruptedException e) {
                    e.printStackTrace();

                }

//                if (null != myHandler) {
//                    myHandler.sendEmptyMessage(0);
//
//                }

                if (null != customHandler){
                    customHandler.sendEmptyMessage(0);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {

        //TODO 方法 1
        isRun = false;
        if (customHandler != null) {
            customHandler.removeCallbacksAndMessages(null);
            customHandler = null;
        }
        //TODO 方法 1



        super.onDestroy();
        Log.e("wy__", "onDestroy");
       // System.gc();

    }
}
