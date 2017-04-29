package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

//由于创建Session类需要很多步，为了便于创建和减少重复代码，于是我们创建MyHibrnateSessionFactory这个类用于生成会话工厂，这个类是单例的。
public class MyHibernateSessionFactory {
	
	private static SessionFactory sessionFactory; //会话工厂属性
	
	private MyHibernateSessionFactory() {} //构造方法私有化，保证单例模式。
	
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
