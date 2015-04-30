<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理平台-添加用户</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-jvert-tabs-1.1.4.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/easyui/themes/default/easyui.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/easyui/themes/icon.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/search.css" />

<link href="${pageContext.request.contextPath}/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/lib/ligerUI/skins/Gray/css/dialog.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/css/ui-lightness/jquery-ui-1.8.23.custom.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/common/css/global.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/jquery/jquery-ui-1.8.23.custom.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/lib/ligerUI/${pageContext.request.contextPath}/js/ligerui.all.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/jquery/TC.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/lib/easyui/jquery.easyui.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/lib/jquery-jvert-tabs-1.1.4.js"	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/jquery.validate.min.js"	type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/jquery.metadata.js"	type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/messages_cn.js"	type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/common/js/common.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/js/admin/adduser.js" type="text/javascript"></script>
<style type="text/css">
.txt_300 {
	margin-bottom: 2px;
	margin-top: 2px;
	width: 300px;
}

.txt_150 {
	margin-bottom: 2px;
	margin-top: 2px;
	width: 150px;
}

.txt_margin {
	margin-bottom: 2px;
	margin-top: 2px;
	margin-left: 2px;
	width: 120px;
}

.th_ {
	font-weight: bold;
	text-align: center;
}

.tip {
	color: #a2a2a2;
}

input.error, select.error { border: 1px solid red; }

label.error {
	background:url("${pageContext.request.contextPath}/images/unchecked.gif") no-repeat 0px 0px;
	padding-left: 16px;
	padding-bottom: 2px;
	font-weight: bold;
	color: #EA5200;
}
</style>
<script type="text/javascript">
	var basePath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<form id="userForm">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="tabg">
			<tr class="tab">
				<td class="td1" width="20%"><font color=red>*</font>用户名:</td>
				<td class="td2" width="18%">
					<input name="userVo.userName" type="text" id="userName" class="l-text txt_150" />
				</td>
				<td class="td1" width="20%"><font color=red>*</font>性别:</td>
				<td class="td2" width="30%">
					<input name="userVo.sexy" type="radio" id="male" value="1" checked/>男&nbsp;
					<input name="userVo.sexy" type="radio" id="female" value="2"/>女
				</td>
			</tr>
			<tr class="tab">
				<td class="td1" width="20%"><font color=red>*</font>密码:</td>
				<td class="td2" width="18%">
					<input name="userVo.userPass" type="password" id="userPass" class="l-text txt_150" />
				</td>
				<td class="td1" width="20%"><font color=red>*</font>密码确认:</td>
				<td class="td2" width="30%">
					<input name="rePassword" type="password" id="rePassword" class="l-text txt_150" />
				</td>
			</tr>
			<tr class="tab">
				<td class="td1" width="20%"><font color=red>*</font>联系电话:</td>
				<td class="td2" width="18%"><input name="userVo.phone" type="text" id="phone" class="l-text txt_150" /></td>
				<td class="td1" width="20%"><font color=red>*</font>电子邮件:</td>
				<td class="td2" width="30%"><input name="userVo.email" type="text" id="email" class="l-text txt_150" /></td>
			</tr>
			<!-- 
			<tr class="tab">
				<td class="td1" width="20%"><font color=red>*</font>用户角色:</td>
				<td class="td2" width="18%">
					<select id="role" class="l-text txt_150">
						<option value="0">请选择</option>
						<option value="1">普通会员</option>
						<option value="2">版主</option>
						<option value="3">管理员</option>
					</select>
				</td>
				<td class="td1" width="20%">版块:</td>
				<td class="td2" width="30%">
					<select id="category" class="l-text txt_150" disabled>
						<option value="0">请选择</option>
						<option value="1">计算机科学与技术专业</option>
						<option value="11">&nbsp;&nbsp;&nbsp;|-软件工程</option>
						<option value="12">&nbsp;&nbsp;&nbsp;|-网络工程与应用</option>
						<option value="2">汉语言专业</option>
						<option value="21">&nbsp;&nbsp;&nbsp;|-汉语文化</option>
						<option value="22">&nbsp;&nbsp;&nbsp;|-汉语研究</option>
					</select>&nbsp;
					<span class="tip">（版主角色所在的论坛版块）</span>
				</td>
			</tr>
			 -->
		</table>
	</form>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr height="10px"></tr>
		<tr>
			<td width="40%"></td>
			<td width="20%">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="right">
							<div class="l-cuz-btn_" id="save">
								<div class="l-cuz-btn-l_"></div>
								<div class="l-cuz-btn-r_"></div>
								<div class="l-cuz-btn-inner_">保存</div>
							</div>
						</td>
						<td width="5%"></td>
						<td align="left">
							<div class="l-cuz-btn_" id="cancel">
								<div class="l-cuz-btn-l_"></div>
								<div class="l-cuz-btn-r_"></div>
								<div class="l-cuz-btn-inner_">取消</div>
							</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="40%"></td>
		</tr>
	</table>
</body>
</html>