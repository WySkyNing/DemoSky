package com.ning.demosky.view.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/6/30.
 */
public class ViewHolder {

    private SparseArray<View> mViews;

    private int mPosition;

    private View mConvertView;

    /**
     * @param layoutId itemId
     * */
    public ViewHolder(Context context, ViewGroup parent,int layoutId,int position){

        this.mPosition = position;
        mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);

        mConvertView.setTag(this);
    }

    /*入口方法 判断 ViewHolder 是否需要去 new */
    public static ViewHolder getViewHolder(Context context,ViewGroup parent,
                                           int layoutId, int position,View convertView){

        if (null == convertView){
            return new ViewHolder(context,parent,layoutId,position);

        }else {

            ViewHolder viewHolder = (ViewHolder) convertView.getTag();

            /* 发生复用时改变 position  */
            viewHolder.mPosition = position;
            return viewHolder;
        }
    }

    /* 返回 convertView */
    public View getmConvertView() {
        return mConvertView;
    }

    /**
     *  通过 viewId 获取控件
     *  @param viewId 控件 ID
     *  */
    public <T extends View> T getView(int viewId){

        View view = mViews.get(viewId);

        if (null == view){
            view = mConvertView.findViewById(viewId);
        }

        return (T) view;
    }
}