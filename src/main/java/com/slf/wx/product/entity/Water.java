package com.slf.wx.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
*	Description:继承Product类的Water子类 代表售卖的水类型商品
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年7月8日
 * 
 */

@Entity
@Table(name="SLF_WATER")
@PrimaryKeyJoinColumn(name="product_id")
public class Water extends Product {

	private int volume;
	private String msg;
	
	@Column(name="volume")
	@NotNull
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	@Column(name="msg",columnDefinition="varchar(150) default '无'")
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "Water [volume=" + volume + ", msg=" + msg + ", getId()=" + getId() + ", getProductName()="
				+ getProductName() + ", getPrice()=" + getPrice() + ", getRegisterTime()=" + getRegisterTime()
				+ ", getTag()=" + getTag() + ", getFlag()=" + getFlag() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	

	
	
}
