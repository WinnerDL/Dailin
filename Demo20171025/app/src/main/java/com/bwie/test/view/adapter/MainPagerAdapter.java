package com.bwie.test.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * http://www.jianshu.com/p/7f79b08f5afa
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> frags;
    private ArrayList<String> titles;

    public MainPagerAdapter(FragmentManager fm, ArrayList<Fragment> frags, ArrayList<String> titles) {
        super(fm);
        this.frags = frags;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return frags.get(position);
    }

    @Override
    public int getCount() {
        return frags.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}