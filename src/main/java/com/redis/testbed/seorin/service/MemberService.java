package com.redis.testbed.seorin.service;

import static com.redis.testbed.seorin.common.exceptions.ErrorCodes.*;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import com.redis.testbed.seorin.entity.MemberEntity;
import com.redis.testbed.seorin.entity.MemberLoginInfoDto;
import com.redis.testbed.seorin.repository.MemberRepository;

@Configuration
public class MemberService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(MemberService.class);
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.memberRepository = memberRepository;

	}

	public MemberEntity login(MemberLoginInfoDto memberLoginInfoDto) throws ResponseStatusException {
		Optional<MemberEntity> member = this.memberRepository.findByEmail(memberLoginInfoDto.email());

		return member.orElseThrow(() -> new ResponseStatusException(
			MEMBER_NOT_FOUND.getHttpStatus(),
			MEMBER_NOT_FOUND.getDetail()));
	}

	public void register(MemberEntity memberEntity) {

		memberEntity.setPassword(passwordEncoder.encode(memberEntity.getPassword()));
		memberEntity.setEnabled(1);
		memberEntity.setAuth("ADMIN");

		this.memberRepository.findByEmail(memberEntity.getUsername())
			.ifPresent(existingMember -> {
				throw new ResponseStatusException(
					INVALID_REGISTER_REQUEST.getHttpStatus(),
					INVALID_REGISTER_REQUEST.getDetail());
			});
		this.memberRepository.save(memberEntity);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws ResponseStatusException {

		MemberEntity member = this.memberRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(
			MEMBER_NOT_FOUND.getHttpStatus(),
			MEMBER_NOT_FOUND.getDetail()));

		log.info("Member found: {}", member.getEmail());

		return member;
	}
}
