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
import entities.Role;
import entities.User;
import exception.UserExistException;

public class RegisterAdminOperations {
		private static SessionFactory factory;
		private Session session=null;
		private Transaction tx=null;
		
		public RegisterAdminOperations() {
			StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
			 Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
			factory = meta.getSessionFactoryBuilder().build(); 
		}

		public boolean validateUsername(String username) throws UserExistException {
			List<User> list = new ArrayList<>();
			try{
				session=factory.openSession();
				tx = session.beginTransaction();
				@SuppressWarnings("unchecked")
				Query<User> query = session.createQuery("select admin.username from Admin admin where admin.username=:u");
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

		public boolean adminRegister(Admin admin) {
			boolean confirmed=false;
			try{
				Role role = new Role();
				role.setRolename("admin");
				role.setAdmin(admin);
				admin.setRole(role);
				session=factory.openSession();
				tx=session.beginTransaction();
				session.save(admin);
				session.save(role);
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
