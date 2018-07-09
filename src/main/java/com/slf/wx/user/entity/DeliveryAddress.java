package com.slf.wx.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:用户的收货地址填写 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年7月7日
 * 
 */

@Entity
@Table(name = "SLF_DELIVERY_ADDRESS")
public class DeliveryAddress {

	private String id;// 数据库中的唯一编号 自动生成并递增
	private String province;// 省
	private String city;// 市
	private String area;// 区
	private String phone;// 收货人联系方式
	private String name;// 收货人姓名
	private String address;// 详细地址xx懂xx号
//	private User user;

	public DeliveryAddress() {
		super();
	}

	@Id
	@GeneratedValue(generator = "ad_uuid")
	@GenericGenerator(name="ad_uuid",strategy="uuid")
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "province", length = 50)
	@NotNull
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 60)
	@NotNull
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "district", length = 40)
	@NotNull
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "phone")
	@NotNull
	@Pattern(regexp = "^13\\d{9}|14[57]\\d{8}|15[012356789]\\d{8}|18[01256789]\\d{8}|170\\d{8}$", message = "手机号码格式不正确")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="name")
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="address",length=60)
	@NotNull
	public String getAddress() {
		return address;
	}

	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	public DeliveryAddress(String id, String province, String city, String area, String phone, String name,
			String address) {
		super();
		this.id = id;
		this.province = province;
		this.city = city;
		this.area = area;
		this.phone = phone;
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return "DeliveryAddress [id=" + id + ", province=" + province + ", city=" + city + ", district=" + area
				+ ", phone=" + phone + ", name=" + name + ", address=" + address + "]";
	}

	
	
}
