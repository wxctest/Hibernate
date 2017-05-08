package cn.wxc.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.wxc.entity.Customer;
import cn.wxc.entity.LinkMan;
import cn.wxc.utils.HbernateUtils;

public class HibernateOnetoMany {

	@Test
	public void testUpdate() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HbernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// 把apple的联系人改到google去
			// 根据 id查询客户对象
			Customer google = session.get(Customer.class, 1);
			// 根据id查询联系人
			LinkMan apple = session.get(LinkMan.class, 2);

			// 设置持久态对象值
			// 把联系人放到客户里，
			google.getSetLinkMan().add(apple);
			// 把客户放到联系人里
			apple.setCustomer(google);

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		}
	}

}
