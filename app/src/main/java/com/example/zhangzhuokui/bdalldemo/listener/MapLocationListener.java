package com.example.zhangzhuokui.bdalldemo.listener;

import com.baidu.location.BDLocation;

/**
 * Created by zhangzhuokui on 2017/8/27.
 */

public interface MapLocationListener {

    void onSuccess(BDLocation location);

    void onFail(BDLocation location);
}
