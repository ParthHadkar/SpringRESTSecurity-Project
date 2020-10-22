package com.student_crm.dao;

import java.util.List;

import com.student_crm.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();
	
	public void saveOrUpdate(Customer customer);
	
	public Customer getCustomer(int id);
	
	public void deleteCustomer(int id);
	
	public Customer checkEmailId(String emailId);
	
}
