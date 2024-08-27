package com.example.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.book.entity.Book;
import com.example.book.entity.Customer;
import com.example.book.entity.Purchase;
import com.example.book.model.PurchaseRequest;

import com.example.book.repository.BookRepository;
import com.example.book.repository.CustomerRepository;
import com.example.book.repository.PurchaseRepository;
import com.example.book.serviceimpl.ProcessPurchaseImpl;

@SpringBootTest
class ProcessPurchaseServiceTest {
	@Mock
	BookRepository bookRepository;
	@Mock
	CustomerRepository customerRepository;

	@Mock
	PurchaseRepository purchaseRepository; // Inject the PurchaseRepository

	@InjectMocks
	ProcessPurchaseImpl processPurchaseImpl;

	@Test
	@Order(1)
	void test_processPurchase() throws Exception {
		// Arrange

		Long bid = 1l;
		Book book = new Book();
		book.setQuantity(10);

		Long cid = 1l;
		Customer customer = new Customer();

		PurchaseRequest purchaseRequest = new PurchaseRequest();
		purchaseRequest.setBookId(1L);
		purchaseRequest.setCustomerId(1L);
		purchaseRequest.setQuantity(5);

		when(bookRepository.findById(bid)).thenReturn(Optional.of(book));
		when(customerRepository.findById(cid)).thenReturn(Optional.of(customer));

		// Act
		processPurchaseImpl.processPurchase(purchaseRequest);

		// Assert
		assertEquals(5, book.getQuantity());
		verify(bookRepository, times(1)).save(book);
		verify(purchaseRepository, times(1)).save(any(Purchase.class));
	}
}

//@Test
//public void testProcessPurchase_BookNotFound() {
//    // Arrange
//    PurchaseRequest purchaseRequest = new PurchaseRequest();
//    purchaseRequest.setBookId(1L);
//
//    when(bookRepository.findById(1L)).thenReturn(Optional.empty());
//
//    // Act & Assert
//    Exception exception = assertThrows(Exception.class, () -> {
//        processPurchaseImpl.processPurchase(purchaseRequest);
//    });
//
//    assertEquals(BookStoreAppConstants.BOOK_NOT_FOUND, exception.getMessage());
//    verify(bookRepository, never()).save(any(Book.class));
//    verify(purchaseRepository, never()).save(any(Purchase.class));
//
//}
