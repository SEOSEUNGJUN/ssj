package com.ssj.place.service.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 1. ClassName : MainController
 * 2. FileName          : MainController.java
 * 3. Package           : com.ssj.place.service.main
 * 4. Commnet           : 메인
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:17:37
 */
@RestController
@RequestMapping("/main")
public class MainController {

	/**
	 * 1. MethodName        : main
	 * 2. ClassName         : MainController
	 * 3. Commnet           : 메인 화면 호출
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:17:44
	 * @return ModelAndView
	 * @return
	 */
	@GetMapping(value = "/view")
    public ModelAndView main() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("/main"); 
    	return modelAndView;
    }
}
