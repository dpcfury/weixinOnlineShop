package com.slf.wx.order.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.slf.wx.user.entity.DeliveryAddress;
import com.slf.wx.user.entity.User;
import com.slf.wx.util.CustomDateSerializer;

/**
 * Description:订单的实体类 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年7月8日
 * 
 */
@Entity
@Table(name = "SLF_ORDER")
public class Order {
	
	private String oId;
	private double summary;
	private Date createTime;
	private Set<OrderItem> items=new HashSet<OrderItem>();
	private User user;
	private DeliveryAddress address;
	private String msg;
	private int payWay;
	private int status;
	private int notifyFlag;
	private int confirmFlag;//订单后台是否接受确认表示为
	
	@Id
	@GeneratedValue(generator="order_id")
	@GenericGenerator(name="order_id",strategy="assigned")
	public String getoId() {
		return oId;
	}
	
	public void setoId(String oId) {
		this.oId = oId;
	}
	
	@Column(name="summary",columnDefinition="double default 0.0")
	@NotNull
	public double getSummary() {
		return summary;
	}
	public void setSummary(double summary) {
		this.summary = summary;
	}
	
	@Column(name="create_time")
	@NotNull
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="order_id",nullable=false)
	@JsonProperty
	public Set<OrderItem> getItems() {
		return items;
	}
	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="user_id")
	@NotNull
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="address_id",nullable=false)
	@NotNull
	@JsonProperty
	public DeliveryAddress getAddress() {
		return address;
	}
	public void setAddress(DeliveryAddress address) {
		this.address = address;
	}
	
	@Column(name="msg",columnDefinition="varchar(150) default '无'")
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Column(name="pay_way",columnDefinition="int default 1")
	public int getPayWay() {
		return payWay;
	}
	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}
	
	@Column(name="status",columnDefinition="int default 0")
	@NotNull
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	@Column(name="notify_flag",columnDefinition="int default 0")
	public int getNotifyFlag() {
		return notifyFlag;
	}
	public void setNotifyFlag(int notifyFlag) {
		this.notifyFlag = notifyFlag;
	}
	
	
	@Column(name="confirm_flag",columnDefinition="int default 0")
	public int getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(int confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public Order() {
		super();
		items.add(new OrderItem());
	}

	@Override
	public String toString() {
		return "Order [oId=" + oId + ", summary=" + summary + ", createTime=" + createTime + ", items=" + items
				+ ", user=" + user + ", address=" + address + ", msg=" + msg + ", payWay=" + payWay + ", status="
				+ status + ", notifyFlag=" + notifyFlag + ", confirmFlag=" + confirmFlag + "]";
	}

	
}
