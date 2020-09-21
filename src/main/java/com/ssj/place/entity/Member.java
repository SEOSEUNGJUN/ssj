package com.ssj.place.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 1. ClassName : Member
 * 2. FileName          : Member.java
 * 3. Package           : com.ssj.place.entity
 * 4. Commnet           : 회원 정보
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:20:40
 */
@Entity
@Table(name = "member")
public class Member {
	
	public Member() {
		super();
	}

	@Id
    @NotNull(message = "member_id must not be null")
    @NotEmpty(message = "member_id must not be empty")
    @Column(name = "member_id")
    private String memberId; 	/** 사용자 ID */
	
	@Column(name = "password")
    private String password; 	/** 사용자 비밀번호 */
	
	@NotNull(message = "member_name must not be null")
    @NotEmpty(message = "member_name must not be empty")
    @Column(name = "member_name")
    private String memberName;	/** 사용자명 */

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
}
