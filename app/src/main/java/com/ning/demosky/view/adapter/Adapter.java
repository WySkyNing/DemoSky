package com.ning.demosky.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ning.demosky.R;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 *
 */
public class Adapter extends CommonAdapter<String>{

    public Adapter(Context context, List<String> listData){
        super(context,listData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = ViewHolder.getViewHolder(mContext,parent, R.layout.item_list_view,
                position,convertView);

        TextView textView = viewHolder.getView(R.id.list_view_item_text_view);

        textView.setTag(listData.get(position));

        return viewHolder.getmConvertView();
    }
}
