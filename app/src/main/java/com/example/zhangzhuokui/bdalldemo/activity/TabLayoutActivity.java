package com.example.zhangzhuokui.bdalldemo.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.zhangzhuokui.bdalldemo.R;
import com.example.zhangzhuokui.bdalldemo.adapter.TabLayoutAdapter;
import com.example.zhangzhuokui.bdalldemo.fragment.TabBaseFragment;
import com.example.zhangzhuokui.bdalldemo.view.DefaultViewPager;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhangzhuokui on 2017/9/4.
 */

public class TabLayoutActivity extends FragmentActivity{

    private List<String> mData;
    private Toolbar toolbar;
    private ImmersionBar mImmersionBar;

    private DefaultViewPager mViewPager;
    private TabLayoutAdapter mViewPagerAdapter;
    private List<Fragment> mFragmentList;
    private List<String> mFragmentTitle = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
//        initData(1);
        initView();
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();

    }

    private void initData(int pager) {
        mData = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            mData.add("pager" + pager + " 第" + i + "个item");
        }
    }

    private void initView() {
        //设置ToolBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        //setSupportActionBar(toolbar);//替换系统的actionBar

        mViewPager = (DefaultViewPager) findViewById(R.id.tablayout_viewpager);

        initFragmentList();

        //设置TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        for (int i = 1; i < 9; i++) {
            tabLayout.addTab(tabLayout.newTab().setText("TAB" + i));
            mFragmentTitle.add("TAB" + i);
        }

        mViewPager.setAdapter(mViewPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.setScanScroll(false);

        //TabLayout的切换监听
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                //切换的时候更新RecyclerView
//                initData(tab.getPosition() + 1);
//                mAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }

    private void initFragmentList(){
        if(mFragmentList == null || mViewPagerAdapter == null){
            mFragmentList = new ArrayList<Fragment>();
            for (int i = 1; i < 9; i++) {
                mFragmentList.add(new TabBaseFragment());
            }

            mViewPagerAdapter=new TabLayoutAdapter(getSupportFragmentManager(),mFragmentList, mFragmentTitle);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mImmersionBar.destroy();
    }
}
