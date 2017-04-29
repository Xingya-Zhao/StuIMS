package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

//���ڴ���Session����Ҫ�ܶಽ��Ϊ�˱��ڴ����ͼ����ظ����룬�������Ǵ���MyHibrnateSessionFactory������������ɻỰ������������ǵ����ġ�
public class MyHibernateSessionFactory {
	
	private static SessionFactory sessionFactory; //�Ự��������
	
	private MyHibernateSessionFactory() {} //���췽��˽�л�����֤����ģʽ��
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			Configuration config = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		}else{
			return sessionFactory;
		}
	}

}
