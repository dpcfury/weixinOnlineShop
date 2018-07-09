package com.slf.wx.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
*	Description:订单中的商品条目
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年7月8日
 * 
 */
@Entity
@Table(name="SLF_ORDER_ITEM")
public class OrderItem {

	private String itemId;//item的唯一标示
	private String proId;//该项代表的产品编号
	private String proName;//该项代表的产品名称
	private double proPrice;//该商品的单价
	private int num;//用户购买的该项产品的数量
	private double itemSummary;//该条目的总计金额
	
	@Id
	@GeneratedValue(generator="item_id")
	@GenericGenerator(name="item_id",strategy="uuid")
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	@Column(name="pro_id")
	@NotNull
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	
	@Column(name="pro_name")
	@NotNull
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	
	@Column(name="num")
	@NotNull
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	@Column(name="item_summary")
	@NotNull
	public double getItemSummary() {
		return itemSummary;
	}
	public void setItemSummary(double itemSummary) {
		this.itemSummary = itemSummary;
	}
	public OrderItem() {
		super();
	}
	
	@Column(name="pro_price")
	@NotNull
	public double getProPrice() {
		return proPrice;
	}
	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}
	@Override
	public String toString() {
		return "OrderItem [itemId=" + itemId + ", proId=" + proId + ", proName=" + proName + ", proPrice=" + proPrice
				+ ", num=" + num + ", itemSummary=" + itemSummary + "]";
	}
	
	
	
	
	
}
