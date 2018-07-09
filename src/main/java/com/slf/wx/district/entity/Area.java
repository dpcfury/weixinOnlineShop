package com.slf.wx.district.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
*	Description:代表三级的区信息的实体类
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年7月15日
 * 
 */
@Entity
@Table(name="SLF_AREAS")
public class Area {

	private int id;
	private String areaId;
	private String areaName;
	private String cityId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="areaid")
	@NotNull
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	@Column(name="area")
	@NotNull
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@Column(name="cityid")
	@NotNull
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Area [id=" + id + ", areaId=" + areaId + ", areaName=" + areaName + ", cityId=" + cityId + "]";
	}
	
	
}
