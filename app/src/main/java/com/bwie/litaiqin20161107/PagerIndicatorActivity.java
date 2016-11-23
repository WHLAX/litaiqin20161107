package com.bwie.litaiqin20161107;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bwie.litaiqin20161107.frag.TabFragment;
import com.viewpagerindicator.TabPageIndicator;

public class PagerIndicatorActivity extends FragmentActivity {
    private String[] title = {"房产", "头条", "体育", "新闻", "经济", "财经", "军事", "女性", "社会", "游戏", "淘宝", "体育"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_indicator);
        initView();
    }

    private void initView() {
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        indicator.setViewPager(viewpager);
        //这里是为了清楚TabPageIndicator时报的ViewPager has not been bound异常，先给TabPageIndicator设置android:visibility="gone"
        indicator.setVisibility(View.VISIBLE);
    }

    /**
     * 适配器代码
     */
    class TabAdapter extends FragmentPagerAdapter {


        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            Fragment fragment = new TabFragment();
            Bundle args = new Bundle();
            args.putString("args", title[position]);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
