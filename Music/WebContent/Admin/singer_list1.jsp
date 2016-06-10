<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.nuc.bean.SingersBean"%>
<%@page import="com.nuc.bean.PageBean"%>
<%@ page language="java" import="java.util.*"%>
<% String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+
   request.getServerName()+":"+request.getServerPort();
   String projectUrl=basePath+path+"/";
%>
<%
//得到全部歌曲信息表
List<SingersBean> singersBeanInfoList =(List<SingersBean>)request.getAttribute("singersBeanInfoList");
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
<script type="text/javascript">
<%-- $.ajax({
	url:'<%=path %>/SingerListServlet',
	dataType:'json',
	success:function(data){
		/*總頁數*/
		document.getElementById("rowCount").innerHTML=data.getSingerInfoRows;
		document.getElementById("pageNow").innerHTML=data.pageBean.pageno;
		if(null!=data && ""!=data){
			var singers=data.singersBeanInfoList;
			$.each(singers,function(num,item){
					$("#singer").append("<tr><td>"+item.singerId+"</td>"
							+"<td>"+item.singerName+"</td>"
							+"<td>"+item.singerInfo+"</td>"
							+"<td><a href='/Music/SingerDelServlet?singerId="+item.singerId+"'>删除</a></td></tr>");
				
			});
		}
	}
	});
	/** 普通跳转 **/
	function jumpNormalPage(param){
		var pageNo;
		alert(document.getElementById("pageNo").value);
		if("down"==param){
			pageNo=document.getElementById("pageNo").value+1;
			/* if(pageNo == data.pageBean.pageno){
				alert("当前是最后一页了!");
				break;
			} */
		}else if("up"==param){
			pageNo=document.getElementById("pageNo").value-1;
		}else {
			pageNo=1;
		} 
		//$("#songList").remove($'tr);
		var path="<%=path %>/SingerListServlet?pageno=";
		$.ajax({
			url:path+pageNo,
			type:'post',
			dataType:'json',
			success:function(data){
				/*总页数*/
				$("#pageNo").value=data.pageBean.pageno;
				document.getElementById("rowCount").innerHTML=data.getSingerInfoRows;
				document.getElementById("pageNow").innerHTML=data.pageBean.pageno;
				
				//String basePath = request.getServletContext().getRealPath("")+"songFile\\"; 
				if(null!=data && ""!=data){
					var singers=data.singersBeanInfoList;
					$.each(singers,function(num,item){
							$("#singer").append("<tr><td>"+item.singerId+"</td>"
									+"<td>"+item.singerName+"</td>"
									+"<td>"+item.singerInfo+"</td>"
									+"<td><a href='/Music/SingerDelServlet?singerId="+item.singerId+"'>删除</a></td></tr>");
						
					});
				}
			}
			});
		/* if("down"==param && document.getElementById("pageNo").value ==1 ){
			alert("当前是第一页了!");
			break;
		}
		if("up"==param && document.getElementById("pageNo").value == data.pageBean.pageno){
			alert("当前是最后一页了!");
			break;
		} */
	} --%>
	//取出传回来的参数error并与yes比较
	 var errori ='<%=request.getParameter("error")%>';
	  if(errori=='yes'){
	   alert("有此歌手的相关歌曲，若要删除请先删除此歌手所属歌曲");
	 }
</script> 
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
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0" id="singer">
						<tr>
							<th>歌手id</th>
							<th>歌手名</th>
							<th>歌手简介</th>
							<th>操作</th>
						</tr>
						<%
						for(SingersBean singersBeanInfo:singersBeanInfoList){
						%>
						<tr>
							<td><%=singersBeanInfo.getSingerId()%></td>
							<td><%=singersBeanInfo.getSingerName()%></td>
							<td><%=singersBeanInfo.getSingerInfo()%></td>
							<td><a href='<%=path %>/SingerDelServlet?songId=<%=singersBeanInfo.getSingerId()%>'>删除</a></td>
						</tr>
						<%
						}
						%>
					</table>
				</div>
				<div class="ui_tb_h30">
					<!-- <div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04"  id="rowCount"></span>
						条记录，当前第
						<span class="ui_txt_bold04" id="pageNow">
						</span>
						页
					</div> -->
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04"  id="rowCount">
						 <%=request.getAttribute("result")!=null? request.getAttribute("result"):0%>
						</span>
						条记录，当前第
						<span class="ui_txt_bold04" id="pageNow">
						<%=pageBean.getPageno()%>
						</span>
						页
					</div>
					<!-- <div class="ui_frt">
						   如果是第一页，则只显示下一页、尾页
						
							<input type="button" value="上一页" class="ui_input_btn01" onclick="jumpNormalPage('up');"/>
							<input type="button" value="下一页" class="ui_input_btn01"
								onclick="jumpNormalPage('down');" />
					</div> -->
					<div class="ui_frt">
						<!--    如果是第一页，则只显示下一页、尾页 -->
						<a href="SingerListServlet?pageno=<%=(pageBean.getPageno()-1)%>">上一页</a>
						<a href="SingerListServlet?pageno=<%=(pageBean.getPageno()+1)%>">下一页</a>
					</div>
				</div>
			</div>
		</div>
	</form>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
    