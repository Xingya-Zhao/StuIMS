package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Users;
import service.UsersDAO;

public class UsersDAOImpl implements UsersDAO {

	//������һ��Users���󣬸÷���������������е��û�������������ѯ���ݿ����Ƿ���и��û�������еĻ�����true��û�з���false��
	@Override
	public boolean usersLogin(Users u) {
		Transaction tx = null;
		String hql = "";
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users where uname=? and password=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, u.getUname());
			query.setParameter(1, u.getPassword());
			List<?> list = query.list();
			if(list.size() > 0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			tx.commit();
			if(tx != null) {
				tx = null;
			}
		}
	}

}
