package com.dyl.shop.user.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dyl.shop.user.vo.User;

@Repository
@SuppressWarnings("unchecked")
public class UserDao {

	@Resource
	private SessionFactory sessionFactory;

	public User findByUsername(String username) {

		if (username == null || username.length() == 0) {

			return null;
		} else {
			return (User) sessionFactory.getCurrentSession()//
					.createQuery("from User u where u.username=?")//
					.setParameter(0, username)//
					.uniqueResult();

		}
	}

	// 保存用户
	public void save(User user) {

		sessionFactory.getCurrentSession().save(user);

	}

	// 根据激活码查询用户
	public User findByCode(String code) {
		if (code == null || code.length() == 0) {

			return null;
		} else {
			return (User) sessionFactory.getCurrentSession()
					.createQuery("from User u where u.code=? ")
					.setParameter(0, code).uniqueResult();
		}
	}

	// 激活后更新用户
	public void update(User existUser) {
		sessionFactory.getCurrentSession().update(existUser);

	}
	
	//用户登录，根据用户名和密码查询用户
	public User login(String username, String password, Integer state) {
		return (User) sessionFactory.getCurrentSession()
				.createQuery("from User where username = ?  and password = ? and state=?")
				.setParameter(0, username).setParameter(1, password)
				.setParameter(2, 1)
				.uniqueResult(); 
	}

}
