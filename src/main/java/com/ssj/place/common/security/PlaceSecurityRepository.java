package com.ssj.place.common.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssj.place.entity.Member;

/**
 * 1. ClassName : 
 * 2. FileName          : PlaceSecurityRepository.java
 * 3. Package           : com.ssj.place.common.security
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:23:55
 */
@Repository
public interface PlaceSecurityRepository extends JpaRepository<Member, String> {

	Member findByMemberId(String memberId);
	
}