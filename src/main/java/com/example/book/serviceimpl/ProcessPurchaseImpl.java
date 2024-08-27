package com.example.book.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.book.appconstants.BookStoreAppConstants;
import com.example.book.entity.Book;
import com.example.book.entity.Customer;
import com.example.book.model.PurchaseRequest;
import com.example.book.entity.Purchase;
import com.example.book.globalexceptionhandle.InsufficientStockException;
import com.example.book.globalexceptionhandle.ResourceNotFoundException;
import com.example.book.repository.BookRepository;
import com.example.book.repository.CustomerRepository;
import com.example.book.repository.PurchaseRepository;
import com.example.book.service.ProcessPurchase;

@Service
public class ProcessPurchaseImpl implements ProcessPurchase {

	BookRepository bookRepository;
	CustomerRepository customerRepository;

	PurchaseRepository purchaseRepository;

	public ProcessPurchaseImpl(BookRepository bookRepository, CustomerRepository customerRepository,
			PurchaseRepository purchaseRepository) {
		this.bookRepository = bookRepository;
		this.customerRepository = customerRepository;
		this.purchaseRepository = purchaseRepository;
	}

	@Override
	public void processPurchase(PurchaseRequest purchaseRequest) {

		Book book = bookRepository.findById(purchaseRequest.getBookId())
				.orElseThrow(() -> new ResourceNotFoundException(BookStoreAppConstants.BOOK_NOT_FOUND));

		if (book.getQuantity() < purchaseRequest.getQuantity()) {
			throw new InsufficientStockException(BookStoreAppConstants.NO_STOCK);
		}

		Customer customer = customerRepository.findById(purchaseRequest.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException(BookStoreAppConstants.CUST_NOT_FOUND));

		book.setQuantity(book.getQuantity() - purchaseRequest.getQuantity());
		bookRepository.save(book);

		Purchase purchase = new Purchase(book, customer, purchaseRequest.getQuantity(), LocalDateTime.now());
		purchaseRepository.save(purchase);

	}

}
