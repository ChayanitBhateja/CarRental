package pkg;


import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Validation {
	
	private static SessionFactory factory;
	private Session session=null;
	public void createSession() {
		factory= new Configuration().configure().buildSessionFactory();
		session = factory.openSession();
	}
	public boolean mobileCheck(String mobileno) throws pkg.exception.WrongNumberException{ 
		int num = Integer.parseInt(mobileno);
		int count=0;
		while(num>0) {
			num=num/10;
			count++;
		}
		
		if(count==10)
			return true;
		else
			return false;
	}
	
	@SuppressWarnings({ "null", "unchecked" })
	public boolean usernameCheck(String username) {
		createSession();
		Transaction tx = null;
		tx.begin();
		Query<User> query = session.createQuery("from username where tbl_reguser_username:u");
		query.setParameter("u",username);
		List<User> list = query.list();
		tx.commit();
		session.close();
		if(list.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean aadharCheck(String aadharno) {
		int num = Integer.parseInt(aadharno);
		int count=0;
		while(num>0) {
			num=num/10;
			count++;
		}
		
		if(count==12)
			return true;
		else
			return false;
	}

}
