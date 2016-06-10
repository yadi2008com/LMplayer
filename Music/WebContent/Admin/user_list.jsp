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
	url:'<%=path %>/UserListServlet',
	type:'post',
	dataType:'json',
	success:function(data){
		if(null!=data && ""!=data){
			console.log(data);
			$.each(data,function(num,item){
				$("#userList").append("<tr><td>"+item.userId+"</td>"
						+"<td>"+item.userName+"</td>"
						+"<td>"+item.userPwd+"</td></tr>");
			});
		}
	}
	
}); 
</script>
<script> 

//取出传回来的参数error并与yes比较
  var errori ='<%=request.getParameter("error")%>';
  if(errori=='yes'){
   alert("有次歌曲类型的相关歌曲，若要删除请先删除此类型的歌曲");
  }
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
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0" id="userList">
						<tr>
							<th>管理员Id</th>
							<th>管理员名字</th>
							<th>管理员密码</th>
							<!-- <th>操作</th> -->
						</tr>
					</table>
				</div>
			
			</div>
		</div>
	</form>

</body>
</html>
    