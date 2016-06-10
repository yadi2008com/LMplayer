<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.nuc.bean.SongsVo"%>
<%@page import="com.nuc.bean.PageBean"%>
<%@ page language="java" import="java.util.*"%>
<%
//得到全部歌曲信息表
List<SongsVo> songsVoInfoList =(List<SongsVo>)request.getAttribute("songsVoInfoList");
//得到分页信息实例
PageBean pageBean=(PageBean)request.getAttribute("pageBean");
%>
<%@ include file="head.jsp" %>
<%-- <script type="text/javascript">
 $.ajax({
	url:'<%=path %>/SongRankHomeServlet',
	type:'post',
	dataType:'json',
	success:function(data){
		if(null!=data && ""!=data){
			var songs=data.songsVoInfoList;
			console.log(songs);
			$.each(songs,function(num,item){
				$("#songList").append("<tr><td>"+item.songId+"</td>"
						+"<td>"+item.songName+"</td>"
						+"<td>"+item.singerName+"</td>"
						+"<td>"+new Date(item.songTime)+"</td>"
						+"<td>"+item.typeName+"</td>"
						+"<td>"+item.songWord+"</td>"
						+"<td>"+item.songHeat+"</td>"
						+"<td>"+item.songAlbum+"</td></tr>");
				
			});
		}
	}
	
}); 
</script> --%>
<style>


table {
    *border-collapse: collapse; /* IE7 and lower */
    border-spacing: 0;
    width: 100%;    
}

.bordered {
    border: solid #ccc 1px;
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    -webkit-box-shadow: 0 1px 1px #ccc; 
    -moz-box-shadow: 0 1px 1px #ccc; 
    box-shadow: 0 1px 1px #ccc;         
}

.bordered tr:hover {
    background: #fbf8e9;
    -o-transition: all 0.1s ease-in-out;
    -webkit-transition: all 0.1s ease-in-out;
    -moz-transition: all 0.1s ease-in-out;
    -ms-transition: all 0.1s ease-in-out;
    transition: all 0.1s ease-in-out;     
}    
    
.bordered td, .bordered th {
    border-left: 1px solid #ccc;
    border-top: 1px solid #ccc;
    padding: 10px;
    text-align: left;    
}

