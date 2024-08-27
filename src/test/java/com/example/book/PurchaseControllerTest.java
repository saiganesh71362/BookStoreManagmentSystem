package com.example.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.book.appconstants.BookStoreAppConstants;
import com.example.book.controller.PurchaseController;
import com.example.book.entity.Book;
import com.example.book.entity.Customer;
import com.example.book.model.PurchaseRequest;
import com.example.book.serviceimpl.ProcessPurchaseImpl;

@SpringBootTest
class PurchaseControllerTest {
	@Mock
	ProcessPurchaseImpl processPurchaseImpl;

	@InjectMocks
	PurchaseController purchaseController;

	@Test
	@Order(1)
	void test_purchaseBook() throws Exception {

		Long bid = 1l;
		Book book = new Book();
		book.setQuantity(10);

		Long cid = 1l;
		Customer customer = new Customer();

		PurchaseRequest purchaseRequest = new PurchaseRequest();
		purchaseRequest.setBookId(1L);
		purchaseRequest.setCustomerId(1L);
		purchaseRequest.setQuantity(5);

		// Mock the processPurchase method to do nothing
		doNothing().when(processPurchaseImpl).processPurchase(purchaseRequest);

		ResponseEntity<String> purchaseBook = purchaseController.purchaseBook(purchaseRequest);

		// Assertions
		assertEquals(HttpStatus.OK, purchaseBook.getStatusCode());
		assertEquals(BookStoreAppConstants.PURCHASE_SUCCFL, purchaseBook.getBody());

	}

}
