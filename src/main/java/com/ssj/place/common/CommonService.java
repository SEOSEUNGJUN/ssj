package com.ssj.place.common;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * 1. ClassName : 
 * 2. FileName          : CommonService.java
 * 3. Package           : com.ssj.place.common
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:23:37
 */
@Service
public class CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);
    
    /**
     * 1. MethodName        : setErrorResult
     * 2. ClassName         : CommonService
     * 3. Commnet           : 
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:23:45
     * @return ResponseEntity<?>
     * @param errorMessage
     * @param httpStatus
     * @return
     */
    public ResponseEntity<?> setErrorResult(String errorMessage, HttpStatus httpStatus) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("errorMessage", errorMessage);

        return new ResponseEntity<>(resultMap, httpStatus);
    }
    
    /**
     * 1. MethodName        : procValidator
     * 2. ClassName         : CommonService
     * 3. Commnet           : 
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:23:42
     * @return String
     * @param reqObject
     * @return
     */
    public String procValidator(Object reqObject) {
        String result = "SUCCESS";

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(reqObject);

        for (ConstraintViolation<Object> violation : violations) {
            result = violation.getMessage();
        }

        if (!result.equals("SUCCESS")) {
            LOGGER.error("########## VALIDATION ERROR :: {}", result);
        }

        return result;
    }
}
