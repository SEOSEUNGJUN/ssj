package com.ssj.place.service.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssj.place.entity.Member;

/**
 * 1. ClassName : MemberController
 * 2. FileName          : MemberController.java
 * 3. Package           : com.ssj.place.service.member
 * 4. Commnet           : 회원 관리
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:15:57
 */
@RestController
@RequestMapping("/member")
public class MemberController {
	
	/**
     * The memberService.
     */
    private final MemberService memberService;
    
    /**
     * Instantiates a new MemberController.
     *
     * @param memberService the member service
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
	
	/**
	 * 1. MethodName        : loginView
	 * 2. ClassName         : MemberController
	 * 3. Commnet           : 회원 등록 화면 호출
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:16:06
	 * @return ModelAndView
	 * @param model
	 * @param req
	 * @return
	 */
	@GetMapping
	public ModelAndView loginView(Model model,HttpServletRequest req){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register"); 
		return modelAndView;
	}
	
	/**
	 * 1. MethodName        : createMember
	 * 2. ClassName         : MemberController
	 * 3. Commnet           : 회원 등록
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:16:35
	 * @return ResponseEntity<?>
	 * @param member
	 * @return
	 */
	@PostMapping()
    public ResponseEntity<?> createMember(Member member) {
		if (memberService.isExist(member) > 0) {
            return new ResponseEntity<>(member,HttpStatus.CONFLICT);
        }
        return memberService.createMember(member);
    }
	
	/**
	 * 1. MethodName        : isExist
	 * 2. ClassName         : MemberController
	 * 3. Commnet           : 중복 체크
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:16:42
	 * @return ModelAndView
	 * @param member
	 * @return
	 */
	@PostMapping(value = "/check")
    public ModelAndView isExist(Member member) throws Exception {
		int check = memberService.isExist(member);
		boolean flag = true;
		if(check > 0) {
			flag = false;
		}
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("flag", flag);
    	modelAndView.setViewName("jsonView"); 
    	
        return modelAndView;
    }
}
