package entity;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import db.MyHibernateSessionFactory;

public class TestStudents {
	//�������ɱ�ṹ
	@Test
	public void testSchemaExport() {
		//�������ö���
		Configuration config = new Configuration().configure();
//		//��������ע�����
//		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
//		//����SessionFactory
//		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
//		//����Session����
//		Session session = sessionFactory.getCurrentSession();
		//����SchemaExport����
		SchemaExport export = new SchemaExport(config);
		
		export.create(true,true); //��һ��true�������ɱ�ṹ���ڶ���true�������sql��䡣
	}
	
	//���Դ���ѧ������
	@Test
	public void testSaveStudents() {
		//����֮ǰ�����ĵ���ģʽ�����Ự�������������Ự��
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		//����ѧ������
		Students s1 = new Students("s0000001","�ų�","��",new Date(),"����");
		Students s2 = new Students("s0000002","�Ψ","��",new Date(),"�Ϻ�");
		Students s3 = new Students("s0000003","����","��",new Date(),"�ɶ�");
		//����ѧ�����ݵ�ѧ����
		session.save(s1);
		session.save(s2);
		session.save(s3);
		//�ύ����
		tx.commit();
	}

}
