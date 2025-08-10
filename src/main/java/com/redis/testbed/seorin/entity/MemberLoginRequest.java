package com.redis.testbed.seorin.entity;

import jakarta.validation.constraints.NotBlank;

public class MemberLoginRequest {

	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;

	public MemberLoginInfoDto toLogin() {
		return new MemberLoginInfoDto(this.email, this.password);
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}
}
