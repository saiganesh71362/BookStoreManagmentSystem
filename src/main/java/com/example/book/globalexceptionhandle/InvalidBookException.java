package com.example.book.globalexceptionhandle;

public class InvalidBookException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidBookException(String message)
	{
		super(message);
	}
	

}
