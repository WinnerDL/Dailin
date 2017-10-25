package com.bwie.test.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.R;
import com.bwie.test.model.bean.LatestResult;
import com.bwie.test.model.http.BannerImageLoader;
import com.bwie.test.model.http.ImageLoadProxy;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class LatestAdapter extends RecyclerView.Adapter {

    private List<String> images;//轮播图图片地址
    private List<String> titles;//轮播图标题
    private Context ctx;
    private ArrayList<LatestResult.Story> list;
    private static final int TYPE_TOP_STORY = 1;
    private static final int TYPE_STORY = 2;

    public LatestAdapter(Context ctx, ArrayList<LatestResult.Story> list) {
        this.ctx = ctx;
        this.list = list;
    }

    public void setTopStories(ArrayList<LatestResult.TopStory> top_stories) {
        images = new ArrayList<>();
        titles = new ArrayList<>();
        for (LatestResult.TopStory top: top_stories) {
            images.add(top.image);
            titles.add(top.title);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_TOP_STORY){
            return new BannerHolder(View.inflate(ctx, R.layout.item_latest_top_story, null));
        }
        return new StoryHolder(View.inflate(ctx, R.layout.item_latest_story, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_STORY){
            StoryHolder storyHolder = (StoryHolder) holder;
            if(list.get(position).images!=null && list.get(position).images.length>0){
                ImageLoadProxy.displayImage(list.get(position).images[0], storyHolder.iv, ImageLoadProxy.getOption4ExactlyType());
                //Log.i("xxxyy","url="+list.get(position).images[0]);//上拉加载更多时的图片地址无效？
            }
            storyHolder.tv.setText(list.get(position).title);
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_TOP_STORY;
        }
        return TYPE_STORY;
    }

    class BannerHolder extends RecyclerView.ViewHolder{

        private final Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            banner = (Banner)itemView.findViewById(R.id.banner);
            //设置banner样式
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            //设置图片加载器
            banner.setImageLoader(new BannerImageLoader());
            //设置图片集合
            banner.setImages(images);
            //设置banner动画效果
//            banner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
            banner.setBannerTitles(titles);
            //设置自动轮播，默认为true
            banner.isAutoPlay(true);
            //设置轮播时间
            banner.setDelayTime(3000);
            //设置指示器位置（当banner模式中有指示器时）
            banner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }
    }

    static class StoryHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv;

        public StoryHolder(View itemView) {
            super(itemView);
            iv = (ImageView)itemView.findViewById(R.id.iv);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }
}
