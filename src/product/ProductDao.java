package product;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;

import utils.PageBean;

public class ProductDao  {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Product> findHot() {
		Query query=sessionFactory.getCurrentSession().createQuery("from Product where is_hot=1");
		/*//统计总记录数
		//得到滚动结果集
		ScrollableResults scrollableResults= query.scroll();
		//滚动到最后一行
		scrollableResults.last();
		//统计总数
		int i=scrollableResults.getRowNumber()+1;*/
		query.setFirstResult(0);
		query.setMaxResults(10);
		return query.list();
	}

	public List<Product> findNew() {
		Query query=sessionFactory.getCurrentSession().createQuery("from Product order by pdate desc");
		query.setFirstResult(0);
		query.setMaxResults(10);
		return query.list();
	}

	public PageBean findProductByPage(Integer cid,Integer page) {
		PageBean<Product>pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(12);
		Query query =sessionFactory.getCurrentSession().createQuery("select p from Product p,CategorySecond cs where p.categorySecond=cs and cs.category.cid=? ").setParameter(0, cid);
		ScrollableResults scrollableResults=query.scroll();
		scrollableResults.last();
		//总记录数
		int i=scrollableResults.getRowNumber()+1;
		
		pageBean.setTotalCount(i);
		if(i%12==0){
			pageBean.setTotalPage(i/12);
		}else{
			pageBean.setTotalPage(i/12+1);
		}
		query.setFirstResult(12*(page-1));
		query.setMaxResults(12);
		pageBean.setList(query.list());
		return pageBean;
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return (Product) sessionFactory.getCurrentSession().createQuery("from Product where pid=?").setParameter(0, pid).uniqueResult();
	}

	public PageBean<Product> findByCsid(Integer csid, Integer page) {
		Integer limit=8;
		PageBean<Product>pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		Query query =sessionFactory.getCurrentSession().createQuery(" select p from Product p join p.categorySecond cs  where cs.csid=? ").setParameter(0, csid);
		ScrollableResults scrollableResults=query.scroll();
		scrollableResults.last();
		//总记录数
		int i=scrollableResults.getRowNumber()+1;
		
		pageBean.setTotalCount(i);
		if(i%12==0){
			pageBean.setTotalPage(i/limit);
		}else{
			pageBean.setTotalPage(i/limit+1);
		}
		query.setFirstResult(limit*(page-1));
		query.setMaxResults(limit);
		pageBean.setList(query.list());
		return pageBean;
	}

	public PageBean<Product> findByPage(Integer page) {
		Integer limit=8;
		PageBean<Product>pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		Query query =sessionFactory.getCurrentSession().createQuery(" from Product ");
		ScrollableResults scrollableResults=query.scroll();
		scrollableResults.last();
		//总记录数
		int i=scrollableResults.getRowNumber()+1;
		
		pageBean.setTotalCount(i);
		if(i%12==0){
			pageBean.setTotalPage(i/limit);
		}else{
			pageBean.setTotalPage(i/limit+1);
		}
		query.setFirstResult(limit*(page-1));
		query.setMaxResults(limit);
		pageBean.setList(query.list());
		return pageBean;
	}

	public void save(Product product) {
		sessionFactory.getCurrentSession().save(product);
		
	}
	
}
