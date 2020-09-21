package com.ssj.place.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssj.place.entity.History;

/**
 * 1. ClassName : HistoryRepository
 * 2. FileName          : HistoryRepository.java
 * 3. Package           : com.ssj.place.repository
 * 4. Commnet           : 검색 기록 Repository
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:19:28
 */
public interface HistoryRepository extends JpaRepository<History, String> {
	
	/**
	 * 1. MethodName        : findOneByKeyWord
	 * 2. ClassName         : HistoryRepository
	 * 3. Commnet           : 
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:19:47
	 * @return History
	 * @param keyWord
	 * @return
	 */
	History findOneByKeyWord(String keyWord);
	
	/**
	 * 1. MethodName        : getMyHistory
	 * 2. ClassName         : HistoryRepository
	 * 3. Commnet           : 내 검색 기록 조회
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:19:49
	 * @return List<History>
	 * @param memberId
	 * @return
	 */
	@Query(value = "SELECT seq, TO_CHAR(create_date, 'YYYY-mm-dd') create_date, key_word, member_id, search_cnt FROM HISTORY  "
            + "WHERE MEMBER_ID = :memberId "
            + "ORDER BY CREATE_DATE DESC ", nativeQuery = true)
    List<History> getMyHistory(@Param("memberId") String memberId);
	
	/**
	 * 1. MethodName        : getkeyWordHistory
	 * 2. ClassName         : HistoryRepository
	 * 3. Commnet           : 키워드 검색 조회
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:19:52
	 * @return List<History>
	 * @return
	 */
	@Query(value = "SELECT * FROM HISTORY  "
            + "ORDER BY SEARCH_CNT DESC "
            + "LIMIT 10 ", nativeQuery = true)
    List<History> getkeyWordHistory();
}
