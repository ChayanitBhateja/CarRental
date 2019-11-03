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

import entities.Brand;
import entities.Vehicle;
import exception.NoBrandAvailableException;
import exception.NoVehicleException;

public class IndexAdminOperations {
	private static SessionFactory factory;
	private static Session session=null;
	private static Transaction tx=null;
	
	public IndexAdminOperations() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
		 Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
		factory = meta.getSessionFactoryBuilder().build(); 
	}
	
	@SuppressWarnings("unchecked")
	public static List<Long> countQueries() {
		List<Long> list=new ArrayList<>();
		try {
		session = factory.openSession();
		tx = session.beginTransaction();
		
		Query<Long> query1 = session.createQuery("select count(*) from Booking");
		Query<Long> query2 = session.createQuery("select count(*) from Query");
		Query<Long> query3 = session.createQuery("select count(*) from User");
		list.add(query1.uniqueResult());
		list.add(query2.uniqueResult());
		list.add(query3.uniqueResult());
		tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}	
		return list;
	}
	
	public boolean addVehicle(Vehicle vehicle) {
		boolean flag=false;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
//			session.save(brand);
			session.save(vehicle);
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
	
	public boolean addBrand(Brand brand) {
		boolean flag=false;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(brand);
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
	public int editVehicle(Vehicle vehicle,String number) {
		int flag=0;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<Long> query = session.createQuery("update Vehicle set number=:num, name=:name where number=:num2");
			query.setParameter("num",vehicle.getNumber());
			query.setParameter("name",vehicle.getName());
			query.setParameter("num2",number);
			flag=query.executeUpdate();
			tx.commit();
			}catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} finally {
				session.close(); 
			}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public int deleteVehicle(String number) {
		int flag=0;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<Long> query = session.createQuery("delete Vehicle where number=:num");
			query.setParameter("num",number);
			flag=query.executeUpdate();
			tx.commit();
			}catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} finally {
				session.close(); 
			}	
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public int editBrand(Brand brand,String name) {
		int flag=0;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<Long> query = session.createQuery("update Brand set name=:name1 where name=:name2");
			query.setParameter("name1",brand.getName());
			query.setParameter("name2",name);
			flag=query.executeUpdate();
			tx.commit();
			}catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} finally {
				session.close(); 
			}	
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public int deleteBrand(String name) {
		int flag=0;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<Long> query = session.createQuery("delete Brand where name=:name1");
			query.setParameter("name1",name);
			flag=query.executeUpdate();
			tx.commit();
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
			Query<Vehicle> query = session.createQuery("from vehicle");
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
	
	@SuppressWarnings("unchecked")
	public List<Brand> displayBrand() throws NoBrandAvailableException{
		List<Brand> list = new ArrayList<>();
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<Brand> query = session.createQuery("from Brand");
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
			throw new NoBrandAvailableException();
	}

	@SuppressWarnings("unchecked")
	public Brand getBrandByName(String brandname) throws NoBrandAvailableException {
		// TODO Auto-generated method stub
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
		if(!list.isEmpty())
		{
			Brand brand = list.get(0);
			return brand;
		}	
		else
			throw new NoBrandAvailableException();
		
	}
}
