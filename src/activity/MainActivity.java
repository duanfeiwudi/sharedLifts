package activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.example.sharelife.R;

public class MainActivity extends Activity {
	



	private double latitude = 0.0;
	private double longitude = 0.0;



	public AMapLocationClient mLocationClient = null;
	public AMapLocationClientOption mLocationOption = null;
	MapView mMapView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取地图控件引用
		mMapView = (MapView) findViewById(R.id.map);
		// 在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
		mMapView.onCreate(savedInstanceState);
		
		//初始化地图控制器对象
		AMap aMap = null;
		if (aMap == null) {
		    aMap = mMapView.getMap();        
		}
		
		// 声明定位回调监听器

		// 初始化定位
		mLocationClient = new AMapLocationClient(getApplicationContext());
		// 设置定位回调监听
		mLocationClient.setLocationListener(mAMapLocationListener);

		// 初始化AMapLocationClientOption对象
		mLocationOption = new AMapLocationClientOption();

		mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);

		mLocationOption.setOnceLocationLatest(true);
		mLocationOption.setNeedAddress(true);

		mLocationClient.setLocationOption(mLocationOption);
		// 启动定位
		mLocationClient.startLocation();
		
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
		mMapView.onPause();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// 在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState
		// (outState)，保存地图当前的状态
		mMapView.onSaveInstanceState(outState);
	}

	
	AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
		@Override
		public void onLocationChanged(AMapLocation amapLocation) {

			if (amapLocation != null) {
				if (amapLocation.getErrorCode() == 0) {
					double latitude1 = amapLocation.getLatitude();
					double longitude1 = amapLocation.getLongitude();
					latitude = latitude1;
					longitude = longitude1;
					Log.i("latitude", latitude1 + "");
					Log.i("longitude", longitude1 + "");

				} else {
					// 定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
					Log.e("AmapError", "location Error, ErrCode:"
							+ amapLocation.getErrorCode() + ", errInfo:"
							+ amapLocation.getErrorInfo());
				}
			}
		}

	};
}
