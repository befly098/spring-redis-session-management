package com.redis.testbed.seorin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class MemberEntity extends BaseEntity {
	@Column(nullable = false, length = 100)
	private String email;

	@Column(nullable = false, length = 50)
	private String password;

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}
}
