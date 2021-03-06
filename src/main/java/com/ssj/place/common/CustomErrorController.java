package com.ssj.place.common;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1. ClassName : 
 * 2. FileName          : CustomErrorController.java
 * 3. Package           : com.ssj.place.common
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:23:30
 */
@Controller 
public class CustomErrorController implements ErrorController {
	private String VIEW_PATH = "errors/";
	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(status != null){
			int statusCode = Integer.valueOf(status.toString());
			if(statusCode == HttpStatus.NOT_FOUND.value()){
				return VIEW_PATH + "404"; 
			} 
			if(statusCode == HttpStatus.FORBIDDEN.value()){
				return VIEW_PATH + "500"; 
				} 
			} 
		return "error"; 
} 
	
	@Override 
	public String getErrorPath() {
		return "/error"; 
	} 
}
