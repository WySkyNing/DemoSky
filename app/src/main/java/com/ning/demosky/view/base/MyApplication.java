package com.ning.demosky.view.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by yorki on 2016/6/14.
 */
public class MyApplication extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;

        MyCrashHandler.getInstance().init(getApplicationContext());
    }
}
