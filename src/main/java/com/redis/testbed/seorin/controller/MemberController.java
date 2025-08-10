package com.redis.testbed.seorin.controller;

import javax.naming.AuthenticationNotSupportedException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.redis.testbed.seorin.entity.MemberEntity;
import com.redis.testbed.seorin.entity.MemberLoginRequest;
import com.redis.testbed.seorin.entity.MemberRegisterRequest;
import com.redis.testbed.seorin.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/members")
public class MemberController {

	private static final String SESSION_NAME = "member";
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// @PostMapping("/login") // TODO: spring security 적용, jwt 토큰 발급
	// public void login(@RequestBody @Valid MemberLoginRequest memberLoginRequest, HttpSession httpSession) throws
	// 	ResponseStatusException {
	// 	MemberEntity member = memberService.login(memberLoginRequest.toLogin());
	// 	httpSession.setAttribute(SESSION_NAME, member.getUsername());
	// }

	@PostMapping("/register")
	public String register(@RequestBody @Valid MemberRegisterRequest memberRegisterRequest) {
		MemberEntity member = memberRegisterRequest.toEntity();
		memberService.register(member);
		return "Registration successful";
	}
}
