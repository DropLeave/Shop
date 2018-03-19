package order;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;

import product.Product;
import user.User;
import utils.PageBean;

public class OrderDao {

	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Integer saveOrder(Order order) {
		return (Integer) sessionFactory.getCurrentSession().save(order);
		
	}
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return (Order) sessionFactory.getCurrentSession().get(Order.class, oid);
	}
	public void updateOrder(Order currorder) {
			
		sessionFactory.getCurrentSession().update(currorder);
	}
	public List<Order> findByUid(Integer uid) {
		
		return  sessionFactory.getCurrentSession().createQuery("from Order o where o.user.uid=? order by o.ordertime desc").setParameter(0, uid).list();
	}
	public PageBean<Order> findByPage(Integer page) {
		Integer limit=8;
		PageBean<Order>pageBean=new PageBean<Order>();
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		Query query =sessionFactory.getCurrentSession().createQuery(" from Order ");
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
	public PageBean<Order> findBySate(Integer page, Integer state) {
		Integer limit=8;
		PageBean<Order>pageBean=new PageBean<Order>();
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		Query query =sessionFactory.getCurrentSession().createQuery(" from Order where state=?").setParameter(0, state);
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

}
