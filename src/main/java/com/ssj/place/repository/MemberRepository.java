package com.ssj.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssj.place.entity.Member;

/**
 * 1. ClassName : MemberRepository
 * 2. FileName          : MemberRepository.java
 * 3. Package           : com.ssj.place.repository
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:20:14
 */
public interface MemberRepository extends JpaRepository<Member, String> {

    /**
     * 1. MethodName        : countByMemberId
     * 2. ClassName         : MemberRepository
     * 3. Commnet           : 중복 조회
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:20:16
     * @return int
     * @param memberId
     * @return
     */
    int countByMemberId(String memberId);
}
