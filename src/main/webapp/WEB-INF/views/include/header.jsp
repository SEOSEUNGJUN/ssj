<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/WEB-INF/views/common/commonHead.jsp"></c:import>
<meta charset="UTF-8">
<title>PLACE</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <header class="main-header">
    <a href="/main/view" class="logo">
      <span class="logo-lg"><b>PLACE</b></span>
    </a>
    <nav class="navbar navbar-static-top">
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <li class="user user-menu">
            <a href="/logout">
              <img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.jpg" class="user-image">
              <b>${sessionScope.memberId}</b>님
              <b>로그아웃</b>
            </a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <aside class="main-sidebar">
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><b>${sessionScope.memberId}</b>님</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">메뉴</li>
        <li class="active treeview">
          <a href="/main/view">
            <i class="fa fa-dashboard"></i> <span>메인</span>
          </a>
        </li>
        <li><a href="/history/view"><i class="fa fa-book"></i> <span>검색 현황</span></a></li>
        <li><a href="/place/view"><i class="fa fa-book"></i> <span>장소 검색</span></a></li>
      </ul>
    </section>
  </aside>
  <div class="content-wrapper">
