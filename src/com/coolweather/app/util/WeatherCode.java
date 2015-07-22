package com.coolweather.app.util;

public class WeatherCode {

	/**
	 * 根据编码返回天气
	 * 
	 * @param code
	 * @return
	 */
	public static String getWeather(String code) {
		if (code.equals("00"))
			return "晴";
		if (code.equals("01"))
			return "多云";
		if (code.equals("02"))
			return "阴";
		if (code.equals("03"))
			return "阵雨";
		if (code.equals("04"))
			return "雷阵雨";
		if (code.equals("05"))
			return "雷阵雨伴有冰雹";
		if (code.equals("06"))
			return "雨夹雪";
		if (code.equals("07"))
			return "小雨";
		if (code.equals("08"))
			return "中雨";
		if (code.equals("09"))
			return "大雨";
		if (code.equals("10"))
			return "暴雨";
		if (code.equals("11"))
			return "大暴雨";
		if (code.equals("12"))
			return "特大暴雨";
		if (code.equals("13"))
			return "阵雪";
		if (code.equals("14"))
			return "小雪";
		if (code.equals("15"))
			return "中雪";
		if (code.equals("16"))
			return "大雪";
		if (code.equals("17"))
			return "暴雪";
		if (code.equals("18"))
			return "雾";
		if (code.equals("19"))
			return "冻雨";
		if (code.equals("20"))
			return "沙尘暴";
		if (code.equals("21"))
			return "小到中雨";
		if (code.equals("22"))
			return "中到大雨";
		if (code.equals("23"))
			return "大到暴雨";
		if (code.equals("24"))
			return "暴雨到大暴雨";
		if (code.equals("25"))
			return "大暴雨到特大暴雨";
		if (code.equals("26"))
			return "小到中雪";
		if (code.equals("27"))
			return "中到大雪";
		if (code.equals("28"))
			return "大到暴雪";
		if (code.equals("29"))
			return "浮尘";
		if (code.equals("30"))
			return "扬沙";
		if (code.equals("31"))
			return "强沙尘暴";
		if (code.equals("53"))
			return "霾";
		if (code.equals("99"))
			return "无";
		return "未知";
	}

	/**
	 * 根据编码返回风向
	 * 
	 * @param code
	 * @return
	 */
	public static String getWindDirection(String code) {
		if (code.equals("0"))
			return "无持续风向";
		if (code.equals("1"))
			return "东北风 ";
		if (code.equals("2"))
			return "东风 ";
		if (code.equals("3"))
			return "东南风 ";
		if (code.equals("4"))
			return "南风 ";
		if (code.equals("5"))
			return "西南风 ";
		if (code.equals("6"))
			return "西风 ";
		if (code.equals("7"))
			return "西北风 ";
		if (code.equals("8"))
			return "北风 ";
		if (code.equals("9"))
			return "旋转风 ";
		return "未知";
	}

	/**
	 * 根据编码返回风力
	 * 
	 * @param code
	 * @return
	 */
	public static String getWindPower(String code) {
		if (code.equals("0"))
			return "微风 ";
		if (code.equals("1"))
			return "3-4级 ";
		if (code.equals("2"))
			return "4-5级 ";
		if (code.equals("3"))
			return "5-6级 ";
		if (code.equals("4"))
			return "6-7级 ";
		if (code.equals("5"))
			return "7-8级 ";
		if (code.equals("6"))
			return "8-9级 ";
		if (code.equals("7"))
			return "9-10级 ";
		if (code.equals("8"))
			return "10-11 级 ";
		if (code.equals("9"))
			return "11-12 级 ";

		return "未知";
	}
}
