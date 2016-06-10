<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+
   request.getServerName()+":"+request.getServerPort();
   String projectUrl=basePath+path+"/";
%>
<%@page import="com.nuc.bean.SingersBean"%>
<%@ page language="java" import="java.util.*"%>
<%
SingersBean singersBean=(SingersBean)request.getAttribute("singersBean");
%>
<!DOCTYPE html>
<html>
<head>
<title>LM管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="<%=projectUrl%>Admin/scripts/jquery/jquery-1.7.1.js"></script>
<link href="<%=projectUrl%>Admin/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="<%=projectUrl%>Admin/style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=projectUrl%>Admin/scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="<%=projectUrl%>Admin/scripts/jquery/jquery-1.4.4.min.js"></script>
<script src="<%=projectUrl%>Admin/scripts/My97DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script>
<script type="text/javascript" src="<%=projectUrl%>Admin/scripts/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript">
	/** 表单验证  **/
	function validateForm(){
		if($("#singerName").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请填写歌手姓名', ok:true,});
			return false;
		}
		if($("#singerInfo").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请填写歌手简介', ok:true,});
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<form id="submitForm" name="submitForm" action="<%=path %>/SingerUpdServlet" method="post"  onSubmit="return validateForm()" >
	<input type="hidden" name="fyID" value="14458625716623" id="fyID"/>
	<div id="container">
		
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt">歌手名称</td>
					<td class="ui_text_lt">
						<input type="text" name="singerId" value="<%=singersBean.getSingerId() %>"  style="display:none;"/>
						<input type="text" id="singerName" name="singerName"  class="ui_input_txt02" value="<%=singersBean.getSingerName()%>"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">歌手简介</td>
					<td class="ui_text_lt">
						<textarea id="singerInfo" name="singerInfo"  class="ui_input_txt02" clos="20" rows="5"style="width:200px;height:70px;"><%=singersBean.getSingerInfo()%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input id="submitbutton" type="submit" value="提交" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="reset" value="取消" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</body>
</html>