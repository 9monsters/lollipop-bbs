<%@ page language="java" import="java.util.List,com.just.lollipop.bbs.vo.UserVo" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>课程论坛毕业设计</title>
<meta name="keywords" content="" />
<link rel="SHORTCUT ICON" href="favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.8.2.min.js"></script>
<style>

</style>
</head>
<body>
	<s:include value="header.jsp"></s:include>
	<!--公告和版面列表-->
	<div class="mainbox">
		<div class="nav">
			<div style="float:left;">
				<img src="${pageContext.request.contextPath}/images/forum_readme.gif" />
				<a href="${pageContext.request.contextPath}/IndexAction!index.action">课程论坛首页</a> >> 版块列表
			</div>
			<div class="notice">
				<form id="searchForm" action="${pageContext.request.contextPath}/IndexAction!search.action" 
					method="post" onsubmit="return check();" target="_blank">
					<input type="text" id="keyword" name="keyword" size="35" />
					<input type="submit" value=" 搜 索 " />
				</form>
			</div>
		</div>
		<div id="Tab">
			<div class="Menubox">
			<ul>
				<li class="hover">子版面</li>			
			</ul>
			</div>		
			<div class="Contentbox">  
				<!--论坛一级版面开始-->
				<div id="con_two_1" >
					<div class="bbs_column1">
						<ul style="border:none;">		
							<table id="board_31" cellpadding="0" cellspacing="0">
								<s:iterator value="boards" status="bo">
									<s:if test="#bo.index % 4 == 0"><tr class="bbsboxout"></s:if>
										<td style="width:60px" >
											<div class="today"><s:property value='%{todayOfTiezi}'/><p>today</p></div>
										</td>
										<td width="25%">
											<strong><a href="${pageContext.request.contextPath}/IndexAction!forwardDetailPage.action?boardVo.id=${id}" class="newlink"><s:property value='%{name}'/></a></strong>
											<span>版主：<a href="${pageContext.request.contextPath}/IndexAction!forwardUserPage.action?userVo.id=${moderator.id}" target="_blank"><s:property value='%{moderator.userName}'/></a>&nbsp;&nbsp;<br />主题：<em><s:property value='%{totalOfTheme}'/></em>   |  帖子：<em><s:property value='%{totalOfTiezi}'/></em></span>
										</td>
									<s:if test="(#bo.index + 1) % 4 == 0"></tr></s:if>
								</s:iterator>
							</table>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<s:include value="footer.jsp"></s:include>
	<script type="text/javascript">
		function check(){
			var keyword = $.trim($("#keyword").val());
			if (keyword == ""){
				alert("请输入搜索关键字.");
				return false;
			}
			return true;
		}
	</script>
</body>	
</html>