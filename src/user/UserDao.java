package user;


import org.hibernate.SessionFactory;


public class UserDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * ±£¥Ê”√ªß
	 * @param user
	 */
	public void regist(User user) {
		
		sessionFactory.getCurrentSession().save(user);
		
	}
	public User findByCode(String code) {
		return  (User) sessionFactory.getCurrentSession()
				.createQuery("from User where code=:code1")
				.setParameter("code1",code).uniqueResult();
			
	}
	public void updateState(User user){
		sessionFactory.getCurrentSession().update(user);
	}
	public User login(User user) {
	return	(User)sessionFactory.getCurrentSession().createQuery("from User where username=? and password=? and state=1")
		.setParameter(0, user.getUsername()).setParameter(1, user.getPassword()).uniqueResult();
		
	}
	public User getUserByUserName(String username) {
		return (User) sessionFactory.getCurrentSession().createQuery("from User where username=:username1").setParameter("username1", username).uniqueResult();
	}
	
}
