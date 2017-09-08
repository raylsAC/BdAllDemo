package com.example.zhangzhuokui.bdalldemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.example.zhangzhuokui.bdalldemo.R;
import com.example.zhangzhuokui.bdalldemo.listener.MapLocationListener;
import com.example.zhangzhuokui.bdalldemo.util.MapLocUtil;


/***
 * 单点定位示例，用来展示基本的定位结果，配置在LocationService.java中
 * 默认配置也可以在LocationService中修改
 * 默认配置的内容自于开发者论坛中对开发者长期提出的疑问内容
 * 
 * @author baidu
 *
 */
public class MapLocationActivity extends Activity {
	private MapLocUtil locationService;
	private TextView LocationResult;
	private Button startLocation;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// -----------demo view config ------------
		setContentView(R.layout.location);
		LocationResult = (TextView) findViewById(R.id.textView1);
		LocationResult.setMovementMethod(ScrollingMovementMethod.getInstance());
		startLocation = (Button) findViewById(R.id.addfence);

	}

	/**
	 * 显示请求字符串
	 * 
	 * @param str
	 */
	public void logMsg(String str) {
		final String s = str;
		try {
			if (LocationResult != null){
				new Thread(new Runnable() {
					@Override
					public void run() {
						LocationResult.post(new Runnable() {
							@Override
							public void run() {
								LocationResult.setText(s);
							}
						});

					}
				}).start();
			}
			//LocationResult.setText(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/***
	 * Stop location service
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		locationService.unregisterListener(); //注销掉监听
		locationService.stop(); //停止定位服务
		super.onStop();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		locationService = new MapLocUtil(getApplicationContext());
		//获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
		locationService.setLocationListener(mListener);

		//注册监听
		int type = getIntent().getIntExtra("from", 0);
		if (type == 0) {
			locationService.setLocationOption(locationService.getDefaultLocationClientOption());
		} else if (type == 1) {
			locationService.setLocationOption(locationService.getOption());
		}

		startLocation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (startLocation.getText().toString().equals("开始定位")) {
					locationService.start();// 定位SDK
											// start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
					startLocation.setText("停止定位");
				} else {
					locationService.stop();
					startLocation.setText("开始定位");
				}
			}
		});
	}

	private MapLocationListener mListener = new MapLocationListener() {
		@Override
		public void onSuccess(BDLocation location) {
//			if (null != location && location.getLocType() != BDLocation.TypeServerError) {
				StringBuffer sb = new StringBuffer(256);
				sb.append("time : ");
				/**
				 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
				 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
				 */
				sb.append(location.getTime());
				sb.append("\nlocType : ");// 定位类型
				sb.append(location.getLocType());
				sb.append("\nlocType description : ");// *****对应的定位类型说明*****
				sb.append(location.getLocTypeDescription());
				sb.append("\nlatitude : ");// 纬度
				sb.append(location.getLatitude());
				sb.append("\nlontitude : ");// 经度
				sb.append(location.getLongitude());
				sb.append("\nradius : ");// 半径
				sb.append(location.getRadius());
				sb.append("\nCountryCode : ");// 国家码
				sb.append(location.getCountryCode());
				sb.append("\nCountry : ");// 国家名称
				sb.append(location.getCountry());
				sb.append("\ncitycode : ");// 城市编码
				sb.append(location.getCityCode());
				sb.append("\ncity : ");// 城市
				sb.append(location.getCity());
				sb.append("\nDistrict : ");// 区
				sb.append(location.getDistrict());
				sb.append("\nStreet : ");// 街道
				sb.append(location.getStreet());
				sb.append("\naddr : ");// 地址信息
				sb.append(location.getAddrStr());
				sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
				sb.append(location.getUserIndoorState());
				sb.append("\nDirection(not all devices have value): ");
				sb.append(location.getDirection());// 方向
				sb.append("\nlocationdescribe: ");
				sb.append(location.getLocationDescribe());// 位置语义化信息
				sb.append("\nPoi: ");// POI信息
				if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
					for (int i = 0; i < location.getPoiList().size(); i++) {
						Poi poi = (Poi) location.getPoiList().get(i);
						sb.append(poi.getName() + ";");
					}
				}
				logMsg(sb.toString());
//			}
		}

		@Override
		public void onFail(BDLocation location) {

		}
	};


}
