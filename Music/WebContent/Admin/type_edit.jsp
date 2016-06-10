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
		if($("#typeId").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写歌曲类型', ok:true,});
			return false;
		}
		if($("#typeName").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写类型名称', ok:true,});
			return false;
		}
		return true;
	}
	//取出传回来的参数error并与yes比较
	  var errori ='<%=request.getParameter("error")%>';
	  if(errori=='yes'){
	   alert("该类型已经存在，请重新添加");
	  }
</script>
</head>
<body>
<form id="submitForm" name="submitForm" action="<%=path %>/TypeServlet" method="post"  onSubmit="return validateForm()" >
	<input type="hidden" name="fyID" value="14458625716623" id="fyID"/>
	<div id="container">
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="80">歌曲类型</td>
					<td class="ui_text_lt">
						<select name="typeId" id="typeId" class="ui_select01" onchange="getFyDhListByFyXqCode();">
                            <option value="">--请选择--</option>
                            <option value="1" selected="selected">语种</option>
                            <option value="2">流派</option>
                            <option value="3">主题</option>
                            <option value="4">心情</option>
                            <option value="5">场景</option>
                        </select>
					</td>
				</tr>
				
				
				<tr>
					<td class="ui_text_rt">类型名称</td>
					<td class="ui_text_lt">
						<input type="text" id="typeName" name="typeName"  class="ui_input_txt02"/>
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