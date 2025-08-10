package com.redis.testbed.seorin.entity;

import jakarta.validation.constraints.NotBlank;

public class MemberRegisterRequest {
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;

	public MemberEntity toEntity() {
		MemberEntity member = new MemberEntity();
		member.setEmail(this.email);
		member.setPassword(this.password);
		return member;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
