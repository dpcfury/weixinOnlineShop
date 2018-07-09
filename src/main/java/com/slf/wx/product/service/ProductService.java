package com.slf.wx.product.service;

import com.slf.wx.product.entity.Product;

/**
*	Description:提供产品类信息服务的接口
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年7月8日
 * 
 */
public interface ProductService {
	
	//新增加一个产品
	public Product addProduct(Product product);
	
	//根据id查询产品
	public Product getProductById(String id);
	
}
