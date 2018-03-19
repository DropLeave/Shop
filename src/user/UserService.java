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
 * ҵ���ע���û�
 */
	public void regist(User user) throws Exception {
		//�����û�
		user.setState(0);//�����û�״̬,0δ���1�Ѽ���
		String code =UUIDutils.getUUID()+UUIDutils.getUUID();//���ɼ�����
		user.setCode(code);
		userDao.regist(user);
		//�����ʼ�
		
		SendMail.sendMail("1442532801@qq.com", "����ҵ", "�̳�������", "<h1>����shop�̳ǵļ�����</h1>"
				+ "<h3><a href='http://192.168.9.103:8080/Shop/user_active.action?code="+code+"'>http://192.168.9.103:8080/Shop/user_active.action?code="+code+"</a></h3>");
		
	}
	/**
	 * ͨ���������ѯ�û���Ϣ
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		User user=(User) userDao.findByCode(code);
		return user;
		}
	
	/**
	 * �޸�״̬state
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
