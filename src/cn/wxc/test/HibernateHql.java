package cn.wxc.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.wxc.entity.Customer;
import cn.wxc.utils.HbernateUtils;

public class HibernateHql {

	@Test
	public void testSexect() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HbernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Query query = session.createQuery("from Customer");
			List<Customer> list = query.list();
			for (Customer customer : list) {
				System.out.println(customer.getCid() + "::" + customer.getCustName());
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
