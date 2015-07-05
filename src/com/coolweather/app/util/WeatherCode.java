package com.coolweather.app.util;

public enum WeatherCode {
	晴	("00"),	//晴
	多云	("01"),	//多云
	Overcast	("02"),	//阴
	Shower	("03"),	//阵雨
	Thundershower	("04"),	//雷阵雨
	ThundershowerWithHail	("05"),	//雷阵雨伴有冰雹
	Sleet	("06"),	//雨夹雪
	LightRain	("07"),	//小雨
	ModerateRain	("08"),	//中雨
	HeavyRain	("09"),	//大雨
	Storm	("10"),	//暴雨
	HeavyStorm	("11"),	//大暴雨
	SevereStorm	("12"),	//特大暴雨
	SnowFlurry	("13"),	//阵雪
	LightSnow	("14"),	//小雪
	ModerateSnow	("15"),	//中雪
	HeavySnow	("16"),	//大雪
	Snowstorm	("17"),	//暴雪
	Foggy	("18"),	//雾
	IceRain	("19"),	//冻雨
	Duststorm	("20"),	//沙尘暴
	LightModerateRain	("21"),	//小到中雨
	ModerateHeavyRain	("22"),	//中到大雨
	HeavyRainStorm	("23"),	//大到暴雨
	StormHeavyStorm	("24"),	//暴雨到大暴雨
	HeavySevereStorm	("25"),	//大暴雨到特大暴雨
	LightModerateSnow	("26"),	//小到中雪
	ModerateHeavySnow	("27"),	//中到大雪
	HeavySnowSnowstorm	("28"),	//大到暴雪
	Dust	("29"),	//浮尘
	Sand	("30"),	//扬沙
	Sandstorm	("31"),	//强沙尘暴
	Haze	("53"),	//霾
	Unknown	("99");//无

	
    private final String value;
    
	WeatherCode(String value){
		this.value = value;
	}
    public String getValue() {
        return value;
    }
}
