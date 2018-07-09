package com.slf.wx.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:代表客服人员 即实际的后台使用者 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年9月9日
 * 
 */
@Entity
@Table(name="SLF_STAFF")
public class CSStaff {

	private String sID;
	private String sName;//登录名
	private String rName;//实际姓名
	private String password;
	

	@Id
	@GeneratedValue(generator="order_id")
	@GenericGenerator(name="order_id",strategy="assigned")
	public String getsID() {
		return sID;
	}

	public void setsID(String sID) {
		this.sID = sID;
	}

	@Column(name="staff_name")
	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Column(name="real_name")
	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}
	
	

	public CSStaff() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CSStaff [sID=" + sID + ", sName=" + sName + ", rName=" + rName + ", password=" + password + "]";
	}

	

	
	
}
