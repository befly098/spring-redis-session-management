package com.redis.testbed.seorin.service;

import java.util.Optional;

import javax.naming.AuthenticationNotSupportedException;

import org.springframework.context.annotation.Configuration;

import com.redis.testbed.seorin.entity.MemberEntity;
import com.redis.testbed.seorin.entity.MemberLoginInfoDto;
import com.redis.testbed.seorin.repository.MemberRepository;

@Configuration
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public MemberEntity login(MemberLoginInfoDto memberLoginInfoDto) throws AuthenticationNotSupportedException {
		Optional<MemberEntity> member = this.memberRepository.findByEmail(memberLoginInfoDto.email());

		return member.orElseThrow(() -> new AuthenticationNotSupportedException("Member not found"));
	}

	public void register(MemberEntity memberEntity) {
		this.memberRepository.save(memberEntity);
	}
}
