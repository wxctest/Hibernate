package cn.wxc.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.wxc.manytomany.Role;
import cn.wxc.manytomany.User;
import cn.wxc.utils.HbernateUtils;

public class HibernateManytoMany {

	@Test
	public void test() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HbernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// 先查询
			User google2 = session.get(User.class, 2);
			Role juese3 = session.get(Role.class, 3);
			//从用户里把角色去掉
			google2.getSetRole().remove(juese3);			

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		}
	}

}
