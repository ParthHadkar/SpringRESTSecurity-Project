package com.student_crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student_crm.dao.CustomerDAO;
import com.student_crm.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// Inject CustomerDAO
		@Autowired
		private CustomerDAO customerDAO;
		
	@Override
	@Transactional("mytransactionManager")
	public List<Customer> getCustomers() {
		List<Customer> customers = customerDAO.getCustomers();
		return customers;
	}

	@Override
	@Transactional("mytransactionManager")
	public void saveOrUpdate(Customer customer) {
		customerDAO.saveOrUpdate(customer);
	}

	@Override
	@Transactional("mytransactionManager")
	public Customer getCustomer(int id) {
		Customer customer = customerDAO.getCustomer(id);
		return customer;
	}

	@Override
	@Transactional("mytransactionManager")
	public void deleteCustomer(int id) {
		customerDAO.deleteCustomer(id);
	}

	@Override
	@Transactional("mytransactionManager")
	public Customer checkEmailId(String emailId) {
		// TODO Auto-generated method stub
		return customerDAO.checkEmailId(emailId);
	}

	
}
