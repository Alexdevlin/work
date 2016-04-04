package com.dyl.shop.index.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dyl.shop.category.service.CategoryService;
import com.dyl.shop.category.vo.Category;
import com.dyl.shop.product.service.ProductService;
import com.dyl.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller
@Scope("prototype")
public class IndexAction extends ActionSupport{
	
	//注入一级分类的service
	@Resource
	private CategoryService categoryService;
	
	//注入商品的service
	
	@Resource
	private ProductService productService;
	
	
	public String execute(){
		//查询一级分类并存入到session中
		List<Category> cList=	categoryService.findAll();
		ActionContext.getContext().getSession().put("cList", cList);
				
		//查询热门商品，并存值栈中
		List<Product> hList=productService.findHot();
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//查询最新的商品并存值栈中
		List<Product> nList=productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
		
	}
	
	

}
