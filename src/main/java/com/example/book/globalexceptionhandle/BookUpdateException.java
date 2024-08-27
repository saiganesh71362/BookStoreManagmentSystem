package com.example.book.globalexceptionhandle;

public class BookUpdateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookUpdateException(String message) {
		super(message);
	}
}
