package com.example.zhangzhuokui.bdalldemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ZoomButton;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.zhangzhuokui.bdalldemo.R;
import com.example.zhangzhuokui.bdalldemo.listener.MapLocationListener;
import com.example.zhangzhuokui.bdalldemo.util.MapLocUtil;
import com.example.zhangzhuokui.bdalldemo.util.MapNormalUtil;
import com.example.zhangzhuokui.bdalldemo.util.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangzhuokui on 2017/8/27.
 */

public class MapNormalActivity extends Activity {

    private static final int zoom = 15;

    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private Button reset;
    private MapLocUtil locService;
    private LinkedList<LocationEntity> locationList = new LinkedList<LocationEntity>(); // 存放历史定位结果的链表，最大存放当前结果的前5次定位结果


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
//        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(17));
        mBaiduMap.setBuildingsEnabled(false);
        locService = new MapLocUtil(getApplicationContext());
        LocationClientOption mOption = locService.getDefaultLocationClientOption();
        mOption.setScanSpan(0);
        locService.setLocationOption(mOption);
        locService.setLocationListener(mMapLocationListener);
        locService.start();
    }


    MapLocationListener mMapLocationListener = new MapLocationListener() {
        @Override
        public void onSuccess(BDLocation location) {
            locService.stop();
            Message locMsg = locHander.obtainMessage();
            Bundle locData;
            locData = Algorithm(location);
            if (locData != null) {
                locData.putParcelable("loc", location);
                locMsg.setData(locData);
                locHander.sendMessage(locMsg);
            }

//            LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
//            // 构建Marker图标
//            BitmapDescriptor bitmap = null;
////            if (iscal == 0) {
////                bitmap = BitmapDescriptorFactory.fromResource(R.drawable.huaji); // 非推算结果
////            } else {
////            }
//            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_focuse_mark); // 推算结果
//
//            // 构建MarkerOption，用于在地图上添加Marker
//            OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
//            // 在地图上添加Marker，并显示
//            mBaiduMap.addOverlay(option);
//            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
        }

        @Override
        public void onFail(BDLocation location) {
            locService.start();
        }
    };

    /***
     * 平滑策略代码实现方法，主要通过对新定位和历史定位结果进行速度评分，
     * 来判断新定位结果的抖动幅度，如果超过经验值，则判定为过大抖动，进行平滑处理,若速度过快，
     * 则推测有可能是由于运动速度本身造成的，则不进行低速平滑处理 ╭(●｀∀´●)╯
     *
     * @param BDLocation
     * @return Bundle
     */
    private Bundle Algorithm(BDLocation location) {
        Bundle locData = new Bundle();
        double curSpeed = 0;
        if (locationList.isEmpty() || locationList.size() < 2) {
            LocationEntity temp = new LocationEntity();
            temp.location = location;
            temp.time = System.currentTimeMillis();
            locData.putInt("iscalculate", 0);
            locationList.add(temp);
        } else {
            if (locationList.size() > 5)
                locationList.removeFirst();
            double score = 0;
            for (int i = 0; i < locationList.size(); ++i) {
                LatLng lastPoint = new LatLng(locationList.get(i).location.getLatitude(),
                        locationList.get(i).location.getLongitude());
                LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
                double distance = DistanceUtil.getDistance(lastPoint, curPoint);
                curSpeed = distance / (System.currentTimeMillis() - locationList.get(i).time) / 1000;
                score += curSpeed * Utils.EARTH_WEIGHT[i];
            }
            if (score > 0.00000999 && score < 0.00005) { // 经验值,开发者可根据业务自行调整，也可以不使用这种算法
                location.setLongitude(
                        (locationList.get(locationList.size() - 1).location.getLongitude() + location.getLongitude())
                                / 2);
                location.setLatitude(
                        (locationList.get(locationList.size() - 1).location.getLatitude() + location.getLatitude())
                                / 2);
                locData.putInt("iscalculate", 1);
            } else {
                locData.putInt("iscalculate", 0);
            }
            LocationEntity newLocation = new LocationEntity();
            newLocation.location = location;
            newLocation.time = System.currentTimeMillis();
            locationList.add(newLocation);

        }
        return locData;
    }

    /***
     * 接收定位结果消息，并显示在地图上
     */
    private Handler locHander = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            try {
                BDLocation location = msg.getData().getParcelable("loc");
                int iscal = msg.getData().getInt("iscalculate");
                if (location != null) {
                    LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
                    LatLng point1 = new LatLng(22.548000, 113.959934);
                    // 构建Marker图标

                    List<LatLng> points = new ArrayList<>();
                    points.add(point);
                    points.add(point1);

                    MapNormalUtil util = new MyPoiOverlay(mBaiduMap, mMapView);
                    mBaiduMap.setOnMarkerClickListener(util);
                    mBaiduMap.setOnMapClickListener(util);
                    util.setData(points);

                    util.addToMap(true);
                    util.zoomToSpan(point, zoom);

//                    BitmapDescriptor bitmap = null;
//                    bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_focuse_mark);
//
//                    // 构建MarkerOption，用于在地图上添加Marker
//                    OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
//                    // 在地图上添加Marker，并显示
//                    mBaiduMap.addOverlay(option);
//                    mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
//		WriteLog.getInstance().close();
        locService.unregisterListener();
        locService.stop();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();

    }

    /**
     * 封装定位结果和时间的实体类
     *
     * @author baidu
     *
     */
    class LocationEntity {
        BDLocation location;
        long time;
    }


    private class MyPoiOverlay extends MapNormalUtil {

        public MyPoiOverlay(BaiduMap baiduMap, MapView mapView) {
            super(baiduMap, mapView);
        }

        @Override
        public boolean onPoiClick(Marker marker) {
            super.onPoiClick(marker);

            //创建InfoWindow展示的view
            Button button = new Button(getApplicationContext());
            button.setBackgroundResource(R.drawable.icon_openmap_mark);
            //定义用于显示该InfoWindow的坐标点
            LatLng pt = marker.getPosition();
            //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
            InfoWindow mInfoWindow = new InfoWindow(button, pt, -47);
            //显示InfoWindow
            mBaiduMap.showInfoWindow(mInfoWindow);
            Toast.makeText(MapNormalActivity.this, marker.getPosition().toString(), Toast.LENGTH_LONG).show();
            return true;
        }

        @Override
        public boolean onMyMapClick(LatLng latLng) {
            Toast.makeText(MapNormalActivity.this, latLng.toString(), Toast.LENGTH_LONG).show();

            return super.onMyMapClick(latLng);

        }
    }

}