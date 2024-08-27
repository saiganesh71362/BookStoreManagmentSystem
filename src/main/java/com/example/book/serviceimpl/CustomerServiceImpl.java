package com.example.book.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.book.appconstants.BookStoreAppConstants;
import com.example.book.entity.Customer;
import com.example.book.globalexceptionhandle.CustomerCreationException;
import com.example.book.globalexceptionhandle.CustomerNotFoundException;
import com.example.book.globalexceptionhandle.CustomerUpdateException;
import com.example.book.repository.CustomerRepository;
import com.example.book.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository; // Constructor Injection

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();

	}

	@Override
	public Customer getCustomerById(Long id) {
		Optional<Customer> findById = customerRepository.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		}
		throw new CustomerNotFoundException("No Customer found with ID: " + id);
	}

	@Override
	public Customer createNewCustomer(Customer customer) {
		if (customer != null) {
			return customerRepository.save(customer);
		}
		throw new CustomerCreationException("The customer provided is null and cannot be created.");
	}

	@Override
	public Customer updateCustomerById(Long id, Customer customer) {
		if (customerRepository.existsById(id)) {
			Customer existingCustomer = customerRepository.findById(id)
					.orElseThrow(() -> new CustomerNotFoundException(BookStoreAppConstants.CUST_NOT_FOUND));

			// Update customer details
			existingCustomer.setName(customer.getName());
			existingCustomer.setEmail(customer.getEmail());
			existingCustomer.setAddress(customer.getAddress());

			// Save the updated customer
			return customerRepository.save(existingCustomer);
		} else {
			throw new CustomerUpdateException(BookStoreAppConstants.CUST_ID_NOT_EXIST + id);
		}
	}

	@Override
	public boolean deleteCustomerById(Long id) {
		Optional<Customer> findById = customerRepository.findById(id);
		if (findById.isPresent()) {
			customerRepository.deleteById(id);
			return true;
		} else {
			throw new CustomerNotFoundException("No Customer found with ID: " + id);
		}
	}

}
