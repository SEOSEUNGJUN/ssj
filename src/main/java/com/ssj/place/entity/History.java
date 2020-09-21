package com.ssj.place.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 1. ClassName : History
 * 2. FileName          : History.java
 * 3. Package           : com.ssj.place.entity
 * 4. Commnet           : 검색 기록 정보
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:20:58
 */
@Entity
@Table(name="history")
public class History {
	
	public History() {
		super();
	}

	@Id
	@NotNull(message = "seq must not be null")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private int seq;	/** 번호 */
	
	@NotNull(message = "key_word must not be null")
	@NotEmpty(message = "key_word must not be empty")
	@Column(name = "key_word")
	private String keyWord;	/** 키워드 */
	
	@NotNull(message = "member_id must not be null")
	@NotEmpty(message = "member_id must not be empty")
	@Column(name = "member_id")
	private String memberId;	/** 사용자 ID */
	
	@NotNull(message = "search_cnt must not be null")
	@Column(name = "search_cnt")
	private int searchCnt;		/** 검색 횟수 */
	
	@NotNull(message = "create_date must not be null")
	@Column(name = "create_date")
    private String createDate;		/** 생성 일시 */

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getSearchCnt() {
		return searchCnt;
	}

	public void setSearchCnt(int searchCnt) {
		this.searchCnt = searchCnt;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate() {
		this.createDate = new Date().toString();
	}
}
