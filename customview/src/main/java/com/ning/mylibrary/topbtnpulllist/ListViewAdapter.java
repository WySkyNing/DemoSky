package com.ning.mylibrary.topbtnpulllist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ning.mylibrary.R;
import com.ning.mylibrary.topbtnpulllist.DataBean;

import java.util.List;

/**
 * Created by wy on 2016/6/21.
 *
 */
public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<DataBean> data;
    private LayoutInflater layoutInflater;

    public ListViewAdapter(Context context, List<DataBean> data) {
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return data.size();
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

        ViewHolder viewHolder;

        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.item_list_view, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(data.get(position).getData());

        if (data.get(position).getNumber() == 1) {

            viewHolder.btn2.setVisibility(View.VISIBLE);

            viewHolder.imageView.setVisibility(View.GONE);
            viewHolder.btn1.setVisibility(View.GONE);
            viewHolder.text2.setVisibility(View.GONE);

        } else if (data.get(position).getNumber() == 2) {

            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.text2.setVisibility(View.VISIBLE);
            viewHolder.btn2.setVisibility(View.VISIBLE);

            viewHolder.btn1.setVisibility(View.GONE);


            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);

        } else if (data.get(position).getNumber() == 3) {

            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.text2.setVisibility(View.VISIBLE);
            viewHolder.btn2.setVisibility(View.VISIBLE);
            viewHolder.btn1.setVisibility(View.VISIBLE);

            viewHolder.imageView.setImageResource(R.drawable.explode_0);

        }

        if (data.get(position).getIs()) {
            viewHolder.duiuhao.setVisibility(View.VISIBLE);
        } else {
            viewHolder.duiuhao.setVisibility(View.GONE);
        }

        viewHolder.textView.setText(data.get(position).getData());
        viewHolder.text1.setText("text    1");
        viewHolder.text2.setText("text    2");
        viewHolder.text3.setText("text    3");


        viewHolder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "btn1  :" + position, Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "btn2  :" + position, Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }


    private class ViewHolder {

        private TextView textView;
        private ImageView imageView, duiuhao;

        private Button btn1, btn2;
        private TextView text1, text2, text3;

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

}
