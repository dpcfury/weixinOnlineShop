package com.slf.wx.product.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.slf.wx.product.entity.Product;
import com.slf.wx.product.exception.ProductNotFoundException;
import com.slf.wx.product.service.ProductService;
import com.slf.wx.util.IpTools;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

	private static final Logger logger = LogManager.getLogger(ProductController.class);
	@Resource
	private ProductService productService;

	@RequestMapping(value="/{id}.json",method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody Product getProduct(@PathVariable("id")String id, HttpServletRequest request) {
		// 安全日志记载
		String ip = IpTools.getIpAddr(request);
		logger.info("ip:" + ip + "发起了一次查看商品" + id + "信息的请求");

		// 业务逻辑
		Product product =productService.getProductById(id);
		if(product==null){
			throw new ProductNotFoundException();
		}else{
			return product;
		}
		
	}
}
