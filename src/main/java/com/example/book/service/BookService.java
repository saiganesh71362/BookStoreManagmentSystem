package com.example.book.service;

import java.util.List;

import com.example.book.entity.Book;
import com.example.book.globalexceptionhandle.BookCreationException;
import com.example.book.globalexceptionhandle.BookNotFoundException;
import com.example.book.globalexceptionhandle.BookUpdateException;

public interface BookService {

	public List<Book> getAllBooks();

	public Book getBookById(Long id) throws BookNotFoundException;

	public Book createNewBook(Book book) throws BookCreationException;

	public Book updateBookById( Long id, Book book ) throws BookUpdateException;

	public boolean deleteBookById(Long id) throws BookNotFoundException;
}
