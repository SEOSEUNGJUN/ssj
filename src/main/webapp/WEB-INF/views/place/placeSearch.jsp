<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="../include/header.jsp"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=27463264e16d8c806b7339c52db01e84"></script>
<script type="text/javascript">

$(document).ready(function() {
    $("#placeName").keydown(function(key) {
        if (key.keyCode == 13) {
        	actionSearch()
        }
    });
});

function actionDtl(e){
	var $tr = $(e).closest('tr');
	$("#id b").text("장소ID : " +$tr.find('td:first').text());
	$("#placeName b").text("장소명, 업체명 : " + $tr.find('td:eq(1)').text());
	$("#categoryName b").text("카테고리 이름 : " + $tr.find('td:eq(2)').text());
	$("#phone b").text("전화번호 : " + $tr.find('td:eq(3)').text());
	$("#roadAddressName b").text("전체 도로명 주소 : " + $tr.find('td:eq(4)').text());
	$("#placeUrl b").text("장소 상세페이지 URL : " + $tr.find('td:eq(5)').text());
	$("#addressName b").text("전체 지번 주소 : " + $tr.find('td:eq(6)').text());
	$("#categoryGroupCode b").text("카테고리 그룹 코드 : " + $tr.find('td:eq(7)').text());
	$("#categoryGroupName b").text("카테고리 그룹명 : " + $tr.find('td:eq(8)').text());
	$("#x b").text("X 좌표값 : " + $tr.find('td:eq(9)').text());
	$("#y b").text("Y 좌표값 : " + $tr.find('td:eq(10)').text());
	$("#distance b").text("중심좌표까지의 거리 : " + $tr.find('td:eq(11)').text());
	$("#daumUrl a").text("Daum 지도 바로 가기 : https://map.kakao.com/link/map/"+$tr.find('td:first').text());
	$("#daumUrl a").attr("href", "https://map.kakao.com/link/map/"+$tr.find('td:first').text())
	
	$("#section1").hide(); 
	$("#section2").show(); 
	$("#map").show(); 
	
	//지도 생성 및 마커
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new kakao.maps.LatLng($tr.find('td:eq(10)').text(),$tr.find('td:eq(9)').text()), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨
	};

	//지도를 생성
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 마커가 표시 위치
	var markerPosition  = new kakao.maps.LatLng($tr.find('td:eq(10)').text(),$tr.find('td:eq(9)').text()); 

	// 마커 생성
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});

	// 마커가 지도 위에 표시
	marker.setMap(map);
}

function actionBack(e){
	$("#section2").hide(); 
	$("#map").hide(); 
	$("#section1").show(); 
}

function actionSearch(page){
	if($.trim($("#placeName").val()) == ""){
		alert("키워드를 입력하세요.");
		return;
	}
	var data = JSON.stringify({placeName:$("#placeName").val(), page: page});
    $.ajax({
            url : "/place"
          , type : "POST"
          , cache : false
          , dataType : "json"
          , async:false
          , data : data
          , contentType : "application/json; charset=UTF-8"
          , traditional: true
          , success: function(data) {
        	  	var tblRow = $('#example2 tbody tr:first');
				var nodataTr = $("#nodataTr");	// 자료없음 tr
        	    var dataRows = [];
	      		if(data.list!=0){
	      			$.each(data.list, function(i, obj) {
	      				dataRows.push(
	      						[obj.id,
      							obj.place_name,
      							obj.category_name,
      							obj.phone,
      							obj.road_address_name,
      							obj.place_url, 
      							obj.address_name,
      							obj.category_group_code,
      							obj.category_group_name,
      							obj.x, 
      							obj.y, 
      							obj.distance]
	      						);
	      			});
	      			$('#example2').createTableRow(tblRow, dataRows);
	      			
	    			$('#example2 tr td:nth-child(1)').each( function(i){
	    				$(this).html( '<a href="#" onclick="javascript:actionDtl(this)"; >' + $(this).text() + '</a>' );
	    			});
	      		}else{
	      			$("#example2 tbody").html(nodataTr);
	      		}
	      		if(page == undefined){
        	  		pagination = new tui.Pagination('pagination', {
              		  totalItems: data.resultCnt
      				});
      				
      				pagination.on('afterMove', function(eventData) {
      					actionSearch(eventData.page+"");
      				});	
        		}
          },
          error: function(regCd, regMsg) {
        	  console.log(regCd);
        	  console.log(regMsg);
              alert("카카오 API 호출 실패 > 관리자에게 문의하세요.");
          }
    });
}

