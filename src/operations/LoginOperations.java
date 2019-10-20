package operations;

import java.util.List;

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
	}
	public boolean validateUser(String username, String password) throws InvalidUserException{
		session = factory.openSession();
		tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<String> query = session.createQuery("select user.password from User user where user.username=: u");
		query.setParameter("u", username);
		List<String> list = query.list();
		if(list.get(0).equals(password))
			return true;
		else
			return false;		
	}	

}
