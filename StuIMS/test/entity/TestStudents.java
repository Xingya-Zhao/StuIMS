package entity;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import db.MyHibernateSessionFactory;

public class TestStudents {
	//测试生成表结构
	@Test
	public void testSchemaExport() {
		//创建配置对象
		Configuration config = new Configuration().configure();
//		//创建服务注册对象
//		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
//		//创建SessionFactory
//		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
//		//创建Session对象
//		Session session = sessionFactory.getCurrentSession();
		//创建SchemaExport对象
		SchemaExport export = new SchemaExport(config);
		
		export.create(true,true); //第一个true代表生成表结构，第二个true代表输出sql语句。
	}
	
	//测试存入学生数据
	@Test
	public void testSaveStudents() {
		//利用之前创建的单例模式创建会话工厂进而创建会话。
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		//创建学生对象
		Students s1 = new Students("s0000001","张楚","男",new Date(),"北京");
		Students s2 = new Students("s0000002","窦唯","男",new Date(),"上海");
		Students s3 = new Students("s0000003","何勇","男",new Date(),"成都");
		//存入学生数据到学生表。
		session.save(s1);
		session.save(s2);
		session.save(s3);
		//提交事务。
		tx.commit();
	}

}
