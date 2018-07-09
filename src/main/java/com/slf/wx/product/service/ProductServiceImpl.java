package com.slf.wx.product.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.slf.wx.product.dao.ProductDAO;
import com.slf.wx.product.entity.Product;
import com.slf.wx.service.BaseService;

@Service
@Transactional
public class ProductServiceImpl extends BaseService implements ProductService{

	private static final Logger logger = LogManager
			.getLogger(ProductServiceImpl.class);
	
	@Resource
	private ProductDAO productDAO;
	
	@Override
	public Product addProduct(Product product) {
		validate(product);
		String id=productDAO.save(product);
		Product currentPro=productDAO.find(id);
		return currentPro;
	}

	@Override
	public Product getProductById(String id) {
		return productDAO.find(id);
	}
	
	
}
