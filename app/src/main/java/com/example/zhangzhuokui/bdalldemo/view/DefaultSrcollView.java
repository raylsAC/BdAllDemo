package com.example.zhangzhuokui.bdalldemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by zhangzk on 2017/4/17.
 */

public class DefaultSrcollView extends ScrollView {
    public DefaultSrcollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public DefaultSrcollView(Context context) {
        super(context);
    }
    public DefaultSrcollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}