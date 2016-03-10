package PizzaProject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ConcretePizzaShopDAO implements PizzaShopDAO {

	private SessionFactory sessionFactory;

	public User createUser(User user) {
		Session session = null;
		Transaction transaction = null;
		boolean warning = false;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (HibernateException he) {
			transaction.rollback();
			System.out.println("DAO Error: Transaction rolled back.");
			warning = true;
		} finally {
			session.close();
			sessionFactory.close();
		}
		if (warning) {
			return null;
		}
		return user;
	}

	public User login(String username, String password) {
		Session session = null;
		Transaction transaction = null;
		List result = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Query login = session.createQuery("FROM User WHERE USERNAME = :user AND PASSWORD = :pw");
			login.setParameter("user", username);
			login.setParameter("pw", password);
			result = ((org.hibernate.Query) login).list();
			System.out.println(result.toString());

		} catch (HibernateException he) {
			System.out.println("DAO Error: Transaction rolled back.");
			System.out.println(he.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		if (result.isEmpty()) {
			return null;
		}
		return (User) result.get(0);
	}

	public Order createOrder(User user, Order order) {
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.save(user);
			user.addOrder(order);

			session.update(user);
			transaction.commit();

		} catch (HibernateException he) {
			System.out.println("DAO Error: Create Order Failed");
			System.out.println(he.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	public ArrayList<Order> viewOrder() {
		return null;
	}

	public Order changeOrder(Order order) {
		return null;
	}

	public boolean cancelOrder(Order order) {
		return false;
	}

}
