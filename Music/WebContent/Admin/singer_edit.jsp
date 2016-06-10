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
	<%-- $(document).ready(function() {
	
		/*
		 * 取消
		 */
		$("#cancelbutton").click(function() {
			/**  关闭弹出iframe  **/
			window.parent.$.fancybox.close();
		});
		
		var result = 'null';
		if(result =='success'){
			/**  关闭弹出iframe  **/
			window.parent.$.fancybox.close();
		}
		String mes =<%=request.getAttribute("message")%>;
	    if(!mes.equals("")){
	    	art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:mes, ok:true,});
			return false;
	    }else{
	    	art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:"提交成功", ok:true,});
	    	window.parent.$.fancybox.close();
		}
	}); --%>
	
	
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
	//取出传回来的参数error并与yes比较
	 var errori ='<%=request.getParameter("error")%>';
	  if(errori=='yes'){
	   alert("该歌手已经存在，请重新添加");
	  }
</script>
</head>
<body>
<form id="submitForm" name="submitForm" action="<%=path %>/SingerAddServlet" method="post"  onSubmit="return validateForm()" >
	<input type="hidden" name="fyID" value="14458625716623" id="fyID"/>
	<div id="container">
		<!-- <div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">新增歌手</span>
			<div id="page_close">
				<a href="javascript:parent.$.fancybox.close();">
					<img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div> -->
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				
				
				
				<tr>
					<td class="ui_text_rt">歌手名称</td>
					<td class="ui_text_lt">
						<input type="text" id="singerName" name="singerName"  class="ui_input_txt02"/>
					</td>
				</tr>
				<!-- <tr>
				<td class="ui_text_rt">上传照片</td>
					<td class="ui_text_lt">
						<input type="file"  id="singerImg" name="singerImg">
					</td>
				</tr> -->
				<tr>
					<td class="ui_text_rt">歌手简介</td>
					<td class="ui_text_lt">
						
						<textarea id="singerInfo" name="singerInfo"  class="ui_input_txt02" clos="20" rows="5"style="width:200px;height:70px;"></textarea>
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