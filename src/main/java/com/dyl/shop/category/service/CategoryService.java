package com.dyl.shop.category.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dyl.shop.category.dao.CategoryDao;
import com.dyl.shop.category.vo.Category;


@Service
@Transactional
public class CategoryService {

	@Resource
	private CategoryDao categoryDao;
	
	
	public List<Category> findAll() {
		return categoryDao.findAll();
	}


	public void save(Category category) {

		categoryDao.save(category);
	}


	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}


	public void delete(Category category) {

		categoryDao.delete(category);
	}


	public void update(Category category) {

		
		categoryDao.update(category);
	} 
}
