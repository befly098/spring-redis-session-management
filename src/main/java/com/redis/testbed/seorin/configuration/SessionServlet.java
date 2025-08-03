package com.redis.testbed.seorin.configuration;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(SessionServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String attributeName = req.getParameter("attributeName");
		String attributeValue = req.getParameter("attributeValue");
		req.getSession().setAttribute(attributeName, attributeValue);
		resp.sendRedirect(req.getContextPath() + "/");

		log.info("Session attribute set: {} = {}", attributeName, attributeValue);
	}

	private static final long serialVersionUID = 2878267318695777395L;
}
