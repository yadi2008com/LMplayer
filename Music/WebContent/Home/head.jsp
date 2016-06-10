<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<% String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+
   request.getServerName()+":"+request.getServerPort();
   String projectUrl=basePath+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<%-- <base href="<%=basePath%>"> --%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../Css/index.css" rel="stylesheet" style="text/css" /> 
	<link rel="stylesheet" type="text/css" href="../Css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="../Css/default.css">
	<link rel="stylesheet" href="../Css/style.css">
	<title>LOVE MUSIC</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="音乐播放器、Music" />
    <meta name="description" content="音乐播放器" />
</head>
<body>
	<!-- 主页面 导航 -->
	<div id="header">
		<div id="headerFrame">
			<div class="h_nav">
				<img src="../Img/logo.png">
				<ul>
					<li><a href="index.jsp" class="nav">首页 </a></li>
					<li><a href="<%=projectUrl%>/SongRankHomeServlet">排行榜</a></li> 
					<!-- <li><a href="rank.jsp">排行榜</a></li> 
					<li><a href="">歌单广场</a></li>
					<li><a href="">歌手</a></li>-->
					<li><a href="">下载客户端</a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<!-- <form class="h_search" id="h_search" action="" method="post">
				<div class="kuan"><input name="" type="text" />搜索框
				<input name="" type="button" /></div>搜索按钮
			</form> -->
		</div>
		<div class="clear"></div>
	</div>