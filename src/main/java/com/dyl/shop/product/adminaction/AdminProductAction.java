package com.dyl.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dyl.shop.categorysecond.service.CategorySecondService;
import com.dyl.shop.categorysecond.vo.CategorySecond;
import com.dyl.shop.product.service.ProductService;
import com.dyl.shop.product.vo.Product;
import com.dyl.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {

	private Product product = new Product();

	@Resource
	private ProductService productService;

	private Integer page;

	@Resource
	private CategorySecondService categorySecondService;

	// 文件上传需要的三个属性:
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Product getModel() {

		return product;
	}

	// 后台查询所有产品带分页
	public String findAll() {

		PageBean<Product> pageBean = productService.findAll(page);

		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findAll";

	}

	// 跳转到添加商品页面

	public String addPage() {

		List<CategorySecond> csList = categorySecondService.findAll();

		ActionContext.getContext().getValueStack().set("csList", csList);

		return "addPage";

	}

	// 保存商品的方法

	public String save() throws IOException {

		product.setPdate(new Date());

		// 将提交的数据添加到数据库中.
		product.setPdate(new Date());
		// product.setImage(image);
		if (upload != null) {
			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);

		return "save";
	}

	// 删除商品
	public String delete() {

		product = productService.findByPid(product.getPid());

		String path = ServletActionContext.getServletContext().getRealPath(
				"/" + product.getImage());

		if (path != null) {

			File file = new File(path);
			file.delete();

		}

		productService.delete(product);

		return "delete";

	}

	// 跳转到编辑商品页面
	public String edit() {

		product = productService.findByPid(product.getPid());

		List<CategorySecond> csList = categorySecondService.findAll();

		ActionContext.getContext().getValueStack().set("csList", csList);

		return "edit";

	}

	// 更新商品
	public String update() throws IOException {

		// 将信息修改到数据库
		product.setPdate(new Date());

		// 上传:
		if (upload != null) {
			String delPath = ServletActionContext.getServletContext()
					.getRealPath("/" + product.getImage());
			File file = new File(delPath);
			file.delete();// 删除原来图片路径
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);

		return "update";
	}
}
