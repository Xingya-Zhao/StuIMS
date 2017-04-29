package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;
import service.UsersDAO;
import service.impl.UsersDAOImpl;

//用户Action类
public class UsersAction extends SuperAction implements ModelDriven<Users> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user = new Users();
	private UsersDAO udao = new UsersDAOImpl();

	// 用户登录动作
	public String login() {
		if (udao.usersLogin(user)) {
			// 在session中保存登录成功的用户名，将在登录成功的页面调用该用户名进行显示。
			session.setAttribute("loginUserName", user.getUname());
			return "login_success";
		} else {
			return "login_failure";
		}
	}

	// 用户注销动作，跳过表单验证
	@SkipValidation
	public String logout() {
		if (session.getAttribute("loginUserName") != null) {
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}

	// 表单验证的方法
	@Override
	public void validate() {
		if ("".equals(user.getUname().trim())) {
			this.addFieldError("unameError", "用户名不能为空！");
		} else if (user.getPassword().length() < 6) {
			this.addFieldError("passwordError", "密码不少于6位！");
		} else if ((user.getPassword().length() != 0) && (!udao.usersLogin(user))) {
			this.addFieldError("noUser", "密码错误 或 用户未注册！");
		}
	}

	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}

}
