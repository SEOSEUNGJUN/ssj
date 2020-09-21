package com.ssj.place.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssj.place.common.CommonService;
import com.ssj.place.entity.Member;
import com.ssj.place.repository.MemberRepository;

/**
 * 1. ClassName : MemberService
 * 2. FileName          : MemberService.java
 * 3. Package           : com.ssj.place.service.member
 * 4. Commnet           : 회원 관리
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:17:02
 */
@Service
public class MemberService {
	/**
     * The Common service.
     */
    private final CommonService commonService;
    
	/**
     * The place Repository.
     */
    private final MemberRepository memberRepository;
    
    /**
     * The Result status success.
     */
    private final String resultStatusSuccess = "SUCCESS";
    
    /**
     * Instantiates a new Member service.
     *
     * @param memberRepository
     */
    @Autowired
    public MemberService(CommonService commonService, MemberRepository memberRepository) {
    	this.commonService = commonService;
        this.memberRepository = memberRepository;
    }

    /**
     * 1. MethodName        : createMember
     * 2. ClassName         : MemberService
     * 3. Commnet           : 회원 등록
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:17:14
     * @return ResponseEntity<?>
     * @param member
     * @return
     */
    ResponseEntity<?> createMember(Member member) {
    	
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	member.setPassword(passwordEncoder.encode( member.getPassword()));
		
        String validationResult = commonService.procValidator(member);

        if (validationResult.equals(resultStatusSuccess)) {
            return new ResponseEntity<>(memberRepository.save(member), HttpStatus.OK);
        } else {
            return commonService.setErrorResult(validationResult, HttpStatus.BAD_REQUEST);
        }
    }
    
    /**
     * 1. MethodName        : isExist
     * 2. ClassName         : MemberService
     * 3. Commnet           : 중복 체크
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:17:20
     * @return int
     * @param member
     * @return
     */
    int isExist(Member member) {
    	return memberRepository.countByMemberId(member.getMemberId());
    }
    
}
