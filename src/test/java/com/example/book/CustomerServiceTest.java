package com.example.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book.entity.Customer;
import com.example.book.globalexceptionhandle.CustomerCreationException;
import com.example.book.globalexceptionhandle.CustomerNotFoundException;
import com.example.book.globalexceptionhandle.CustomerUpdateException;
import com.example.book.repository.CustomerRepository;
import com.example.book.serviceimpl.CustomerServiceImpl;

@SpringBootTest
class CustomerServiceTest {
	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Test
	@Order(1)
	void test_getAllCustomers() {
		ArrayList<Customer> customerList = new ArrayList<Customer>();

		Customer customer1 = new Customer("Thanu", "thanu@gmail.com", "Simla");
		Customer customer2 = new Customer("Lireesha", "lireesha@gmail.com", "Kerala");

		customerList.add(customer1);
		customerList.add(customer2);

		// Mock
		when(customerRepository.findAll()).thenReturn(customerList);

		assertEquals(2, customerServiceImpl.getAllCustomers().size());
	}

	@Test
	@Order(2)
	void test_getCustomerById() throws CustomerNotFoundException {
		Long id = 2l;
		Customer customer2 = new Customer("Lireesha", "lireesha@gmail.com", "Kerala");

		// Mock
		when(customerRepository.findById(id)).thenReturn(Optional.of(customer2));

		assertEquals(customer2, customerServiceImpl.getCustomerById(id));

	}

	@Test
	@Order(3)
	void test_getCustomerById_NotFound() {
		Long id = 1L;
		when(customerRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(CustomerNotFoundException.class, () -> customerServiceImpl.getCustomerById(id));
	}

	@Test
	@Order(4)
	void test_createNewCustomer() {
		Customer customer3 = new Customer("Lireesha", "lireesha@gmail.com", "Kerala");

		when(customerRepository.save(customer3)).thenReturn(customer3);

		assertEquals(customer3, customerServiceImpl.createNewCustomer(customer3));
		verify(customerRepository, times(1)).save(customer3);
	}

	@Test
	@Order(5)
	void test_createNewCustomerFaild() {
		Customer customer3 = null;
		assertThrows(CustomerCreationException.class, () -> {
			customerServiceImpl.createNewCustomer(customer3);
		});
	}

	@Test
	@Order(6)
	void test_updateCustomerById() throws CustomerUpdateException {
		Long id = 3L;
		Customer existingCustomer = new Customer("Lireesha", "lireesha@gmail.com", "Kerala");
		Customer updateCustomer = new Customer("Lireesha", "lireesha123@gmail.com", "Kerala");

		// Mocking the repository methods
		when(customerRepository.existsById(id)).thenReturn(true);
		when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));
		when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);

		// Act
		Customer updatedCustomer = customerServiceImpl.updateCustomerById(id, updateCustomer);

		// Assert
		assertEquals(updateCustomer.getName(), updatedCustomer.getName());
		assertEquals(updateCustomer.getEmail(), updatedCustomer.getEmail());
		assertEquals(updateCustomer.getAddress(), updatedCustomer.getAddress());

	}

	@Test
	@Order(7)
	void test_updateBookById_NoBookInRepository() {
		Long id = 3L;
		Customer updateCustomer = new Customer("Lireesha", "lireesha123@gmail.com", "Kerala");

		// Mocking the repository methods
		when(customerRepository.existsById(id)).thenReturn(false);

		// Act & Assert
		CustomerUpdateException exception = assertThrows(CustomerUpdateException.class,
				() -> customerServiceImpl.updateCustomerById(id, updateCustomer));

		assertEquals("Customer With ID  Does Not Exist" + id, exception.getMessage());
	}

	@Test
	@Order(8)
	void test_deleteCustomerById() throws Exception {
		Long id = 3l;
		Customer existingCustomer = new Customer("Lireesha", "lireesha@gmail.com", "Kerala");

		when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));
		assertEquals(true, customerServiceImpl.deleteCustomerById(id));
	}

	@Test
	@Order(9)
	void test_deleteCustomerById_CustomerNotFound() {
		Long id = 3l;

		when(customerRepository.findById(id)).thenReturn(Optional.empty());

		CustomerNotFoundException assertThrows2 = assertThrows(CustomerNotFoundException.class,
				() -> customerServiceImpl.deleteCustomerById(id));

		assertNotNull(assertThrows2);
	}

}
