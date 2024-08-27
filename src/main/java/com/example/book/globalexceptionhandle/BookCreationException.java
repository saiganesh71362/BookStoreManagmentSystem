package com.example.book.globalexceptionhandle;

public class BookCreationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BookCreationException(String message)
	{
		super(message);
	}
}
