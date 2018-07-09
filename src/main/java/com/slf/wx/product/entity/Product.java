package com.slf.wx.product.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
*	Description:所售卖的所有产品的父类 包含基本的产品信息
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年7月8日
 * 
 */
@Entity
@Table(name="SLF_PRODUCT")
@Inheritance(strategy=InheritanceType.JOINED)
public class Product {

	private String id;//产品的编号
	private String productName;//产品名称
	private double price;//产品单价
	private Date registerTime;//产品录入时间
	private String flag;//标志产品是否下架
	private String tag;//产品的标签
	
	public Product(){
		super();
	}
	
	@Id
	@GeneratedValue(generator="pro_uuid")
	@GenericGenerator(name="pro_uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="product_name")
	@NotNull
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(name="price")
	@NotNull
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(name="register_time")
	@NotNull
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
	@Column(name="tag")
	@NotNull
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name="flag",columnDefinition="varchar(10) default 'online'")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
	
}
