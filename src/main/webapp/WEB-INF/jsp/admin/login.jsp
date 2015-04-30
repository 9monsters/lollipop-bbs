<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.8.2.min.js"
	type="text/javascript" ></script>
<title>论坛管理平台登陆</title>
<style type="text/css">
	body {
		font-size: 14px;
		padding: 120px;
	}

	table {
		width: 500px;
		background: #ccc;
	}

	table td {
		background: #fff;
	}

	._input {
		width: 200px;
		height: 25px;
		line-height: 25px;
		border: 1px solid #ccc;
	}

	._button {
		width: 80px;
		height: 25px;
		line-height: 25px;
		border: 1px solid #ccc;
	}
</style>
</head>
<body>
	<center>
		<h1>论坛管理平台</h1>
		<form id="loginForm">
			<table border="0" cellspacing="1" cellpadding="10">
				<tr>
					<td width="30%" align="right">用户名</td>
					<td width="70%" align="left"><input type="text" id="username" 
						name="userVo.userName" class="_input"></td>
				</tr>
				<tr>
					<td align="right">密&nbsp;&nbsp;码</td>
					<td align="left"><input type="password" id="password" 
						name="userVo.userPass" class="_input"></td>
				</tr>
			</table>
			<div style="margin:20px;">
				<div style="margin:15px; ">
					测试账号：admin 123456 （<font color=red>管理员</font>）|&nbsp;&nbsp;lisa 123456 （<font color=green>版主</font>）
				</div>
				<div style="margin:15px; ">
					<a href="${pageContext.request.contextPath}/">>>> 进入网站首页</a>
				</div>
				<input type="button" id="login" value="登录" class="_button" />
				<input type="reset" value="重置" class="_button" />
			</div>
		</form>
	</center>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#username").focus();
			
			$("#login").click(function(){
				var url = "${pageContext.request.contextPath}/admin/IndexAction!login.action";
				var params = $("#loginForm").serialize();
				$.post(url, params, function(data){
					if (data.result == 'ok'){
						alert("登录成功.");
						window.location.href = "${pageContext.request.contextPath}/admin/index_page.jspx";
					}else{
						alert(data.result);
					}
				}, "json");
			});
		});
	</script>
</body>
</html>