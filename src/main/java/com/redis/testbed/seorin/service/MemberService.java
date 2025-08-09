package com.redis.testbed.seorin.service;

import static com.redis.testbed.seorin.common.exceptions.ErrorCodes.*;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ResponseStatusException;

import com.redis.testbed.seorin.entity.MemberEntity;
import com.redis.testbed.seorin.entity.MemberLoginInfoDto;
import com.redis.testbed.seorin.repository.MemberRepository;

@Configuration
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public MemberEntity login(MemberLoginInfoDto memberLoginInfoDto) throws ResponseStatusException {
		Optional<MemberEntity> member = this.memberRepository.findByEmail(memberLoginInfoDto.email());

		return member.orElseThrow(() -> new ResponseStatusException(
			MEMBER_NOT_FOUND.getHttpStatus(),
			MEMBER_NOT_FOUND.getDetail()));
	}

	public void register(MemberEntity memberEntity) {
		// TODO: 중복 ID 체크
		this.memberRepository.findByEmail(memberEntity.getEmail())
			.ifPresent(existingMember -> {
				throw new ResponseStatusException(
					INVALID_REGISTER_REQUEST.getHttpStatus(),
					INVALID_REGISTER_REQUEST.getDetail());
			});
		this.memberRepository.save(memberEntity);
	}
}
