package com.example.book.globalexceptionhandle;

public class CustomerUpdateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerUpdateException(String message) {
		super(message);
	}

}
