package com.example.zhangzhuokui.bdalldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zhangzhuokui.bdalldemo.activity.LabelActivity;
import com.example.zhangzhuokui.bdalldemo.activity.MapLocationActivity;
import com.example.zhangzhuokui.bdalldemo.activity.MapNormalActivity;
import com.example.zhangzhuokui.bdalldemo.activity.MapRoutePlanActivity;
import com.example.zhangzhuokui.bdalldemo.activity.ParallaxToolbarScrollViewActivity;
import com.example.zhangzhuokui.bdalldemo.activity.TabLayoutActivity;
import com.example.zhangzhuokui.bdalldemo.adapter.TabLayoutAdapter;
import com.example.zhangzhuokui.bdalldemo.bikenavi.BNaviMainActivity;
import com.example.zhangzhuokui.bdalldemo.navi.BNDemoMainActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mMapBtn;
    private TextView mLocBtn;
    private TextView mNaviBtn;
    private TextView mRouteBtn;
    private TextView mBikeBtn;
    private TextView mLabelBtn;
    private TextView mToolBarBtn;
    private TextView mTabLayoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        mMapBtn = (TextView) findViewById(R.id.btn_map);
        mLocBtn = (TextView) findViewById(R.id.btn_location);
        mNaviBtn = (TextView) findViewById(R.id.btn_navi);
        mRouteBtn = (TextView) findViewById(R.id.btn_route);
        mBikeBtn = (TextView) findViewById(R.id.btn_bike);
        mLabelBtn = (TextView) findViewById(R.id.btn_label);
        mToolBarBtn = (TextView) findViewById(R.id.btn_toolbar);
        mTabLayoutBtn = (TextView) findViewById(R.id.btn_tablayout);

        mMapBtn.setOnClickListener(this);
        mLocBtn.setOnClickListener(this);
        mNaviBtn.setOnClickListener(this);
        mRouteBtn.setOnClickListener(this);
        mBikeBtn.setOnClickListener(this);
        mLabelBtn.setOnClickListener(this);
        mToolBarBtn.setOnClickListener(this);
        mTabLayoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_map:
                startActivity(new Intent(this, MapNormalActivity.class));
                break;
            case R.id.btn_location:
                startActivity(new Intent(this, MapLocationActivity.class));
                break;
            case R.id.btn_navi:
                startActivity(new Intent(this, BNDemoMainActivity.class));
                break;
            case R.id.btn_route:
                startActivity(new Intent(this, MapRoutePlanActivity.class));
                break;
            case R.id.btn_bike:
                startActivity(new Intent(this, BNaviMainActivity.class));
                break;
            case R.id.btn_label:
                startActivity(new Intent(this, LabelActivity.class));
                break;
            case R.id.btn_toolbar:
                startActivity(new Intent(this, ParallaxToolbarScrollViewActivity.class));
                break;
            case R.id.btn_tablayout:
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
            default:
                break;
        }
    }
}
