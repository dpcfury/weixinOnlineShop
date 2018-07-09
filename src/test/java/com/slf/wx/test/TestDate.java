package com.slf.wx.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TestDate {

//	@Test
	public void testDate(){
		Date date =new Date();
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str=format.format(date);
		String year=str.split(" ")[0];
		String from =year+" 00:00:00";
		String end=year+"  12:00:00";
		System.out.println("from:"+from);
		System.out.println("end:"+end);
		try {
			System.out.println((Date)format.parse(from));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
