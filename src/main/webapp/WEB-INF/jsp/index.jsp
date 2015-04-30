<%@ page language="java" import="com.just.lollipop.bbs.vo.UserVo,java.util.List" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    List<UserVo> onlineUserList = (List<UserVo>) application.getAttribute("onlineUserList");
    request.setAttribute("totalOnline", onlineUserList.size());
    request.setAttribute("onlineUserList", onlineUserList);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>论坛程序设计</title>
    <meta name="keywords" content=""/>
    <link rel="SHORTCUT ICON" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.8.2.min.js"></script>
    <style>

    </style>
</head>
<body>
<s:include value="header.jsp"></s:include>
<!--公告和版面列表-->
<div class="mainbox">
    <div class="rollboder">
        <ul>
            <li id="scrollDiv" class="rollleft">
                <h3 style="float:left; padding-left:5px;">公告栏：</h3>
                <ul>
                    <li><a href="javascript:void(-1);">233333333333333333333333333333</a></li>
                </ul>
            </li>
        </ul>
        <div class="notice">
            <form id="searchForm" action="${pageContext.request.contextPath}/IndexAction!search.action"
                  method="post" onsubmit="return check();" target="_blank">
                <input type="text" id="keyword" name="keyword" size="35"/>
                <input type="submit" value=" 搜 索 "/>
            </form>
        </div>
    </div>
    <div id="main">
        <div class="bbs_column0">
            <h2>分论坛显示</h2>
            <ul>
                <!--版面列表模式-->
                <table id="board_1" style="display:;" cellpadding="0" cellspacing="0">
                    <s:iterator value="columns" var="col">
                        <tr class="bbsboxout">
                            <td width="60">
                                <div class="today" title="今日帖子数"><s:property value="#col.todayOfTiezi"/><p>today</p>
                                </div>
                            </td>
                            <td>
                                <strong><a
                                        href="${pageContext.request.contextPath}/IndexAction!forwardBoardPage.action?columnVo.id=${col.id}"
                                        class="newlink"><s:property value="#col.name"/></a></strong><br/>
                                <img src="${pageContext.request.contextPath}/images/forum_readme.gif" alt=""
                                     border="0"/><s:property value="#col.remark"/><br/>
                                <span>主题：<em><s:property value="#col.totalOfTheme"/></em>   |  帖子：<em><s:property
                                        value="#col.totalOfTiezi"/></em></span></td>
                            <td width="1%" style="text-align:right"><a href="head_list.html?boardid=5"></a></td>
                        </tr>
                    </s:iterator>
                </table>
            </ul>
        </div>
    </div>
    <!--Div page_left End-->
</div>
<div class="mainbox">
    <div class="Menubox">
        <ul>
            <li class="hover">当前在线<span>(${totalOnline}人)</span></li>
        </ul>
    </div>
    <div class="Contentbox">
        <div class="count" style="cursor:pointer;">
            <ul>
                <li>
                    <s:iterator value="#request.onlineUserList" var="user">
                        <s:if test="%{#user.role == 1}">
                            <img src="${pageContext.request.contextPath}/images/usergroup_3.gif"/>
                        </s:if>
                        <s:elseif test="%{#user.role == 2}">
                            <img src="${pageContext.request.contextPath}/images/usergroup_1.gif"/>
                        </s:elseif>
                        <s:elseif test="%{#user.role == 3}">
                            <img src="${pageContext.request.contextPath}/images/usergroup_0.gif"/>
                        </s:elseif>
                        <a href="${pageContext.request.contextPath}/IndexAction!forwardUserPage.action?userVo.id=${user.id}"
                           target="_blank">${user.userName}</a>&nbsp;&nbsp;&nbsp;
                    </s:iterator>
                </li>
                <li>
                    (
                    <img src="${pageContext.request.contextPath}/images/usergroup_0.gif" alt="管理员"/> 管理员&nbsp;&nbsp;||&nbsp;&nbsp;
                    <img src="${pageContext.request.contextPath}/images/usergroup_1.gif" alt="版主"/> 版主&nbsp;&nbsp;||&nbsp;&nbsp;
                    <img src="${pageContext.request.contextPath}/images/usergroup_3.gif" alt="注册会员"/> 注册会员)
                </li>
            </ul>
        </div>
    </div>
</div>
<s:include value="footer.jsp"></s:include>
<script type="text/javascript">
    var time = 3000;
    $(document).ready(function () {
        var myar = setInterval('AutoScroll("#scrollDiv")', time)
        $("#scrollDiv").hover(function () {
            clearInterval(myar);
        }, function () {
            myar = setInterval('AutoScroll("#scrollDiv")', time)
        });
    });

    function AutoScroll(obj) {
        $(obj).find("ul:first").animate({
            marginTop: "-25px"
        }, 500, function () {
            $(this).css({marginTop: "0px"}).find("li:first").appendTo(this);
        });
    }

    function check() {
        var keyword = $.trim($("#keyword").val());
        if (keyword == "") {
            alert("请输入搜索关键字.");
            return false;
        }
        return true;
    }
</script>
</body>
</html>