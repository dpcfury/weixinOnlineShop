package com.slf.wx.user.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description:消费的用户（包括线上注册的和线下的录入） 
 * Company:苏州咕噜信息科技有限公司
 * @author dpc
 * @date 2016年7月6日
 * 
 */
@Entity
@Table(name = "SLF_USER")
public class User {

	private String id;
	private String name;
	private String phone;
	private String other_resident;
	private int level;
	private Date birthday;
	private int tickets_left;
	private int tickets_total;
	private Date register_time;
	private String province;
	private String city;
	private String area;
	private String address;
	private Set<DeliveryAddress> deliveryAddresses=new HashSet<DeliveryAddress>();

	public User() {
		super();
	}

	@Id
	@Column(name = "id", length = 32, nullable = false, unique = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", length = 20, nullable = false)
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone", nullable = false)
	@Pattern(regexp = "^13\\d{9}|14[57]\\d{8}|15[012356789]\\d{8}|18[01256789]\\d{8}|170\\d{8}$", message = "手机号码格式不正确")
	@NotNull
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "other_resident", length = 120)
	public String getOther_resident() {
		return other_resident;
	}

	public void setOther_resident(String other_resident) {
		this.other_resident = other_resident;
	}

	@Column(name = "level", columnDefinition = "int default 1")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Column(name = "birthday")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "tickets_left", columnDefinition = "int default 0")
	public int getTickets_left() {
		return tickets_left;
	}

	public void setTickets_left(int tickets_left) {
		this.tickets_left = tickets_left;
	}

	@Column(name="tickets_total",columnDefinition="int default 0")
	public int getTickets_total() {
		return tickets_total;
	}

	public void setTickets_total(int tickets_total) {
		this.tickets_total = tickets_total;
	}
	
	@Column(name="register_time",nullable=false)
	@NotNull
	public Date getRegister_time() {
		return register_time;
	}


	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}

	@Column(name="province")
	public String getProvince() {
		return province;
	}

	
	public void setProvince(String province) {
		this.province = province;
	}
	
	@Column(name="city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name="area")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	
	@Column(name="address",nullable=false)
	@NotNull
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="user_pk",nullable=false)
	@JsonProperty
	public Set<DeliveryAddress> getDeliveryAddresses() {
		return deliveryAddresses;
	}
	 
	
	
	public void setDeliveryAddresses(Set<DeliveryAddress> deliveryAddresses) {
		this.deliveryAddresses = deliveryAddresses;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", other_resident=" + other_resident
				+ ", level=" + level + ", birthday=" + birthday + ", tickets_left=" + tickets_left + ", tickets_total="
				+ tickets_total + ", register_time=" + register_time + ", province=" + province + ", city=" + city
				+ ", area=" + area + ", address=" + address + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

}
