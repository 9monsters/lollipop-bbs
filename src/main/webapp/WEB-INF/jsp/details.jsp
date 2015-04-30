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
</script>
</head>
<body>
	<s:include value="header.jsp"></s:include>
	<div class="mainbox">
		<div class="nav">
			<div style="float:left;">
				<img src="${pageContext.request.contextPath}/images/forum_readme.gif" />
				<a href="${pageContext.request.contextPath}/IndexAction!index.action">课程论坛首页</a>&nbsp;>>&nbsp;<a href="${pageContext.request.contextPath}/IndexAction!forwardBoardPage.action?columnVo.id=${boardVo.column.id}">${boardVo.name}</a>&nbsp;>>&nbsp;帖子列表
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
					<a href="${pageContext.request.contextPath}/addarticle_page.jspx?boardId=${boardVo.id}" target="_blank"><img src="${pageContext.request.contextPath}/images/post/post.gif" /></a>&nbsp;
				</td>
			</tr>
		</table><br/>
		<form name="batch" action="" method="post">
			<TABLE class="mainbox tableborder" cellSpacing=0 cellPadding=0 style="width:100%">
				<TR>
					<TH style="TEXT-ALIGN: center">
						状态</A>
					</TH>
					<TH style="TEXT-ALIGN: center">主题</TH>
					<TH style="TEXT-ALIGN: center">
						作者</A>
					</TH>
					<TH style="TEXT-ALIGN: center">
						创建时间</A>
					</TH>
					<TH style="TEXT-ALIGN: center">
						回复</A>
					</TH>
					<TH style="TEXT-ALIGN: center">
						最后更新</A>
					</TH>
				</TR>
				<s:iterator value="result.items">
					<TR class=trout onmouseover="this.className='tron'" onmouseout="this.className='trout'">
						<TD class=list1>
							<A href="${pageContext.request.contextPath}/IndexAction!forwardArticlePage.action?articleVo.id=${id}">
								<IMG border=0 title="在新窗口打开帖子" src="${pageContext.request.contextPath}/images/top3.gif">
							</A>
						</TD>
						<TD class=list2>
							<SPAN>
								<s:property value="title" escape="true" />
							</SPAN>
						</TD>
						<TD class=list3>
							<A href="${pageContext.request.contextPath}/IndexAction!forwardUserPage.action?userVo.id=${createPerson.id}" target="_blank">
								<s:property value="createPerson.userName" />
							</A><BR>
						</TD>
						<TD class=list4 font10>
							<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" />
						</TD>
						<TD class="list4 font10">
							<s:property value="totalReply" />
						</TD>
						<TD class=list5>
							<s:date name="lastUpdateTime" format="yyyy-MM-dd HH:mm:ss" /><BR>
							by：<A href="${pageContext.request.contextPath}/IndexAction!forwardUserPage.action?userVo.id=${lastUpdatePerson.id}" target="_blank"><s:property value="lastUpdatePerson.userName" /></A>
						</TD>
					</TR>
				</s:iterator>
			</TABLE>
		</form>
		<div class="pager">
			第&nbsp;<span id="pageBtn"></span>&nbsp;页
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
		
		var pageUrl = "${pageContext.request.contextPath}/IndexAction!forwardDetailPage.action?boardVo.id=${result.items[0].discussionBoard.id}";
		loadPage(pageUrl);
	</script>
</body>	
</html>