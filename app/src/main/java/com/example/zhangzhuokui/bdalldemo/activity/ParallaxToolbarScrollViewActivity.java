/*
 * Copyright 2014 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.zhangzhuokui.bdalldemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zhangzhuokui.bdalldemo.R;
import com.example.zhangzhuokui.bdalldemo.view.observablescrollview.ObservableScrollView;
import com.example.zhangzhuokui.bdalldemo.view.observablescrollview.ObservableScrollViewCallbacks;
import com.example.zhangzhuokui.bdalldemo.view.observablescrollview.ScrollState;
import com.example.zhangzhuokui.bdalldemo.view.observablescrollview.ScrollUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.nineoldandroids.view.ViewHelper;




public class ParallaxToolbarScrollViewActivity extends ParallaxBaseActivity implements ObservableScrollViewCallbacks {

    private View mImageView;
    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;

    private TextView mTitleBarText;

    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallaxtoolbarscrollview);

//        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mImageView = findViewById(R.id.image);
        mToolbarView = findViewById(R.id.refule_fragment_titlebar);
        mTitleBarText = (TextView) findViewById(R.id.refule_fragment_titlebar_text);

        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.colorAccent)));
        mTitleBarText.setTextColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.white)));
        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);

        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);

        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
//        int baseColor = getResources().getColor(R.color.primary);
//        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
//        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
//        ViewHelper.setTranslationY(mImageView, scrollY / 2);

        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);

        int baseTitleBarColor = getResources().getColor(R.color.colorAccent);
        int baseTitlebarTextColor = getResources().getColor(R.color.white);
//        int baseTitlebarLineColor = getResources().getColor(R.color.common_color_grey_bg_title_line);

        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseTitleBarColor));
        mTitleBarText.setTextColor(ScrollUtils.getColorWithAlpha(alpha, baseTitlebarTextColor));
//        mRefuleTitlebarScanText.setTextColor(ScrollUtils.mixColors(baseTitleBarColor, baseTitlebarTextColor, alpha));
//        mRefuleTitlebarLine.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseTitlebarLineColor));

        ViewHelper.setTranslationY(mImageView, scrollY / 2);
//        if (alpha < 0.5) {
//            mRefuleTitlebarScanImg.setImageDrawable(getResources().getDrawable(R.drawable.refule_btn_icon_scan_white));
//            mRefuleTitlebarScanImg.setAlpha(1-alpha);
//        }else {
//            mRefuleTitlebarScanImg.setImageDrawable(getResources().getDrawable(R.drawable.refule_btn_icon_scan_black));
//            mRefuleTitlebarScanImg.setAlpha(alpha);
//        }
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}
