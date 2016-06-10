<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+
   request.getServerName()+":"+request.getServerPort();
   String projectUrl=basePath+path+"/";
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
<script type="text/javascript">
	<%-- $.ajax({
	url:'<%=path%>/SongListServlet?',
	type:'post',
	dataType:'json',
	success:function(data){
		/*总页数*/
		document.getElementById("rowCount").innerHTML=data.getSongInfoRows;
		document.getElementById("pageNow").innerHTML=data.pageBean.pageno;
		if(null!=data && ""!=data){
			var songs=data.songsVoInfoList;
			$.each(songs,function(num,item){
				console.log(item.songName);
				$("#songList").append("<tr><td>"+item.songId+"</td>"
				+"<td>"+item.songName+"</td>"
				+"<td>"+item.singerName+"</td>"
				+"<td>"+new Date(item.songTime)+"</td>"
				+"<td>"+item.typeName+"</td>"
				+"<td>"+item.songWord+"</td>"
				+"<td>"+item.songHeat+"</td>"
				+"<td><a href='http://www.baidu.com'>删除</a></td></tr>");
			});
		}
	}
	}); --%>
	$.ajax({
		url:'<%=path%>/SongListServlet?',
		type:'post',
		dataType:'json',
		success:function(data){
			/*总页数*/
			document.getElementById("rowCount").innerHTML=data.getSongInfoRows;
			document.getElementById("pageNow").innerHTML=data.pageBean.pageno;
			
			//String basePath = request.getServletContext().getRealPath("")+"songFile\\"; 
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
							+"<td><a href='/Music/SongDelServlet?songId="+item.songId+"'>删除</a></td></tr>");
					
				});
			}
		}
		});

	/** 普通跳转**/
	function jumpNormalPage(param){
		var pageNo;
		alert(document.getElementById("pageNo").value);
		if("down"==param){
			pageNo=document.getElementById("pageNo").value+1;
		}else if("up"==param){
			pageNo=document.getElementById("pageNo").value-1;
		}else {
			pageNo=1;
		}
		//$("#songList").remove($'tr);
		var path="<%=path%>/SongListServlet?pageno=";
		$.ajax({
			url:path+pageNo,
			type:'post',
			dataType:'json',
			success:function(data){
				/*总页数*/
				$("#pageNo").value=data.pageBean.pageno;
				document.getElementById("rowCount").innerHTML=data.getSongInfoRows;
				document.getElementById("pageNow").innerHTML=data.pageBean.pageno;
				//String basePath = request.getServletContext().getRealPath("")+"songFile\\"; 
				if(null!=data && ""!=data){
					var songs=data.songsVoInfoList;
					$.each(songs,function(num,item){
						console.log(item.songName);
						//String musicName = "basePath/"+item.songId+".mp3";
						$("#songList").append("<tr><td>"+item.songId+"</td>"
						+"<td>"+item.songName+"</td>"
						+"<td>"+item.singerName+"</td>"
						+"<td>"+new Date(item.songTime)+"</td>"
						+"<td>"+item.typeName+"</td>"
						+"<td>"+item.songWord+"</td>"
						+"<td>"+item.songHeat+"</td>"
						+"<td><a href='/Music/SongDelServlet?songId="+item.songId+"'>删除</a></td></tr>");
					});
				}
			}
			}); 
	}
</script>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="submitForm" name="submitForm" action="<%=path %>/SongListServlet" method="post">
		<input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
		<input type="hidden" name="fangyuanEntity.fyXqName" value="" id="fyXqName"/>
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							歌曲名&nbsp;&nbsp;<input type="text" id="songName" name="songName" class="ui_input_txt02" />
							<input type="submit" value="查询"/>
							<input type="reset" value="取消" /> 
							
						</div>
						
					</div>
				</div>
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
							<th>操作</th>
						</tr>
					</table>
				</div>
				<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04"  id="rowCount"></span>
						条记录，当前第
						<span class="ui_txt_bold04" id="pageNow">
						</span>
						页
					</div>
					<div class="ui_frt">
						<!--    如果是第一页，则只显示下一页、尾页 -->
						
							<input type="button" value="上一页" class="ui_input_btn01" onclick="jumpNormalPage('up');"/>
							<input id="pageNo" value='1' type="text" >
							<input type="button" value="下一页" class="ui_input_btn01"
								onclick="jumpNormalPage('down');" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
    