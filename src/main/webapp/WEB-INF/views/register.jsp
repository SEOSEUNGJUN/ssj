<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/WEB-INF/views/common/commonHead.jsp"></c:import>
<title>place</title>
<script type="text/javascript">
var duplicationCheck = true;
  $(function () {
  	$("#passwordConfirm").keydown(function(key) {
        if (key.keyCode == 13) {
        	$("#btnSave").trigger("click");
        }
    });
	//회원가입
	$('#btnSave').click(function(e) {
		e.preventDefault();
		if($('#memberId').val().trim() == ''){
			alert("ID를 입력하세요.");
			return false;
		}
		if(duplicationCheck || $('#memberIdHidden').val() != $('#memberId').val()){
			alert("ID 중복체크를 진행하세요.");
			return false;
		}
		if($('#memberName').val().trim() == ''){
			alert("성함을 입력하세요.");
			return false;
		}
		if(!chkPW()){
			return false;
		}
		
		if(! confirm('회원가입 하시겠습니까?') ){ return;}
		$.ajax({
		    type       : 'POST',
		    data : $("#registerForm").serializeFORMJSON(),
		    dataType   : 'json',
		    url        : '/member',
		    error      : function(json){
		        alert('저장 중 오류가 발생하였습니다.');
		    },
		    success    : function(data, status){
		    	alert('회원가입을 축하합니다.');
	            location.href = '<c:url value="/login"/>';
		    }
		});
	});
	
	//중복 체크
	$('#btnCheck').click(function(e) {
		e.preventDefault();
		if($('#memberId').val().trim() == ''){
			alert("ID를 입력하세요.");
			return false;
		}else{
			$('#memberIdHidden').val($('#memberId').val());
			duplicationCheck = false;
		}
		
		$.ajax({
		    type       : 'POST',
		    data : $("#registerForm").serializeFORMJSON(),
		    dataType   : 'json',
		    url        : '/member/check',
		    error      : function(json){
		        alert('중복 체크 중 오류가 발생하였습니다.');
		    },
		    success    : function(data, status){
		    	console.log(data)
		    	if(data.flag == true){
		    		alert('사용가능한 ID 입니다.');
		    	}else{
		    		alert('중복된 ID 입니다.');
		    		$('#memberId').val('');
		    	}
		    }
		});
	});
  });
  	function chkPW(){
		var pw = $("#password").val();
  		var num = pw.search(/[0-9]/g);
  		var eng = pw.search(/[a-z]/ig);
  		var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

		 if(pw.length < 8 || pw.length > 20){
			alert("비밀번호는 8자리 ~ 20자리 이내로 입력해주세요.");
 			return false;
 		}else if(pw.search(/\s/) != -1){
 			alert("비밀번호는 공백 없이 입력해주세요.");
  			return false;
		}else if(num < 0 || eng < 0 || spe < 0 ){
			alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
			return false;
 		}else if($('#passwordConfirm').val().trim() != $('#password').val().trim()){
 			alert("비밀번호가 같지않습니다.");
			return false;
 		}else {
			return true;
 		}
	}
</script>
</head>
<body class="hold-transition register-page">
<div class="register-box">
	<div class="register-logo">
		<b>회원가입</b>
	</div>
	<div class="register-box-body">
		<form:form modelAttribute="member" id="registerForm" name="registerForm">
			<div class="input-group form-group has-feedback">
				<input type="text" class="form-control" placeholder="ID" name="memberId" id="memberId">
				<input type="hidden" name="memberIdHidden" id="memberIdHidden">
				<span class="input-group-btn">
					<button type="button" id="btnCheck" class="btn btn-primary btn-flat" style="line-height:1.5;">중복확인</button>
				</span>
			</div>
			<div class="form-group has-feedback">
				<input type="text" class="form-control" placeholder="name" name="memberName" id="memberName">
				<span class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<div class="form-group has-feedback">
				<input type="password" class="form-control" placeholder="Password" name="password" id="password">
				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="form-group has-feedback">
				<input type="password" class="form-control" placeholder="Confirm Password" name="passwordConfirm" id="passwordConfirm">
				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="row">
   				<div class="col-xs-6">
      				<a href="/login" class="text-center">로그인</a>
    			</div>
     			<div class="col-xs-6">
       				<button type="button" class="btn btn-primary btn-block btn-flat" id="btnSave">등록</button>
     			</div>
  			</div>
		</form:form>
	</div>
</div>
</body>
</html>
