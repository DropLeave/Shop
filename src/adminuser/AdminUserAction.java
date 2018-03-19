package adminuser;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<Admin>{
	private AdminUserService adminUserService;
	private Admin admin=new Admin();
	@Override
	public Admin getModel() {
		// TODO Auto-generated method stub
		return admin;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	public String login(){
		Admin adminUser=(Admin)adminUserService.login(admin);
		if(adminUser==null){
			this.addActionError("用户名或密码失误！");
			return "loginFail";
		}
		ServletActionContext.getRequest().getSession().setAttribute("adminUser", adminUser);
		return "loginSuccess";
	}
	public String loginout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "loginoutSuccess";
	}
}
