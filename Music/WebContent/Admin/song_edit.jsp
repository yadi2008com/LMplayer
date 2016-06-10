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
<script type="text/javascript" src="<%=projectUrl%>Admin/scripts/jquery/jquery.form.js"></script>
<script type="text/javascript">
	$.ajax({
		url:'<%=path %>/TypeListServlet',
		type:'post',
		dataType:'json',
		success:function(data){
			if(null!=data && ""!=data){
				console.log(data);
				$.each(data,function(num,item){
					$("#songType").append("<option value="+item.typeId+">"+item.typeName+"</option>");
				});
			}
		}
		
	});
	$.ajax({
		url:'<%=path %>/SingerListJsonServlet',
		type:'post',
		dataType:'json',
		success:function(data){
			if(null!=data && ""!=data){
				var singers=data.singersBeanInfoList;
				console.log(singers);
				$.each(singers,function(num,item){
						$("#songers").append("<option value="+item.singerId+">"+item.singerName+"</option>");
				});
			}
		}
		});
		
		
	/** 表单验证  **/
	function validateForm(){
		if($("#songType").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写歌曲类型', ok:true,});
			return false;
		}
		if($("#songers").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写歌手名称', ok:true,});
			return false;
		}
		uploanSong();
	}

/**
 * 上传歌曲附件
 */	
 function uploanSong(){
	if(confirm("确定上传该歌曲吗？")){
				// 提交前
			function showRequest(formData, jqForm, options) { 
			// formdata是数组对象,在这里，我们使用$.param()方法把他转化为字符串.
				var queryString = $.param(formData); //组装数据，插件会自动提交数据
				return true; 
			} 
			// 提交后
			function showResponse(responseText, statusText) { 
				alert('状态: ' + statusText + '\n 返回的内容是: \n' + responseText); 
			} 

		  var requestUrl='<%=path %>/SongAddServlet';
		  $("#submitForm").ajaxSubmit({
			    async:true,
				contentType: "application/x-www-form-urlencoded;charset=UTF-8",
// 				beforeSubmit: showRequest, // 提交前
			  	url:requestUrl+'?'+$('#submitForm').serialize(), //参数如此拼接是因为post方式提交时http://blog.csdn.net/mhmyqn/article/details/25561535
			  	type:'post',
		        dataType: "json",//响应信息格式
	            success: function(result) {//普通ajax提交返回信息（成功和失败）
			 		$.each(result,function(idx,item){   
			 			art.dialog({icon:result.message.success, title:'友情提示', drag:false, resize:false, content:result.message, ok:true});
				  		$("#submitForm").resetForm(); // 提交后重置表单
			 			return false;
			 		});
				},
				error:function(XmlHttpRequest,textStatus,errorThrown){//上传附件响应错误信息
					art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'上传文件失败', ok:true});
				  	$("#submitForm").resetForm(); // 提交后重置表单
				}
		  });
	}else{
		/*
		 * 取消
		 */
		$("#cancelbutton").click(function() {
			/**  关闭弹出iframe  **/
			window.parent.$.fancybox.close();
		});
	}
 }	
 /**
 *下拉框赋值
 */
//  function getFyDhListByFyXqCode(type){
// 	 qlert(type);
// 	 if(type=="songType"){
// 	 	$("#typeId").value=$("#songType").val();
// 	 }else{
// 	 	$("#songerId").value=$("#songers").val();
// 	 }
//  }
 
 function selectMusic(){
	
	 $('#bg_music').append('<embed id="m_bg_music"  loop=true  volume="60" autostart=true hidden=true src="<%=projectUrl%>/songFile/-1.mp3" />');
     $('#bg_music_btn').click(function(){
         var state = $('#bg_music_btn').attr('state');
         if(state == '1')//
         {
             $('#bg_music_btn').attr('state','0');
             $('#bg_music_btn').html('打开背景音乐');
             $('#m_bg_music').remove();
         }else if(state == '0')
         {
             $('#bg_music_btn').attr('state','1');
             $('#m_bg_music').remove();
             $('#bg_music_btn').html('关闭背景音乐');
             $('#bg_music').append('<embed id="m_bg_music"  loop=true  volume="60" autostart=true hidden=true src="<%=projectUrl%>/songFile/-1.mp3" />');
         }
     });
 }
</script>
</head>
<body>
<iframe id="id_iframe" name="id_iframe" style="display:none;"></iframe>
<form id="submitForm" name="submitForm"  method="post" action="#"  onSubmit="return validateForm()" enctype="multipart/form-data" target="id_iframe">
	<input type="hidden" name="fyID" value="14458625716623" id="fyID"/>
	<div id="container">
		<!-- <div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">新增歌曲</span>
			<div id="page_close">
				<a href="javascript:parent.$.fancybox.close();">
					<img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div> -->
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt">歌曲名称</td>
					<td class="ui_text_lt">
						<input type="text" id="songName" name="songName"  class="ui_input_txt02"/>
					</td>
					<td class="ui_text_lt">
						<label for="file1">上传歌曲</label> <input type="file"  id="songFile" name="songFile" onchange="selectMusic()">
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="80">歌曲类型</td>
					<td class="ui_text_lt">
						<select id="songType"  name="typeId" class="ui_select01">
                        </select>
					</td>
					<!-- <td class="ui_text_lt">
						<label for="file1">上传封面</label> <input type="file"  id="songFile" name="songImg">
					</td> -->
				</tr>
				<tr>
					<td class="ui_text_rt" width="80">歌手名称</td>
					<td class="ui_text_lt">
						<select  id="songers"  name="songerId" class="ui_select01" onchange="getSongerListByFyXqCode(songerId);">
                        </select>
<!--                         <input type="hidden"  id="typeId" > -->
<!--                         <input type="hidden"  id="songerId" > -->
                        
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">金句</td>
					<td class="ui_text_lt">
						
						<textarea id="songWord" name="songWord"  class="ui_input_txt02" clos="20" rows="5"style="width:200px;height:70px;"></textarea>
					</td>
				</tr>
				
				<tr>
					<td class="ui_text_rt">点击量</td>
					<td class="ui_text_lt">
						<input type="text" id="songHeat" name="songHeat"  class="ui_input_txt02"/>
					</td>
				</tr>
				<!-- <tr>
					<td class="ui_text_rt">专辑名称</td>
					<td class="ui_text_lt">
						<input type="text" id="songAlbum" name="songAlbum"  class="ui_input_txt02"/>
					</td>
				</tr> -->
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
<div id="bg_music"></div>	
<div id="bg_music_btn" state='1'>关闭背景音乐</div>

</body>
</html>