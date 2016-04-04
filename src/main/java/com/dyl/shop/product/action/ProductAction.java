package com.dyl.shop.product.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dyl.shop.product.service.ProductService;
import com.dyl.shop.product.vo.Product;
import com.dyl.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class ProductAction extends ActionSupport implements
		ModelDriven<Product> {

	@Resource
	private ProductService productService;

	// 接收分类cid
	private Integer cid;
	// 接收二级分类id
	private Integer csid;
	// 接收当前页数:
	private int page;

	public void setPage(int page) {
		this.page = page;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	

	public Integer getCid() {
		return cid;
	}

	public Integer getCsid() {
		return csid;
	}


	Product product = new Product();

	public Product getModel() {
		return product;
	}

	/**
	 * 根据pid查询商品详细信息
	 * 
	 * @return
	 */
	public String findByPid() {

		product = productService.findByPid(product.getPid());

		return "findByPid";
		
	}

	/**
	 * 根据cid分类查询商品
	 */

	public String findByCid() {

		PageBean<Product> pageBean = productService.findByPageCid(cid, page);// 根据一级分类查询商品,带分页查询
		// 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findByCid";
	}
	
	// 根据二级分类id查询商品:
	public String findByCsid() {
		// 根据二级分类查询商品
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		// 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}
