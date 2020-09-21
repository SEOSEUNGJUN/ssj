package com.ssj.place.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1. ClassName : DataSourceConfig
 * 2. FileName          : DataSourceConfig.java
 * 3. Package           : com.ssj.place.config
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:22:58
 */
@Configuration
public class DataSourceConfig {
	
	/**
	 * 1. MethodName        : h2servletRegistration
	 * 2. ClassName         : DataSourceConfig
	 * 3. Commnet           : 
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:23:02
	 * @return ServletRegistrationBean
	 * @return
	 */
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}
}
