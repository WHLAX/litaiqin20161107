package com.bwie.litaiqin20161107;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import com.bwie.litaiqin20161107.frag.frag_1;
import com.bwie.litaiqin20161107.frag.frag_2;
import com.bwie.litaiqin20161107.frag.frag_3;
import com.bwie.litaiqin20161107.frag.frag_4;
import com.bwie.litaiqin20161107.frag.frag_5;

import java.util.ArrayList;

public class SecondActivity extends FragmentActivity implements View.OnClickListener {

    private Button bt_1, bt_2, bt_3, bt_4, bt_5;
    private ArrayList<Button> bt_list;
    private ViewPager vp_pager;
    //初始化偏移量
    private int offset = 0;
    private int scrollViewWidth = 0;
    private HorizontalScrollView hs_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //初始化控件id
        infoView();
        bt_list = new ArrayList<Button>();
        bt_list.add(bt_1);
        bt_list.add(bt_2);
        bt_list.add(bt_3);
        bt_list.add(bt_4);
        bt_list.add(bt_5);
        for (int i = 0; i < bt_list.size(); i++) {
            bt_list.get(i).setOnClickListener(this);
        }
        //添加pager适配器
        vp_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new frag_1();
                        break;
                    case 1:
                        fragment = new frag_2();
                        break;
                    case 2:
                        fragment = new frag_3();
                        break;
                    case 3:
                        fragment = new frag_4();
                        break;
                    case 4:
                        fragment = new frag_5();
                        break;
                    default:
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        vp_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //根据当前所在页面的索引值切换Button按钮的颜色
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < bt_list.size(); i++) {
                    if (i == position) {
                        bt_list.get(i).setTextColor(Color.RED);
                    } else {
                        bt_list.get(i).setTextColor(Color.BLACK);
                    }
                }

                //获取位置下标
                final Button btn = bt_list.get(position);
                scrollViewWidth = hs_view.getWidth();
                if ((scrollViewWidth + offset) < btn.getRight()) {//需要向右移动
                    hs_view.smoothScrollBy(btn.getRight() - (scrollViewWidth + offset), 0);
                    offset += btn.getRight() - (scrollViewWidth + offset);
                }

                if (offset > btn.getLeft()) {//需要向左移动
                    hs_view.smoothScrollBy(btn.getLeft() - offset, 0);
                    offset += btn.getLeft() - offset;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //初始化控件ID
    private void infoView() {
        bt_1 = (Button) findViewById(R.id.bt_1);
        bt_2 = (Button) findViewById(R.id.bt_2);
        bt_3 = (Button) findViewById(R.id.bt_3);
        bt_4 = (Button) findViewById(R.id.bt_4);
        bt_5 = (Button) findViewById(R.id.bt_5);
        vp_pager = (ViewPager) findViewById(R.id.vp_pager);
        hs_view = (HorizontalScrollView) findViewById(R.id.hs_view);
    }

    //对Button按钮设置监听，点击切换到对应的pager页面
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
                vp_pager.setCurrentItem(0);
                break;
            case R.id.bt_2:
                vp_pager.setCurrentItem(1);
                break;
            case R.id.bt_3:
                vp_pager.setCurrentItem(2);
                break;
            case R.id.bt_4:
                vp_pager.setCurrentItem(3);
                break;
            case R.id.bt_5:
                vp_pager.setCurrentItem(4);
                break;
        }
    }
}
