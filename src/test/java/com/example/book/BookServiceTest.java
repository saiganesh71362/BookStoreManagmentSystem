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

import com.example.book.entity.Book;
import com.example.book.globalexceptionhandle.BookCreationException;
import com.example.book.globalexceptionhandle.BookNotFoundException;
import com.example.book.globalexceptionhandle.BookUpdateException;
import com.example.book.repository.BookRepository;
import com.example.book.serviceimpl.BookServiceImpl;

@SpringBootTest
class BookServiceTest {
	@Mock
	BookRepository bookRepository;
	@InjectMocks
	BookServiceImpl bookServiceImpl;

	@Test
	@Order(1)
	void test_getAllBooks() {
		ArrayList<Book> bookList = new ArrayList<Book>();

		Book book1 = new Book("Mathematics", "Cv Raman", "10188829", 3000.0, 50);
		Book book2 = new Book("Gitanjali", "Rabindranath Tagore", "0091191", 6000.0, 30);
		bookList.add(book1);
		bookList.add(book2);

		when(bookRepository.findAll()).thenReturn(bookList); // Mocking
		assertEquals(2, bookServiceImpl.getAllBooks().size());

	}

	@Test
	@Order(2)
	void test_getBookById() throws BookNotFoundException {
		Book book1 = new Book("Mathematics", "Cv Raman", "10188829", 3000.0, 50);
		Long id = 1l;
		when(bookRepository.findById(id)).thenReturn(Optional.of(book1));
		assertEquals(book1, bookServiceImpl.getBookById(id));
	}

	@Test
	@Order(3)
	void test_getBookById_NotFound() {
		// Arrange
		long id = 1L;
		when(bookRepository.findById(id)).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(BookNotFoundException.class, () -> bookServiceImpl.getBookById(id));
	}

	@Test
	@Order(4)
	void test_createNewBook() throws BookCreationException {
		Book book3 = new Book("Physics", "Albert Einstein", "12345678", 5000.0, 100);
		when(bookRepository.save(book3)).thenReturn(book3);
		assertEquals(book3, bookServiceImpl.createNewBook(book3));
		verify(bookRepository, times(1)).save(book3);

	}

	@Test
	@Order(5)
	void test_createNewBookFailed() {
		Book book = null;

		BookCreationException exception = assertThrows(BookCreationException.class, () -> {
			bookServiceImpl.createNewBook(book);
		});

		// Assert the exception message
		assertEquals("The book provided is null and cannot be created.", exception.getMessage());
	}

	@Test
	@Order(6)
	void test_updateBookById() throws BookUpdateException {
		Long id = 3l;
		Book existingBook = new Book("Physics", "Albert Einstein", "12345678", 5000.0, 100);
		Book updatedBook = new Book("Physics", "Albert Einstein", "12345678", 5000.0, 50);

		when(bookRepository.existsById(id)).thenReturn(true);
		when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));
		when(bookRepository.save(existingBook)).thenReturn(existingBook);

		Book result = bookServiceImpl.updateBookById(id, updatedBook);

		// Assert
		assertEquals(updatedBook.getTitle(), result.getTitle());
		assertEquals(updatedBook.getAuthor(), result.getAuthor());
		assertEquals(updatedBook.getIsbn(), result.getIsbn());
		assertEquals(updatedBook.getPrice(), result.getPrice());
		assertEquals(updatedBook.getQuantity(), result.getQuantity());

		verify(bookRepository, times(1)).existsById(id);
		verify(bookRepository, times(1)).findById(id);
		verify(bookRepository, times(1)).save(existingBook);
	}

	@Test
	@Order(7)
	void test_updateBookById_NoBookInRepository() {
		Long id = 3L;
		Book updatedBook = new Book("New Title", "New Author", "123456789", 3000.0, 50);

		when(bookRepository.existsById(id)).thenReturn(true);
		when(bookRepository.findById(id)).thenReturn(Optional.empty());

		// Act & Assert
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookServiceImpl.updateBookById(id, updatedBook));

		assertEquals("No book found with ID:"+id, exception.getMessage());
	}

	@Test
	@Order(8)
	void test_deleteBookById() throws BookNotFoundException {
		Long id = 3L;
		Book book3 = new Book("New Title", "New Author", "123456789", 3000.0, 50);

		when(bookRepository.findById(id)).thenReturn(Optional.of(book3));

		assertEquals(true, bookServiceImpl.deleteBookById(id));
		verify(bookRepository, times(1)).findById(id);
		verify(bookRepository, times(1)).deleteById(id);
	}

	@Test
	@Order(9)
	void test_deleteBookById_BookNotFound() {
		Long id = 1L;
		when(bookRepository.findById(id)).thenReturn(Optional.empty());

		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookServiceImpl.deleteBookById(id));
		assertNotNull(exception);

	}
}
