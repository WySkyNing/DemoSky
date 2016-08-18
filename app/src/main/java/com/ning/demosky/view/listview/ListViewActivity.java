package com.ning.demosky.view.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ning.demosky.R;
import com.ning.demosky.view.mvp.model.Entity;
import com.ning.demosky.view.volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by wy on 2016/6/16.
 *
 * 测试加载不同布局
 */
public class ListViewActivity extends AppCompatActivity{

   private ViewPager viewPager;

    private ViewPagerAdapter adapter;

    private List<Fragment> fragmentList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        fragmentList = new ArrayList<>();

        fragmentList.add(new ListViewFragment());
        fragmentList.add(new ListViewFragment());
        fragmentList.add(new ListViewFragment());

        viewPager = (ViewPager) this.findViewById(R.id.list_view_activity_view_pager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragmentList);

        viewPager.setAdapter(adapter);

        viewPager.getCurrentItem();

        String str1 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date());

        String str =  SimpleDateFormat.getDateInstance().format(new Date());

        Log.e("data_time",str1 + "\n" + "20"+str );

        JSONObject jsonObject = new JSONObject();
        try {
           // jsonObject.put("userId", SharedPreferencesUtils.readUserId(aq.getContext()));
            jsonObject.put("engineOilSort", "0");
            jsonObject.put("pageNow", "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "http://218.60.28.101/car/API/API_O2_GET_ENGINE_OIL_LIST";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("response","__" + response.toString());

                        String strResult = "";

                        Gson gson = new Gson();
                        try {
                            JSONArray  resultArray = response.getJSONArray("RESULT");
                            strResult = resultArray.getJSONObject(0).toString();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("strResult","__" + strResult);

                        Entity entity = gson.fromJson(strResult, null);

                        if ("".equals(strResult)){
                            Log.e("entityData","strResultNull");
                        }else {
                            Log.e("entityData",entity.getEngineOilBrandList().get(0).getEngineOilBrandName());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyError","__" + error.toString());
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

}
