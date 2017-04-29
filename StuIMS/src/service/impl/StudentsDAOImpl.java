package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Students;
import service.StudentsDAO;

public class StudentsDAOImpl implements StudentsDAO {
	
	private Transaction tx = null;
	private String hql = "";
	
	@Override
	public List<Students> queryAllStudents() {
		List<Students> list = null;
		try{
			hql = "from Students";
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			list = query.list();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return list;
		}finally {
			tx.commit();
			if(tx!=null) {
				tx = null;
			}
		}
	}

	@Override
	public Students queryStudentsBySid(String sid) {
		Students s = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			s = (Students)session.get(Students.class, sid);
			return s;
		}catch(Exception e) {
			e.printStackTrace();
			return s;
		}finally {
			tx.commit();
			if(tx!=null) {
				tx = null;
			}
		}
	}

	@Override
	public boolean addStudents(Students s) {
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(s);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			tx.commit();
			if(tx!=null) {
				tx = null;
			}
		}
	}

	@Override
	public boolean updataStudents(Students s) {
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(s);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			tx.commit();
			if(tx!=null) {
				tx = null;
			}
		}
	}

	@Override
	public boolean deleteStudents(String sid) {
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Students s = (Students)session.get(Students.class, sid);
			session.delete(s);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			tx.commit();
			if(tx!=null) {
				tx = null;
			}
		}	
	}
	
}
