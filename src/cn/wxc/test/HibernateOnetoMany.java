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
	public void testAdd() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HbernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// 添加一个客户，为这个客户添加一个联系人
			Customer customer = new Customer();
			customer.setCustName("myname");
			customer.setCustLevel("vip");
			customer.setCustSource("net");
			customer.setCustPhone("110");
			customer.setCustMobile("999");

			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("linkName");
			linkMan.setLkm_gender("man");
			linkMan.setLkm_phone("911");

			// 在客户里表示所有联系人，在联系人里表示客户
			// 建立客户和联系人对象关系
			// 把联系人的对象放到客户对象的set集合里
			customer.getSetLinkMan().add(linkMan);
			// 把客户对象放到联系人里
			linkMan.setCustomer(customer);
			// 保存到数据库
			session.save(customer);
			session.save(linkMan);

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		}
	}

}
