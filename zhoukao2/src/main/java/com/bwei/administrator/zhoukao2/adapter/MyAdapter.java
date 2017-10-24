package com.bwei.administrator.zhoukao2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.administrator.zhoukao2.R;
import com.bwei.administrator.zhoukao2.bean.SongData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<SongData.SongListBean> list;
    private Context context;

    public MyAdapter(List<SongData.SongListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {
           DisplayImageOptions options =new DisplayImageOptions.Builder().build();
            ImageLoader.getInstance().displayImage(list.get(position).getPic_small(),((MyViewHolder) holder).imageView,options);

            // Picasso.with(context).load(list.get(position).getHeadImg()).placeholder(R.mipmap.ic_launcher).into(((BViewHolder) holder).imageView);
            ((MyViewHolder) holder).title.setText(list.get(position).getTitle());
            ((MyViewHolder) holder).content.setText(list.get(position).getAuthor()+"-"+list.get(position).getAlbum_title());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        TextView content;
        public MyViewHolder(View itemView) {
            super(itemView);
           imageView = itemView.findViewById(R.id.view_image);
            title = itemView.findViewById(R.id.view_titlle);
            content = itemView.findViewById(R.id.view_content);

        }
    }
}
