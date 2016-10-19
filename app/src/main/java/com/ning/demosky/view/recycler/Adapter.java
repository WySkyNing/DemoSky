package com.ning.demosky.view.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ning.demosky.R;

import java.util.List;
import java.util.Random;

/**
 * Created by yorki on 2016/5/23.
 *
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private List<String> datas;
    private Random random;

    public Adapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
        random = new Random();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_recycler_view, parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.item_tx.setText(datas.get(position));
        holder.item_tx.setHeight(random.nextInt(150 - 30 + 1) + 30);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView item_tx;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_tx = (TextView) itemView.findViewById(R.id.item_text_view);
        }
    }
}


