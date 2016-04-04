package com.dyl.shop.category.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dyl.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionSupport;

@Repository
public class CategoryDao extends ActionSupport {

	@Resource
	private SessionFactory sessionFactory;

	public List<Category> findAll() {

		return sessionFactory.getCurrentSession().createQuery("from Category").list();

	}

	public void save(Category category) {

		sessionFactory.getCurrentSession().save(category);

	}

	public Category findByCid(Integer cid) {
		return (Category) sessionFactory.getCurrentSession().createQuery("from Category where cid =?")
				.setParameter(0, cid).uniqueResult();
	}

	public void delete(Category category) {

		sessionFactory.getCurrentSession().delete(category);
	}

	public void update(Category category) {

		sessionFactory.getCurrentSession().update(category);
	}

}