.bordered th {
    background-color: #dce9f9;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc), to(#dce9f9));
    background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:    -moz-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:     -ms-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:      -o-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:         linear-gradient(top, #ebf3fc, #dce9f9);
    -webkit-box-shadow: 0 1px 0 rgba(255,255,255,.8) inset; 
    -moz-box-shadow:0 1px 0 rgba(255,255,255,.8) inset;  
    box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;        
    border-top: none;
    text-shadow: 0 1px 0 rgba(255,255,255,.5); 
}

.bordered td:first-child, .bordered th:first-child {
    border-left: none;
}

.bordered th:first-child {
    -moz-border-radius: 6px 0 0 0;
    -webkit-border-radius: 6px 0 0 0;
    border-radius: 6px 0 0 0;
}

.bordered th:last-child {
    -moz-border-radius: 0 6px 0 0;
    -webkit-border-radius: 0 6px 0 0;
    border-radius: 0 6px 0 0;
}

.bordered th:only-child{
    -moz-border-radius: 6px 6px 0 0;
    -webkit-border-radius: 6px 6px 0 0;
    border-radius: 6px 6px 0 0;
}

.bordered tr:last-child td:first-child {
    -moz-border-radius: 0 0 0 6px;
    -webkit-border-radius: 0 0 0 6px;
    border-radius: 0 0 0 6px;
}

.bordered tr:last-child td:last-child {
    -moz-border-radius: 0 0 6px 0;
    -webkit-border-radius: 0 0 6px 0;
    border-radius: 0 0 6px 0;
}



/*----------------------*/

.zebra td, .zebra th {
    padding: 10px;
    border-bottom: 1px solid #f2f2f2;    
}

.zebra tbody tr:nth-child(even) {
    background: #f5f5f5;
    -webkit-box-shadow: 0 1px 0 rgba(255,255,255,.8) inset; 
    -moz-box-shadow:0 1px 0 rgba(255,255,255,.8) inset;  
    box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;        
}

.zebra th {
    text-align: left;
    text-shadow: 0 1px 0 rgba(255,255,255,.5); 
    border-bottom: 1px solid #ccc;
    background-color: #eee;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#f5f5f5), to(#eee));
    background-image: -webkit-linear-gradient(top, #f5f5f5, #eee);
    background-image:    -moz-linear-gradient(top, #f5f5f5, #eee);
    background-image:     -ms-linear-gradient(top, #f5f5f5, #eee);
    background-image:      -o-linear-gradient(top, #f5f5f5, #eee); 
    background-image:         linear-gradient(top, #f5f5f5, #eee);
}

.zebra th:first-child {
    -moz-border-radius: 6px 0 0 0;
    -webkit-border-radius: 6px 0 0 0;
    border-radius: 6px 0 0 0;  
}

.zebra th:last-child {
    -moz-border-radius: 0 6px 0 0;
    -webkit-border-radius: 0 6px 0 0;
    border-radius: 0 6px 0 0;
}

.zebra th:only-child{
    -moz-border-radius: 6px 6px 0 0;
    -webkit-border-radius: 6px 6px 0 0;
    border-radius: 6px 6px 0 0;
}

.zebra tfoot td {
    border-bottom: 0;
    border-top: 1px solid #fff;
    background-color: #f1f1f1;  
}

.zebra tfoot td:first-child {
    -moz-border-radius: 0 0 0 6px;
    -webkit-border-radius: 0 0 0 6px;
    border-radius: 0 0 0 6px;
}

.zebra tfoot td:last-child {
    -moz-border-radius: 0 0 6px 0;
    -webkit-border-radius: 0 0 6px 0;
    border-radius: 0 0 6px 0;
}

.zebra tfoot td:only-child{
    -moz-border-radius: 0 0 6px 6px;
    -webkit-border-radius: 0 0 6px 6px
    border-radius: 0 0 6px 6px
}
  
</style>
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
					<h2>排行榜</h2>
					
				</div>
				<div class="clear"></div>
				<div class="Lsong">
				
					<table  class="zebra" cellspacing="1" cellpadding="1" width="100%" align="center" border="1" id="songList">
						<thead>
  							<tr>
							<th>歌曲编号</th>
							<th>歌曲名</th>
							<th>歌手名</th>
							<th>发行时间</th>
							<th>类型</th>
							<th>金句</th>
							<th>歌曲热度</th>
							<!-- <th>专辑名称</th> -->
							<!-- <th>操作</th> -->
							</tr>
   						</thead>
						<tfoot>
						    <tr>
						        <th colspan=8>
						                   共有<%=request.getAttribute("getSongInfoRows")!=null? request.getAttribute("getSongInfoRows"):0%>条记录
								，当前第<%=pageBean.getPageno()%>页 
								&nbsp;&nbsp;
								<a href="SongRankHomeServlet?pageno=<%=(pageBean.getPageno()-1)%>">上一页</a>
								&nbsp;&nbsp;
								<a href="SongRankHomeServlet?pageno=<%=(pageBean.getPageno()+1)%>">下一页</a>
								</th>
						    </tr>
					    </tfoot>  
						<%-- <%
						for(SongsVo songsVoInfo:songsVoInfoList){
						%>
						<tr>
							<td><%=songsVoInfo.getSongId()%></td>
							<td><%=songsVoInfo.getSongName()%></td>
							<td><%=songsVoInfo.getSingerName()%></td>
							<td><%=songsVoInfo.getSongTime()%></td>
							<td><%=songsVoInfo.getTypeName()%></td>
							<td><%=songsVoInfo.getSongWord()%></td>
							<td><%=songsVoInfo.getSongHeat()%></td>
							<td><a href='<%=path %>/SongDelServlet?songId=<%=songsVoInfo.getSongId()%>'>删除</a></td>
						</tr>
						<%
						}
						%> --%>
						
						<%
						for(SongsVo songsVoInfo:songsVoInfoList){
						%>
						<tr>
							<td><%=songsVoInfo.getSongId()%></td>
							<td><%=songsVoInfo.getSongName()%></td>
							<td><%=songsVoInfo.getSingerName()%></td>
							<td><%=songsVoInfo.getSongTime()%></td>
							<td><%=songsVoInfo.getTypeName()%></td>
							<td><%=songsVoInfo.getSongWord()%></td>
							<td><%=songsVoInfo.getSongHeat()%></td>
							<%-- <td><%=songsVoInfo.getSongAlbum()%></td> --%>
							
						<%
						}
						%>
					</table>
					
				</div>
				<div class="clear"></div>
				
				</div>
				
		<!-- 
			主页面 主体右边框架
			<div id="b_rightFrame">
					<div class="login">
					<a href="login.jsp"><img src="../Img/bg_login.png"></a>
					</div>
					
			</div> -->
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="foot.jsp" %>