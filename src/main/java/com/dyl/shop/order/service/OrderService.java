package com.dyl.shop.order.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dyl.shop.order.dao.OrderDao;
import com.dyl.shop.order.vo.Order;
import com.dyl.shop.order.vo.OrderItem;
import com.dyl.shop.utils.PageBean;

@Service
@Transactional
public class OrderService {

	@Resource
	private OrderDao orderDao;

	public void save(Order order) {

		orderDao.save(order);
	}

	public PageBean<Order> findByUidPage(Integer uid, Integer page) {

		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页
		pageBean.setPage(page);
		// 设置每页显示记录数
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = 0;
		totalCount = orderDao.findCountUid(uid);
		pageBean.setTotalCount(totalCount);

		// 设置总页数
		int totalPage = 0;
		if (totalCount % limit == 0) {

			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);

		// 每页显示的数据集合
		// 从哪开始
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public Order findByOid(Integer oid) {

		return orderDao.findByOid(oid);
	}

	public void update(Order currOrder) {

		orderDao.update(currOrder);
	}

	// 业务层查询所有订单方法
		public PageBean<Order> findAll(Integer page) {
			PageBean<Order> pageBean = new PageBean<Order>();
			// 设置参数
			pageBean.setPage(page);
			// 设置每页显示的记录数:
			int limit = 10;
			pageBean.setLimit(limit);
			// 设置总记录数
			int totalCount = orderDao.findCount();
			pageBean.setTotalCount(totalCount);
			// 设置总页数
			int totalPage = 0;
			if(totalCount % limit == 0){
				totalPage = totalCount / limit;
			}else{
				totalPage = totalCount / limit + 1;
			}
			pageBean.setTotalPage(totalPage);
			// 设置每页显示数据集合
			int begin = (page - 1) * limit;
			List<Order> list = orderDao.findPage(begin,limit);
			pageBean.setList(list);
			return pageBean;
		}


	public List<OrderItem> findOrderItem(Integer oid) {

		return orderDao.findOrderItem(oid);
	}

}
