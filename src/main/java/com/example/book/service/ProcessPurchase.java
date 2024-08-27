package com.example.book.service;

import com.example.book.globalexceptionhandle.PurchaseException;
import com.example.book.model.PurchaseRequest;

public interface ProcessPurchase
{
	public void processPurchase(PurchaseRequest purchaseRequest) throws PurchaseException;

	
}

