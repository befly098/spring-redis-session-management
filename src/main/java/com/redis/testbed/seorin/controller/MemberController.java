package com.redis.testbed.seorin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redis.testbed.seorin.entity.MemberEntity;
import com.redis.testbed.seorin.entity.MemberRegisterRequest;
import com.redis.testbed.seorin.service.MemberService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/members")
public class MemberController {

	private static final String SESSION_NAME = "member";
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";  // src/main/resources/templates/login.html (Thymeleaf 등 템플릿 엔진 사용 시)
	}

	@PostMapping("/register")
	public String register(@RequestBody @Valid MemberRegisterRequest memberRegisterRequest) {
		MemberEntity member = memberRegisterRequest.toEntity();
		memberService.register(member);
		return "Registration successful";
	}

	@GetMapping("/login-success")
	public String loginSuccess() {
		return "login-success";  // src/main/resources/templates/login-success.html (Thymeleaf 등 템플릿 엔진 사용 시)
	}
}
