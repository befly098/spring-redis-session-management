package com.redis.testbed.seorin.entity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class MemberEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private ZonedDateTime createdAt;

	@Column(nullable = false, length = 100)
	private String email;

	@Column(nullable = false, length = 50)
	private String password;

	private String auth;

	private int enabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(auth));
		return authList;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled == 1;
	}
}
