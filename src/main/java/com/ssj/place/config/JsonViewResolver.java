package com.ssj.place.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 1. ClassName : JsonViewResolver
 * 2. FileName          : JsonViewResolver.java
 * 3. Package           : com.ssj.place.config
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:22:46
 */
@Configuration
public class JsonViewResolver implements InitializingBean {
    
    /**
     * 1. MethodName        : jsonView
     * 2. ClassName         : JsonViewResolver
     * 3. Commnet           : 
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:22:51
     * @return MappingJackson2JsonView
     * @return
     */
    @Bean
    public MappingJackson2JsonView jsonView() {
    	MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
    	mappingJackson2JsonView.setContentType("application/json; charset=UTF-8");
    	return mappingJackson2JsonView;
    }

	/**
	 * 1. MethodName        : afterPropertiesSet
	 * 2. ClassName         : JsonViewResolver
	 * 3. Commnet           : 
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:22:54
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
	}
}