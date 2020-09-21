<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/dist/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	srchHistory();
});

function srchHistory(){
	var formData = JSON.stringify({order: "history"});
    $.ajax({
		url : "/history"
      , type : "POST"
      , cache : false
      , dataType : "json"
      , async:false
      , data : formData
      , contentType : "application/json; charset=UTF-8"
      , traditional: true
      , success: function(data) { 
    		var tblRow2 = $('#example2 tbody tr:first');
			var nodataTr2 = $("#nodataTr2");	// 자료없음 tr
    	    var dataRows2 = [];
      		if(data.keyWordHistoryList!=0){
      			$.each(data.keyWordHistoryList, function(i, obj) {
      				dataRows2.push([obj.keyWord, obj.searchCnt]);
      			});
      			$('#example2').createTableRow(tblRow2, dataRows2);
      		}else{
      			$("#example2 tbody").html(nodataTr2);
      		}
      }
	});
}
</script>
	<section class="content-header">
		<h1>
			카카오뱅크 코딩 테스트 프로그램
		</h1>
		<ol class="breadcrumb">
			<li><a href="/main/view"><i class="fa fa-tags"></i> Home</a></li>
			<li><a href="/history/view"><i class="fa fa-tags"></i> History</a></li>
		</ol>
	</section>
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
        	<div class="box">
            <div class="box-header">
              <h3 class="box-title">인기 키워드</h3>
            </div>
            <div class="box-body">
              <table id="example2" class="table table-bordered table-striped">
              <colgroup>
				<col width="50%" />
				<col width="50%" />
			</colgroup>
                <thead>
                <tr>
                  <td style="text-align:center"><b>키워드</b></td>
                  <td style="text-align:center"><b>검색 횟수</b></td>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td style="text-align:center"></td>
                  <td style="text-align:center"></td>
                </tr>
                <tr id="nodataTr2">
					<td colspan="2" style="text-align:center">검색된 자료가 없습니다.</td>
				</tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
<%@include file="../include/footer.jsp"%>