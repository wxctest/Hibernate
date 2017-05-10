package cn.wxc.test;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.wxc.entity.Customer;
import cn.wxc.entity.LinkMan;
import cn.wxc.utils.HbernateUtils;

public class HibernateDemo {

	@Test
	public void testSelect(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HbernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			// 根据cid=1的客户，再查询这个客户里面的所有联系人
			Customer customer = session.get(Customer.class, 1);
			// 再查询这个客户里面的所有联系人
			//直接得到客户里面联系人的set集合
			Set<LinkMan> linkMans = customer.getSetLinkMan();
			System.out.println(linkMans.size());
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
}
