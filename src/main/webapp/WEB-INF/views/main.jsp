<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="/WEB-INF/resources/tlds/taglib.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnc" uri="/WEB-INF/resources/tlds/function.tld" %>
<%@include file="./include/header.jsp"%>
<!DOCTYPE html>
<head>
    <title>main</title>
    <c:import url="/WEB-INF/views/common/commonHead.jsp"></c:import>
</head>
	<!-- Main Content Header -->
<section class="content-header">
	<h1>
		카카오뱅크 코딩 테스트 프로그램
	</h1>
	<ol class="breadcrumb">
		<li><a href="/main/view"><i class="fa fa-tags"></i> Home</a></li>
	</ol>
</section>

<!-- Main Content -->
<section class="content">
     <div class="row">
       <div class="col-xs-12">
         <div class="box">
           <div class="box-body">
           <pre>
* [고객플랫폼] B2B API서비스 개발 및 운영 담당자
   - 지원자 : 서승준
   - 검색 현황 : 내 검색 히스토리, 인기 키워드
   - 장소 검색 : 카카오 API 지도 검색 서비스 
   - H2 Console :
     * url : http://localhost:8080/console
     * Driver Class: org.h2.Driver
     * JDBC URL: jdbc:h2:mem:kakaobank
     * username : placemanagement
     * password : placemanagement!
			</pre>
         </div>
       </div>
     </div>
   </div>
</section>
</div>
<%@include file="./include/footer.jsp"%>
