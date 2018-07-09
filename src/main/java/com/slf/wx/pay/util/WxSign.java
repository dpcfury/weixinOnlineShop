package com.slf.wx.pay.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class WxSign {
	 private static String characterEncoding = "UTF-8";
	 
	 private static final Logger logger = LogManager.getLogger(WxSign.class);

     @SuppressWarnings("rawtypes")
     public static String createSign(SortedMap<Object,Object> parameters,String key){ 
       StringBuffer sb = new StringBuffer(); 
       Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序） 
       Iterator it = es.iterator(); 
       while(it.hasNext()) { 
         Map.Entry entry = (Map.Entry)it.next(); 
         String k = (String)entry.getKey(); 
         Object v = entry.getValue(); 
         if(null != v && !"".equals(v)  
             && !"sign".equals(k) && !"key".equals(k)) { 
           sb.append(k + "=" + v + "&"); 
         }
//         System.out.println(sb.toString());
       } 
       sb.append("key=" + key);
      logger.info("stringA&key::"+sb.toString());
       String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase(); 
       return sign; 
     }

     public static String getNonceStr() {
       Random random = new Random();
       return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
     }

     public static String getTimeStamp() {
       return String.valueOf(System.currentTimeMillis() / 1000);
     }
     
     public static String dateFormat(Date date){
    	 SimpleDateFormat fort=new SimpleDateFormat("yyyyMMddHHmmss");
    	 return fort.format(date);
     }
}
