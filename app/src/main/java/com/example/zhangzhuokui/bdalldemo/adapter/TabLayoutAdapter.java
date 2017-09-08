package com.example.zhangzhuokui.bdalldemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhangzhuokui on 2017/9/4.
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;
    List<String> mTitles;
    FragmentManager fm;



    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public TabLayoutAdapter(FragmentManager fm,List<Fragment> fragmentList, List<String> titles) {
        super(fm);
        this.fm=fm;
        this.fragmentList=fragmentList;
        this.mTitles=titles;
    }

    @Override
    public Fragment getItem(int position) {

        return  fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if(null!=fragmentList){
            return  fragmentList.size();
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fm.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
        Fragment fragment = fragmentList.get(position);
        fm.beginTransaction().hide(fragment).commit();
    }
}
