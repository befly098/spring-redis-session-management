package com.redis.testbed.seorin.service;

import java.util.Optional;

import javax.naming.AuthenticationNotSupportedException;

import org.springframework.context.annotation.Configuration;

import com.redis.testbed.seorin.entity.MemberEntity;
import com.redis.testbed.seorin.entity.MemberLoginInfo;
import com.redis.testbed.seorin.repository.MemberRepository;

import jakarta.servlet.http.Cookie;

@Configuration
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Cookie login(MemberLoginInfo memberLoginInfo) throws AuthenticationNotSupportedException {
		Optional<MemberEntity> member = this.memberRepository.findByEmail(memberLoginInfo.email());

		if (member.isEmpty()) {
			throw new AuthenticationNotSupportedException("Member not found");
		}

		return new Cookie("memberId", "dummy");
	}
}
