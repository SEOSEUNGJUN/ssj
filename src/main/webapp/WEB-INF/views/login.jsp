<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<c:import url="/WEB-INF/views/common/commonHead.jsp"></c:import>
	<title>place</title>
	<script type="text/javascript">
		$(document).ready(function() {
			retMessage();
			$("#password").keydown(function(key) {
		        if (key.keyCode == 13) {
		        	actionLogin()
		        }
		    });
		});
		
		function retMessage(){
			var message = '';
			<c:if test="${param.error != null}">
				message = '아이디와 비밀번호를 확인하세요.';
			</c:if>
			
			<c:if test="${param.logout != null}">
				message = '로그아웃 되었습니다.';
			</c:if>
			if( message != ''){
				alert(message);	
			}
		}
		
		//로그인
		function actionLogin() {
			if($('#username').val() != "" && $('#password').val() != ""){
				loginForm.submit();
			}else{
				alert("아이디와 비밀번호를 입력하세요.");
			}
		}
	</script>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<b>지도 검색 서비스</b>
		</div>
		<div class="login-box-body">
			<p class="login-box-msg">로그인</p>
			<form action="${loginUrl}" method="post" id="loginForm" >
				<div class="form-group has-feedback">
					<input type="input" class="form-control" placeholder="memberId" id="memberId" name="memberId">
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="Password" id="password" name="password">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
    				<div class="col-xs-6">
        				<a href="/member" class="text-center">회원 가입</a>
      				</div>
	      			<div class="col-xs-6">
	        			<button type="button" class="btn btn-primary btn-block btn-flat" onclick="javascript:actionLogin();">로그인</button>
	      			</div>
    			</div>
			</form>
		</div>
	</div>
</body>
</html>
