package com.redis.testbed.seorin.common.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCodes {

	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "Member not found"),
	INVALID_REGISTER_REQUEST(HttpStatus.BAD_REQUEST, "Invalid register request");

	private final HttpStatus httpStatus;
	private final String detail;

	ErrorCodes(HttpStatus httpStatus, String detail) {
		this.httpStatus = httpStatus;
		this.detail = detail;
	}

	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	public String getDetail() {
		return this.detail;
	}
}
