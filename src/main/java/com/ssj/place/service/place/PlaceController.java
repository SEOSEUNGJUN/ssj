package com.ssj.place.service.place;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssj.place.domain.PlaceInfo;

/**
 * 1. ClassName : PlaceController
 * 2. FileName          : PlaceController.java
 * 3. Package           : com.ssj.place.service.place
 * 4. Commnet           : 장소 검색 서비스
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:13:37
 */
@RestController
@RequestMapping("/place")
public class PlaceController {

	/**
     * The placeService.
     */
    private final PlaceService placeService;
    
    /**
     * Instantiates a new PlaceController.
     *
     * @param placeService the place Service
     */
    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    /**
     * 1. MethodName        : placeView
     * 2. ClassName         : PlaceController
     * 3. Commnet           : 장소 검색 화면 호출
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:14:12
     * @return ModelAndView
     * @return
     */
    @GetMapping(value = "/view")
    public ModelAndView placeView() {
    	ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("place/placeSearch"); 
		return modelAndView;
    }
    
    /**
     * 1. MethodName        : getPlaces
     * 2. ClassName         : PlaceController
     * 3. Commnet           : 카카오 장소 API 호출
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:14:40
     * @return ModelAndView
     * @param placeInfo
     * @param modelAndView
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping
    public ModelAndView getPlaces(@RequestBody PlaceInfo placeInfo
    		, ModelAndView modelAndView, HttpServletRequest request) throws Exception {
    	Map<String, String> rtnMap = new HashMap();
    	try{
	    	//카카오 API 호출
	    	ResponseEntity<?> ResponseEntity = placeService.getPlaces(placeInfo.getPlaceName(), placeInfo.getPage(), request);
	    	
	    	Map placeMap = (Map) ResponseEntity.getBody();
	    	Map metaData = (Map) placeMap.get("meta");
	    	List<PlaceInfo> PlaceList = (List<PlaceInfo>) placeMap.get("documents");
	    	
	    	modelAndView.addObject("resultCnt",metaData.get("pageable_count"));
	    	modelAndView.addObject("list", PlaceList);
	    	modelAndView.setViewName("jsonView"); 
	    	
	    	rtnMap.put("ERROR_CODE","1");
    		rtnMap.put("ERROR_MSG","카카오 API 호출 성공");
    	}catch(Exception ex){
    		rtnMap.put("ERROR_CODE","-1");
    		rtnMap.put("ERROR_MSG","카카오 API 호출 실패");
        }
    	
    	modelAndView.addObject("returnCode", rtnMap.get("ERROR_CODE"));
        modelAndView.addObject("returnMessage", rtnMap.get("ERROR_MSG"));
        
    	return modelAndView;
    }
}
