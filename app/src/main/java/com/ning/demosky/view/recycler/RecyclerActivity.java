package com.ning.demosky.view.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


import com.ning.demosky.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        /**设置布局管理器  瀑布流在适配器中设置Item高度随机*/
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        /* 瀑布流效果 */
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
//                StaggeredGridLayoutManager.VERTICAL));

        /**设置分割线*/
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));


        Adapter adapter = new Adapter(datas, this);

        recyclerView.setAdapter(adapter);
    }

    private void initData() {

        datas = new ArrayList<>();

        for (int i = 0; i < 200; i++) {

            datas.add("数据" + i);
        }
    }
}
