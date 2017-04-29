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

//ѧ��Action��
public class StudentsAction extends SuperAction implements ModelDriven<Students> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Students s = new Students();
	private StudentsDAO sdao = new StudentsDAOImpl();
	private List<Students> list;

	// ��ѯ����ѧ���Ķ���
	@SkipValidation
	public String query() {
		list = sdao.queryAllStudents();
		if (list != null && list.size() > 0) {
			session.setAttribute("studentsList", list);
		}
		return "query_success";
	}

	// ɾ������ѧ���Ķ���
	@SkipValidation
	public String delete() {
		String sid = request.getParameter("sid");
		sdao.deleteStudents(sid);
		return "delete_success";
	}

	// ��ӵ���ѧ���Ķ���
	public String add() {
		sdao.addStudents(s);
		return "add_success";
	}
	
	//�޸ĵ���ѧ����Ϣ�Ķ���
	@SkipValidation
	public String modify() {
		//����jspҳ�洫�ݽ�����sid������
		String sid = request.getParameter("sid");
		//����sid���Students����
		Students s = sdao.queryStudentsBySid(sid);
		//�����ѧ�����󱣴���session�С�
		session.setAttribute("modify_students",s);
		return "queryBySid_success";
	}
	
	//�����޸ĺ��ѧ�����ϡ�
	@SkipValidation
	public String save() {
		sdao.updataStudents(s);
		return "modify_success";
	}

	// �����֤
	@Override
	public void validate() {
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		if ("".equals(s.getSid()) | "".equals(s.getSname()) | s.getBirthday() == null | "".equals(s.getAddress())) {
			this.addFieldError("existNull", "��δ�������Ϣ��");
		}
		if (session.get(Students.class, s.getSid()) != null) {
			this.addFieldError("sidExist", "��ѧ���Ѿ����ڣ�");
		}
		tx.commit();
	}

	@Override
	public Students getModel() {
		// TODO Auto-generated method stub
		return s;
	}
}
