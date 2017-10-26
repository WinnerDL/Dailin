package com.bwei.administrator.myshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bwie.mycartutils.bean.ChildBean;
import com.bwie.mycartutils.bean.GroupBean;
import com.bwie.mycartutils.utils.CartUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expan;
    private CheckBox quan;
    private TextView he,jie;
    private List<GroupBean> group;
    private List<List<ChildBean>> child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expan = (ExpandableListView) findViewById(R.id.expan);
        quan = (CheckBox) findViewById(R.id.quan);
        he = (TextView) findViewById(R.id.he);
        jie = (TextView) findViewById(R.id.jie);

        group = new ArrayList<com.bwie.mycartutils.bean.GroupBean>();
        child = new ArrayList<List<com.bwie.mycartutils.bean.ChildBean>>();

        for (int i = 0; i< 2; i++){

            group.add(new com.bwie.mycartutils.bean.GroupBean("商铺",false));
            List<com.bwie.mycartutils.bean.ChildBean> chilDb = new ArrayList<>();
            for (int j = 0; j< 2; j++){

                chilDb.add(new com.bwie.mycartutils.bean.ChildBean("商品","100","http://avatar.csdn.net/E/B/A/1_liu461211527.jpg ",false,1));
            }
            child.add(chilDb);
        }
        CartUtils.setCartData(this,group,child, expan,quan,he,jie);
    }
}
