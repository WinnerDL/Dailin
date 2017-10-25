package com.bwie.test.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.R;
import com.bwie.test.model.bean.ThemesResult;
import com.bwie.test.model.http.ImageLoadProxy;
import com.bwie.test.view.activity.ThemeDetailActivity;

import java.util.ArrayList;

/**
 *
 */

public class ThemesAdapter extends RecyclerView.Adapter<ThemesAdapter.ThemesHolder> {

    private Context ctx;
    private ArrayList<ThemesResult.Other> list;

    public ThemesAdapter(Context ctx, ArrayList<ThemesResult.Other> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public ThemesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemesHolder(View.inflate(ctx, R.layout.item_themes, null));
    }

    @Override
    public void onBindViewHolder(ThemesHolder holder, int position) {
        holder.tv.setText(list.get(position).name);
        ImageLoadProxy.displayImage(list.get(position).thumbnail, holder.iv, ImageLoadProxy.getOption4ExactlyType());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class ThemesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv;
        TextView tv;
        public ThemesHolder(View itemView) {
            super(itemView);
            iv = (ImageView)itemView.findViewById(R.id.iv);
            tv = (TextView)itemView.findViewById(R.id.tv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //点击条目跳转到主题日报内容页面
            ctx.startActivity(new Intent(ctx, ThemeDetailActivity.class).putExtra("id", list.get(getAdapterPosition()).id));
        }
    }

}
