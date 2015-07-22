package com.coolweather.app.util;

import java.net.Proxy.Type;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.coolweather.app.R;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;

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
			JSONObject areaInfo = jsonObject.getJSONObject("c");// ������Ϣ
			String publishTime = weatherInfo.getString("f0");// ���������󷢲�ʱ��
			JSONArray weatherArray = weatherInfo.getJSONArray("f1");// �����������
			JSONObject firstWeatherInfo = weatherArray.getJSONObject(0);// ��һ���������
			
			int areaID = areaInfo.getInt("c1");// ����ID
			//String areaNameEN = areaInfo.getString("c2");// ��������Ӣ��
			String areaNameCN = areaInfo.getString("c3");// ������������
			String dayWeather = WeatherCode.getWeather(firstWeatherInfo.getString("fa"));//�����������
			String nightWeather = WeatherCode.getWeather(firstWeatherInfo.getString("fb"));//ҹ��������� 
			saveWeatherInfo(context, areaNameCN, areaID, dayWeather, nightWeather,
					firstWeatherInfo.getString("fc"),
					firstWeatherInfo.getString("fd"), publishTime);

			//JSONObject secondWeatherInfo = weatherInfo.getJSONObject("f2");// �ڶ����������
			//JSONObject thirdWeatherInfo = weatherInfo.getJSONObject("f3");// �������������
			
		} catch (JSONException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

	/**
	 * �����������ص�����������Ϣ�洢��SharedPreferences�ļ��С�
	 */
	public static void saveWeatherInfo(Context context, String cityName,
			int cityID, String DaytimeWeather, String EveningWeather,
			String temp1, String temp2, String publishTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��", Locale.CHINA);
		SharedPreferences.Editor editor = PreferenceManager
				.getDefaultSharedPreferences(context).edit();
		editor.putBoolean("city_selected", true);
		editor.putInt("city_ID", cityID);
		editor.putString("city_name", cityName);
		editor.putString("daytimeWeather", DaytimeWeather);
		editor.putString("eveningWeather", EveningWeather);
		editor.putString("temp2", temp1 + "��");
		editor.putString("temp1", temp2);
	    try {
			SimpleDateFormat publishSDF = new SimpleDateFormat("yyyyMMddhhmm", Locale.CHINA);
			editor.putString("publish_time", getDate(publishSDF.parse(publishTime)));	    	
			editor.putString("current_date", sdf.format(new Date()));
		} catch (Exception e) {
			// TODO: handle exception
		}	
		editor.commit();

	}
	
	public static String GenerateURL(String CountyCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		String date = sdf.format(new Date());
		// ��Ҫ���ܵ�����
		
		String appID = "2c1c14deaa312a7b";
		String data = "http://open.weather.com.cn/data/?areaid=" + CountyCode
				+ "&type=forecast_v&date=" + date + "&appid=" + appID;
		String key = Util.standardURLEncoder(data,
				"9d60e7_SmartWeatherAPI_6d717b5");

		String url = "http://open.weather.com.cn/data/?areaid=" + CountyCode
				+ "&type=forecast_v&date=" + date + "&appid="
				+ appID.substring(0, 6);
		String tempString = url + "&key=" + key;
		return tempString;
	}

	public static String getDate(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm",Locale.CHINA);
		Date now = new Date();
		if(now.getDay()== date.getDay())
			return "����" + sdf.format(date)+"����";
		return "����" + sdf.format(date)+"����";
	}
}
