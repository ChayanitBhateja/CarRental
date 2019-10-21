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

import entities.Admin;
import entities.User;
import exception.InvalidUserException;
import exception.UserExistException;

public class LoginAdminOperations {
	
	private static SessionFactory factory;
	private Session session=null;
	private Transaction tx=null;
	
	public LoginAdminOperations() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
		 Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
		factory = meta.getSessionFactoryBuilder().build(); 
	}
	public String getRolename(String username) throws InvalidUserException{
		
		List<String> list = new ArrayList<>();
		try{
			session=factory.openSession();
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query<String> query = session.createQuery("select role.rolename from Role role inner join Admin admin1 on admin1.username=:u and role.admin=admin1.id");
			query.setParameter("u",username);
			list = query.list();
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }	
		if(!list.isEmpty()) {
		String name=list.get(0);
		return name;
		}
		else {
			throw new InvalidUserException();
		}
		
	}
	public boolean authenticateAdmin(String username, String password) throws InvalidUserException {
		List<Admin> list = new ArrayList<>();
		try{
			session=factory.openSession();
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query<Admin> query = session.createQuery("from Admin admin where admin.username=:u and admin.password=:p");
			query.setParameter("u",username);
			query.setParameter("p",password);
			list = query.list();
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }	
		if(!list.isEmpty()) {
			Admin admin = list.get(0);
				if(admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
					return true;
				}
				else {
					throw new InvalidUserException();
				}
		}
		else {
			throw new InvalidUserException();
		}
		
	}
	
	

}
