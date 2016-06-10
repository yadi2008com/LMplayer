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
<title>SCT-后台系统</title>
<link href="<%=projectUrl%>Admin/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="<%=projectUrl%>Admin/style/authority/common_style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center"  border="0">
		    <tr>
		      <th colspan="2">系统介绍</th>
		    </tr>
		    <tr>
		      <td width="100" height="30">当前版本<span class="TableRow2"></span></td>
		      <td style="text-align:left">后台管理系统 4.1</td>
		    </tr>
		    <tr>
		      <td width="100" height="30">版权声明</td>
		      <td style="text-align:left">1、本软件为毕业设计使用,未经书面授权，不得向任何第三方提供本软件系统; <br>        
		      	  2、本软件与商业无关。</td>
		    </tr>
		</table>
		<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
		    <tr>
		      <th colspan="2">系统开发 </th>
		    </tr>
		    <tr>
		      <td width="100" height="30">程序制作</td>
		      <td style="text-align:left">陈雅迪独立开发完成</td>
		    </tr>
		    <tr>
		      <td width="100" height="30">联系方式</td>
		      <td style="text-align:left">
		      	<a href="mailto:yadi2008com@sina.com">yadi2008com@sina.com</a>
		      </td>
		    </tr>
		    <tr>
		      <td width="100" height="30">技术博客<span class="TableRow2"></span></td>
		      <td style="text-align:left">
		      	<a href="http://blog.csdn.net/xieqiang2008com" target="_blank">http://blog.csdn.net/xieqiang2008com</a>
		      </td>
		    </tr>
		</table>
	</div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
