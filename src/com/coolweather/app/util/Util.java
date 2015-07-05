package com.coolweather.app.util;

import java.net.Proxy.Type;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

public class Util {

	private static final char last2byte = (char) Integer
			.parseInt("00000011", 2);
	private static final char last4byte = (char) Integer
			.parseInt("00001111", 2);
	private static final char last6byte = (char) Integer
			.parseInt("00111111", 2);
	private static final char lead6byte = (char) Integer
			.parseInt("11111100", 2);
	private static final char lead4byte = (char) Integer
			.parseInt("11110000", 2);
	private static final char lead2byte = (char) Integer
			.parseInt("11000000", 2);
	private static final char[] encodeTable = new char[] { 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', '+', '/' };

	public static String standardURLEncoder(String data, String key) {
		byte[] byteHMAC = null;
		String urlEncoder = "";
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
			mac.init(spec);
			byteHMAC = mac.doFinal(data.getBytes());
			if (byteHMAC != null) {
				String oauth = encode(byteHMAC);
				if (oauth != null) {
					urlEncoder = URLEncoder.encode(oauth, "utf8");
				}
			}
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return urlEncoder;
	}

	public static String encode(byte[] from) {
		StringBuffer to = new StringBuffer((int) (from.length * 1.34) + 3);
		int num = 0;
		char currentByte = 0;
		for (int i = 0; i < from.length; i++) {
			num = num % 8;
			while (num < 8) {
				switch (num) {
				case 0:
					currentByte = (char) (from[i] & lead6byte);
					currentByte = (char) (currentByte >>> 2);
					break;
				case 2:
					currentByte = (char) (from[i] & last6byte);
					break;
				case 4:
					currentByte = (char) (from[i] & last4byte);
					currentByte = (char) (currentByte << 2);
					if ((i + 1) < from.length) {
						currentByte |= (from[i + 1] & lead2byte) >>> 6;
					}
					break;
				case 6:
					currentByte = (char) (from[i] & last2byte);
					currentByte = (char) (currentByte << 4);
					if ((i + 1) < from.length) {
						currentByte |= (from[i + 1] & lead4byte) >>> 4;
					}
					break;
				}
				to.append(encodeTable[currentByte]);
				num += 6;
			}
		}
		if (to.length() % 4 != 0) {
			for (int i = 4 - to.length() % 4; i > 0; i--) {
				to.append("=");
			}
		}
		return to.toString();
	}

	public static void handleWeatherRespose(Context context, String response) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			JSONObject weatherInfo = jsonObject.getJSONObject("f");
			JSONObject areaInfo = jsonObject.getJSONObject("c");// 地区信息
			String publishTime = weatherInfo.getString("f0");// 天气情况最后发布日期
			JSONObject firstWeatherInfo = weatherInfo.getJSONObject("f1");// 第一天天气情况
			//JSONObject secondWeatherInfo = weatherInfo.getJSONObject("f2");// 第二天天气情况
			//JSONObject thirdWeatherInfo = weatherInfo.getJSONObject("f3");// 第三天天气情况

			int areaID = areaInfo.getInt("c1");// 城市ID
			//String areaNameEN = areaInfo.getString("c2");// 城市名字英文
			String areaNameCN = areaInfo.getString("c3");// 城市名字中文
			WeatherCode fa = WeatherCode.valueOf(firstWeatherInfo
					.getString("fa"));
			WeatherCode fb = WeatherCode.valueOf(firstWeatherInfo
					.getString("fb"));
			saveWeatherInfo(context, areaNameCN, areaID, fa.name(), fb.name(),
					firstWeatherInfo.getString("fc"),
					firstWeatherInfo.getString("fd"), publishTime);

		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 将服务器返回的所有天气信息存储到SharedPreferences文件中。
	 */
	public static void saveWeatherInfo(Context context, String cityName,
			int cityID, String DaytimeWeather, String EveningWeather,
			String temp1, String temp2, String publishTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日", Locale.CHINA);
		SharedPreferences.Editor editor = PreferenceManager
				.getDefaultSharedPreferences(context).edit();
		editor.putBoolean("city_selected", true);
		editor.putInt("city_ID", cityID);
		editor.putString("city_name", cityName);
		editor.putString("daytimeWeather", DaytimeWeather);
		editor.putString("eveningWeather", EveningWeather);
		editor.putString("temp1", temp1);
		editor.putString("temp2", temp2);
		editor.putString("publish_time", publishTime);
		editor.putString("current_date", sdf.format(new Date()));
		editor.commit();

	}
}
