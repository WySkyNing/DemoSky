package com.ning.demosky.view.mvp.model;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ning.demosky.view.base.MyApplication;
import com.ning.demosky.view.mvp.presenter.NetworkConnectInter;


/**
 * Created by yorki on 2016/6/14.
 */
public class MVPModel implements ModelInter{

    @Override
    public void startLoadConnect(String url, final NetworkConnectInter networkConnectInter) {

        RequestQueue queue = Volley.newRequestQueue(MyApplication.appContext);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("a",response + "-------");
                networkConnectInter.onLodeNetDataCompleted(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("result_mvp","-------");
            }
        });
        queue.add(stringRequest);
    }

}
