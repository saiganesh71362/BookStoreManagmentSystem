package com.example.book.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PurchaseRequest {

	private Long bookId;

	@NotNull(message = "Customer ID is required")
	private Long customerId;

	@Min(value = 1, message = "Quantity must be at least 1")
	private Integer quantity;

	public PurchaseRequest() {

	}

	PurchaseRequest(Long bookId, Long customerId, Integer quantity) {
		this.bookId = bookId;
		this.customerId = customerId;
		this.quantity = quantity;

	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getBookId() {
		return bookId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "PurchaseRequest [bookId=" + bookId + ", customerId=" + customerId + ", quantity=" + quantity + "]";
	}
	

}
