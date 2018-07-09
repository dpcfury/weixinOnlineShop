package com.slf.wx.order.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OIDGenerator {

	public static String createOID() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String order_no = sdf.format(date) + "" + ((int) (Math.random() * 99999 + 100000));
		return order_no;
	}
}
