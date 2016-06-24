package com.ning.demosky.view.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ning.demosky.R;

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

    }

}
