package com.bwie.test.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.R;
import com.bwie.test.model.bean.LatestResult;
import com.bwie.test.model.bean.MultiType;
import com.bwie.test.model.bean.ThemeDetailResult;
import com.bwie.test.model.http.BannerImageLoader;
import com.bwie.test.model.http.ImageLoadProxy;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class ThemeDetailAdapter extends RecyclerView.Adapter {

    private Context ctx;
    private ArrayList<MultiType> list;
    public static final int TYPE_STORY = 1;
    public static final int TYPE_EDITOR = 2;

    public ThemeDetailAdapter(Context ctx, ArrayList<MultiType> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_STORY){
            return new LatestAdapter.StoryHolder(View.inflate(ctx, R.layout.item_latest_story, null));
        }else if(viewType == TYPE_EDITOR) {
            return new EditorsHolder(View.inflate(ctx, R.layout.item_theme_detail_editor, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_STORY){
            LatestAdapter.StoryHolder storyHolder = (LatestAdapter.StoryHolder) holder;
            LatestResult.Story story = (LatestResult.Story)list.get(position);
            if(story.images!=null && story.images.length>0){
                ImageLoadProxy.displayImage(story.images[0], storyHolder.iv, ImageLoadProxy.getOption4ExactlyType());
                //Log.i("xxxyy","url="+list.get(position).images[0]);//上拉加载更多时的图片地址无效？
            }
            storyHolder.tv.setText(story.title);
        }else if(getItemViewType(position) == TYPE_EDITOR) {
            EditorsHolder editorsHolder = (EditorsHolder) holder;
            ThemeDetailResult.Editors editors = (ThemeDetailResult.Editors)list.get(position);
            ImageLoadProxy.displayImage(editors.avatar, editorsHolder.iv, ImageLoadProxy.getOption4ExactlyType());
            editorsHolder.tv1.setText(editors.name);
            editorsHolder.tv2.setText(editors.bio);
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).itemType;
    }

    class EditorsHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv1;
        TextView tv2;

        public EditorsHolder(View itemView) {
            super(itemView);
            iv = (ImageView)itemView.findViewById(R.id.iv);
            tv1 = (TextView)itemView.findViewById(R.id.tv1);
            tv2 = (TextView)itemView.findViewById(R.id.tv2);
        }
    }
}
