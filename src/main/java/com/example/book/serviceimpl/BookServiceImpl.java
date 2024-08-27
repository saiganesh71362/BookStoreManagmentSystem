package com.example.book.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.book.appconstants.BookStoreAppConstants;
import com.example.book.entity.Book;
import com.example.book.globalexceptionhandle.BookCreationException;
import com.example.book.globalexceptionhandle.BookNotFoundException;
import com.example.book.globalexceptionhandle.BookUpdateException;
import com.example.book.repository.BookRepository;
import com.example.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository; // throw constructor injection

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public List<Book> getAllBooks() {

		return bookRepository.findAll(); // external dependency

	}

	@Override
	public Book getBookById(Long id) {
		Book orElse = bookRepository.findById(id).orElse(null);
		if (orElse != null) {
			return orElse;
		}

		throw new BookNotFoundException("No book found with :" + id);
	}

	@Override
	public Book createNewBook(Book book) {
		if (book != null) {
			return bookRepository.save(book);
		}

		throw new BookCreationException("The book provided is null and cannot be created.");
	}

	@Override
	public Book updateBookById(Long id, Book book) {
		if (bookRepository.existsById(id)) {
			Book existingBook = bookRepository.findById(id)
					.orElseThrow(() -> new BookNotFoundException("No book found with ID:"+id));
			existingBook.setTitle(book.getTitle());
			existingBook.setAuthor(book.getAuthor());
			existingBook.setIsbn(book.getIsbn());
			existingBook.setPrice(book.getPrice());
			existingBook.setQuantity(book.getQuantity());

			return bookRepository.save(existingBook);
		} else {
			throw new BookUpdateException(BookStoreAppConstants.ID_NOT_EXIST + id);
		}

	}

	@Override
	public boolean deleteBookById(Long id) {
		Optional<Book> findById = bookRepository.findById(id);
		if (findById.isPresent()) {
			bookRepository.deleteById(id);
			return true;
		}
		throw new BookNotFoundException("No book found with ID: " + id);
	}

}
