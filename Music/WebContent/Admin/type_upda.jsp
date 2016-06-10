<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+
   request.getServerName()+":"+request.getServerPort();
   String projectUrl=basePath+path+"/";
%>
<%@page import="com.nuc.bean.TypeBean"%>
<%@ page language="java" import="java.util.*"%>
<%
TypeBean typeBean=(TypeBean)request.getAttribute("typeBean");
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
	   alert("该类型已经存在，请重新更新");
	 }
	 
	 //动态指定option值
	 <%-- var ops=document.getElementById("typeId");
	 mySel(ops);
	 function mySel(ops){
        //var ops=document.getElementById("typeId");
        var v='<%=typeBean.getTypeParentId()%>'
        for(var i=0;i<ops.options.length;i++){
            if(ops.options[i].value==v){
               ops.options[i].selected="selected";
                break;
            }
        }

    } --%>
    <%-- var index='<%=typeBean.getTypeParentId()%>' --%>
   // $(#typeId).get(0).selectedIndex=index;
  
   // $('#typeId option:eq(index)').attr('selected','selected');
    $(document).ready(function(){
    	 var opts = document.getElementById("typeParentId");
         var value ='test';//这个值就是你获取的值;
         var selectId='<%=typeBean.getTypeParentId()%>';
         //alert(opts.options[selectId].value);
         opts.options[selectId].selected = 'selected';
         console.log(selectId);
	});
</script>
</head>
<body>
<form id="submitForm" name="submitForm" action="<%=path %>/TypeUpdServlet" method="post"  onSubmit="return validateForm()" >
	<input type="hidden" name="fyID" value="14458625716623" id="fyID"/>
	<div id="container">
		<!-- <div class="ui_content"> -->
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="80">歌曲类型</td>
					<td class="ui_text_lt">
						<input type="text" name="typeId" value="<%=typeBean.getTypeId() %>"  style="display:none;"/>
						<select name="typeParentId" id="typeParentId" class="ui_select01">
                            <option value="">--请选择--</option>
                            <option value="1">语种</option>
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
						<input type="text" id="typeName" name="typeName"  class="ui_input_txt02" value="<%=typeBean.getTypeName()%>"/>
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
	<!-- </div> -->
</form>

</body>
</html>