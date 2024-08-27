package com.example.book.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.appconstants.BookStoreAppConstants;
import com.example.book.globalexceptionhandle.PurchaseProcessingException;
import com.example.book.model.PurchaseRequest;
import com.example.book.serviceimpl.ProcessPurchaseImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/parches")
public class PurchaseController {

	private static final Logger logger = LogManager.getLogger(PurchaseController.class);

	private ProcessPurchaseImpl processPurchaseImpl;

	public PurchaseController(ProcessPurchaseImpl processPurchaseImpl) {
		super();
		this.processPurchaseImpl = processPurchaseImpl;
	}

	@PostMapping("/purchase")
	public ResponseEntity<String> purchaseBook(@Valid @RequestBody PurchaseRequest purchaseRequest)
			throws PurchaseProcessingException {
		logger.info("Request Received To Create A New Purchase Record : {}", purchaseRequest);
		processPurchaseImpl.processPurchase(purchaseRequest);
		logger.info(" Purchase Created Successfully: {}", purchaseRequest);
		return new ResponseEntity<>(BookStoreAppConstants.PURCHASE_SUCCFL, HttpStatus.OK);
	}
}


