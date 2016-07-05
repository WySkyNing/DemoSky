package com.ning.demosky.view.view.topbtnpulllist;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ning.demosky.R;
import com.ning.demosky.view.listview.DataBean;
import com.ning.demosky.view.listview.ListViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yorki on 2016/6/21.
 */
public class ListViewBtnPullList extends AppCompatActivity {

    private ListView listView;
    private List<DataBean> datas;
    private ListViewAdapter listViewAdapter;
    private Context context;



    private DropDownMenu mDropDownMenu;
    private String headers[] = {"城市", "年龄", "性别"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_view);
        this.context = this;

        initData();
        initView();
    }




    private void initData() {

        datas = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            DataBean dataBean = new DataBean();

            if (i <= 30) {
                dataBean.setNumber(1);
            } else if (i <= 60) {
                dataBean.setNumber(2);
            } else {
                dataBean.setNumber(3);
            }

            if (i % 2 == 0) {
                dataBean.setIs(true);
            } else {
                dataBean.setIs(false);
            }

            dataBean.setData("数据" + i);
            datas.add(dataBean);

        }
    }


    private void initView() {

        listView  = new ListView(context);
        listViewAdapter = new ListViewAdapter(context, datas);
        listView.setAdapter(listViewAdapter);

        mDropDownMenu = (DropDownMenu) this.findViewById(R.id.dropDownMenu);
        //init city menu
        final ListView cityView = new ListView(context);
        cityAdapter = new GirdDropDownAdapter(context, Arrays.asList(citys));
        cityView.setDividerHeight(0);

        cityView.setAdapter(cityAdapter);

        //init age menu
        final ListView ageView = new ListView(context);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(context, Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);

        //init sex menu
        final ListView sexView = new ListView(context);
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(context, Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);



        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);


        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                mDropDownMenu.closeMenu();
            }
        });



        //init context view
        TextView contentView = new TextView(context);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, listView);




    }




}
