package com.slf.wx.product.dao;

import org.springframework.stereotype.Repository;

import com.slf.wx.dao.BaseDaoImpl;
import com.slf.wx.product.entity.Product;

/**
*	Description:ProductDAO的具体实现类
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年7月8日
 * 
 */
@Repository("productDAO")
public class ProductDAOImpl extends BaseDaoImpl<Product,String> implements ProductDAO {
	
}
