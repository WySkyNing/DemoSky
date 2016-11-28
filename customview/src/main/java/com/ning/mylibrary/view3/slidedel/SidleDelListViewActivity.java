package com.ning.mylibrary.view3.slidedel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.ning.mylibrary.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wy on 2016/11/26.
 *
 */

public class SidleDelListViewActivity extends AppCompatActivity{

    private SidleDelListView mListView;
    private List<String> mDatas;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sidle_del_list_view);

        mListView = (SidleDelListView) findViewById(R.id.list_view);

        mDatas = new ArrayList<>(Arrays.asList("HelloWorld", "Welcome", "Java",
                "Android", "Servlet", "Struts",
                "Hibernate", "Spring", "HTML5", "Javascript", "Lucene"));

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mDatas);

        mListView.setAdapter(adapter);

        mListView.setDeleteBtnClickListener(new SidleDelListView.DeleteBtnClickListener()
        {
            @Override
            public void onDeleteBtnClick(final int position)
            {
                Toast.makeText(SidleDelListViewActivity.this, position
                        + " : "
                        + adapter.getItem(position), Toast.LENGTH_SHORT).show();

                adapter.remove(adapter.getItem(position));
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(SidleDelListViewActivity.this, position
                        + " : "
                        + adapter.getItem(position), Toast.LENGTH_LONG).show();
            }
        });
    }

}
