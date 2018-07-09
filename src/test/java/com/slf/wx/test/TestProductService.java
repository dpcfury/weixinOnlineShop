package com.slf.wx.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.slf.wx.product.entity.Water;
import com.slf.wx.product.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestProductService {
	
	@Resource
	private ProductService productService;
	
//	@Test
	public void testAddProduct(){
		Water water=new Water();
		water.setMsg("来自于天然水");
		water.setPrice(50);
		water.setProductName("吉氧山泉");
		water.setRegisterTime(new Date(System.currentTimeMillis()));
		water.setTag("泉水");
		productService.addProduct(water);
	}
	
//	@Test
	public void testGetProductById(){
		System.out.println((Water)productService.getProductById("402881e755c940be0155c940c2ff0000"));
	}
}
