package adminuser;

import java.util.List;

import org.hibernate.SessionFactory;

public class AdminUserDao {
	private SessionFactory sessionFactory;

	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		List<Admin>list= sessionFactory.getCurrentSession().createQuery("from Admin a where a.username=? and a.password=?").
				setParameter(0,admin.getUsername()).setParameter(1, admin.getPassword()).list();
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}
	
}
