package com.bwei.administrator.dailin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.administrator.dailin.R;
import com.bwei.administrator.dailin.bean.MyBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<MyBean.SongListBean> list;
    private Context context;

    public MyAdapter(List<MyBean.SongListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyBean.SongListBean songListBean = list.get(position);/*placeholder(R.mipmap.ic_launcher).into(((BViewHolder) holder).imageView*/
        Picasso.with(context).load(list.get(position).getPic_small()).into(holder.imageView);
        holder.tv1.setText(songListBean.getAlbum_title());
        holder.tv2.setText(songListBean.getAuthor());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView tv1;
        private final TextView tv2;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);

        }
    }
}
