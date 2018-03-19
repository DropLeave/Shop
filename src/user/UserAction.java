package user;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/*
 * 用户的Action类
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
	
	//编写调转到注册页面
	public String registPage(){
		return "registPageSuccess";
	}
	//用户退出功能
	public String loginout(){
		//获取session并销毁
		ServletActionContext.getRequest().getSession().invalidate();
		return "loginoutSuccess";
	}
	//注册用户
	@InputConfig(resultName="registInput")
	public String regist() throws Exception{
		//从Session中获取验证码
		String checkcodeInSession=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode_regist");
		if(this.checkCode==null||!this.checkCode.equalsIgnoreCase(checkcodeInSession)){
			this.addActionError("验证码错误！");
			return "registInput";
		}
		userService.regist(user);
		this.addActionMessage("注册成功，请去邮箱激活！");
		return "registSuccess";
	}
	/**
	 * 激活用户
	 * @return
	 */
	public String active(){
		User u=userService.findByCode(user.getCode());
		if(u!=null){
			u.setState(1);
			userService.updateState(u);
			this.addActionMessage("激活成功，请去登录！");
			return "activeSuccess";
		}else{
			this.addActionMessage("激活失败，激活码有误！");
			return "activeSuccess";
		}	
	}
	/**
	 * 跳转到登录页面
	 *
	 */
	public String loginPage(){
		return "loginPageSuccess";
	}
	
	/**
	 * 前台登录功能
	 */
	@InputConfig(resultName="loginInput")
	public String login(){
		//从Session中获取验证码
				String checkcodeInSession=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode_login");
				if(this.checkCode==null||!this.checkCode.equalsIgnoreCase(checkcodeInSession)){
					this.addActionError("验证码错误！");
					return "loginInput";
				}
		User u=userService.login(user);
		if(u!=null){
			//将用户信息保存到session中
			ServletActionContext.getRequest().getSession().setAttribute("user", u);
			return "loginSuccess";
		}else{
			this.addActionError("用户名或密码错误或用户未激活！");
			return "loginInput";
			
		}
		
	}
	/**
	 * 判断用户是否已存在
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
