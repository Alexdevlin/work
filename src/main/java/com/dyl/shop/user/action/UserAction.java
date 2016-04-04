package com.dyl.shop.user.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dyl.shop.user.service.UserService;
import com.dyl.shop.user.vo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author duyunlei 2015-9-2 上午7:02:02
 * 
 */

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {

	User user = new User();

	public User getModel() {
		return user;
	}

	// 接受验证码

	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	@Resource
	private UserService userService;

	/**
	 * AJAX进行异步校验用户名的执行方法
	 * 
	 * @throws IOException
	 */
	public String findByName() throws IOException {
		// 调用Service进行查询:
		User existUser = userService.findByUsername(user.getUsername());// user.getUsername()是实现modeldriven接口才有的这个方法，否则需使用username的get/set方法
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// 判断
		if (existUser != null) {
			// 查询到该用户:用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		} else {
			// 没查询到该用户:用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	/**
	 * 用户注册页面跳转
	 * 
	 * @return
	 */
	public String registPage() {

		return "registPage";
	}

	/**
	 * 后台校验用户注册页面校验
	 * 
	 */

	public String regist() {

		// 判断验证码程序:
		// 从session中获得验证码的随机值:
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(checkcode1)) {
			this.addActionError("验证码输入错误!");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功，请去邮箱激活！");
		return "msg";
	}

	/**
	 * 用户激活
	 * 
	 * @return
	 */
	public String active() {

		User existUser = userService.findByCode(user.getCode());
		if (existUser == null) {

			this.addActionMessage("激活失败，激活码错误");
		} else {

			existUser.setState(1);// 设置为激活状态
			existUser.setCode(null);// 防止二次激活
			userService.update(existUser);
			this.addActionMessage("激活成功，请去登录！");

		}

		return "msg";

	}

	/**
	 * 用户登录页面跳转
	 * 
	 * @return
	 */
	public String loginPage() {

		return "loginPage";
	}

	/**
	 * 用户登录
	 */

	public String login() {

		User existUser = userService.login(user.getUsername(),
				user.getPassword(), user.getState());
		if (existUser == null) {
			this.addActionError("用户名或密码错误或未激活");
			return "login";
		} else {

			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}

	/**
	 * 用户退出
	 */

	public String quit() {

		ServletActionContext.getRequest().getSession().invalidate();

		return "quit";

	}

	
}
