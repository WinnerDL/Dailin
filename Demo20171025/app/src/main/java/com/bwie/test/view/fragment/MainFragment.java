package com.bwie.test.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.test.R;
import com.bwie.test.view.adapter.MainPagerAdapter;

import java.util.ArrayList;

/**
 *http://www.jianshu.com/p/7f79b08f5afa
 * TabLayout
 */
public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, null, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        //初始化并关联TabLayout和ViewPager
        TabLayout tabLayout = (TabLayout) (getView().findViewById(R.id.tabLayout));
        ArrayList<Fragment> frags = new ArrayList<Fragment>();
        frags.add(new LatestFragment());
        frags.add(new OtherFragment());
        frags.add(new OtherFragment());
        frags.add(new ThemesFragment());
        ArrayList<String> titles = new ArrayList<String>();
        titles.add("最新日报");
        titles.add("专栏");
        titles.add("热门");
        titles.add("主题日报");
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getChildFragmentManager(), frags, titles);
        ViewPager vp = (ViewPager)(getView().findViewById(R.id.vp));
        vp.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(vp);
    }

}
