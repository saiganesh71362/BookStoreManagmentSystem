package com.example.book.globalexceptionhandle;

public class PurchaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	PurchaseException(String message) {
		super(message);
	}

}
