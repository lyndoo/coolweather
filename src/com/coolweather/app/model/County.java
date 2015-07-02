package com.coolweather.app.model;

public class County {
	private int id;
	private String countyNameCN;
	private String countyNameEN;
	private String districtEN;
	private String districtCN;
	private String ProvCN;
	private String ProvEN;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvEN() {
		return ProvEN;
	}

	public void setProvEN(String provEN) {
		ProvEN = provEN;
	}

	public String getProvCN() {
		return ProvCN;
	}

	public void setProvCN(String provCN) {
		ProvCN = provCN;
	}

	public String getDistrictCN() {
		return districtCN;
	}

	public void setDistrictCN(String districtCN) {
		this.districtCN = districtCN;
	}

	public String getDistrictEN() {
		return districtEN;
	}

	public void setDistrictEN(String districtEN) {
		this.districtEN = districtEN;
	}

	public String getCountyNameEN() {
		return countyNameEN;
	}

	public void setCountyNameEN(String countyNameEN) {
		this.countyNameEN = countyNameEN;
	}

	public String getCountyNameCN() {
		return countyNameCN;
	}

	public void setCountyNameCN(String countyNameCN) {
		this.countyNameCN = countyNameCN;
	}

}
