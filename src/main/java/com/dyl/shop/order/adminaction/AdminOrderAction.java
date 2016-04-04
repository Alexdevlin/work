package com.dyl.shop.order.adminaction;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dyl.shop.order.service.OrderService;
import com.dyl.shop.order.vo.Order;
import com.dyl.shop.order.vo.OrderItem;
import com.dyl.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class AdminOrderAction extends ActionSupport implements
		ModelDriven<Order> {

	private Order order = new Order();
	@Resource
	private OrderService orderService;

	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	public Order getModel() {
		return order;
	}

	// 查询所有带分页的订单
	public String findAll() {

		PageBean<Order> pageBean = orderService.findAll(page);

		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findAll";
	}

	// 异步刷新订单详情
	public String findOrderItem() {

		List<OrderItem> list = orderService.findOrderItem(order.getOid());

		ActionContext.getContext().getValueStack().set("list", list);

		return "findOrderItem";

	}

	// 后台更新订单状态
	public String updateState() {

		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateState";
	}

}
