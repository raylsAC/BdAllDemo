package com.example.zhangzhuokui.bdalldemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by zhangzk on 2017/4/17.
 */

public class DefaultListView extends ListView {
    public DefaultListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public DefaultListView(Context context) {
        super(context);
    }
    public DefaultListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}