package com.ssj.place.common.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 1. ClassName : 
 * 2. FileName          : PlaceAuthenticationSuccessHandler.java
 * 3. Package           : com.ssj.place.common.security
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:24:00
 */
public class PlaceAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private static Logger logger = LoggerFactory.getLogger(PlaceAuthenticationSuccessHandler.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	/**
	 * 1. MethodName        : onAuthenticationSuccess
	 * 2. ClassName         : PlaceAuthenticationSuccessHandler
	 * 3. Commnet           : 
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:24:03
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession(true);
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		/* 세션처리 */
		session.setAttribute("securityUser", authUser);
		session.setAttribute("memberId", authUser.getUsername());
		
		/* Set target URL to redirect */
		String targetUrl = "/main/view";
		
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

}