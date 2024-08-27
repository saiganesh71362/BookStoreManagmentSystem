package com.example.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.example.book.controller.BookController;
import com.example.book.entity.Book;
import com.example.book.serviceimpl.BookServiceImpl;

@SpringBootTest
class BookControllerTest {

	@Mock
	BookServiceImpl bookServiceImpl;

	@InjectMocks
	BookController bookController;

	@Test
	@Order(1)
	void test_getAllBooks() {
		ArrayList<Book> bookList = new ArrayList<Book>();
		Book book1 = new Book("Mathematics", "Cv Raman", "10188829", 3000.0, 50);
		Book book2 = new Book("Gitanjali", "Rabindranath Tagore", "0091191", 6000.0, 30);

		bookList.add(book1);
		bookList.add(book2);

		when(bookServiceImpl.getAllBooks()).thenReturn(bookList);
		ResponseEntity<List<Book>> allBooks = bookController.getAllBooks();

		assertEquals(HttpStatus.FOUND, allBooks.getStatusCode());
		assertEquals(2, allBooks.getBody().size());
	}

	@Test
	@Order(2)
	void test_getBookById() throws Exception {
		Long id = 1l;
		Book book1 = new Book("Mathematics", "Cv Raman", "10188829", 3000.0, 50);

		// Mock
		when(bookServiceImpl.getBookById(id)).thenReturn(book1);

		ResponseEntity<Book> bookById = bookController.getBookById(id);

		assertEquals(HttpStatus.FOUND, bookById.getStatusCode());

		assertEquals(book1, bookById.getBody());
	}

	@Test
	@Order(3)
	void test_createNewBook() throws Exception {
		Long id = 1l;
		Book book1 = new Book("Mathematics", "Cv Raman", "10188829", 3000.0, 50);

		when(bookServiceImpl.createNewBook(book1)).thenReturn(book1);

		ResponseEntity<Book> createNewBook = bookController.createNewBook(book1);

		assertEquals(HttpStatus.CREATED, createNewBook.getStatusCode());

		assertEquals(book1, createNewBook.getBody());
	}

	@Test
	@Order(4)
	void test_updateBookById() throws Exception {
		Long eid = 1l;
		Book existingBook = new Book("Mathematics", "Cv Raman", "10188829", 3000.0, 50);

		Long uid = 1l;
		Book updatingBook = new Book("Mathematics", "C.v. Raman", "10188829", 3000.0, 100);

		when(bookServiceImpl.updateBookById(uid, updatingBook)).thenReturn(updatingBook);
		ResponseEntity<Book> updateBookById = bookController.updateBookById(uid, updatingBook);

		assertEquals(HttpStatus.OK, updateBookById.getStatusCode());
		assertEquals(updatingBook.getTitle(), updateBookById.getBody().getTitle());
		assertEquals(updatingBook.getAuthor(), updateBookById.getBody().getAuthor());
		assertEquals(updatingBook.getIsbn(), updateBookById.getBody().getIsbn());
		assertEquals(updatingBook.getPrice(), updateBookById.getBody().getPrice());
		assertEquals(updatingBook.getQuantity(), updateBookById.getBody().getQuantity());
	}

	@Test
	@Order(5)
	void test_DeleteBookId() throws Exception {
		Long id = 1l;
		Book book1 = new Book("Mathematics", "Cv Raman", "10188829", 3000.0, 50);

		when(bookServiceImpl.deleteBookById(id)).thenReturn(true);

		ResponseEntity<Boolean> deleteBookId = bookController.deleteBookId(id);
		assertEquals(HttpStatus.OK, deleteBookId.getStatusCode());
		assertEquals(true, deleteBookId.getBody());
	}
}
