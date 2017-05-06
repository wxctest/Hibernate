package cn.wxc.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HbernateUtils {

	private static final Configuration configuration;
	private static final SessionFactory sessionFactory;

	static {
		configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void main(String[] args) {
		
	}
}
