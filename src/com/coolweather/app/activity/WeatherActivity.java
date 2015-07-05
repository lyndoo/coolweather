package com.coolweather.app.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.coolweather.app.R;
import com.coolweather.app.util.HttpCallbackListener;
import com.coolweather.app.util.HttpUtil;
import com.coolweather.app.util.Util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeatherActivity extends Activity {
	private LinearLayout weatherInfoLayout;
	/**
	 * ������ʾ������
	 */
	private TextView cityNameText;
	/**
	 * ������ʾ����ʱ��
	 */
	private TextView publishText;
	/**
	 * ������ʾ����������Ϣ
	 */
	private TextView weatherDespText;
	/**
	 * ������ʾ����1
	 */
	private TextView temp1Text;
	/**
	 * ������ʾ����2
	 */
	private TextView temp2Text;
	/**
	 * ������ʾ��ǰ����
	 */
	private TextView currentDateText;
	/**
	 * �л����а�ť
	 */
	private Button switchCity;
	/**
	 * ����������ť
	 */
	private Button refreshWeather;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weather_layout);
		test();

	}

	void init(){
		// ��ʼ�����ؼ�
		weatherInfoLayout = (LinearLayout) findViewById(R.id.weather_info_layout);
				cityNameText = (TextView) findViewById(R.id.city_name);
				publishText = (TextView) findViewById(R.id.publish_text);
				weatherDespText = (TextView) findViewById(R.id.weather_desp);
				temp1Text = (TextView) findViewById(R.id.temp1);
				temp2Text = (TextView) findViewById(R.id.temp2);
				currentDateText = (TextView) findViewById(R.id.current_date);
				//switchCity = (Button) findViewById(R.id.switch_city);
				//refreshWeather = (Button) findViewById(R.id.refresh_weather);
				String countyCode = getIntent().getStringExtra("areaID");
				String countyNameCN = getIntent().getStringExtra("areaNameCN");
				if (!TextUtils.isEmpty(countyCode)) {
					// ���ؼ�����ʱ��ȥ��ѯ����
					publishText.setText("ͬ����...");
					weatherInfoLayout.setVisibility(View.INVISIBLE);
					cityNameText.setVisibility(View.INVISIBLE);
					queryWeatherCode(countyCode);
					} else {
					// û���ؼ�����ʱ��ֱ����ʾ��������
						showWeather();
					}
					//switchCity.setOnClickListener(this);
					//refreshWeather.setOnClickListener(this);
	}

	private void queryWeatherCode(String countyCode) {
		// TODO �Զ����ɵķ������
		
	}

	private void showWeather() {
		// TODO �Զ����ɵķ������
		
	}

	private void test() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
			String date = sdf.format(new Date());
			// ��Ҫ���ܵ�����
			String appID = this.getString(R.string.appID);
			String data = "http://open.weather.com.cn/data/?areaid=101010200&type=forecast_v&date="
					+ date + "&appid=" + appID;
			String key = Util.standardURLEncoder(data,
					this.getString(R.string.Private_Key));

			String url = "http://open.weather.com.cn/data/?areaid=101010200&type=forecast_v&date="
					+ date + "&appid=" + appID.substring(0, 6);

			Log.e("return response", key);
			Log.e("return response", url + "&key=" + key);

			HttpUtil.sendHttpRequest(url + "&key=" + key,
					new HttpCallbackListener() {
						@Override
						public void onFinish(String response) {
							// TODO �Զ����ɵķ������
							Log.e("return response", response);
							Util.handleWeatherRespose(getApplicationContext(),
									response);
						}

						@Override
						public void onError(Exception e) {
							// TODO �Զ����ɵķ������

						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
