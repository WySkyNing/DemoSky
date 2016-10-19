package com.ning.demosky.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 *
 */
public abstract class CommonAdapter <T> extends BaseAdapter{

    protected Context mContext;
    protected List<T> listData;
    protected LayoutInflater layoutInflater;

    public CommonAdapter(Context context,List<T> listData){

        this.mContext = context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /* getView 代码不同 改成抽象公布出去 */
    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

}
