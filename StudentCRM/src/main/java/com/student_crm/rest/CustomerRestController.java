package com.student_crm.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_crm.entity.Customer;
import com.student_crm.rest.exception.CustomerNotFoundException;
import com.student_crm.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	// inject CustomerService dependency
	@Autowired
	private CustomerService customerService;
	
	// define endpoint for Get /customers - get list of customers
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	// define endpoint for Get /customers/{customerId} - get single customer
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId){
		Customer customer = customerService.getCustomer(customerId);
		if(customer == null) {
			throw new CustomerNotFoundException("Customer Id Not Found  : "+customerId);
		}
		return customer;
	}
	
	// define endpoint for Post /customers - add customer
	@PostMapping("/customers")
	public Customer addCustomer(@Valid @RequestBody Customer customer,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			List<String> errorMessage = new ArrayList<String>();
			for (FieldError error : bindingResult.getFieldErrors() ) {
				errorMessage.add(error.getField() + " - " + error.getDefaultMessage());
			}
			for (ObjectError error : bindingResult.getGlobalErrors()) {
				errorMessage.add(error.getObjectName() + ": " + error.getDefaultMessage());
			}
			throw new CustomerNotFoundException(errorMessage.toString());
		}
		Customer tempCustomer = customerService.checkEmailId(customer.getEmail());
		if(tempCustomer != null) {
			throw new CustomerNotFoundException("EmailId Already Exists !!!!");
		}
		customer.setId(0);// force to save new customer
		customerService.saveOrUpdate(customer);
		return customer;
	}
	
	// define endpoint for Put /customers - update customer
	@PutMapping("/customers")
	public Customer updateCustomer(@Valid @RequestBody Customer customer,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			List<String> errorMessage = new ArrayList<String>();
			for (FieldError error : bindingResult.getFieldErrors() ) {
				errorMessage.add(error.getField() + " - " + error.getDefaultMessage());
			}
			for (ObjectError error : bindingResult.getGlobalErrors()) {
				errorMessage.add(error.getObjectName() + ": " + error.getDefaultMessage());
			}
			throw new CustomerNotFoundException(errorMessage.toString());
		}
		Customer tempCustomer = customerService.getCustomer(customer.getId());
		if(tempCustomer == null) {
			throw new CustomerNotFoundException("Customer Id Not Found  : "+customer.getId());
		}
		Customer tempCustomerEmail = customerService.checkEmailId(customer.getEmail());
		if(tempCustomerEmail != null  && tempCustomer.getId() != customer.getId()) {
			throw new CustomerNotFoundException("EmailId Already Exists !!!!");
		}
		customerService.saveOrUpdate(customer);
		return customer;
	}
	
	// define endpoint for Delete /customers/{customerId} - get single customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId){
		Customer customer = customerService.getCustomer(customerId);
		if(customer == null) {
			throw new CustomerNotFoundException("Customer Id Not Found  : "+customerId);
		}
		customerService.deleteCustomer(customerId);
		return "Customer Delected Successfully With Id : "+customerId;
	}
	
}
