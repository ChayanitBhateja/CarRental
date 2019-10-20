package operations;

import java.util.ArrayList;
import java.util.Iterator;
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

import exception.InvalidUserException;

public class LoginOperations {
	private SessionFactory factory;
	private Session session=null;
	private Transaction tx=null;
	public LoginOperations() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
		factory = meta.getSessionFactoryBuilder().build();
		session = factory.openSession();
	}
	public boolean validateUser(String username, String password) throws InvalidUserException{
		
		tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<String> query = session.createQuery("select user.password from User user where user.username=: u");
		query.setParameter("u", username);
		List<String> list = query.list();
		tx.commit();
		if(list.isEmpty()) 
			throw new InvalidUserException();
			else {
				if(list.get(0).equals(password))
					return true;
				else
					throw new InvalidUserException();
			}
	}
	
	public String getName(String username) {
		List<String> list = new ArrayList<>();
		try{
//			session=factory.openSession();
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query<String> query = session.createQuery("select user.name from User user where user.username=:u");
			query.setParameter("u",username);
			list = query.list();
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }	
		String name = list.get(0);
		return name;
	}
	public String getRole(String username) {
		List<String> list = new ArrayList<>();
		tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<String> query = session.createQuery("select role.rolename from Role role where role.user.username=:u");
		query.setParameter("u",username);
		list = query.list();
		tx.commit();
		return list.get(0);
	}	

}
