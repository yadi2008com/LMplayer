<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+
   request.getServerName()+":"+request.getServerPort();
   String projectUrl=basePath+path+"/";
%>
<!DOCTYPE html>
<html>
<title>LOVE MUSIC 音乐播放器</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Ethos Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="<%=projectUrl%>Css/login.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<link href='http://fonts.googleapis.com/css?family=Nixie+One' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<!--//webfonts-->
<script language="JavaScript">
	function mycheck() {
		if (isNull(registerForm.username.value)) {
			alert("请输入用户名！");
			return false;
		}
		if (isNull(registerForm.userpwd.value)) {
			alert("请输入密码！");
			return false;
		}
		if (isNull(registerForm.userpwdTwo.value)) {
			alert("请输入确认密码！");
			return false;
		}
		/* if ((registerForm.userpwd.value).equals(registerForm.userpwdTwo.value)) {
			alert("俩次密码不一致，请重新输入！");
			return false;
		}
		 */
 		if ((registerForm.userpwd.value)!=(registerForm.userpwdTwo.value)) {
		alert("俩次密码不一致，请重新输入！");
		return false;
		}
	}

	function isNull(str) {
		if (str == "")
			return true;
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		return re.test(str);
	}
</script>
</head>
<body>
	 <div class="main">
		<div class="login">
			<h1>LOVE MUSIC</h1>
			<div class="inset">
				<!--start-main-->
					<form action="<%=request.getContextPath()%>/registerServlet" method="post" name="registerForm"  onSubmit="return mycheck()">
			         <div>
			         	<h2>Register Form</h2>
			         	 <%=request.getAttribute("message") != null ? request
					.getAttribute("message") : ""%> 
						<span><label>用户名</label></span>
						<span><input type="text" class="textbox" id="active" name="username"></span>
					 </div>
					 <div>
						<span><label>密码</label></span>
					    <span><input type="password" class="password" name="userpwd"></span>
					 </div>
					 <div>
						<span><label>确认密码</label></span>
					    <span><input type="password" class="password"  name="userpwdTwo"></span>
					 </div>
					<div class="sign">
						<div class="">
							<input type="submit" value="注册">   
						</div>
						
					</div>
					<a href="<%=projectUrl%>Admin/login.jsp" style="color:#0091E6">已有账号登录</a>
					</form>

				</div>
			</div>
		<!--//end-main-->
		</div>
<!--start-copyright-->
<div class="copy-right">
	<p>&copy; 2016 陈雅迪 毕业设计 LOVE MUSIC 音乐播放器 </p>

</div>
<!--//end-copyright-->
</body>
</html>