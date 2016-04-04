package com.dyl.shop.adminuser.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dyl.shop.adminuser.dao.AdminUserDao;
import com.dyl.shop.adminuser.vo.AdminUser;


@Service
@Transactional
public class AdminUserService {
	
	@Resource
	private AdminUserDao adminUserDao;

	public AdminUser login(String username, String password) {
		
		return adminUserDao.login(username,password);
	}

}
