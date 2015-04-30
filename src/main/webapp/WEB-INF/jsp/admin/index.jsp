<%@ page language="java" import="java.util.Date" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
	Date now = new Date();
	request.setAttribute("now", now);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>论坛管理平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/easyui/themes/default/easyui.css" />

<link href="${pageContext.request.contextPath}/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/lib/ligerUI/skins/Gray/css/dialog.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/common/css/global.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/lib/zTree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/css/outlook.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/css/mydesk.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/lib/zTree/jquery.ztree.all-3.4.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/jquery/TC.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/jquery.validate.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/jquery.metadata.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/messages_cn.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/common/js/common.js" type="text/javascript"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/index.js"></script>

<script type="text/javascript">
	var supperType = request('type');
	var basePath = "${pageContext.request.contextPath}";
</script>
</head>

<body style="overflow:hidden;" >
	<div id="layout1">
		<div position="top" title="Top">
			<img alt="" src="${pageContext.request.contextPath}/images/top4.jpg" width="100%" height="95"/>
			<div style="width:380px; position:absolute;right:0px;bottom: 20px;">
				<s:if test="#session.user != null">
					<span> 
						您好: ${sessionScope.user.userName} 
						<s:if test="#session.user.role == 2">
							(<font color=red>版主</font>)
						</s:if>
						<s:elseif test="#session.user.role == 3">
							(<font color=red>管理员</font>)
						</s:elseif>
					</span> 
					<span>
						，当前时间 : <s:date name="#request.now" format="yyyy-MM-dd" />
						[<a href="${pageContext.request.contextPath}/" title="返回网站首页">网站首页</a>&nbsp;|
						<a href="${pageContext.request.contextPath}/admin/IndexAction!logout.action" title="注销当前账号">退出登录</a>]
					</span> 
				</s:if>
			</div>
		</div>
		<div position="left" title="系统菜单" style="overflow: auto; height: 100%;">
			<div class="zTreeDemoBackground left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</div>
		<div position="center" title="">
			<div id="tab1">
				<div title="首页" style="height: 100%;overflow:scroll;padding:10px;font-size:16px;">
					温馨提示：<hr>
					<ul>
						<li>1.管理员角色具有全部的权限。它可以对用户、栏目、版块、文章进行管理；</li>
						<li>2.版主只能够对所属版块中的文章进行管理；</li>
						<li>3.栏目显示在网站首页，每个栏目下面可以有多个版块；</li>
						<li>4.一个用户可以是多个版块的版主；</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>