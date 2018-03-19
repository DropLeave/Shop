package user;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/*
 * �û���Action��
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User user=new User();
	private UserService userService;
	private Boolean result;
	private String checkCode;
	
	public Boolean getResult() {
		return result;
	}

	

	

	public String getCheckCode() {
		return checkCode;
	}





	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}





	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	//��д��ת��ע��ҳ��
	public String registPage(){
		return "registPageSuccess";
	}
	//�û��˳�����
	public String loginout(){
		//��ȡsession������
		ServletActionContext.getRequest().getSession().invalidate();
		return "loginoutSuccess";
	}
	//ע���û�
	@InputConfig(resultName="registInput")
	public String regist() throws Exception{
		//��Session�л�ȡ��֤��
		String checkcodeInSession=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode_regist");
		if(this.checkCode==null||!this.checkCode.equalsIgnoreCase(checkcodeInSession)){
			this.addActionError("��֤�����");
			return "registInput";
		}
		userService.regist(user);
		this.addActionMessage("ע��ɹ�����ȥ���伤�");
		return "registSuccess";
	}
	/**
	 * �����û�
	 * @return
	 */
	public String active(){
		User u=userService.findByCode(user.getCode());
		if(u!=null){
			u.setState(1);
			userService.updateState(u);
			this.addActionMessage("����ɹ�����ȥ��¼��");
			return "activeSuccess";
		}else{
			this.addActionMessage("����ʧ�ܣ�����������");
			return "activeSuccess";
		}	
	}
	/**
	 * ��ת����¼ҳ��
	 *
	 */
	public String loginPage(){
		return "loginPageSuccess";
	}
	
	/**
	 * ǰ̨��¼����
	 */
	@InputConfig(resultName="loginInput")
	public String login(){
		//��Session�л�ȡ��֤��
				String checkcodeInSession=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode_login");
				if(this.checkCode==null||!this.checkCode.equalsIgnoreCase(checkcodeInSession)){
					this.addActionError("��֤�����");
					return "loginInput";
				}
		User u=userService.login(user);
		if(u!=null){
			//���û���Ϣ���浽session��
			ServletActionContext.getRequest().getSession().setAttribute("user", u);
			return "loginSuccess";
		}else{
			this.addActionError("�û��������������û�δ���");
			return "loginInput";
			
		}
		
	}
	/**
	 * �ж��û��Ƿ��Ѵ���
	 */
	public String checkUserName(){
		User u=userService.getUserByUserName(user.getUsername());
		this.result=true;
		if(u!=null){
			this.result=false;
		}
		
		return "checkUserName";
		
	}
}
