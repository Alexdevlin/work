package com.dyl.shop.categorysecond.adminaction;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dyl.shop.category.service.CategoryService;
import com.dyl.shop.category.vo.Category;
import com.dyl.shop.categorysecond.service.CategorySecondService;
import com.dyl.shop.categorysecond.vo.CategorySecond;
import com.dyl.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class AdminCategorySecondAction extends ActionSupport implements
		ModelDriven<CategorySecond> {

	private CategorySecond categorySecond = new CategorySecond();

	@Resource
	private CategorySecondService categorySecondService;

	@Resource
	private CategoryService categoryService;

	public CategorySecond getModel() {
		return categorySecond;
	}

	// 接受page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// 查询二级分类
	public String findAll() {

		PageBean<CategorySecond> pageBean = categorySecondService.findAll(page);

		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";

	}

	// 跳转到添加二级分类页面的方法
	public String addPage() {
		// 查询所有的一级分类
		List<Category> cList = categoryService.findAll();

		ActionContext.getContext().getValueStack().set("cList", cList);

		return "addPageSuccess";

	}

	// 保存二级分类

	public String save() {

		categorySecondService.save(categorySecond);

		return "save";
	}

	// 删除二级分类，级联一级分类

	public String delete() {
		// 查询二级分类
		categorySecond = categorySecondService.findByCsid(categorySecond
				.getCsid());
		// 删除
		categorySecondService.delete(categorySecond);

		return "delete";

	}
	// 查询并更新二级分类
	
	public String edit(){
		
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList=categoryService.findAll();
		
		ActionContext.getContext().getValueStack().set("cList", cList);
		
		return "edit";
	}
	
	public String update(){
		
		categorySecondService.update(categorySecond);
		
		return "update";
	}

}
