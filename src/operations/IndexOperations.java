package operations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import entities.User;
import exception.InvalidUserException;

public class IndexOperations {
	private static SessionFactory factory;
	private static Session session=null;
	private static Transaction tx=null;
	
	public IndexOperations() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
		 Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
		factory = meta.getSessionFactoryBuilder().build(); 
	}
	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username) throws InvalidUserException {
		User user;
		List<User> list = new ArrayList();
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<User> query = session.createQuery("from User where username=:userr");
			query.setParameter("userr",username);
			list=query.list();
			tx.commit();
			}catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} finally {
				session.close(); 
			}	
		if(!list.isEmpty()) {
			user = list.get(0);
			return user;
		}
		else {
			throw new InvalidUserException();
		}
	}
}
