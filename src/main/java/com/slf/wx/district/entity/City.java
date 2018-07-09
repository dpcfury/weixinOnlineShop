package com.slf.wx.district.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
*	Description:代表市级别的实体类
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年7月15日
 * 
 */
@Entity
@Table(name="SLF_CITIES")
public class City {

	private int id;
	private String cityId;
	private String cityName;
	private String provinceId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="cityid")
	@NotNull
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	@Column(name="city")
	@NotNull
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Column(name="provinceid")
	@NotNull
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public City() {
		super();
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", cityId=" + cityId + ", cityName=" + cityName + ", provinceId=" + provinceId + "]";
	}
	
	
	
}
