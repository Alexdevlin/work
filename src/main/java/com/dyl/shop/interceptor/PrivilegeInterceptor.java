package com.dyl.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.dyl.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation actionInvocation)
			throws Exception {
		AdminUser existAdminUser = (AdminUser) ServletActionContext
				.getRequest().getSession().getAttribute("existAdminUser");
		if (existAdminUser == null) {
			ActionSupport support = (ActionSupport) actionInvocation
					.getAction();
			support.addActionError("您还没有登录!没有权限访问!");
			return "loginFail";

		} else {
			return actionInvocation.invoke();

		}

		
	}

}
