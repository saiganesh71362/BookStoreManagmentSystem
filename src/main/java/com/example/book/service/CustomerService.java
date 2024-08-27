package com.example.book.service;

import java.util.List;

import com.example.book.entity.Customer;
import com.example.book.globalexceptionhandle.CustomerCreationException;
import com.example.book.globalexceptionhandle.CustomerNotFoundException;
import com.example.book.globalexceptionhandle.CustomerUpdateException;

public interface CustomerService {

	public List<Customer> getAllCustomers();

	public Customer getCustomerById(Long id) throws CustomerNotFoundException;

	public Customer createNewCustomer(Customer customer) throws CustomerCreationException;

	public Customer updateCustomerById( Long id , Customer customer ) throws CustomerUpdateException;

	public boolean deleteCustomerById(Long id) throws CustomerNotFoundException;
}
