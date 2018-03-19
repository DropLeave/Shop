package category;

import java.util.List;

import org.hibernate.SessionFactory;

public class CategoryDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
//��ѯ����һ������
	public List<Category> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Category").list();
		
	}
//����һ������
	public void save(Category category) {
		 sessionFactory.getCurrentSession().save(category);		
	}
//ɾ��һ������
	public void delete(Category category) {
		category=(Category) sessionFactory.getCurrentSession().get(Category.class, category.getCid());
		sessionFactory.getCurrentSession().delete(category);
		
	}
//��ѯһ������
	public  Category findByCid(Integer cid) {
		
		return (Category) sessionFactory.getCurrentSession().get(Category.class, cid);
	}
	public void update(Category category) {
		 sessionFactory.getCurrentSession().update(category);
		
	}

}
