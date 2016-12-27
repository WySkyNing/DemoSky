package com.ning.demosky.view.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;

/**
 * Created by wy on 2016/12/22.
 * OkHttp 封装
 */

public class OkHttpManager {

    private Handler deliveryHandler;
    private OkHttpClient okHttpClient;
    private static OkHttpManager okHttpManager;

    private OkHttpManager getInstance(){

        if (null == okHttpManager){

            synchronized (OkHttpManager.class){

                if (null == okHttpManager){

                    okHttpManager = new OkHttpManager();
                }
            }
        }
        return okHttpManager;
    }

    private OkHttpManager(){


        deliveryHandler = new Handler(Looper.getMainLooper());

    }


    private void deliveryResult(){

    }


    private void sendSuccessResultCallback(final ResultCallBack resultCallBack,final Object object){

        deliveryHandler.post(new Runnable() {
            @Override
            public void run() {

                if (null != resultCallBack){

                    resultCallBack.onNetRequestSuccess(object);
                }
            }
        });

    }

    private void sendErrorStringCallback(final ResultCallBack resultCallBack){

        deliveryHandler.post(new Runnable() {
            @Override
            public void run() {

                if (null != resultCallBack){

                    resultCallBack.onNetRequestError("");
                }

            }
        });

    }

    public static abstract class ResultCallBack<T>{

        Type type;

        public ResultCallBack(){

            type = getSuperclassTypeParameter(getClass());
        }

        /** Type是 Java 编程语言中所有类型的公共高级接口 */
        private Type getSuperclassTypeParameter(Class<?> subclass){

            /** getGenericSuperclass()获得带有泛型的父类 */
            Type superclass = subclass.getGenericSuperclass();

            if (superclass instanceof Class){

                throw new RuntimeException("Missing type parameter.");
            }

            /** ParameterizedType参数化类型，即泛型   */
            ParameterizedType parameterizedType = (ParameterizedType) superclass;

            /** getActualTypeArguments获取参数化类型的数组，泛型可能有多个   */
            return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);


        }



        public abstract void onNetRequestSuccess(T result);

        public abstract void onNetRequestError(String errorInfo);
    }
}
