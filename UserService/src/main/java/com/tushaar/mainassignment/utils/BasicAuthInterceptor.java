package com.tushaar.mainassignment.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BasicAuthInterceptor extends HandlerInterceptorAdapter {
	
	private final JwtUtil jwtUtil = new JwtUtil();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String authHeader = request.getHeader("authorization");
		if (authHeader == null) {
			return false;
		}
		String jwt = authHeader.substring(7);
		if (jwt != null && jwtUtil.validateToken(jwt, jwtUtil.extractUsername(jwt))) {
			return true;
		} else {
			response.sendError(401, "Unauthorized");
			return false;
		}
	}

}
