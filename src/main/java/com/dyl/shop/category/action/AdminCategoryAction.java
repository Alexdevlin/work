package com.dyl.shop.category.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dyl.shop.category.service.CategoryService;
import com.dyl.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class AdminCategoryAction extends ActionSupport implements
		ModelDriven<Category> {
	private Category category = new Category();

	@Resource
	private CategoryService categoryService;

	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}

	// 查询一级分类
	public String findAll() {

		List<Category> clist = categoryService.findAll();

		ActionContext.getContext().getValueStack().set("cList", clist);

		return "findAll";

	}

	// 添加一级分类

	public String save() {

		categoryService.save(category);

		return "save";

	}

	// 删除一级分类

	public String delete() {

		category = categoryService.findByCid(category.getCid());
		
		categoryService.delete(category);

		return "deleteSuccess";

	}
	//修改一级分类进行跳转到更新action的jsp
	public String edit(){
		
		//查询一级分类
		category =categoryService.findByCid(category.getCid());
		
		return "editSuccess";
		
		
	}
	//更新一级分类
	
	public String update(){
		
		categoryService.update(category);
		
		return "updateSuccess";
		
	}
	
}
