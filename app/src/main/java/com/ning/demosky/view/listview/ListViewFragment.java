package com.ning.demosky.view.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.ning.demosky.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wy on 2016/6/21.
 *
 */
public class ListViewFragment extends android.support.v4.app.Fragment {

    private ListView listView;
    private List<DataBean> datas;
    private ListViewAdapter listViewAdapter;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
        initView(view);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
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


    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list_view);
        listViewAdapter = new ListViewAdapter(context, datas);
        listView.setAdapter(listViewAdapter);

//        mAdapter = new MAdapter(datas,context);
//        listView.setAdapter(mAdapter);
    }

}
