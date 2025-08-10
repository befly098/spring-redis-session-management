package com.redis.testbed.seorin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ControllerExceptionAdivce {

	private static final Logger log = LoggerFactory.getLogger(ControllerExceptionAdivce.class);

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<?> handleError(ResponseStatusException ex) {
		log.error("Error occurred: {}", ex.getReason());
		return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
	}
}
