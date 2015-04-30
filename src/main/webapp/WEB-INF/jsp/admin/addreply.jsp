<%@ page language="java" import="org.springframework.web.context.WebApplicationContext,com.just.lollipop.bbs.dao.ArticleDao,com.just.lollipop.bbs.domain.Article" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
	String articleId = request.getParameter("articleId");
	WebApplicationContext wac = (WebApplicationContext) 
		config.getServletContext().getAttribute(WebApplicationContext.
      		ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	ArticleDao articleDao = (ArticleDao) wac.getBean("articleDao");
	Article article = articleDao.getArticle(Integer.parseInt(articleId));
	request.setAttribute("title", article.getTitle());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理平台-添加文章</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-jvert-tabs-1.1.4.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/easyui/themes/default/easyui.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/easyui/themes/icon.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/search_.css" />

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

<script src="${pageContext.request.contextPath}/lib/kindeditor/kindeditor-min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/lib/kindeditor/lang/zh_CN.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/js/admin/addreply.js"	type="text/javascript"></script>
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

input.error, select.error { border: 1px solid red; }

label.error {
	background:url("images/unchecked.gif") no-repeat 0px 0px;
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
	<form id="replyForm">
		<input type="hidden" id="articleId" name="articleVo.themeArticle.id" 
			value="${param.articleId}" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="tabg">
			<tr class="tab">
				<td class="td1" width="20%"><font color=red>*</font>标题:</td>
				<td class="td2" width="80%">
					<span id="title"><s:property value="#request.title" /></span>
				</td>
			</tr>
			<tr class="tab">
				<td class="td1" width="20%"><font color=red>*</font>回复内容:</td>
				<td class="td2" width="80%">
					<textarea id="content" name="content" style="width: 100%; height: 200px; visibility: hidden;"></textarea>
				</td>
			</tr>
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