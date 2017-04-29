package action;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ModelDriven;

import db.MyHibernateSessionFactory;
import entity.Students;
import service.StudentsDAO;
import service.impl.StudentsDAOImpl;

//学生Action类
public class StudentsAction extends SuperAction implements ModelDriven<Students> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Students s = new Students();
	private StudentsDAO sdao = new StudentsDAOImpl();
	private List<Students> list;

	// 查询所有学生的动作
	@SkipValidation
	public String query() {
		list = sdao.queryAllStudents();
		if (list != null && list.size() > 0) {
			session.setAttribute("studentsList", list);
		}
		return "query_success";
	}

	// 删除单个学生的动作
	@SkipValidation
	public String delete() {
		String sid = request.getParameter("sid");
		sdao.deleteStudents(sid);
		return "delete_success";
	}

	// 添加单个学生的动作
	public String add() {
		sdao.addStudents(s);
		return "add_success";
	}
	
	//修改单个学生信息的动作
	@SkipValidation
	public String modify() {
		//接受jsp页面传递进来的sid参数。
		String sid = request.getParameter("sid");
		//根据sid获得Students对象。
		Students s = sdao.queryStudentsBySid(sid);
		//将这个学生对象保存在session中。
		session.setAttribute("modify_students",s);
		return "queryBySid_success";
	}
	
	//保存修改后的学生资料。
	@SkipValidation
	public String save() {
		sdao.updataStudents(s);
		return "modify_success";
	}

	// 表达验证
	@Override
	public void validate() {
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		if ("".equals(s.getSid()) | "".equals(s.getSname()) | s.getBirthday() == null | "".equals(s.getAddress())) {
			this.addFieldError("existNull", "有未输入的信息！");
		}
		if (session.get(Students.class, s.getSid()) != null) {
			this.addFieldError("sidExist", "该学号已经存在！");
		}
		tx.commit();
	}

	@Override
	public Students getModel() {
		// TODO Auto-generated method stub
		return s;
	}
}
