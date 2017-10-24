package com.bwei.administrator.zhoukao2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bwei.administrator.zhoukao2.adapter.MyAdapter;
import com.bwei.administrator.zhoukao2.bean.SongData;
import com.bwei.administrator.zhoukao2.presenter.SongPresert;
import com.bwei.administrator.zhoukao2.presenter.SongPreserter;
import com.bwei.administrator.zhoukao2.view.SongView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SongView {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private SongPresert songPresert;
    private int page = 21;
    private ImageView img;
    private MyAdapter adapter;
    private RecyclerView recycler_view;
    private SwipyRefreshLayout swipy_layout;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songPresert = new SongPreserter(this);
        initView();


    }

    private void initView() {
        songPresert.getData(page);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        img = (ImageView) findViewById(R.id.img);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recycler_view.setLayoutManager(linearLayoutManager);
        swipy_layout = (SwipyRefreshLayout) findViewById(R.id.swipy_layout);
        swipy_layout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 21;
                        swipy_layout.setRefreshing(false);
                    }
                },1000);
            }

            @Override
            public void onLoad(int index) {

                page++;
                songPresert.getData(page);
                swipy_layout.setRefreshing(false);
            }
        });


    }

    @Override
    public void getData(List<SongData.SongListBean> songListBeen, SongData songData) {
        Log.i("ggg",songData.getBillboard().getComment());

        Picasso.with(this).load(songData.getBillboard().getPic_s192()).into(img);
        //DisplayImageOptions options = new DisplayImageOptions.Builder().build();
        //ImageLoader.getInstance().displayImage(songData.getBillboard().getPic_s192(), img, options);
        tv1.setText(songData.getBillboard().getName());
        tv2.setText("最近更新:" + songData.getBillboard().getUpdate_date());
        tv3.setText(songData.getBillboard().getComment());

        if (adapter == null) {
            adapter = new MyAdapter(songListBeen,MainActivity.this);
            recycler_view.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
