package com.example.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.book.controller.CustomerController;
import com.example.book.entity.Customer;
import com.example.book.serviceimpl.CustomerServiceImpl;

@SpringBootTest
class CustomerControllerTest {

	@Mock
	CustomerServiceImpl customerServiceImpl;

	@InjectMocks
	CustomerController customerController;

	@Test
	@Order(1)
	void test_getAllCustomers() {
		ArrayList<Customer> customerList = new ArrayList<Customer>();

		Customer customer1 = new Customer("Thanu", "thanu@gmail.com", "Venkatagiri");
		Customer customer2 = new Customer("Lireesha", "lireesha@gmail.com", "Bangarupeta");

		customerList.add(customer1);
		customerList.add(customer2);

		when(customerServiceImpl.getAllCustomers()).thenReturn(customerList);

		ResponseEntity<List<Customer>> allCustomers = customerController.getAllCustomers();

		assertEquals(HttpStatus.FOUND, allCustomers.getStatusCode());
		assertEquals(2, allCustomers.getBody().size());
	}

	@Test
	@Order(2)
	void test_getCustomerById() throws Exception {
		Long id = 1l;
		Customer customer1 = new Customer("Thanu", "thanu@gmail.com", "Venkatagiri");

		// Mock
		when(customerServiceImpl.getCustomerById(id)).thenReturn(customer1);

		ResponseEntity<Customer> customerById = customerController.getCustomerById(id);

		assertEquals(HttpStatus.FOUND, customerById.getStatusCode());
		assertEquals(customer1, customerById.getBody());
	}

	@Test
	@Order(3)
	void test_createCustomer() {
		Long id = 3l;
		Customer customer3 = new Customer("Deepthi", "deepthi25@gmail.com", "Nellore");

		when(customerServiceImpl.createNewCustomer(customer3)).thenReturn(customer3);

		ResponseEntity<Customer> createCustomer = customerController.createCustomer(customer3);

		assertEquals(HttpStatus.CREATED, createCustomer.getStatusCode());
		assertEquals(customer3, createCustomer.getBody());
	}

	@Test
	@Order(4)
	void test_updateCustomerById() throws Exception {
		Long eid = 3l;
		Customer existingCust = new Customer("Deepthi", "deepthi25@gmail.com", "Nellore");

		Long uid = 3l;
		Customer updateCust = new Customer("Deepthi", "deepthi25@gmail.com", "Gudur-Ma");

		when(customerServiceImpl.updateCustomerById(uid, updateCust)).thenReturn(updateCust);
		ResponseEntity<Customer> updateCustomerById = customerController.updateCustomerById(uid, updateCust);

		assertEquals(HttpStatus.OK, updateCustomerById.getStatusCode());

		assertEquals(updateCust.getName(), updateCustomerById.getBody().getName());
		assertEquals(updateCust.getEmail(), updateCustomerById.getBody().getEmail());
		assertEquals(updateCust.getAddress(), updateCustomerById.getBody().getAddress());

		verify(customerServiceImpl, times(1)).updateCustomerById(uid, updateCust);
	}

	@Test
	@Order(5)
	void test_deleteCustomerById() throws Exception {

		Long id = 3l;
		Customer customer3 = new Customer("Deepthi", "deepthi25@gmail.com", "Gudur-Ma");

		when(customerServiceImpl.deleteCustomerById(id)).thenReturn(true);

		ResponseEntity<Boolean> deleteCustomerById = customerController.deleteCustomerById(id);

		assertEquals(HttpStatus.OK, deleteCustomerById.getStatusCode());
		assertEquals(true, deleteCustomerById.getBody());
	}
}
