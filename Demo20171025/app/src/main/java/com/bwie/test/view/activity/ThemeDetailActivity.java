package com.bwie.test.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bwie.test.R;
import com.bwie.test.model.bean.LatestResult;
import com.bwie.test.model.bean.MultiType;
import com.bwie.test.model.bean.ThemeDetailResult;
import com.bwie.test.presenter.ThemeDetailPresenter;
import com.bwie.test.view.adapter.ThemeDetailAdapter;
import com.bwie.test.view.iview.BaseIView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Request;
/**
 * 主题日报内容页面
 * */
public class ThemeDetailActivity extends AppCompatActivity implements BaseIView<ThemeDetailResult>{

    private ThemeDetailPresenter presenter;
    private ArrayList<MultiType> totalList;
    private ThemeDetailAdapter adapter;
    private String url = "http://news-at.zhihu.com/api/4/theme/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_detail);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        totalList = new ArrayList<MultiType>();
        adapter = new ThemeDetailAdapter(this, totalList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        //创建Presenter对象
        presenter = new ThemeDetailPresenter();
        presenter.attachView(this);

        int id = getIntent().getIntExtra("id", -1);
        presenter.getThemeDetail(url+id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onSuccess(ThemeDetailResult result) {
        if(result!=null){
            ArrayList<LatestResult.Story> stories = result.stories;
            for (LatestResult.Story s: stories) {
                s.itemType = ThemeDetailAdapter.TYPE_STORY;
                totalList.add(s);
            }
            ArrayList<ThemeDetailResult.Editors> editors = result.editors;
            for (ThemeDetailResult.Editors e: editors) {
                e.itemType = ThemeDetailAdapter.TYPE_EDITOR;
                totalList.add(e);
            }
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this, "获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Request request, IOException e) {
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
    }
}
