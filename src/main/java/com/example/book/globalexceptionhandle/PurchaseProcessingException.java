package com.example.book.globalexceptionhandle;

public class PurchaseProcessingException  extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	PurchaseProcessingException(String message)
	{
		super(message);
	}
}
