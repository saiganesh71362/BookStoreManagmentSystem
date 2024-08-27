package com.example.book.globalexceptionhandle;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandle {

	@ExceptionHandler(BookCreationException.class)
	ResponseEntity<ExceptionMessage> handleBookCreationException(BookCreationException bookCreationException,
			WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), bookCreationException.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BookDeletionException.class)
	ResponseEntity<ExceptionMessage> handleBookDeletionException(BookDeletionException bookDeletionException,
			WebRequest request) {

		ExceptionMessage message = new ExceptionMessage(new Date(), bookDeletionException.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BookNotFoundException.class)
	ResponseEntity<ExceptionMessage> handleBookNotFoundException(BookNotFoundException bookNotFoundException,
			WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), bookNotFoundException.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BookUpdateException.class)
	ResponseEntity<ExceptionMessage> handleBookUpdateException(BookUpdateException bookUpdateException,
			WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), bookUpdateException.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerCreationException.class)
	ResponseEntity<ExceptionMessage> handleCustomerCreationException(
			CustomerCreationException customerCreationException, WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), customerCreationException.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerDeletionException.class)
	ResponseEntity<ExceptionMessage> customerDeletionException(CustomerDeletionException customerDeletionException,
			WebRequest request) {

		ExceptionMessage message = new ExceptionMessage(new Date(), customerDeletionException.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	ResponseEntity<ExceptionMessage> handleCustomerNotFoundException(
			CustomerNotFoundException customerNotFoundException, WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), customerNotFoundException.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomerUpdateException.class)
	ResponseEntity<ExceptionMessage> handleCustomerUpdateException(CustomerUpdateException customerUpdateException,
			WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), customerUpdateException.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InsufficientStockException.class)
	ResponseEntity<ExceptionMessage> handleinsufficientStockException(
			InsufficientStockException insufficientStockException, WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), insufficientStockException.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(InvalidBookException.class)
	ResponseEntity<ExceptionMessage> handleinvalidBookException(InvalidBookException invalidBookException,
			WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), invalidBookException.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidCustomerException.class)
	ResponseEntity<ExceptionMessage> handleinvalidCustomerException(InvalidCustomerException invalidCustomerException,
			WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), invalidCustomerException.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PurchaseException.class)
	ResponseEntity<ExceptionMessage> handlepurchaseException(PurchaseException purchaseException, WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), purchaseException.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PurchaseProcessingException.class)
	ResponseEntity<ExceptionMessage> handlepurchaseProcessingException(
			PurchaseProcessingException purchaseProcessingException, WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), purchaseProcessingException.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<ExceptionMessage> handleresourceNotFoundException(
			ResourceNotFoundException resourceNotFoundException, WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), resourceNotFoundException.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
}
