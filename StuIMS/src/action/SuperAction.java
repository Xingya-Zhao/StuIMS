package action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

//所有Action动作的父类,为了方便实现过滤器、拦截器所以继承于ActionSupport类，为了获得内置对象所以分别实现以下接口。
public class SuperAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		protected HttpServletRequest request; //请求对象
		protected HttpServletResponse response; //响应对象
		protected HttpSession session; //会话对象
		protected ServletContext application; //全局对象
	
	@Override
	public void setServletContext(ServletContext application) {
		// TODO Auto-generated method stub
		this.application = application;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		this.session = this.request.getSession();
	}
	

}
