package com.ssj.place.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 1. ClassName : RestTemplateService
 * 2. FileName          : RestTemplateService.java
 * 3. Package           : com.ssj.place.common
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:23:22
 */
@Service
public class RestTemplateService {

	 /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateService.class);
    
	/**
     * The constant AUTHORIZATION_HEADER_KEY.
     */
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    
    /**
     * The constant CONTENT_TYPE.
     */
    private static final String CONTENT_TYPE = "Content-Type";
    
    /**
     * The Rest template.
     */
    private final RestTemplate restTemplate;
    
    /**
     * The Common service.
     */
    private final CommonService commonService;
    
    /**
     * Instantiates a new Rest template service.
     *
     * @param restTemplate  the rest template
     * @param commonService the common service
     */
    @Autowired
    public RestTemplateService(RestTemplate restTemplate, CommonService commonService) {
        this.restTemplate = restTemplate;
        this.commonService = commonService;
    }
    
    /**
     * 1. MethodName        : send
     * 2. ClassName         : RestTemplateService
     * 3. Commnet           : 
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:23:15
     * @return ResponseEntity<?>
     * @param apiUrl
     * @param authorization
     * @param reqUrl
     * @param httpMethod
     * @param bodyObject
     * @param responseType
     * @return
     */
    public ResponseEntity<?> send(String apiUrl, String authorization, String reqUrl, HttpMethod httpMethod, Object bodyObject, Class<?> responseType) {

        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, authorization);
        reqHeaders.add(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return send( apiUrl, apiUrl+reqUrl, httpMethod, reqHeaders, bodyObject, responseType);
    }
    
    /**
     * 1. MethodName        : send
     * 2. ClassName         : RestTemplateService
     * 3. Commnet           : 
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:23:11
     * @return ResponseEntity<?>
     * @param apiUrl
     * @param reqUrl
     * @param httpMethod
     * @param httpHeaders
     * @param bodyObject
     * @param responseType
     * @return
     */
    public ResponseEntity<?> send(String apiUrl, String reqUrl, HttpMethod httpMethod, HttpHeaders httpHeaders, Object bodyObject, Class<?> responseType) {

        HttpEntity<Object> reqEntity = new HttpEntity<>(bodyObject, httpHeaders);
        LOGGER.info("<T> T send :: Request : {} {} : {}, Content-Type: {}", httpMethod, apiUrl, reqUrl, httpHeaders.get(CONTENT_TYPE));
        System.out.println(reqUrl);
        System.out.println(httpMethod);
        System.out.println(httpHeaders);
        System.out.println(bodyObject);
        
        try {
            ResponseEntity<?> resEntity = restTemplate.exchange(reqUrl, httpMethod, reqEntity, responseType);

            if (resEntity.getBody() != null) {
                LOGGER.info("Response Type: {}", resEntity.getBody().getClass());
                LOGGER.info(resEntity.getBody().toString());
            } else {
                LOGGER.info("Response Type: {}", "response body is null");
            }

            return resEntity;
        } catch (Exception e) {
            LOGGER.error("Error {}", e.getMessage());
            return commonService.setErrorResult("ERROR", HttpStatus.BAD_REQUEST);
        }
    }
}
