package com.dyl.shop.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dyl.shop.user.dao.UserDao;
import com.dyl.shop.user.vo.User;
import com.dyl.shop.utils.MailUitls;
import com.dyl.shop.utils.UUIDUtils;

@Service
@Transactional
public class UserService {

	@Resource
	private UserDao userDao;


	// 按用户名查询用户的方法:
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	// 完成用户注册
	public void save(User user) {

		user.setState(0);// 0表示未激活，1表示已激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);

		MailUitls.sendMail(user.getEmail(), code);

	}

	// 完成用户激活
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	// 激活后更新用户
	public void update(User existUser) {

		userDao.update(existUser);
	}

	// 用户登录

	public User login(String username, String password, Integer state) {
		return userDao.login(username, password,state);
	}
	

}
