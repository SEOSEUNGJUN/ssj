package com.ssj.place.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ssj.place.common.security.PlaceAuthenticationSuccessHandler;
import com.ssj.place.common.security.PlaceUserDetailsService;

/**
 * 1. ClassName : SecurityConfig
 * 2. FileName          : SecurityConfig.java
 * 3. Package           : com.ssj.place.config
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:22:27
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.ssj.place")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired 
	PlaceUserDetailsService placeUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(placeUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	};
	
	/**
	 * 1. MethodName        : configure
	 * 2. ClassName         : SecurityConfig
	 * 3. Commnet           : HttpSecurity 설정
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:22:13
	 * @param http
	 * @throws Exception
	 */
	@Override
    public void configure(HttpSecurity http) throws Exception {
      http.httpBasic();
      
	  http
	  	.csrf().disable()
	  	.authorizeRequests()
	  		.antMatchers("/login", "/member", "/member/check", "/resources/**",  "/console/**").permitAll()
	  		.anyRequest().authenticated()
	  		.and()
	  	.headers().frameOptions().disable()
	  	.and()
	  	.formLogin()
	  		.loginPage("/login").usernameParameter("memberId").passwordParameter("password").permitAll()
	  		.loginProcessingUrl("/login")
	  		.successHandler(successHandler())
	  		.and()
	  	.logout()
	  		.logoutUrl("/logout").permitAll()
	  		.and()
	  	.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.NEVER)
	  	;
	}
    
    /**
     * 1. MethodName        : successHandler
     * 2. ClassName         : SecurityConfig
     * 3. Commnet           : 로그인 성공 후 이벤트
     * 4. 작성자                       : SSJ
     * 5. 작성일                       : 2020. 9. 21. 오후 3:21:28
     * @return AuthenticationSuccessHandler
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler successHandler(){
    	return new PlaceAuthenticationSuccessHandler();
    }
}
