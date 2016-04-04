package com.dyl.shop.order.dao;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dyl.shop.order.vo.Order;
import com.dyl.shop.order.vo.OrderItem;

@Repository
public class OrderDao {

	@Resource
	private SessionFactory sessionFactory;

	// 保存订单
	public void save(Order order) {

		sessionFactory.getCurrentSession().save(order);

	}

	// 根据uid查询订单总数
	public int findCountUid(Integer uid) {
		Long count = (Long) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(*) from Order o where o.user.uid = ?")//
				.setParameter(0, uid).uniqueResult();

		return count.intValue();
	}

	// 根据uid查询订单的集合
	public List<Order> findByPageUid(Integer uid, int begin, int limit) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Order o where o.user.uid = ? order by o.ordertime desc")//
				.setParameter(0, uid)//
				.setFirstResult(begin)// /
				.setMaxResults(limit)//
				.list();

	}

	public Order findByOid(Integer oid) {
		return (Order) sessionFactory.getCurrentSession()
				.createQuery("from Order o where o.oid=?").setParameter(0, oid)
				.uniqueResult();
	}

	public void update(Order currOrder) {

		sessionFactory.getCurrentSession().update(currOrder);

	}

	public int findCount() {

		Long count = (Long) sessionFactory.getCurrentSession()
				.createQuery("select count(*) from Order ")//
				.uniqueResult();
		if (count != null) {
			return count.intValue();
		} else {

			return 0;
		}
	}

	public List<Order> findPage(int begin, int limit) {

		return sessionFactory.getCurrentSession()
				.createQuery("from Order order by ordertime desc ")
				.setFirstResult(begin).setMaxResults(limit).list();
	}

	public List<OrderItem> findOrderItem(Integer oid) {

		return sessionFactory.getCurrentSession()
				.createQuery("from OrderItem o where o.order.oid = ?")
				.setParameter(0, oid).list();
	}

}
