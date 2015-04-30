<%@ page language="java" import="java.util.List,com.just.lollipop.bbs.vo.UserVo" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程论坛毕业设计</title>
<meta name="keywords" content="" />
<link rel="SHORTCUT ICON" href="favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.8.2.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/kindeditor/kindeditor-min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/lib/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/page.js" type="text/javascript"></script>
<style>
	.page_button {
		border: 1px solid #ccc;
		font-size: 14.5px;
		margin: 2px;
	}
	.current_page_button {
		border: 1px solid #ccc;
		font-size: 14.5px;
		margin: 2px;
		background: #aaffdd;
	}
</style>
<script type="text/javascript">
	var page = "<s:property value='result.page'/>";
	var totalPage = "<s:property value='result.totalPage'/>";
	var boardId = "${articleVo.discussionBoard.id}";
	var tip = "${tip}";
	if (tip != ''){
		alert(tip);
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<!--公告和版面列表-->
	<div class="mainbox">
		<div class="nav">
			<div style="float:left;">
				<img src="${pageContext.request.contextPath}/images/forum_readme.gif" />
				<a href="${pageContext.request.contextPath}/IndexAction!index.action">课程论坛首页</a>&nbsp;>>&nbsp;
				<a href="${pageContext.request.contextPath}/IndexAction!forwardBoardPage.action?columnVo.id=${articleVo.discussionBoard.column.id}">${articleVo.discussionBoard.name}</a>&nbsp;>>&nbsp;
				<a href="${pageContext.request.contextPath}/IndexAction!forwardDetailPage.action?boardVo.id=${articleVo.discussionBoard.id}">帖子列表</a>&nbsp;>>&nbsp;
				关于毕业论文
			</div>
			<div class="notice">
				<form id="searchForm" action="${pageContext.request.contextPath}/IndexAction!search.action" 
					method="post" onsubmit="return check();" target="_blank">
					<input type="text" id="keyword" name="keyword" size="35" />
					<input type="submit" value=" 搜 索 " />
				</form>
			</div>
		</div><br/>
		<table style="width:100%; margin-bottom: -5px;" cellSpacing="0" cellPadding="0">
			<tr>
				<td style="text-align: left;">
					<a href="${pageContext.request.contextPath}/addarticle_page.jspx?boardId=${articleVo.discussionBoard.id}" target="_blank"><img src="${pageContext.request.contextPath}/images/post/post.gif" /></a>&nbsp;
					<a href="#reply"><img src="${pageContext.request.contextPath}/images/post/post_reply.gif" /></a>&nbsp;
				</td>
				<td style="text-align: right;">
					<div>		
						<div style="width: 170px; float: right;">
							<a style="margin-right: 0px; float: right;" class="page" href="${pageContext.request.contextPath}/IndexAction!forwardArticlePage.action?articleVo.id=${articleVo.id}&oper=next">下一主题 &gt;&gt;</a>
							<a style="float: right;" class="page" href="${pageContext.request.contextPath}/IndexAction!forwardArticlePage.action?articleVo.id=${articleVo.id}&oper=prev">&lt;&lt; 上一主题</a>	
						</div>
						<div style="float: right;" id="showpage_top"></div>	
					</div>
				</td>
			</tr>
		</table>
		<table class="topic border" style="width:100%; border-bottom-color: currentColor; border-bottom-width: medium; border-bottom-style: none; margin-top:10px;" cellSpacing="0" cellPadding="0">
			<tr>
				<th colSpan="5">
					</span><h1>主题：<s:property value="articleVo.title"/></h1>
				</th>
			</tr>
		</table>
		<s:iterator value="result.items" status="article">
			<table class="bbslist border" style="width:100%; table-layout: fixed; word-break: break-all;" cellSpacing="0" cellPadding="0">
				<tr>
					<td style="background: rgb(250, 253, 255); border-bottom-color: rgb(228, 231, 236); border-bottom-width: 1px; border-bottom-style: solid;" class="infoleft leftcolor" vAlign="middle">
						<span class="username">
							<s:if test="createPerson.sexy == 1">
								<img src="${pageContext.request.contextPath}/images/male.gif" />
							</s:if>
							<s:else>
								<img src="${pageContext.request.contextPath}/images/female.gif" />
							</s:else>
							<span style="width: 105px; filter: glow(color='#A6BA98',strength='2');">
								<font color="#61b713" face="Verdana">
									<b><a href="${pageContext.request.contextPath}/IndexAction!forwardUserPage.action?userVo.id=${createPerson.id}" target="_blank">${createPerson.userName}</a></b>
								</font>
							</span>
						</span>
					</td>
					<td class="usermenu">
						<em>${result.startOfPage + article.index + 1}楼</em>
					</td>
				</tr>
				<tr>
					<td class="infoleft leftcolor" vAlign="top" rowSpan="3"><br>
						<span>性别：<s:if test="article.createPerson.sexy == 1">男</s:if><s:else>女</s:else></span>
						<span>注册：<em><s:date name="%{createPerson.registTime}" format="yyyy-MM-dd HH:mm:ss" /></em></span>
					</td>
					<td style="line-height: 120%;" class="inforight" valign="top">
						<b style="line-height: 15px; font-size: 12px;">${title}</b>&nbsp;&nbsp;
						<span style="line-height: 20px;" class="font10">
							Post By：<s:date name="%{createTime}" format="yyyy-MM-dd HH:mm:ss" /> 
						</span>
						
						<br><br>
						<table cellSpacing="0" cellPadding="0">
							<tr>
								<td>
									<div style="text-indent: 24px; overflow: hidden; font-size: 9pt; word-break: break-all; word-wrap: break-word;" id="textstyle_1">
										${content}
									</div>
								</td>
							</tr>
						</table><br>
					</td>
				</tr>
				<tr>
					<td class="signed"></td>
				</tr>
				<tr>
					<td class="usermenu2">
						<span class="imp"></span>
							<span style="padding: 5px;">
								<span style="display: inline;" class="m_li_top">
								<s:if test="#session.user.id == createPerson.id">
									<a href="${pageContext.request.contextPath}/IndexAction!forwardEditArticlePage.action?articleVo.id=${id}" target="_blank">编辑</a>
									&nbsp;|&nbsp; 
									<s:if test="isThemeArticle == 1">
										<a href="javascript:deleteThemeArticle(${discussionBoard.id}, ${id});">删除</a>
									</s:if>
									<s:else>
										<a href="javascript:deleteReply(${id});">删除</a>
									</s:else>
									&nbsp;|&nbsp; 
								</s:if>
								<a href="#top" title="返回页面顶部"><img style="padding-bottom: 10px;" alt="回到顶部" src="${pageContext.request.contextPath}/images/skins/p_up.gif"></a>
							</span>
						</span>
					</td>
				</tr>
			</table>
		</s:iterator>
		<!-- 分页 -->
		<div class="pager">
			第&nbsp;<span id="pageBtn"></span>&nbsp;页
		</div>
		<!-- 回复 -->
		<table class="topic border" style="width:100%; border-bottom-color: currentColor; border-bottom-width: 0px; border-bottom-style: none;" cellSpacing="0" cellPadding="0">
			<tr>
				<th>
					<span style="float: right;">
						<a href="${pageContext.request.contextPath}/IndexAction!forwardDetailPage.action?boardVo.id=${articleVo.discussionBoard.id}"><b>返回版面帖子列表</b></a>
					</span>
					<h1>${article.title}</h1>
				</th>
			</tr>
		</table>
		<table id="reply" class="tablebox border" cellspacing="0" cellpadding="0" style="width:100%;">
			<tr>
				<td style="background: rgb(250, 253, 255); padding: 10px; text-align: center;" 
					class="infoleft leftcolor" valign="top" align="center">
					请输入回复内容：
				</td>
				<td align="left">
					<s:if test="#session.user != null">
						<form id="replyForm" action="${pageContext.request.contextPath}/IndexAction!addReply.action" method="post"
							onsubmit="return validate();">
							<textarea name="content" style="width: 100%; height: 200px; visibility: hidden;"></textarea>
							<input type="hidden" id="content" name="articleVo.content" value="" />
							<input style="margin: 3px 3px 3px 0px;" id="sendBtn" class="input0" 
								value="OK!发表回复" type="button" />
							<input type="hidden" name="articleVo.themeArticle.id" value="${articleVo.id}" />
						</form>
					</s:if>
					<s:else>
						<form id="loginForm" >	
							<table cellpadding="0" cellspacing="10" style="width:300px; margin:10px;">
								<tr>
									<td align="left">
										用户名：<input class="TextBox" name="userVo.userName" id="username" type="text" />
									</td>
								</tr>	
								<tr>
									<td align="left">
										密&nbsp;&nbsp;&nbsp;码：<input class="TextBox" name="userVo.userPass" id="password" type="password" />
									</td>
								</tr>	
								<tr>
									<td align="left">
										<input style="margin: 3px 3px 3px 0px;" id="loginBtn" class="input0" 
											value=" 登 录 " type="button" />&nbsp;
										<font color=red>请先登录！</font>
									</td>
								</tr>	
							</table>
						</form>
					</s:else>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="content"]', {
				resizeType : 1,
				allowPreviewEmoticons : false,
				allowImageUpload : false,
				items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
						'bold', 'italic', 'underline', 'removeformat', '|',
						'justifyleft', 'justifycenter', 'justifyright',
						'insertorderedlist', 'insertunorderedlist', '|',
						'link' ]
			});
		});
		
		$("#loginBtn").click(function(){
			var url = "${pageContext.request.contextPath}/IndexAjaxAction!login.action";
    		var params = $("#loginForm").serialize();
    		$.post(url, params, function(data){
    			var result = $.trim(data);
    			if (result == "ok"){
    				window.location.reload();
    			}else{
    				alert(data);
    				$("#username").val("").focus();
    				$("#password").val("");
    			}
    		});
		});
		
		$("#sendBtn").click(function(){
			var content = editor.html();
			if (content == ""){
				alert("请输入内容.");
				return false;
			}
			$("#content").val(content);
			var url = "${pageContext.request.contextPath}/IndexAjaxAction!addReply.action";
			var params = $("#replyForm").serialize();
			$.post(url, params, function(data){
    			var result = $.trim(data);
    			if (result == "ok"){
    				alert("发布成功!");
    			}else{
    				alert(data);
    			}
   				window.location.reload();
    		});
		});
		
		function check(){
			var keyword = $.trim($("#keyword").val());
			if (keyword == ""){
				alert("请输入搜索关键字.");
				return false;
			}
			return true;
		}
		
		function validate(){
			var content = editor.html();
			if (content == ""){
				alert("请输入内容.");
				return false;
			}
			$("#content").val(content);
		}
		
		function deleteThemeArticle(boardId, articleId){
			if (confirm("如果你删除该主题帖子，它的所有回复也会被同时删除，您确定要继续吗？")){
				var url = "${pageContext.request.contextPath}/IndexAjaxAction!deleteArticle.action";
				$.post(url, "articleVo.id=" + articleId, function(data){
	    			var result = $.trim(data);
	    			if (result == "ok"){
	    				alert("删除成功.");
	    				window.location.href = "${pageContext.request.contextPath}/IndexAction!forwardDetailPage.action?boardVo.id=" + boardId;
	    			}else{
	    				alert(data);
	    			}
	    		});
			}
		}
		
		function deleteReply(articleId){
			if (confirm("您确定要删除该帖子吗？")){
				var url = "${pageContext.request.contextPath}/IndexAjaxAction!deleteArticle.action";
				$.post(url, "articleVo.id=" + articleId, function(data){
	    			var result = $.trim(data);
	    			if (result == "ok"){
	    				alert("删除成功.");
	    				window.location.reload();
	    			}else{
	    				alert(data);
	    			}
	    		});
			}
		}
		
		var max = 10; //max page number is 10.

		function loadPage(url){
			var i;
			if (totalPage > max){
				for (i = 1; i <= max; i++){
					if (i == page){
						$("#pageBtn").append("<a href='" + url + "&currentPage=" + i + "' class='current_page_button'>&nbsp;" + i + "&nbsp;</a>");
					}else{
						$("#pageBtn").append("<a href='" + url + "&currentPage=" + i + "' class='page_button'>&nbsp;" + i + "&nbsp;</a>");
					}
				}
			}else{
				for (i = 1; i <= totalPage; i++){
					if (i == page){
						$("#pageBtn").append("<a href='" + url + "&currentPage=" + i + "' class='current_page_button'>&nbsp;" + i + "&nbsp;</a>");
					}else{
						$("#pageBtn").append("<a href='" + url + "&currentPage=" + i + "' class='page_button'>&nbsp;" + i + "&nbsp;</a>");
					}
				}
			}	
		}
		
		var pageUrl = "${pageContext.request.contextPath}/IndexAction!forwardArticlePage.action?articleVo.id=${articleVo.id}";
		loadPage(pageUrl);
	</script>
</body>	
</html>