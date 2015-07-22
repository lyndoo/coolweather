package com.coolweather.app.util;

public class WeatherCode {

	/**
	 * ���ݱ��뷵������
	 * 
	 * @param code
	 * @return
	 */
	public static String getWeather(String code) {
		if (code.equals("00"))
			return "��";
		if (code.equals("01"))
			return "����";
		if (code.equals("02"))
			return "��";
		if (code.equals("03"))
			return "����";
		if (code.equals("04"))
			return "������";
		if (code.equals("05"))
			return "��������б���";
		if (code.equals("06"))
			return "���ѩ";
		if (code.equals("07"))
			return "С��";
		if (code.equals("08"))
			return "����";
		if (code.equals("09"))
			return "����";
		if (code.equals("10"))
			return "����";
		if (code.equals("11"))
			return "����";
		if (code.equals("12"))
			return "�ش���";
		if (code.equals("13"))
			return "��ѩ";
		if (code.equals("14"))
			return "Сѩ";
		if (code.equals("15"))
			return "��ѩ";
		if (code.equals("16"))
			return "��ѩ";
		if (code.equals("17"))
			return "��ѩ";
		if (code.equals("18"))
			return "��";
		if (code.equals("19"))
			return "����";
		if (code.equals("20"))
			return "ɳ����";
		if (code.equals("21"))
			return "С������";
		if (code.equals("22"))
			return "�е�����";
		if (code.equals("23"))
			return "�󵽱���";
		if (code.equals("24"))
			return "���굽����";
		if (code.equals("25"))
			return "���굽�ش���";
		if (code.equals("26"))
			return "С����ѩ";
		if (code.equals("27"))
			return "�е���ѩ";
		if (code.equals("28"))
			return "�󵽱�ѩ";
		if (code.equals("29"))
			return "����";
		if (code.equals("30"))
			return "��ɳ";
		if (code.equals("31"))
			return "ǿɳ����";
		if (code.equals("53"))
			return "��";
		if (code.equals("99"))
			return "��";
		return "δ֪";
	}

	/**
	 * ���ݱ��뷵�ط���
	 * 
	 * @param code
	 * @return
	 */
	public static String getWindDirection(String code) {
		if (code.equals("0"))
			return "�޳�������";
		if (code.equals("1"))
			return "������ ";
		if (code.equals("2"))
			return "���� ";
		if (code.equals("3"))
			return "���Ϸ� ";
		if (code.equals("4"))
			return "�Ϸ� ";
		if (code.equals("5"))
			return "���Ϸ� ";
		if (code.equals("6"))
			return "���� ";
		if (code.equals("7"))
			return "������ ";
		if (code.equals("8"))
			return "���� ";
		if (code.equals("9"))
			return "��ת�� ";
		return "δ֪";
	}

	/**
	 * ���ݱ��뷵�ط���
	 * 
	 * @param code
	 * @return
	 */
	public static String getWindPower(String code) {
		if (code.equals("0"))
			return "΢�� ";
		if (code.equals("1"))
			return "3-4�� ";
		if (code.equals("2"))
			return "4-5�� ";
		if (code.equals("3"))
			return "5-6�� ";
		if (code.equals("4"))
			return "6-7�� ";
		if (code.equals("5"))
			return "7-8�� ";
		if (code.equals("6"))
			return "8-9�� ";
		if (code.equals("7"))
			return "9-10�� ";
		if (code.equals("8"))
			return "10-11 �� ";
		if (code.equals("9"))
			return "11-12 �� ";

		return "δ֪";
	}
}
