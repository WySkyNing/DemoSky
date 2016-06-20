package com.ning.demosky.view.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ning.demosky.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yorki on 2016/6/16.
 */
public class ListViewActivity extends AppCompatActivity {

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

    private void initData() {

        datas = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            DataBean dataBean = new DataBean();

            if (i <= 30){
                dataBean.setNumber(1);
            }else if (i <= 60){
                dataBean.setNumber(2);
            }else {
                dataBean.setNumber(3);
            }

            if (i % 2 == 0){
                dataBean.is = true;
            }else {
                dataBean.is = false;
            }

            dataBean.setData("数据" + i);
            datas.add(dataBean);

        }
    }


    private void initView() {
        listView = (ListView) this.findViewById(R.id.list_view);
        adapter = new Adapter();
        listView.setAdapter(adapter);
    }

    private class Adapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public Adapter() {
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;

            if (null == convertView) {
                convertView = layoutInflater.inflate(R.layout.item_list_view, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText(datas.get(position).getData());

            if (datas.get(position).getNumber() == 1){

                viewHolder.btn2.setVisibility(View.VISIBLE);

                viewHolder.imageView.setVisibility(View.GONE);
                viewHolder.btn1.setVisibility(View.GONE);
                viewHolder.text2.setVisibility(View.GONE);

            }else if (datas.get(position).getNumber() == 2){

                viewHolder.imageView.setVisibility(View.VISIBLE);
                viewHolder.text2.setVisibility(View.VISIBLE);
                viewHolder.btn2.setVisibility(View.VISIBLE);

                viewHolder.btn1.setVisibility(View.GONE);


                viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);

            }else if (datas.get(position).getNumber() == 3){

                viewHolder.imageView.setVisibility(View.VISIBLE);
                viewHolder.text2.setVisibility(View.VISIBLE);
                viewHolder.btn2.setVisibility(View.VISIBLE);
                viewHolder.btn1.setVisibility(View.VISIBLE);

                viewHolder.imageView.setImageResource(R.drawable.explode_0);

            }

            if (datas.get(position).is){
                viewHolder.duiuhao.setVisibility(View.VISIBLE);
            }else {
                viewHolder.duiuhao.setVisibility(View.GONE);
            }

            viewHolder.textView.setText(datas.get(position).getData());
            viewHolder.text1.setText("text    1");
            viewHolder.text2.setText("text    2");
            viewHolder.text3.setText("text    3");


            viewHolder.btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ListViewActivity.this, "btn1  :" + position, Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ListViewActivity.this, "btn2  :" + position, Toast.LENGTH_SHORT).show();
                }
            });


            return convertView;
        }
    }

    private class ViewHolder {

        private TextView textView;
        private ImageView imageView ,duiuhao;

        private Button btn1,btn2;
        private TextView text1,text2,text3;

        public ViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.list_view_item_text_view);
            imageView = (ImageView) view.findViewById(R.id.list_view_item_image_view);
            duiuhao = (ImageView) view.findViewById(R.id.list_view_item_image_duihao);

            btn1 = (Button) view.findViewById(R.id.list_view_item_btn1);
            btn2 = (Button) view.findViewById(R.id.list_view_item_btn2);

            text1 = (TextView) view.findViewById(R.id.list_view_item_text1);
            text2 = (TextView) view.findViewById(R.id.list_view_item_text2);
            text3 = (TextView) view.findViewById(R.id.list_view_item_text3);
        }
    }


    private class DataBean {
        private int number;
        private String data;

        private boolean is;

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

        public boolean is() {
            return is;
        }

        public void setIs(boolean is) {
            this.is = is;
        }
    }
}
