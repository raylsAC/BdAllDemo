package com.example.zhangzhuokui.bdalldemo.util;

import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.PoiResult;
import com.example.zhangzhuokui.bdalldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于显示marker的overly
 * new LatLng(22.542838, 113.959495));//创维半导体设计大厦
 * new LatLng(22.547244, 114.091833));//华强北地铁站
 */
public class MapNormalUtil extends OverlayManager {

    private static final int MAX_POI_SIZE = 20;
    public static final double LOCATION_LAT_CHUANGWEI = 22.542838;
    public static final double LOCATION_LOG_CHUANGWEI = 113.959495;
    public static final double LOCATION_LAT_HUAQIANGBEI = 22.547244;
    public static final double LOCATION_LOG_HUAQIANGBEI = 114.091833;


    private List<LatLng> mPoiResult = null;

    /**
     * 构造函数
     * 
     * @param baiduMap
     *            该 MapNormalUtil 引用的 BaiduMap 对象
     */
    public MapNormalUtil(BaiduMap baiduMap, MapView mapView) {
        super(baiduMap, mapView);
    }

    /**
     * 设置POI数据
     * 
     * @param poiResult
     *            设置POI数据(可以设置为自身的bean)
     */
    public void setData(List<LatLng> poiResult) {
        this.mPoiResult = poiResult;
    }

    @Override
    public final List<OverlayOptions> getOverlayOptions() {
        if (mPoiResult == null) {
            return null;
        }
        List<OverlayOptions> markerList = new ArrayList<OverlayOptions>();
        int markerSize = 0;
        for (int i = 0; i < mPoiResult.size()
                && markerSize < MAX_POI_SIZE; i++) {
            if (mPoiResult.get(i) == null) {
                continue;
            }
            markerSize++;
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            markerList.add(new MarkerOptions()
                    .icon(BitmapDescriptorFactory
//                     .fromAssetWithDpi("Icon_mark" + markerSize + ".png"))
                            .fromResource(R.drawable.icon_openmap_focuse_mark))
                    .extraInfo(bundle)
                    .position(mPoiResult.get(i)));
            
        }
        return markerList;
    }

    /**
     * 获取该 MapNormalUtil 的 poi数据
     * 
     * @return
     */
    public List<LatLng> getPoiResult() {
        return mPoiResult;
    }

    /**
     * 覆写此方法以改变默认点击行为
     * 
     * @param i
     *            被点击的poi在
     *            {@link com.baidu.mapapi.search.poi.PoiResult#getAllPoi()} 中的索引
     * @return
     */
    public boolean onPoiClick(Marker marker) {
//        if (mPoiResult.getAllPoi() != null
//                && mPoiResult.getAllPoi().get(i) != null) {
//            Toast.makeText(BMapManager.getInstance().getContext(),
//                    mPoiResult.getAllPoi().get(i).name, Toast.LENGTH_LONG)
//                    .show();
//        }
        return false;
    }

    public boolean onMyMapClick(LatLng latLng) {
//        if (mPoiResult.getAllPoi() != null
//                && mPoiResult.getAllPoi().get(i) != null) {
//            Toast.makeText(BMapManager.getInstance().getContext(),
//                    mPoiResult.getAllPoi().get(i).name, Toast.LENGTH_LONG)
//                    .show();
//        }
        return false;
    }

    //点击marker
    @Override
    public final boolean onMarkerClick(Marker marker) {
        if (!mOverlayList.contains(marker)) {
            return false;
        }

        if (marker.getExtraInfo() != null) {
//            for (int i = 0; i < mOverlayList.size(); i++) {
//                ((Marker)mOverlayList.get(i)).setIcon(BitmapDescriptorFactory
//                        .fromResource(R.drawable.poi_marker_normal));
//            }
//
//            marker.setIcon(BitmapDescriptorFactory
//                    .fromResource(R.drawable.poi_marker_click));
            return onPoiClick(marker);
        }
        return false;
    }

    @Override
    public boolean onPolylineClick(Polyline polyline) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public void onMapClick(LatLng latLng) {
        onMyMapClick(latLng);
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }
}
