package com.slf.wx.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
*	Description:自定义的序列化Date类型类
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年8月24日
 * 
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日hh时mm分");  
          String formattedDate = formatter.format(value);  
          jgen.writeString(formattedDate);  
	}

}
