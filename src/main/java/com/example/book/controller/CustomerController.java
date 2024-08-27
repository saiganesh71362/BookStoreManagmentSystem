package com.example.book.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.entity.Customer;
import com.example.book.globalexceptionhandle.CustomerCreationException;
import com.example.book.globalexceptionhandle.CustomerDeletionException;
import com.example.book.globalexceptionhandle.CustomerNotFoundException;
import com.example.book.globalexceptionhandle.CustomerUpdateException;
import com.example.book.serviceimpl.CustomerServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	private CustomerServiceImpl customerServiceImpl;

	public CustomerController(CustomerServiceImpl customerServiceImpl) {
		this.customerServiceImpl = customerServiceImpl;
	}

	@GetMapping("/allCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		logger.info("Received Request To Get All Customers");
		List<Customer> allCustomers = customerServiceImpl.getAllCustomers();
		logger.info("Successfully Retrieved {} Customers Availabe ", allCustomers.size());
		return new ResponseEntity<>(allCustomers, HttpStatus.FOUND);
	}

	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
		logger.info("Received Request To Get Customer By ID: {}", id);
		Customer customerById = customerServiceImpl.getCustomerById(id);
		logger.info("Successfully Retrieved Customer With ID: {}", id);
		return new ResponseEntity<>(customerById, HttpStatus.FOUND);
	}

	@PostMapping("/newCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws CustomerCreationException {
		logger.info("Request Received To Create A New Customer : {}",customer);
		Customer createNewCustomer = customerServiceImpl.createNewCustomer(customer);
		logger.info(" Customer Created Successfully: {}",createNewCustomer);
		return new ResponseEntity<>(createNewCustomer, HttpStatus.CREATED);
	}

	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<Customer> updateCustomerById(@PathVariable Long id, @RequestBody Customer customer)
			throws CustomerUpdateException {
		logger.info("Received Request TO Update Customer With ID: {}", id);
		logger.debug("Customer Details: {}", customer);
		Customer updateCustomerById = customerServiceImpl.updateCustomerById(id, customer);
		logger.info("Successfully Updated Customer With ID: {}", id);
		return new ResponseEntity<>(updateCustomerById, HttpStatus.OK);
	}

	@DeleteMapping("deleteCustomer/{id}")
	public ResponseEntity<Boolean> deleteCustomerById(@PathVariable Long id) throws CustomerDeletionException {
		logger.info("Received Request To Delete Customer with ID: {}", id);
		boolean deleteCustomerById = customerServiceImpl.deleteCustomerById(id);
		logger.info("Successfully Deleted Customer With ID: {}", id);
		return new ResponseEntity<>(deleteCustomerById, HttpStatus.OK);
	}
}
