package com.slf.wx.order.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.slf.wx.dao.BaseDaoImpl;
import com.slf.wx.order.constants.OrderNotifyStatus;
import com.slf.wx.order.constants.OrderQueryKind;
import com.slf.wx.order.entity.Order;
import com.slf.wx.pay.constant.PayStatus;
import com.slf.wx.pay.constant.PayWay;
import com.slf.wx.user.entity.User;

@Repository("orderDAO")
public class OrderDAOImpl extends BaseDaoImpl<Order, String> implements OrderDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getUnSentOrders(Date fromDate, Date endDate) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.between("createTime", fromDate, endDate))
				.add(Restrictions.eq("status", PayStatus.PAY_DONE))
				.add(Restrictions.eq("notifyFlag", OrderNotifyStatus.NOTIFY_UNDO));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // 设置ENTITY级的DISTINCT模式，根实体
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrders(User user, String kind) {
		Criteria criteria = createCriteria();
		switch (kind) {
		case OrderQueryKind.QUERY_UNPAY:
			criteria.add(Restrictions.eq("status", PayStatus.PAY_NOT_YET));
			break;
		case OrderQueryKind.QUERY_SUCCESS:
			criteria.add(Restrictions.eq("status", PayStatus.PAY_DONE));
			break;
		default:
			break;
		}
		criteria.add(Restrictions.eq("user", user));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // 设置ENTITY级的DISTINCT模式，根实体
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getUnSentTicOrders(Date fromDate, Date endDate) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.between("createTime", fromDate, endDate))
				.add(Restrictions.eq("payWay", PayWay.TICKET))
				.add(Restrictions.eq("notifyFlag", OrderNotifyStatus.NOTIFY_UNDO));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // 设置ENTITY级的DISTINCT模式，根实体
		return criteria.list();
		
	}

}
