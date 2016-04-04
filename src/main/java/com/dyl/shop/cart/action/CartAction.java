package com.dyl.shop.cart.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.dyl.shop.cart.vo.Cart;
import com.dyl.shop.cart.vo.CartItem;
import com.dyl.shop.product.service.ProductService;
import com.dyl.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 购物车action
 * @author duyunlei
 * 2015-9-6 上午7:08:08
 *
 */

@Controller
@Scope("prototype") 
public class CartAction extends ActionSupport {

	// 接收pid
		private Integer pid;
		// 接收数量count
		private Integer count;
		// 注入商品的Service
		@Resource
		private ProductService productService;
		public void setPid(Integer pid) {
			this.pid = pid;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		// 将购物项添加到购物车:执行的方法
		public String addCart() {
			// 封装一个CartItem对象.
			CartItem cartItem = new CartItem();
			// 设置数量:
			cartItem.setCount(count);
			// 根据pid进行查询商品:
			Product product = productService.findByPid(pid);
			// 设置商品:
			cartItem.setProduct(product);
			// 将购物项添加到购物车.
			// 购物车应该存在session中.
			Cart cart = getCart();
			cart.addCart(cartItem);

			return "addCart";
		}

		/**
		 * 清空购物车
		 * @return
		 */
		public String clearCart(){
			
			Cart cart =getCart();
			cart.clearCart();
			return "clearCart";
			
		}
		/**
		 * 删除购物车条目
		 */
		
		public String removeCart(){
			
			Cart cart =getCart();
			
			cart.removeCart(pid);
			
			return "removeCart";
			
		}
		
		/**
		 * 我的购物车
		 */
		
		public String myCart(){
			
			return "myCart";
		}
		
		/**
		 * 获得购物车的方法:从session中获得购物车.
		 * @return
		 */
		private Cart getCart() {
			Cart cart = (Cart) ServletActionContext.getRequest().getSession()
					.getAttribute("cart");
			if (cart == null) {
				cart = new Cart();
				ServletActionContext.getRequest().getSession()
						.setAttribute("cart", cart);
			}
			return cart;
		}
	}


