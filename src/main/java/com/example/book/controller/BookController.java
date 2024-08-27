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

import com.example.book.entity.Book;
import com.example.book.globalexceptionhandle.BookCreationException;
import com.example.book.globalexceptionhandle.BookDeletionException;
import com.example.book.globalexceptionhandle.BookNotFoundException;
import com.example.book.globalexceptionhandle.BookUpdateException;
import com.example.book.serviceimpl.BookServiceImpl;

@RestController
@RequestMapping("/book")
public class BookController {

	private static final Logger logger = LogManager.getLogger(BookController.class);

	private BookServiceImpl bookServiceImpl; // constructor injection

	public BookController(BookServiceImpl bookServiceImpl) {
		this.bookServiceImpl = bookServiceImpl;
	}

	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		logger.info("Received Request To Get All Books");
		List<Book> allBooks = bookServiceImpl.getAllBooks();
		logger.info("Successfully Retrieved {} Books Availabe ", allBooks.size());
		return new ResponseEntity<>(allBooks, HttpStatus.FOUND);
	}

	@GetMapping("getBook/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) throws BookNotFoundException {
		logger.info("Received Request To Get Category By ID: {}", id);
		Book bookById = bookServiceImpl.getBookById(id);
		logger.info("Successfully Retrieved Book With ID: {}", id);
		return new ResponseEntity<>(bookById, HttpStatus.FOUND);
	}

	@PostMapping("/createNewBook")
	public ResponseEntity<Book> createNewBook(@RequestBody Book book) throws BookCreationException {
		logger.info("Request Received To Create A New Book: {}",book);
		Book createNewBook = bookServiceImpl.createNewBook(book);
		logger.info("Book Created Successfully: {}", createNewBook);
		return new ResponseEntity<>(createNewBook, HttpStatus.CREATED);
	}

	@PutMapping("/updateBookById/{id}")
	public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book book)
			throws BookUpdateException {
		logger.info("Received Request To Update Category With ID: {}", id);
		logger.debug("Book Details: {}", book);
		Book updateBookById = bookServiceImpl.updateBookById(id, book);
		logger.info("Successfully Updated Book With ID: {}", id);
		return new ResponseEntity<>(updateBookById, HttpStatus.OK);

	}

	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<Boolean> deleteBookId(@PathVariable Long id) throws BookDeletionException {
		logger.info("Received Request To Delete Book with ID: {}", id);
		boolean deleteBookById = bookServiceImpl.deleteBookById(id);
		logger.info("Successfully Deleted Book With ID: {}", id);
		return new ResponseEntity<>(deleteBookById, HttpStatus.OK);
	}
}
