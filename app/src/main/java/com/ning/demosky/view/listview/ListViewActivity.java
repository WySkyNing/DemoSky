package com.ning.demosky.view.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ning.demosky.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yorki on 2016/6/16.
 */
public class ListViewActivity extends AppCompatActivity{

    private ListView listView;
    private List<DataBean> datas;
    private Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        initData();
        initView();
    }

    private void initData(){

        datas = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            DataBean dataBean = new DataBean();
            dataBean.setNumber(i);
            dataBean.setData("数据" + i);
            datas.add(dataBean);
        }
    }


    private void initView(){
        listView = (ListView) this.findViewById(R.id.list_view);
        adapter = new Adapter();
        listView.setAdapter(adapter);
    }

    private class Adapter extends BaseAdapter{

        private LayoutInflater layoutInflater;

        public Adapter (){
            layoutInflater = LayoutInflater.from(ListViewActivity.this);
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;

            if (null == convertView){
                convertView = layoutInflater.inflate(R.layout.item_list_view,parent,false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText(datas.get(position).getData());

            if (datas.get(position).getNumber() % 2 != 0){
                Log.i("list_view_wy",position + "");
                viewHolder.imageView.setVisibility(View.GONE);
            }else {
                viewHolder.imageView.setVisibility(View.VISIBLE);
            }

            return convertView;
        }
    }

    private class ViewHolder{

        private TextView textView;
        private ImageView imageView;

        public ViewHolder(View view){
            textView = (TextView) view.findViewById(R.id.list_view_item_text_view);
            imageView = (ImageView) view.findViewById(R.id.list_view_item_image_view);
        }
    }


    private class DataBean{
        private int number;
        private String data;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
