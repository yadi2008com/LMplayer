<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+
   request.getServerName()+":"+request.getServerPort();
   String projectUrl=basePath+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>LOVE MUSIC</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="音乐播放器、Music" />
<meta name="description" content="音乐播放器" />
<link rel="stylesheet" href="<%=projectUrl%>Css/stylesheets/style.css">
<script src="<%=projectUrl%>Js/jquery-1.7.2.min.js"></script>
</head>
<body class="keBody">
<h1 class="keTitle">LM音乐播放器-播放列表</h1>

<div class="kePublic">
<!--效果html开始-->
<!-- <div id="background"></div> -->
<div id="player">
	<div class="cover"></div>
	<div class="ctrl">
		<div class="tag">
			<strong>Title</strong>
			<span class="artist">Artist</span>
			<span class="album">Album</span>
		</div>
		<div class="control">
			<div class="left">
				<div class="rewind icon"></div>
				<div class="playback icon"></div>
				<div class="fastforward icon"></div>
			</div>
			<div class="volume right">
				<div class="mute icon left"></div>
				<div class="slider left">
					<div class="pace"></div>
				</div>
			</div>
		</div>
		<div class="progress">
			<div class="slider">
				<div class="loaded"></div>
				<div class="pace"></div>
			</div>
			<div class="timer left">0:00</div>
			<div class="right">
				<div class="repeat icon"></div>
				<div class="shuffle icon"></div>
			</div>
		</div>
	</div>
</div>
<ul id="playlist"></ul>
<script src="<%=projectUrl%>Js/jquery-ui-1.8.17.custom.min.js"></script>
<script src="<%=projectUrl%>Js/script.js"></script>
<div style="margin:0 auto; width:50;">
	<center>
		<a href="index.jsp" style="font-color:white;">返回首页</a>
	</center>
</div>
</div>

<div class="keBottom">

<p class="keTxtP">关于LOVEMUSIC |服务条款 | 用户服务协议 | 广告服务| 客服中心 | 网站导航</br>
Copyright © 陈雅迪 2016年 毕业设计</p>

</div>
</body>
</html>