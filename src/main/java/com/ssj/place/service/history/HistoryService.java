package com.ssj.place.service.history;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssj.place.entity.History;
import com.ssj.place.repository.HistoryRepository;

/**
 * 1. ClassName : 검색 기록 관리
 * 2. FileName          : HistoryService.java
 * 3. Package           : com.ssj.place.service.history
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:18:29
 */
@Service
public class HistoryService {

	/**
     * The HistoryRepository.
     */
    private final HistoryRepository historyRepository;
    
    /**
     * Instantiates a new place service.
     *
     * @param historyRepository
     * @param restTemplateService
     */
    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }
    
    /**
     * 1. MethodName        : getMyHistory
     * 2. ClassName         : HistoryService
     * 3. Commnet           : 검색 기록 조회
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:18:53
     * @return List<History>
     * @param request
     * @return
     */
    List<History> getMyHistory(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	return historyRepository.getMyHistory(session.getAttribute("memberId").toString());
    }
    
    /**
     * 1. MethodName        : getkeyWordHistory
     * 2. ClassName         : HistoryService
     * 3. Commnet           : 키워드 조회
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:19:14
     * @return List<History>
     * @return
     */
    List<History> getkeyWordHistory() {
    	return historyRepository.getkeyWordHistory();
    }
}
