package user;

import org.springframework.transaction.annotation.Transactional;

import utils.UUIDutils;



@Transactional
public class UserService {
	
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
/*
 * 业务层注册用户
 */
	public void regist(User user) throws Exception {
		//保存用户
		user.setState(0);//设置用户状态,0未激活，1已激活
		String code =UUIDutils.getUUID()+UUIDutils.getUUID();//生成激活码
		user.setCode(code);
		userDao.regist(user);
		//发送邮件
		
		SendMail.sendMail("1442532801@qq.com", "廖培业", "商场激活码", "<h1>来自shop商城的激活码</h1>"
				+ "<h3><a href='http://192.168.9.103:8080/Shop/user_active.action?code="+code+"'>http://192.168.9.103:8080/Shop/user_active.action?code="+code+"</a></h3>");
		
	}
	/**
	 * 通过激活码查询用户信息
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		User user=(User) userDao.findByCode(code);
		return user;
		}
	
	/**
	 * 修改状态state
	 * @param user
	 */
	public void updateState(User user) {
		userDao.updateState(user);
		
	}
	public User login(User user) {
		
		return userDao.login(user);
	}
	public User getUserByUserName(String username) {
		return userDao.getUserByUserName(username);
	}
		
	
	
}
