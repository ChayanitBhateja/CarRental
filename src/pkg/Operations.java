package pkg;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
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
		int num = Integer.parseInt(mobileno);
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
	
	@SuppressWarnings({ "unchecked" })
	public boolean usernameCheck(String username) throws UserExistException{
		List<User> list = null;
		try{
			session=factory.openSession();
			tx = session.beginTransaction();
			Query<User> query = session.createQuery("from username where tbl_reguser_username:u");
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
		int num = Integer.parseInt(aadharno);
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
