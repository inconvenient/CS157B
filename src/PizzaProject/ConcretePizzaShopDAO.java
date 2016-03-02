package PizzaProject;

import java.util.ArrayList;
import javax.persistence.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ConcretePizzaShopDAO implements PizzaShopDAO {

	private SessionFactory sessionFactory;

	@Override
	public User createUser(User user) {
		Session session = null;
		Transaction transaction = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (HibernateException he) {
			transaction.rollback();
			System.out.println("DAO Error: Transaction rolled back.");
		} finally {
			session.close();
			sessionFactory.close();
		}
		return user;
	}

	@Override
	public User login(String username, String password) {
		return null;
	}

	@Override
	public Order createOrder(Order order) {
		return null;
	}

	@Override
	public ArrayList<Order> viewOrder() {
		return null;
	}

	@Override
	public Order changeOrder(Order order) {
		return null;
	}

	@Override
	public boolean cancelOrder(Order order) {
		return false;
	}

}
