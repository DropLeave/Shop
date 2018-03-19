package adminuser;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AdminUserService {
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	

	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		return adminUserDao.login(admin);
	}
	
}
