package com.example.zhangzhuokui.bdalldemo.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhangzhuokui.bdalldemo.R;
import com.example.zhangzhuokui.bdalldemo.util.SnackbarUtil;

public class LabelActivity extends FragmentActivity {

    private RelativeLayout mSnackerbarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        mSnackerbarBtn = (RelativeLayout) findViewById(R.id.btn_snacker);

        mSnackerbarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);
                setSnackbarColor(snackbar, getResources().getColor(R.color.accent), getResources().getColor(R.color.colorAccent));
                snackbar.show();
            }


        });

        SnackbarUtil.show(mSnackerbarBtn, "123456");
    }

    public  void setSnackbarColor(Snackbar snackbar, int messageColor, int backgroundColor) {

        View snackbarview = snackbar.getView();//获取snackbar的View(其实就是SnackbarLayout)

        snackbarview.setBackgroundColor(getResources().getColor(R.color.black_08));
        snackbarview.setAlpha(0.8f);
        Snackbar.SnackbarLayout snackbarLayout=(Snackbar.SnackbarLayout)snackbarview;//将获取的View转换成SnackbarLayout

        View add_view = LayoutInflater.from(snackbarview.getContext()).inflate(R.layout.view_snackbar,null);//加载布局文件新建View

        TextView desc1 = (TextView) add_view.findViewById(R.id.snackbar_desc1);
        TextView desc2 = (TextView) add_view.findViewById(R.id.snackbar_desc2);

        desc1.setText("网络好像断开了");
        desc2.setText("请确认网络连接再刷新");

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);//设置新建布局参数

        p.gravity= Gravity.CENTER_VERTICAL;//设置新建布局在Snackbar内垂直居中显示

        snackbarLayout.addView(add_view,0,p);//将新建布局添加进snackbarLayout相应位置

    }


}
