package com.main.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GloblaException_Master {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	// Handles DB constraint errors like NOT NULL violations
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleSQLIntegrityConstraintViolation(DataIntegrityViolationException ex) {
		return new ResponseEntity<>("Database constraint violated: " + ex.getRootCause().getMessage(),
				HttpStatus.BAD_REQUEST);
	}

	// Optional: Catch-all
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllOtherExceptions(Exception ex) {
		return new ResponseEntity<>("Something went wrong: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
