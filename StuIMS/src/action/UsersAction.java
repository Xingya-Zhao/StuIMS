package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;
import service.UsersDAO;
import service.impl.UsersDAOImpl;

//�û�Action��
public class UsersAction extends SuperAction implements ModelDriven<Users> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user = new Users();
	private UsersDAO udao = new UsersDAOImpl();

	// �û���¼����
	public String login() {
		if (udao.usersLogin(user)) {
			// ��session�б����¼�ɹ����û��������ڵ�¼�ɹ���ҳ����ø��û���������ʾ��
			session.setAttribute("loginUserName", user.getUname());
			return "login_success";
		} else {
			return "login_failure";
		}
	}

	// �û�ע����������������֤
	@SkipValidation
	public String logout() {
		if (session.getAttribute("loginUserName") != null) {
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}

	// ����֤�ķ���
	@Override
	public void validate() {
		if ("".equals(user.getUname().trim())) {
			this.addFieldError("unameError", "�û�������Ϊ�գ�");
		} else if (user.getPassword().length() < 6) {
			this.addFieldError("passwordError", "���벻����6λ��");
		} else if ((user.getPassword().length() != 0) && (!udao.usersLogin(user))) {
			this.addFieldError("noUser", "������� �� �û�δע�ᣡ");
		}
	}

	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}

}
