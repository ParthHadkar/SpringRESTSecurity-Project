package com.student_crm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.student_crm.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//Inject Session Factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	//@Transactional
	public List<Customer> getCustomers() {
		 List<Customer> customers = null;
		try {
			//get hibernate current session
			Session currentSession = sessionFactory.getCurrentSession();
			Query<Customer> query = currentSession.createQuery("from Customer c",Customer.class);
			customers = query.getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public void saveOrUpdate(Customer customer) {
		try {
			//get hibernate current session
			Session currentSession = sessionFactory.getCurrentSession();
			//save or update Customer
			currentSession.saveOrUpdate(customer);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Customer getCustomer(int id) {
		Customer customer = null;
		try {
			//get hibernate current session
			Session session = sessionFactory.getCurrentSession();
			customer = session.get(Customer.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		try {
			//get hibernate current session
			Session session = sessionFactory.getCurrentSession();
			Customer customer = session.get(Customer.class, id);
			session.delete(customer);
			//Query query = session.createQuery("Delete FROM Customer WHERE id=:customerId");
			//query.setParameter("customerId", id);
			//query.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Customer checkEmailId(String emailId) {
		Customer customer = null;
		try {
			//get hibernate current session
			Session currentSession = sessionFactory.getCurrentSession();
			Query<Customer> query = currentSession.createQuery("from Customer c WHERE c.email=:email",Customer.class);
			query.setParameter("email", emailId);
			customer = query.getSingleResult();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	

}
