package com.example.zhangzhuokui.bdalldemo.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangzhuokui.bdalldemo.R;


/**
 * Created by zhangzhuokui on 2017/9/6.
 */

public class SnackbarUtil {

    public static void show(View view, String msg){
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    public static void show(Context context, View view, String title, String subTitle){

        Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);

        View snackbarview = snackbar.getView();//获取snackbar的View(其实就是SnackbarLayout)
        snackbarview.setBackgroundColor(context.getApplicationContext().getResources().getColor(R.color.black_08));
        snackbarview.setAlpha(0.8f);
        Snackbar.SnackbarLayout snackbarLayout=(Snackbar.SnackbarLayout)snackbarview;//将获取的View转换成SnackbarLayout

        View add_view = LayoutInflater.from(snackbarview.getContext()).inflate(R.layout.view_snackbar,null);//加载布局文件新建View
        TextView desc1 = (TextView) add_view.findViewById(R.id.snackbar_desc1);
        TextView desc2 = (TextView) add_view.findViewById(R.id.snackbar_desc2);

        desc1.setText(title);
        desc2.setText(subTitle);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);//设置新建布局参数
        p.gravity= Gravity.CENTER_VERTICAL;//设置新建布局在Snackbar内垂直居中显示

        snackbarLayout.addView(add_view,0,p);//将新建布局添加进snackbarLayout相应位置

        snackbar.show();

    }


}
