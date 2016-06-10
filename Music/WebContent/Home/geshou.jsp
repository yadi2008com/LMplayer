<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ include file="head.jsp" %>
<!-- 主页面 内容主体 -->
	<div id="bodyer" contentEditable="false">
		<div id="bodyerFrame">
			<!-- 主页面 主体轮播 -->
				<div class="poster-main A_Demo">
					<div class="poster-btn poster-prev-btn"></div>
					<ul class="poster-list">
					<li class="poster-item"><a href="#"><Img src="../Img/1.jpg" width="100%" ></a></li>
					<li class="poster-item"><a href="#"><Img src="../Img/2.jpg" width="100%" ></a></li>
					<li class="poster-item"><a href="#"><Img src="../Img/3.jpg" width="100%" ></a></li>
					<li class="poster-item"><a href="#"><Img src="../Img/4.jpg" width="100%" ></a></li>
					<li class="poster-item"><a href="#"><Img src="../Img/5.jpg" width="100%" ></a></li>
					<li class="poster-item"><a href="#"><Img src="../Img/6.jpg" width="100%" ></a></li>
					<li class="poster-item"><a href="#"><Img src="../Img/7.jpg" width="100%" ></a></li>
					</ul>
					<div class="poster-btn poster-next-btn"></div>
				</div>
				<script type="text/javascript" src="../Js/jquery.js"></script>
				<script type="text/javascript" src="../Js/PicCarousel.js"></script>
				<script type="text/javascript">
				$("#bodyerFrame .A_Demo").PicCarousel("init");
				$("#bodyerFrame .B_Demo").PicCarousel({
						"width":1000,		//幻灯片的宽度
						"height":300,		//幻灯片的高度
						"posterWidth":520,	//幻灯片第一帧的宽度
						"posterHeight":300, //幻灯片第一张的高度
						"scale":0.9,		//记录显示比例关系
						"speed":1500,		//记录幻灯片滚动速度
						"autoPlay":true,	//是否开启自动播放
						"delay":1000,		//自动播放间隔
						"verticalAlign":"top"	//图片对齐位置
				});
				</script>
			<!-- 主页面 主体左边框架 -->
			<div id="b_leftFrame">
				<!--歌单 -->
				<div class="Ltitle"> 
					<h2>最热歌手</h2>
					<a href="">/更多</a>
				</div>
				<div class="clear"></div>
				<div class="Lsong">
					<!-- ----------------------------------------加java代码 -->
					 <div class="LsongSingle">
					 	  <img alt="REFRESH" title="REFRESH" src="../Img/1.jpg"/>
					 	  <a href="">歌单名称</a>
					 </div>	
					 <div class="LsongSingle">
					 	  <img alt="REFRESH" title="REFRESH" src="../Img/1.jpg"/>
					 	  <a href="">歌单名称</a>
					 </div>
					 <div class="LsongSingle">
					 	  <img alt="REFRESH" title="REFRESH" src="../Img/1.jpg"/>
					 	  <a href="">歌单名称</a>
					 </div>	
					 <div class="LsongSingle">
					 	  <img alt="REFRESH" title="REFRESH" src="../Img/1.jpg"/>
					 	  <a href="">歌单名称</a>
					 </div>	
					 <div class="LsongSingle">
					 	  <img alt="REFRESH" title="REFRESH" src="../Img/1.jpg"/>
					 	  <a href="">歌单名称</a>
					 </div>
					 <div class="LsongSingle">
					 	  <img alt="REFRESH" title="REFRESH" src="../Img/1.jpg"/>
					 	  <a href="">歌单名称</a>
					 </div>		
				</div>
				<div class="clear"></div>
				</div>
				
		
			<!-- 主页面 主体右边框架 -->
			<div id="b_rightFrame">
					<div class="login">
					<a href=""><img src="../Img/bg_login.png"></a>
					</div>
					
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="foot.jsp" %>