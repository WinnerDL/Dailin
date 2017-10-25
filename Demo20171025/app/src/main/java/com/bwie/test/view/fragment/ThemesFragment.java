package com.bwie.test.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.test.R;
import com.bwie.test.model.bean.ThemesResult;
import com.bwie.test.presenter.ThemesPresenter;
import com.bwie.test.view.adapter.ThemesAdapter;
import com.bwie.test.view.iview.BaseIView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Request;

/**
 * 主题日报
 */

public class ThemesFragment extends android.support.v4.app.Fragment implements BaseIView<ThemesResult>{
    private String url = "http://news-at.zhihu.com/api/4/themes";
    private ArrayList<ThemesResult.Other> totalList;
    private ThemesAdapter adapter;
    private ThemesPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_themes, null, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        //初始化RecyclerView
        RecyclerView rv = (RecyclerView)(getView().findViewById(R.id.rv));
        totalList = new ArrayList<ThemesResult.Other>();
        //设置适配器
        adapter = new ThemesAdapter(getActivity(), totalList);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv.setAdapter(adapter);
        //创建Presenter对象
        presenter = new ThemesPresenter();
        presenter.attachView(this);
        presenter.getThemes(url);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onSuccess(ThemesResult result) {
        if(result!=null){
            ArrayList<ThemesResult.Other> others = result.others;
            totalList.addAll(others);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Request request, IOException e) {
        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
    }

}
