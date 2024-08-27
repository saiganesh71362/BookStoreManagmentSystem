package com.example.book.globalexceptionhandle;

public class BookDeletionException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	BookDeletionException(String message)
	{
		super(message);
	}
	
}
