package com.redis.testbed.seorin.controller;

import javax.naming.AuthenticationNotSupportedException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redis.testbed.seorin.entity.MemberLoginRequest;
import com.redis.testbed.seorin.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/members")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/login")
	public void login(@RequestBody @Valid MemberLoginRequest memberLoginRequest, HttpServletResponse response) throws
		AuthenticationNotSupportedException {
		Cookie cookie = memberService.login(memberLoginRequest.toLogin());
		response.addCookie(cookie);
	}
}
