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

	public void createOrder(User user, Order order) {
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.save(order);
			transaction.commit();

		} catch (HibernateException he) {
			System.out.println("DAO Error: Create Order Failed");
			System.out.println(he.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	public List<Order> viewOrder(User user) {
		Session session = null;
		Transaction transaction = null;
		List<Order> result = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Query viewOrders = session.createQuery("FROM Order WHERE USERNAME = :userID");
			viewOrders.setParameter("userID", user.getUserId());
			result = (List<Order>) ((org.hibernate.Query) viewOrders).list();

		} catch (HibernateException he) {
			System.out.println("DAO Error: Transaction rolled back.");
			System.out.println(he.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return result;
	}

	public void changeOrder(User user, int orderID, Order newOrder) {
		Session session = null;
		Transaction transaction = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Order oldOrder = (Order) session.get(Order.class, orderID);
			oldOrder.setSize(newOrder.getSize());
			oldOrder.setTopping(newOrder.getToppings());
			oldOrder.setPrice(newOrder.getPrice());
			session.merge(oldOrder);
			transaction.commit();

		} catch (HibernateException he) {
			transaction.rollback();
			System.out.println("DAO Error: Transaction rolled back.");
			System.out.println(he.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
			System.out.println("Success.");
		}
	}

	public int cancelOrder(User user, int orderID) {
		Session session = null;
		Transaction transaction = null;
		int successes = 0;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			org.hibernate.Query deleteOrder = session
					.createQuery("DELETE FROM Order WHERE USERNAME = :userID AND ORDER_ID = :orderID");
			deleteOrder.setParameter("userID", user.getUserId());
			deleteOrder.setParameter("orderID", orderID);
			successes = deleteOrder.executeUpdate();
			transaction.commit();

		} catch (HibernateException he) {
			transaction.rollback();
			System.out.println("DAO Error: Transaction rolled back.");
			System.out.println(he.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return successes;
	}

	public void createDiscountedOrder(User user, DiscountedOrder order) {
		Session session = null;
		Transaction transaction = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();

			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(order);
			transaction.commit();

		} catch (HibernateException he) {
			System.out.println("DAO Error: Create Order Failed");
			System.out.println(he.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	// Helper to create toppings table
	public void prepareToppings(List<Topping> t) {
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			for (Topping topping : t) {
				session.save(topping);
			}
			transaction.commit();

		} catch (HibernateException he) {
			System.out.println("Toppings table add has failed");
			System.out.println(he.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
