package com.ning.demosky.view.okhttp;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yorki on 2016/12/20.
 */

public class OkHttpSingleton {

    private Gson gson;
    private OkHttpClient okHttpClient;
    private final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpSingleton() {

    }

    public static OkHttpSingleton getInstance() {

        return SingleHolder.instance;

    }

    private static final class SingleHolder {
        private static final OkHttpSingleton instance = new OkHttpSingleton();
    }


    private OkHttpClient getOkHttpClient() {
        if (null == okHttpClient) {
            okHttpClient = new OkHttpClient();
        }

        return okHttpClient;
    }

    private Gson getGson() {
        if (null == gson) {
            gson = new Gson();
        }

        return gson;
    }

    /**
     * JSONObject 请求
     */
    public <T> void startJsonRequest(JSONObject jsonObject, String url, Class<T> mClass
            , NetRequestResultInter netRequestResultInter, String tag) {

        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        addRequest(request, netRequestResultInter, mClass, tag);

    }

    private <T> void addRequest(Request request, final NetRequestResultInter netRequestResultInter
            , final Class<T> mClass, final String tag) {

        Call call = getOkHttpClient().newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                netRequestResultInter.onNetRequestError("网络链接超时");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

               // T resultEntity = getGson().fromJson(response.body().charStream(), mClass);



                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    //String msg =

                    Log.e("ok_json",jsonObject.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }


               // netRequestResultInter.onNetRequestSuccess(response.body().string(), tag);


            }
        });

    }
}
