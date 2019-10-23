package operations;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class IndexAdminOperations {
	private static SessionFactory factory;
	private static Session session=null;
	private static Transaction tx=null;
	
	public IndexAdminOperations() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
		 Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
		factory = meta.getSessionFactoryBuilder().build(); 
	}
	
	public static Long countBookings() {
		Long result = null;
		try {
		session = factory.openSession();
		tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<Long> query = session.createQuery("select count(*) from Booking");
		result = query.uniqueResult();
		tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}	
		if(result!=null) {
			return result;
		}
		else {
			return (long) 0;
		}
	}
	
	public Long countQueries() {
		Long result = null;
		try {
		session = factory.openSession();
		tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<Long> query = session.createQuery("select count(*) from Query");
		result = query.uniqueResult();
		tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}	
		if(result!=null) {
			return result;
		}
		else {
			return (long) 0;
		}
	}
	
	public Long countUsers() {
		Long result = null;
		try {
		session = factory.openSession();
		tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<Long> query = session.createQuery("select count(*) from User");
		result = query.uniqueResult();
		tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}	
		if(result!=null) {
			return result;
		}
		else {
			return (long) 0;
		}
	
	}

}
