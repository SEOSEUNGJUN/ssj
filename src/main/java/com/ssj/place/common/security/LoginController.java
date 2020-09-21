package com.ssj.place.common.security;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 1. ClassName : LoginController
 * 2. FileName          : LoginController.java
 * 3. Package           : com.ssj.place.common.security
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:24:07
 */
@RestController
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 1. MethodName        : loginView
	 * 2. ClassName         : LoginController
	 * 3. Commnet           : 
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:24:12
	 * @return ModelAndView
	 * @param locale
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/login")
	public ModelAndView loginView(Locale locale, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); 
    	
    	return modelAndView;
	}
	
	/**
	 * 1. MethodName        : logoutPage
	 * 2. ClassName         : LoginController
	 * 3. Commnet           : 
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:24:14
	 * @return String
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping(value = "/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
}