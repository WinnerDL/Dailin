package com.bwie.test.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bwie.test.R;
import com.bwie.test.view.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //添加主页对应的Fragment
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, mainFragment, "mainFrag").commit();
    }
}
