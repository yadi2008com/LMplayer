<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.nuc.bean.SongsVo"%>
<%@page import="com.nuc.bean.PageBean"%>
<%@ page language="java" import="java.util.*"%>
<% String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+
   request.getServerName()+":"+request.getServerPort();
   String projectUrl=basePath+path+"/";
%>
<%
//得到全部歌曲信息表
List<SongsVo> songsVoInfoList =(List<SongsVo>)request.getAttribute("songsVoInfoList");
//得到分页信息实例
PageBean pageBean=(PageBean)request.getAttribute("pageBean");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=projectUrl%>Admin/scripts/jquery/jquery-1.7.1.js"></script>
<link href="<%=projectUrl%>Admin/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="<%=projectUrl%>Admin/style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=projectUrl%>Admin/scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="<%=projectUrl%>Admin/scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="<%=projectUrl%>Admin/scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=projectUrl%>Admin/style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
<script type="text/javascript" src="<%=projectUrl%>Admin/scripts/artDialog/artDialog.js?skin=default"></script>

<title>LM管理系统</title>

<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="submitForm" name="submitForm" action="" method="post">
		<input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
		<input type="hidden" name="fangyuanEntity.fyXqName" value="" id="fyXqName"/>
		<div id="container">
			<div class="ui_content">
				
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0" id="songList">
						<tr>
							<th>歌曲编号</th>
							<th>歌曲名</th>
							<th>歌手名</th>
							<th>发行时间</th>
							<th>类型</th>
							<th>金句</th>
							<th>歌曲热度</th>
							<!-- <th>专辑名</th> -->
							
						</tr>
						<%
						if(songsVoInfoList.isEmpty()){
						%>
						<tr><td colspan="7">暂时没数据，敬请期待</td></tr>
						<%
						}else{
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
						}
						%>
					</table>
				</div>
				<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04"  id="rowCount">
						 <%=request.getAttribute("getSongInfoRows")!=null? request.getAttribute("getSongInfoRows"):0%>
						</span>
						条记录，当前第
						<span class="ui_txt_bold04" id="pageNow">
						<%=pageBean.getPageno()%>
						</span>
						页
					</div>
					<div class="ui_frt">
						<!--    如果是第一页，则只显示下一页、尾页 -->
						<a href="SongRankListServlet?pageno=<%=(pageBean.getPageno()-1)%>">上一页</a>
						<a href="SongRankListServlet?pageno=<%=(pageBean.getPageno()+1)%>">下一页</a>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
    