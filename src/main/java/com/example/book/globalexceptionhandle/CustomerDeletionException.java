package com.example.book.globalexceptionhandle;

public class CustomerDeletionException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	CustomerDeletionException(String message)
	{
		super(message);
	}

}
