package com.ssj.place.service.history;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssj.place.entity.History;

/**
 * 1. ClassName : HistoryController
 * 2. FileName          : HistoryController.java
 * 3. Package           : com.ssj.place.service.history
 * 4. Commnet           : 검색 기록 관리
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:17:54
 */
@RestController
@RequestMapping("history")
public class HistoryController {

	/**
     * The historyService.
     */
    private final HistoryService historyService;
    
    /**
     * Instantiates a new historyController.
     *
     * @param historyService the historyService
     */
    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }
    
    /**
     * 1. MethodName        : placeView
     * 2. ClassName         : HistoryController
     * 3. Commnet           : 히스토리 화면 호출
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:18:08
     * @return ModelAndView
     * @return
     */
    @GetMapping(value = "/view")
    public ModelAndView placeView() {
    	ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("history/historySearch"); 
		return modelAndView;
    }
    
    /**
     * 1. MethodName        : getHistory
     * 2. ClassName         : HistoryController
     * 3. Commnet           : 검색 기록 조회
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:18:14
     * @return ModelAndView
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping
    public ModelAndView getHistory(HttpServletRequest request)  throws Exception {
    	ModelAndView modelAndView = new ModelAndView();
    	
    	//내 검색 히스토리
    	List<History> myHistoryList = historyService.getMyHistory(request);
    	//인기 키워드
    	List<History> keyWordHistoryList = historyService.getkeyWordHistory();
    	
    	modelAndView.addObject("myHistoryList", myHistoryList);
    	modelAndView.addObject("keyWordHistoryList", keyWordHistoryList);
    	modelAndView.setViewName("jsonView"); 
    	return modelAndView;
    }
}
