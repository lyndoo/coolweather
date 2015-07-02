package com.coolweather.app.db;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	/**
	 * 数据库名
	 */
	public static final String DB_NAME = "cool_weather";

	/**
	 * 数据库版本
	 */
	public static final int VERSION = 1;

	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;

	/**
	 * 将构造方法私有化
	 */
	private CoolWeatherDB(Context context) {
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,
				DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * 获取CoolWeatherDB的实例。
	 */
	public synchronized static CoolWeatherDB getInstance(Context context) {
		if (coolWeatherDB == null)
			coolWeatherDB = new CoolWeatherDB(context);
		return coolWeatherDB;
	}
/*
 
	//将Province实例存储到数据库。
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}


	 //将City实例存储到数据库。
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);
		}
	}


	//将County实例存储到数据库。
	public void saveCounty(County county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);
		}
	}
*/
	
	/**
	 * 从数据库读取全国所有的省份信息。
	 * @return
	 */
	public List<Province> loadProvinces() {
		List<Province> list = new ArrayList<Province>();
		
		Cursor cursor = 
				db.query("area", 
						new String[]{"provcn,proven"}, null, null, "provcn,proven", null, "id");
		if (cursor.moveToFirst()) {
			do {
				Province province = new Province();
				province.setProvinceNameCN(cursor.getString(cursor.getColumnIndex("provcn")));
				province.setProvinceNameEN(cursor.getString(cursor.getColumnIndex("proven")));
				list.add(province);
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * 从数据库读取某省下所有的城市信息。
	 */
	public List<City> loadCity(String provinceNameEN) {
		List<City> citys = new ArrayList<City>();
		Cursor cursor = db.query("area", new String[]{"districten","districtcn"},
				"proven = ?",
				new String[] { provinceNameEN }, "districten,districtcn", null, "id");
		if (cursor.moveToFirst()) {
			do {
				City city = new City();
				city.setCityNameCN(cursor.getColumnName(cursor.getColumnIndex("districtcn")));
				city.setCityNameEN(cursor.getColumnName(cursor.getColumnIndex("districten")));
				citys.add(city);
			} while (cursor.moveToNext());
		}
		return citys;
	}
	

	/**
	 * 从数据库读取某城市下所有的县信息。
	 */
	public List<County> loadCounties(String cityNameEN) {
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query("area", 
				new String[]{"id","nameen","namecn","districtEN","districtEN","provEN","provCN"}, 
				"districten = ?",new String[] { cityNameEN }, null, null, "id");
		
		if (cursor.moveToFirst()) {
			do {
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyNameCN(cursor.getString(cursor
						.getColumnIndex("NameCN")));
				county.setCountyNameEN(cursor.getString(cursor
						.getColumnIndex("NameEN")));
				county.setDistrictEN(cursor.getString(cursor
						.getColumnIndex("districtEN")));
				county.setDistrictCN(cursor.getString(cursor
						.getColumnIndex("districtCN")));
				county.setProvCN(cursor.getString(cursor
						.getColumnIndex("provCN")));
				county.setProvEN(cursor.getString(cursor
						.getColumnIndex("provEN")));
				list.add(county);
			} while (cursor.moveToNext());
		}
		return list;
	}
	
	/**
	 * 根据定位名称获取信息
	 * @param cityNameCN
	 * @return
	 */
	public County loadCounty(String cityNameCN)
	{
		County county = new County();
		Cursor cursor=
		db.query("area", null, "nameCN = ? ", new String[]{cityNameCN}, null, null, null);
		if(cursor.moveToFirst())
		{
			county.setId(cursor.getInt(cursor.getColumnIndex("id")));
			county.setCountyNameCN(cursor.getString(cursor
					.getColumnIndex("NameCN")));
			county.setCountyNameEN(cursor.getString(cursor
					.getColumnIndex("NameEN")));
			county.setDistrictEN(cursor.getString(cursor
					.getColumnIndex("districtEN")));
			county.setDistrictCN(cursor.getString(cursor
					.getColumnIndex("districtCN")));
			county.setProvCN(cursor.getString(cursor
					.getColumnIndex("provCN")));
			county.setProvEN(cursor.getString(cursor
					.getColumnIndex("provEN")));
		}
		return county;
	}
}
