package com.example.book.globalexceptionhandle;

public class CustomerCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CustomerCreationException(String message)
	{
		super(message);
	}

}
