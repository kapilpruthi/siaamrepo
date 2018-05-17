package com.siaam.auth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.siaam.auth.event.ResponseEvent;
import com.siaam.auth.exception.DBAccessException;
import com.siaam.auth.exception.ValidationException;

/**
 * Central Controller for all exceptions.
 * @author Kapil Pruthi
 */
@ControllerAdvice
public class ExceptionController {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	/**
	 * Handle Input Validation Exception.
	 * @param ex MethodArgumentNotValidException
	 * @return ResponseEntity<ResponseEvent>
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<ResponseEvent> handleValidationException(MethodArgumentNotValidException ex) {
		LOGGER.info("handle validation exception " + ex.getMessage());
		List<ObjectError> errors = ex.getBindingResult().getAllErrors();
		ResponseEvent validationFailureEvent = ResponseEvent.build(ResponseEvent.Type.VALIDATION_ERROR,
				errors.get(0).getDefaultMessage());

		return new ResponseEntity<ResponseEvent>(validationFailureEvent, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle Input Validation Exception.
	 * @param ex MethodArgumentNotValidException
	 * @return ResponseEntity<ResponseEvent>
	 */
	@ExceptionHandler({ ValidationException.class })
	public ResponseEntity<ResponseEvent> handleValidationException(ValidationException ex) {
		LOGGER.info("handle validation exception " + ex.getMessage());
		ResponseEvent failureEvent = ResponseEvent.build(ResponseEvent.Type.BAD_REQUEST_ERROR,
				ex.getMessage());
		return new ResponseEntity<ResponseEvent>(failureEvent, HttpStatus.BAD_REQUEST);
	}
	/**
	 * Handle Data Layer Exception.
	 * @param ex DBAccessException
	 * @return ResponseEntity<ResponseEvent>
	 */
	@ExceptionHandler({ DBAccessException.class })
	public ResponseEntity<ResponseEvent> handleDBAccessException(DBAccessException ex) {
		LOGGER.info("handle DBAccessException " + ex.getMessage());
		ResponseEvent failureEvent = ResponseEvent.build(ResponseEvent.Type.INTERNAL_SERVER_ERROR,
				"Unexpected Error, please try with different values");
		return new ResponseEntity<ResponseEvent>(failureEvent, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Handle InsufficientAuthenticationException.
	 * @param ex InsufficientAuthenticationException
	 * @return ResponseEntity<ResponseEvent>
	 */
	@ExceptionHandler({ InsufficientAuthenticationException.class })
	public ResponseEntity<ResponseEvent> handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {
		LOGGER.info("handle InsufficientAuthenticationException " + ex.getMessage());
		ResponseEvent failureEvent = ResponseEvent.build(ResponseEvent.Type.INTERNAL_SERVER_ERROR,
				"Unexpected Error, please try with different values");
		return new ResponseEntity<ResponseEvent>(failureEvent, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}