package com.ning.demosky.view.thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by wy on 2016/11/24..
 *
 */

public class CustomHandler<T> extends Handler{

    private WeakReference<T> weakReference;

    public CustomHandler(T t){
        weakReference = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        HandlerInterface handlerInterface = (HandlerInterface) weakReference.get();

        if (null != handlerInterface){
            handlerInterface.mHandleMessage(msg);
        }else {
            Log.e("wy","error");
        }
    }

    interface HandlerInterface {
        void mHandleMessage(Message message);
    }
}
