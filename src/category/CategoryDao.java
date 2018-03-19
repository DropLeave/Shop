package category;

import java.util.List;

import org.hibernate.SessionFactory;

public class CategoryDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
//查询所有一级分类
	public List<Category> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Category").list();
		
	}
//保存一级分类
	public void save(Category category) {
		 sessionFactory.getCurrentSession().save(category);		
	}
//删除一级分类
	public void delete(Category category) {
		category=(Category) sessionFactory.getCurrentSession().get(Category.class, category.getCid());
		sessionFactory.getCurrentSession().delete(category);
		
	}
//查询一级分类
	public  Category findByCid(Integer cid) {
		
		return (Category) sessionFactory.getCurrentSession().get(Category.class, cid);
	}
	public void update(Category category) {
		 sessionFactory.getCurrentSession().update(category);
		
	}

}
