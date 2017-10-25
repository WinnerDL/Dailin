package com.bwie.test.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.test.R;
import com.bwie.test.model.bean.LatestResult;
import com.bwie.test.presenter.LatestPresenter;
import com.bwie.test.view.adapter.LatestAdapter;
import com.bwie.test.view.iview.LatestIView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Request;

/**
 * 最新日报
 */

public class LatestFragment extends Fragment implements LatestIView<LatestResult>, SwipeRefreshLayout.OnRefreshListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private String url1 = "http://news-at.zhihu.com/api/4/news/latest";
    private String url2 = "http://news-at.zhihu.com/api/4/news/before/20131119";
    private ArrayList<LatestResult.Story> totalList;
    private LatestAdapter adapter;
    private LatestPresenter presenter;
    private SwipeRefreshLayout swipe;
    private PullLoadMoreRecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_latest, null, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        //初始化RecyclerView
        rv = (PullLoadMoreRecyclerView)(getView().findViewById(R.id.rv));
        totalList = new ArrayList<LatestResult.Story>();
        adapter = new LatestAdapter(getActivity(), totalList);
        rv.setLinearLayout();
        rv.setPullRefreshEnable(false);
        rv.setOnPullLoadMoreListener(this);
        rv.setAdapter(adapter);
        //创建Presenter对象
        presenter = new LatestPresenter();
        presenter.attachView(this);
        swipe = (SwipeRefreshLayout)getView().findViewById(R.id.swipe);
        swipe.setOnRefreshListener(this);
        //请求网络数据
        swipe.setRefreshing(true);
        presenter.getLatestNews(url1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onRefresh() {
        //请求网络数据
        presenter.getLatestNews(url1);
    }

    @Override
    public void onLoadMore() {
        //加载更多数据
        presenter.getBeforeNews(url2);
    }

    @Override
    public void onSuccess(LatestResult result) {
        swipe.setRefreshing(false);
        if(result!=null){
            totalList.clear();
            ArrayList<LatestResult.TopStory> top_stories = result.top_stories;
            ArrayList<LatestResult.Story> stories = result.stories;
            adapter.setTopStories(top_stories);
            totalList.addAll(stories);
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Request request, IOException e) {
        swipe.setRefreshing(false);
        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetBeforeSuccess(LatestResult result) {
        if(result!=null){
            ArrayList<LatestResult.Story> stories = result.stories;
            totalList.addAll(stories);
            adapter.notifyDataSetChanged();
            rv.setPullLoadMoreCompleted();
        }else{
            Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetBeforeFailure(Request request, IOException e) {
        rv.setPullLoadMoreCompleted();
        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
    }
}
