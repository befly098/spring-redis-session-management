package com.redis.testbed.seorin.entity;

import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private ZonedDateTime createdAt;

	public abstract String getEmail();
}