</script>
	<section class="content-header">
		<h1>
			카카오뱅크 코딩 테스트 프로그램
		</h1>
		<ol class="breadcrumb">
			<li><a href="/main/view"><i class="fa fa-tags"></i>Home</a></li>
			<li><a href="/place/view"><i class="fa fa-tags"></i>Place</a></li>
		</ol>
	</section>
   <section class="content" id="section1">
     <div class="row">
       <div class="col-xs-12">
         <div class="box">
           <div class="box-header">
             <h3 class="box-title">장소 검색 서비스</h3>
           </div>
        <!-- search form -->
        <div class="sidebar-form">
          <div class="input-group">
            <b><input type="text" name="placeName" id="placeName" class="form-control" placeholder="Search..." ></b>
            <span class="input-group-btn">
                <button type="button" id="btnSearch" class="btn btn-flat" onclick="javascript:actionSearch();" >
                  <i class="fa fa-search"></i>
                </button>
              </span>
          </div>
          </div>
           <!-- /.box-header -->
           <div class="box-body">
             <table id="example2" class="table table-bordered table-hover">
             <colgroup>
				<col width="5%" />
				<col width="20%" />
				<col width="25%" />
				<col width="10%" />
				<col width="20%" />
				<col width="20%" />
			</colgroup>
               <thead>
               <tr>
                 <th style="text-align:center">장소 ID</th>
                 <th style="text-align:center">장소명, 업체명</th>
                 <th style="text-align:center">카테고리 이름</th>
                 <th style="text-align:center">전화번호</th> 
                 <th style="text-align:center">전체 도로명 주소</th> 
                 <th style="text-align:center">장소 상세페이지 URL</th> 
                 <!-- <th style="text-align:center">전체 지번 주소</th> 
                 <th style="text-align:center">카테고리 그룹 코드</th>
                 <th style="text-align:center">카테고리 그룹명</th> 
                 <th style="text-align:center">X 좌표값</th> 
                 <th style="text-align:center">Y 좌표값</th> 
                 <th style="text-align:center">중심좌표까지의 거리</th> --> 
               </tr>
               </thead>
               <tbody>
               		<tr>
               			<td style="text-align:center"></td>
               			<td></td>
               			<td></td>
               			<td style="text-align:center"></td>
               			<td></td>
               			<td></td>
               			<td style="display:none;"></td>
               			<td style="display:none;"></td>
               			<td style="display:none;"></td>
               			<td style="display:none;"></td>
               			<td style="display:none;"></td>
               		</tr>
               		<tr id="nodataTr">
						<td colspan="6" style="text-align:center">검색된 자료가 없습니다.</td>
					</tr>
               </tbody>
             </table>
             <div id="pagination" class="tui-pagination"></div>
           </div>
         </div>
       </div>
     </div>
   </section>
   <input type="hidden" name="x" id="x" value ="33.450701">
   <input type="hidden" name="y" id="y" value ="126.570667">
   <section class="content" id="section2" style="display:none;">
   	<div class="row">
       <div class="col-md-12">
         <div class="box">
           <div class="box-header">
             <h3 class="box-title">장소 상세 정보</h3>
           </div>
	       <div class="row">
				<div class="col-xs-12">
				<button type="button" id="btnBack" class="btn btn-block btn-flat" onclick="javascript:actionBack();"><b>뒤로가기</b></button>
				</div>
			</div>
           </div>
	    </div>
	</div>
     <div class="row">
       <div class="col-md-12">
         <div class="box box-danger">
           <div class="box-body">
           <ol>
				<li id ="placeName"><b></b></li>
				<li id ="id"><b></b></li>
				<li id ="categoryName"><b></b></li>
				<li id ="phone"><b></b></li>
				<li id ="roadAddressName"><b></b></li>
				<li id ="placeUrl"><b></b></li>
				<li id ="addressName"><b></b></li>
				<li id ="categoryGroupCode"><b></b></li>
				<li id ="categoryGroupName"><b></b></li>
				<li id ="x"><b></b></li>
				<li id ="y"><b></b></li>
				<li id ="distance"><b></b></li>
				<li id ="daumUrl"><a></a></li>
			</ol>
           </div>
         </div>
       </div>
     </div>
   </section>
   <div id="map" style="width:100%;height:350px;display:none;"></div>
 </div>
<%@include file="../include/footer.jsp"%>