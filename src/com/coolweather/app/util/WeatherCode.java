package com.coolweather.app.util;

public enum WeatherCode {
	��	("00"),	//��
	����	("01"),	//����
	Overcast	("02"),	//��
	Shower	("03"),	//����
	Thundershower	("04"),	//������
	ThundershowerWithHail	("05"),	//��������б���
	Sleet	("06"),	//���ѩ
	LightRain	("07"),	//С��
	ModerateRain	("08"),	//����
	HeavyRain	("09"),	//����
	Storm	("10"),	//����
	HeavyStorm	("11"),	//����
	SevereStorm	("12"),	//�ش���
	SnowFlurry	("13"),	//��ѩ
	LightSnow	("14"),	//Сѩ
	ModerateSnow	("15"),	//��ѩ
	HeavySnow	("16"),	//��ѩ
	Snowstorm	("17"),	//��ѩ
	Foggy	("18"),	//��
	IceRain	("19"),	//����
	Duststorm	("20"),	//ɳ����
	LightModerateRain	("21"),	//С������
	ModerateHeavyRain	("22"),	//�е�����
	HeavyRainStorm	("23"),	//�󵽱���
	StormHeavyStorm	("24"),	//���굽����
	HeavySevereStorm	("25"),	//���굽�ش���
	LightModerateSnow	("26"),	//С����ѩ
	ModerateHeavySnow	("27"),	//�е���ѩ
	HeavySnowSnowstorm	("28"),	//�󵽱�ѩ
	Dust	("29"),	//����
	Sand	("30"),	//��ɳ
	Sandstorm	("31"),	//ǿɳ����
	Haze	("53"),	//��
	Unknown	("99");//��

	
    private final String value;
    
	WeatherCode(String value){
		this.value = value;
	}
    public String getValue() {
        return value;
    }
}
