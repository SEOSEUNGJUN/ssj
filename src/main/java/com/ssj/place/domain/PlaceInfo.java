package com.ssj.place.domain;

/**
 * 1. ClassName : PlaceInfo
 * 2. FileName          : PlaceInfo.java
 * 3. Package           : com.ssj.place.domain
 * 4. Commnet           : 장소 정보
 * 5. 작성자                       : SSJ
 * 6. 작성일                       : 2020. 9. 21. 오후 3:21:10
 */
public class PlaceInfo {
	
	public PlaceInfo() {
		super();
	}
	/** 전체 지번 주소 */
	private String addressName;
	/** 중요 카테고리만 그룹핑한 카테고리 그룹 코드 */
	private String categoryGroupCode;
	/** 중요 카테고리만 그룹핑한 카테고리 그룹명 */
	private String categoryGroupName;
	/** 카테고리 이름 */
	private String categoryName;
	/** 중심좌표까지의 거리 (단, x,y 파라미터를 준 경우에만 존재) 단위 meter */
	private String distance;
	/** 장소 ID */
	private String id;
	/** 전화번호 */
	private String phone;
	/** 	장소명, 업체명 */
	private String placeName;
	/** 장소 상세페이지 URL */
	private String placeUrl;
	/** 전체 도로명 주소 */
	private String roadAddressBame;
	/** X 좌표값 혹은 longitude */
	private String x;
	/** Y 좌표값 혹은 latitude */
	private String y;
	/** 페이지 정보 */
	private String page;
	
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getCategoryGroupCode() {
		return categoryGroupCode;
	}
	public void setCategoryGroupCode(String categoryGroupCode) {
		this.categoryGroupCode = categoryGroupCode;
	}
	public String getCategoryGroupName() {
		return categoryGroupName;
	}
	public void setCategoryGroupName(String categoryGroupName) {
		this.categoryGroupName = categoryGroupName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceUrl() {
		return placeUrl;
	}
	public void setPlaceUrl(String placeUrl) {
		this.placeUrl = placeUrl;
	}
	public String getRoadAddressBame() {
		return roadAddressBame;
	}
	public void setRoadAddressBame(String roadAddressBame) {
		this.roadAddressBame = roadAddressBame;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
}
