package pkg;


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

import exception.InvalidAadharException;
import exception.UserExistException;
import exception.WrongNumberException;

public class Operations {
	
	private static SessionFactory factory;
	private Session session=null;
	private Transaction tx=null;
	public Operations() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
		 Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
		  
		factory = meta.getSessionFactoryBuilder().build(); 
	}
	public boolean mobileCheck(String mobileno) throws WrongNumberException{ 
		long num = Long.parseLong(mobileno);
		int count=0;
		while(num>0) {
			num=num/10;
			count++;
		}
		if(count!=10) {
			throw new WrongNumberException();
		}
		else {
			return true;
		}
	}
	
	public boolean usernameCheck(String username) throws UserExistException{
		List<User> list = new ArrayList<>();
		try{
			session=factory.openSession();
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query<User> query = session.createQuery("select user.username from User user where user.username=:u");
			query.setParameter("u",username);
			list = query.list();
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }	
		if(list.isEmpty()) {
			return true;
		}
		else {
			throw new UserExistException();
		}
	}
	
	public boolean aadharCheck(String aadharno) throws InvalidAadharException {
		Long num = Long.parseLong(aadharno);
		int count=0;
		while(num>0) {
			num=num/10;
			count++;
		}
		
		if(count==12)
			return true;
		else
			throw new InvalidAadharException();
	}
	
	public boolean registerUser(User user) {
		boolean confirmed=false;
		try{
			session=factory.openSession();
			tx=session.beginTransaction();;
			session.save(user);
			tx.commit();
			confirmed=true;
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close();
	      }
        return confirmed;
	}

}
