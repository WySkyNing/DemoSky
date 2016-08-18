package com.ning.demosky.view.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by wuqi on 15/6/19.
 */
public class To {

    private static Context sContext;
    private static Toast sToast;

    private To() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void init(Context context) {
        sContext = context;
        sToast = Toast.makeText(sContext, "", Toast.LENGTH_SHORT);
    }

    public static boolean isShow = true;


    public static void showShort(CharSequence message) {
        if (isShow) {
            sToast.setText(message);
            sToast.show();
        }
    }


    public static void showLong(CharSequence message) {
        if (isShow) {
            sToast.setDuration(Toast.LENGTH_LONG);
            sToast.setText(message);
            sToast.show();
        }
    }


}