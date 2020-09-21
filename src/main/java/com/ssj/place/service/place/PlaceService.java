package com.ssj.place.service.place;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ssj.place.common.RestTemplateService;
import com.ssj.place.entity.History;
import com.ssj.place.repository.HistoryRepository;

/**
 * 1. ClassName 		: PlaceService
 * 2. FileName          : PlaceService.java
 * 3. Package           : com.ssj.place.service.place
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:15:02
 */
@Service
public class PlaceService {

	/**
     * The Place Repository.
     */
    private final HistoryRepository historyRepository;
    
    /**
     * The restTemplateService.
     */
    private final RestTemplateService restTemplateService;
    
    
    /**
     * The authorization.
     */
    @Value("${server.api.authorization}")
    private String authorization;

    /**
     * The url.
     */
    @Value("${server.api.authorization.url}")
    private String apiUrl;
    
    /**
     * Instantiates a new place service.
     *
     * @param historyRepository
     * @param restTemplateService
     */
    @Autowired
    public PlaceService(HistoryRepository historyRepository
    		, RestTemplateService restTemplateService) {
        this.historyRepository = historyRepository;
        this.restTemplateService = restTemplateService;
    }
    
    /**
     * 1. MethodName        : getPlaces
     * 2. ClassName         : PlaceService
     * 3. Commnet           : 카카오 장소 API 호출
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:15:19
     * @return ResponseEntity<?>
     * @param placeName
     * @param page
     * @param request
     * @return
     */
    ResponseEntity<?> getPlaces(String placeName, String page, HttpServletRequest request) {
    	Map documents = null;
    	if(page != null) {
    		return restTemplateService.send(apiUrl, authorization, "?query=" + placeName + "&page=" + page + "&size=10" , HttpMethod.GET, documents, Map.class);
	    }else {
	    	//페이지 검색이 아닐경우 history table 등록
	    	HttpSession session = request.getSession();
	    	
	    	History history = historyRepository.findOneByKeyWord(placeName);
	    	if(history == null) {
	    		//등록
	    		history = new History();
	    		history.setSearchCnt(1);
	    		history.setMemberId(session.getAttribute("memberId").toString());
	    		history.setKeyWord(placeName);
	    		history.setCreateDate();
	    		historyRepository.save(history);
			} else {
				//수정
				history.setMemberId(session.getAttribute("memberId").toString());
	    		history.setSearchCnt(history.getSearchCnt()+1);
	    		historyRepository.save(history);
			}
	    }
    	return restTemplateService.send(apiUrl, authorization,"?query=" + placeName + "&size=10" , HttpMethod.GET, documents, Map.class);
    }
}
