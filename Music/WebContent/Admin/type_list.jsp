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
 $.ajax({
	url:'<%=path %>/TypeListServlet',
	type:'post',
	dataType:'json',
	success:function(data){
		if(null!=data && ""!=data){
			console.log(data);
			$.each(data,function(num,item){
				$("#songType").append("<tr><td>"+item.typeId+"</td>"
						+"<td>"+item.typeName+"</td>"
						/* +"<td>"+item.typeParentId+"</td>" */
						+"<td><a href='/Music/TypeDelServlet?typeId="+item.typeId+"'>删除</a>"
						+"&nbsp;&nbsp;<a href='/Music/TypeSelServlet?typeId="+item.typeId+"'>修改</a></td></tr>");
			});
		}
	}
	
}); 
</script>
<script> 

//取出传回来的参数error并与yes比较
  var errori ='<%=request.getParameter("error")%>';
  var suc ='<%=request.getParameter("success")%>';
  if(errori=='yes'){
   alert("有此歌曲类型的相关歌曲，若要删除请先删除此类型的歌曲");
   //self.location.reload();
  }
  if(suc=='yes'){
	   alert("恭喜您，删除成功！");
	   //self.location.reload();
  }
  //history.go(0);
  
</script>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="submitForm" name="submitForm" action="" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0" id="songType">
						<tr>
							<th>类型id</th>
							<th>类型名称</th>
							<!-- <th>歌曲类型名称</th> -->
							<th>操作</th>
						</tr>
					</table>
				</div>
			
			</div>
		</div>
	</form>

</body>
</html>
    