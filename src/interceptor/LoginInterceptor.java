package interceptor;

import org.apache.struts2.ServletActionContext;

import adminuser.Admin;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		Admin adminUser=(Admin) ServletActionContext.getRequest().getSession().getAttribute("adminUser");
		if(adminUser!=null){
			return actionInvocation.invoke();
		}
	 ActionSupport action=	(ActionSupport) actionInvocation.getAction();
		 action.addActionError("Äã»¹Î´µÇÂ¼£¡");
		 return action.LOGIN;
				 
	}

}
