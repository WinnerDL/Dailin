package com.bwei.administrator.dailin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bwei.administrator.dailin.adapter.MyAdapter;
import com.bwei.administrator.dailin.bean.MyBean;
import com.bwei.administrator.dailin.presenter.Music_P;
import com.bwei.administrator.dailin.view.IMusic_view;

public class MainActivity extends AppCompatActivity implements IMusic_view{

    private RecyclerView recycler;
    private Music_P music_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        music_p = new Music_P(this);
        music_p.getdata();
    }

    private void initView() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
    }

    @Override
    public void stedaa(MyBean myBean) {
        Log.e("SSSS",myBean.toString());
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new MyAdapter(myBean.getSong_list(),this));
    }
}
