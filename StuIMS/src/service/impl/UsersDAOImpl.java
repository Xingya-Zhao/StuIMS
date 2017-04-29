package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Users;
import service.UsersDAO;

public class UsersDAOImpl implements UsersDAO {

	//传给它一个Users对象，该方法根据这个对象中的用户名和密码来查询数据库中是否存有该用户，如果有的话返回true，没有返回false。
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
