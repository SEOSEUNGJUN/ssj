package com.ssj.place.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 1. ClassName : RestTemplateConfig
 * 2. FileName          : RestTemplateConfig.java
 * 3. Package           : com.ssj.place.config
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:22:33
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 1. MethodName        : restTemplate
     * 2. ClassName         : RestTemplateConfig
     * 3. Commnet           : Rest template rest template.
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:22:38
     * @return RestTemplate
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
}
