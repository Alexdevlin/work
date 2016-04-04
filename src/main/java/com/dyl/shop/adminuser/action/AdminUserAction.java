package com.dyl.shop.adminuser.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dyl.shop.adminuser.service.AdminUserService;
import com.dyl.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class AdminUserAction extends ActionSupport implements
		ModelDriven<AdminUser> {

	@Resource
	private AdminUserService adminUserService;

	private AdminUser adminUser = new AdminUser();

	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}

	// 后台管理员登录
	public String login() {

		AdminUser existAdminUser = adminUserService.login(
				adminUser.getUsername(), adminUser.getPassword());
		if (existAdminUser == null) {
			// 登陆失败
			this.addActionError("亲，你的用户名或密码错误");
			return "loginFail";
		} else {
			// 登陆成功
		
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";

		}

	}
}
