package com.coolweather.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.R;
import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseAreaActivity extends Activity {
	public static final int LEVEL_PROVINCE = 0;
	public static final int LEVEL_CITY = 1;
	public static final int LEVEL_COUNTY = 2;

	private ProgressDialog progressDialog;
	private TextView titleText;
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private CoolWeatherDB coolWeatherDB;
	private List<String> dataList = new ArrayList<String>();
	/**
	 * 当前选中的级别
	 */
	private int currentLevel;
	/**
	 * 省列表
	 */
	private List<Province> provinceList;
	/**
	 * 市列表
	 */
	private List<City> cityList;
	/**
	 * 县列表
	 */
	private List<County> countyList;
	/**
	 * 选中的省份
	 */
	private Province selectedProvince;
	/**
	 * 选中的城市
	 */
	private City selectedCity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_are);
		init();

	}

	private void init() {
		coolWeatherDB = CoolWeatherDB.getInstance(this);
		titleText = (TextView) findViewById(R.id.title_text);
		listView = (ListView) findViewById(R.id.list_view);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dataList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int index, long id) {
				if (currentLevel == LEVEL_PROVINCE) {
					selectedProvince = provinceList.get(index);
					queryCities();
				} else if (currentLevel == LEVEL_CITY) {
					selectedCity = cityList.get(index);
					queryCounties();
				}else  if (currentLevel == LEVEL_COUNTY) {
					Intent intent = new Intent(ChooseAreaActivity.this, WeatherActivity.class);
					County county = countyList.get(index);
					intent.putExtra("areaID", county.getId());
					intent.putExtra("areaNameCN", county.getCountyNameCN());
					startActivity(intent);
				}
			}
		});
		queryProvinces(); // 加载省级数据
	}

	@Override
	public void onBackPressed() {
		if (currentLevel == LEVEL_COUNTY) {
			queryCities();
		} else if (currentLevel == LEVEL_CITY) {
			queryProvinces();
		} else {
			finish();
		}

	}

	/**
	 * 显示进度对话框
	 */
	private void showProgressDialog() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("正在加载...");
			progressDialog.setCanceledOnTouchOutside(false);
		}
		progressDialog.show();
	}

	/**
	 * 关闭进度对话框
	 */
	private void closeProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
	}

	private void queryProvinces() {
		// TODO Auto-generated method stub
		provinceList = coolWeatherDB.loadProvinces();
		if (provinceList.size() > 0) {
			dataList.clear();
			for (Province p : provinceList) {
				dataList.add(p.getProvinceNameCN());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText("中国");
			currentLevel = LEVEL_PROVINCE;
		}
	}

	protected void queryCounties() {
		// TODO Auto-generated method stub
		countyList = coolWeatherDB.loadCounties(selectedCity.getCityNameEN());
		if (countyList.size() > 0) {
			dataList.clear();
			for (County county : countyList) {
				dataList.add(county.getCountyNameCN());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedCity.getCityNameCN());
			currentLevel = LEVEL_COUNTY;
		}
	}

	private void queryCities() {
		// TODO Auto-generated method stub
		cityList = coolWeatherDB.loadCity(selectedProvince.getProvinceNameEN());
		if (cityList.size() > 0) {
			dataList.clear();
			for (City city : cityList) {
				dataList.add(city.getCityNameCN());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedProvince.getProvinceNameCN());
			currentLevel = LEVEL_CITY;
		}
	}
}
