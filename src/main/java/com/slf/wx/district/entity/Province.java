package com.slf.wx.district.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
*	Description:代表省信息的实体类
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年7月15日
 * 
 */
@Entity
@Table(name="SLF_PROVINCES")
public class Province {

	private int id;
	private String provinceId;
	private String provinceName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="provinceid")
	@NotNull
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	@Column(name="province")
	@NotNull
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Province() {
		super();
	}
	public Province(int id, String provinceId, String provinceName) {
		super();
		this.id = id;
		this.provinceId = provinceId;
		this.provinceName = provinceName;
	}
	@Override
	public String toString() {
		return "Province [id=" + id + ", provinceId=" + provinceId + ", provinceName=" + provinceName + "]";
	}
	
	
}
