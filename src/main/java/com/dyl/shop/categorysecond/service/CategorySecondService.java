package com.dyl.shop.categorysecond.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dyl.shop.categorysecond.dao.CategorySecondDao;
import com.dyl.shop.categorysecond.vo.CategorySecond;
import com.dyl.shop.product.vo.Product;
import com.dyl.shop.utils.PageBean;

@Transactional
@Service
public class CategorySecondService {

	@Resource
	private CategorySecondDao categorySecondDao;

	public PageBean<CategorySecond> findAll(Integer page) {

		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();

		pageBean.setPage(page);

		int limit = 10;
		pageBean.setLimit(limit);
		int totalCount = 0;
		totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);

		int totalPage = 0;
		if (totalCount % limit == 0) {

			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;

		}
		pageBean.setTotalPage(totalPage);

		int begin = (page - 1) * limit;

		List<CategorySecond> list = categorySecondDao.findPage(begin, limit);
		pageBean.setList(list);

		return pageBean;
	}

	public void save(CategorySecond categorySecond) {

		categorySecondDao.save(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {

		return categorySecondDao.findByCsid(csid);
	}

	public void delete(CategorySecond categorySecond) {

		categorySecondDao.delete(categorySecond);
	}

	public void update(CategorySecond categorySecond) {

		categorySecondDao.update(categorySecond);
	}

	public List<CategorySecond> findAll() {

		return categorySecondDao.findAll();
	}

}
