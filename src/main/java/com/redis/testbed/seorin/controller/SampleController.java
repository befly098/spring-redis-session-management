package com.redis.testbed.seorin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class SampleController {

	private static final String SESSION_NAME = "name";

	@GetMapping("/login")
	public String login(HttpSession httpSession, @RequestParam String name) {
		httpSession.setAttribute(SESSION_NAME, name);
		return "Login successful, session created for " + name;
	}

	@GetMapping("/name")
	public String getName(HttpSession httpSession) {
		String name = (String) httpSession.getAttribute(SESSION_NAME);
		if (name == null) {
			return "No session found";
		}
		return "Session name: " + name;
	}
}
