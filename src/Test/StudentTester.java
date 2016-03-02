package Test;
import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class StudentTester {
    private static SessionFactory sessionFactory; 
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

       Student student = new Student();
       student.setName("Lee");
       
       Student anotherStudent = new Student();
       anotherStudent.setName("Smith");
       
       Session session = null;
       Transaction transaction= null;
       
      // sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
       try
       {
       sessionFactory = HibernateUtil.getSessionFactory();
       session = sessionFactory.openSession();
       transaction = session.beginTransaction();
       
       session.save(student);
       student.setGpa(4.0);
    
       session.save(anotherStudent);
       session.evict(anotherStudent); // detached during the transaction
       anotherStudent.setGpa(4.5); // Smith's GPA will not be changed. 
      
       transaction.commit();
       student.setGpa(2.0); // Lee's GPA will be still 4.0
       }
       catch (HibernateException he)
       {
    	   transaction.rollback();
           System.out.println("Transaction is rolled back.");
       }
       finally
       {
         session.close();
         sessionFactory.close();
       }
	}

}
