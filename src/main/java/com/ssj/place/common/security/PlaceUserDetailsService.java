package com.ssj.place.common.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssj.place.entity.Member;

/**
 * 1. ClassName : 
 * 2. FileName          : PlaceUserDetailsService.java
 * 3. Package           : com.ssj.place.common.security
 * 4. Commnet           : 
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:23:48
 */
@Service
public class PlaceUserDetailsService implements UserDetailsService {

	@Autowired
	private PlaceSecurityRepository placeSecurityRepository;
	
	/**
	 * 1. MethodName        : loadUserByUsername
	 * 2. ClassName         : PlaceUserDetailsService
	 * 3. Commnet           : 
	 * 4. 작성자                       : SSJ
	 * 5. 작성일                       : 2020. 9. 21. 오후 3:23:51
	 * @param memberId
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		
		Member member = placeSecurityRepository.findByMemberId(memberId);
		
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		
		return new org.springframework.security.core.userdetails.User(member.getMemberId(), member.getPassword()
				, roles);
	}

}
