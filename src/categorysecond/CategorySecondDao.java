package categorysecond;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;

import product.Product;
import utils.PageBean;

public class CategorySecondDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public PageBean<CategorySecond> findBypage(Integer page) {
		
		PageBean<CategorySecond>pageBean=new PageBean<CategorySecond>();
		pageBean.setPage(page);
		Integer limit=10;
		pageBean.setLimit(limit);
		Query query =sessionFactory.getCurrentSession().createQuery("from CategorySecond   ");
		ScrollableResults scrollableResults=query.scroll();
		scrollableResults.last();
		//×Ü¼ÇÂ¼Êý
		int i=scrollableResults.getRowNumber()+1;
		
		pageBean.setTotalCount(i);
		if(i%limit==0){
			pageBean.setTotalPage(i/limit);
		}else{
			pageBean.setTotalPage(i/limit+1);
		}
		query.setFirstResult(limit*(page-1));
		query.setMaxResults(limit);
		pageBean.setList(query.list());
		return pageBean;
	}

	public void save(CategorySecond categorySecond) {
		sessionFactory.getCurrentSession().save(categorySecond);
		
	}

	public List<CategorySecond> findAll() {
		
		return sessionFactory.getCurrentSession().createQuery("from CategorySecond").list();
	}
	
}
