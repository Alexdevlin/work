package com.dyl.shop.product.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dyl.shop.product.vo.Product;
/**
 * 
 * @author duyunlei
 * 2015-9-9 下午8:00:09
 *
 */
@Repository
public class ProductDao {

	@Resource
	private SessionFactory sessionFactory;

	// 查询热门商品，每栏显示10个
	public List<Product> findHot() {

		return sessionFactory.getCurrentSession()
				.createQuery("from Product where is_hot=? order by pdate desc")
				.setParameter(0, 1).setFirstResult(0)// 相当于limit语句，查询10个结果并显示出来
				.setMaxResults(10).list();
	}

	// 查询最新商品，每栏显示10个
	public List<Product> findNew() {

		return sessionFactory.getCurrentSession()
				.createQuery("from Product order by pdate desc")
				.setFirstResult(0)// 相当于limit语句，查询10个结果并显示出来
				.setMaxResults(10).list();
	}

	// 根据pid查询商品详细信息
	public Product findByPid(Integer pid) {
		return (Product) sessionFactory.getCurrentSession()//
				.createQuery("from Product where pid =?")//
				.setParameter(0, pid).uniqueResult();
	}

	// 根据分类id查询商品的个数
	public int findCountCid(Integer cid) {

		Long count = (Long) sessionFactory.getCurrentSession()//
				.createQuery("select count(*) from Product p " + //
						"where p.categorySecond.category.cid = ?")//
				.setParameter(0, cid)//
				.uniqueResult();

		return count.intValue();

	}

	// 根据分类id查询商品的集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// System.out.println("limit"+limit);
		return sessionFactory
				.getCurrentSession()
				.createQuery("select p from Product" + //
						" p join p.categorySecond cs join cs.category c where c.cid = ?")//
				.setParameter(0, cid)//
				.setFirstResult(begin)//
				.setMaxResults(limit)//
				.list();

	}

	// 根据二级分类查询商品个数
	public int findCountCsid(Integer csid) {
		Long count = (Long) sessionFactory
				.getCurrentSession()
				//
				.createQuery(
						"select count(*) from Product p where p.categorySecond.csid = ?")//
				.setParameter(0, csid)//
				.uniqueResult();

		return count.intValue();
	}

	// 根据二级分类查询商品的集合
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"select p from Product p join p.categorySecond cs where cs.csid = ?")//
				.setParameter(0, csid)//
				.setFirstResult(begin)//
				.setMaxResults(limit)//
				.list();

	}

	public int findCount() {
		Long l = (Long) sessionFactory.getCurrentSession()
				.createQuery("select count(*) from Product ").uniqueResult();

		return l.intValue();
	}

	public List<Product> findPage(int begin, int limit) {

		return sessionFactory.getCurrentSession().createQuery("from Product order by pdate desc ")
				.setFirstResult(begin).setMaxResults(limit).list();
	}

	public void save(Product product) {

		sessionFactory.getCurrentSession().save(product);
		
	}

	public void delete(Product product) {

		sessionFactory.getCurrentSession().delete(product);
	}

	public void update(Product product) {

		sessionFactory.getCurrentSession().update(product);
	}
}
