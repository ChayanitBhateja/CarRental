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

import entities.Booking;
import entities.Brand;
import entities.User;
import entities.Vehicle;
import exception.InvalidUserException;
import exception.NoBrandAvailableException;
import exception.NoVehicleException;

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
	
	@SuppressWarnings("unchecked")
	public Brand getBrandByBrandname(String brandname) throws NoBrandAvailableException {
		Brand brand;
		List<Brand> list = new ArrayList<>();
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<Brand> query = session.createQuery("from Brand where name=:brandname");
			query.setParameter("brandname",brandname);
			list=query.list();
			tx.commit();
			}catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} finally {
				session.close(); 
			}	
		if(!list.isEmpty()) {
			brand = list.get(0);
			return brand;
		}
		else {
			throw new NoBrandAvailableException();
		}
	}
	
	public boolean addQuery(entities.Query query) {
		boolean flag=false;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(query);
			tx.commit();
			flag=true;
			}catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} finally {
				session.close(); 
			}	
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public List<Vehicle> displayVehicle() throws NoVehicleException{
		List<Vehicle> list = new ArrayList<>();
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<Vehicle> query = session.createQuery("from Vehicle");
			list=query.list();
			tx.commit();
			}catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} finally {
				session.close(); 
			}	
		if(!list.isEmpty())
			return list;
		else
			throw new NoVehicleException();
	}
	
	
	@SuppressWarnings("unchecked")
	public Vehicle displayVehicleByNumber(String number) throws NoVehicleException{
		List<Vehicle> list = new ArrayList<>();
		Vehicle vehicle=null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<Vehicle> query = session.createQuery("from Vehicle where number=:num1");
			query.setParameter("num1",number);
			list=query.list();
			tx.commit();
			}catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} finally {
				session.close(); 
			}	
		if(!list.isEmpty()) {
			vehicle = list.get(0);
			return vehicle;
		}
		else
			throw new NoVehicleException();
	}
	
	@SuppressWarnings("unchecked")
	public List<Vehicle> displayVehiceByType(String name) throws NoVehicleException{
		List<Vehicle> list = new ArrayList<>();
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<Vehicle> query = session.createQuery("from Vehicle where brand=:name1");
			query.setParameter("name1",name);
			list=query.list();
			tx.commit();
			}catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} finally {
				session.close(); 
			}	
		if(!list.isEmpty())
			return list;
		else
			throw new NoVehicleException();
	}
	public boolean createBooking(Booking booking) {
		boolean flag=false;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(booking);
			tx.commit();
			flag=true;
			}catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} finally {
				session.close(); 
			}	
		return flag;
	}
}
