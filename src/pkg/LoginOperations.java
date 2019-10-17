package pkg;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import exception.InvalidUserException;

public class LoginOperations {
	private SessionFactory factory;
	private Session session=null;
	private Transaction tx=null;
	public LoginOperations() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
		factory = meta.getSessionFactoryBuilder().build(); 
	}
	public boolean validateUser(String username, String password) throws InvalidUserException{
		// TODO Auto-generated method stub
		return false;
	}	

}
