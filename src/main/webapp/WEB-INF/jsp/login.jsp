<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>课程论坛毕业设计-会员登录</title>
    <meta name="keywords" content=""/>
    <link rel="SHORTCUT ICON" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css"/>
</head>
<body>
<s:include value="header.jsp"></s:include>
<div class="mainbox">
    <div class="nav">
        <img src="${pageContext.request.contextPath}/images/forum_readme.gif"/>
        <a href="${pageContext.request.contextPath}/IndexAction!index.action">课程论坛首页</a> >> 会员登录
    </div>
    <div class="page_right" style="margin:0px 0px 0px 0px; width:270px; border:0px;">
        <div class="leftcolor" style=" background:#F4FCEE;"><br>
            <span class="font14 username">还不是本站用户？</span>
            <span class="username">那还等什么？赶紧注册吧！</span>
			<span><button onmousemove="this.className='button_on';" onmouseout="this.className='button'"
                          onclick="window.location.href='${pageContext.request.contextPath}/regist_page.jspx';">
                立即注册成为本站会员
            </button></span><br><br>
        </div>
    </div>
    <ul class="tableborder heightd page_left2">
        <h3 class="titlebig">用户登录</h3>

        <form method="post" action="${pageContext.request.contextPath}/IndexAction!login.action">
            <li>
                <p class="font14"></p>
                <s:if test="tip != null">
                    <div style="color:red;">${tip}</div>
                </s:if>
            </li>
            <li>
                <p class="font14">用户名</p>
                <input class="TextBox" name="userVo.userName" id="username" type="text"/>
            </li>
            <li>
                <p class="font14">密&nbsp;&nbsp;&nbsp;码</p>
                <input class="TextBox" name="userVo.userPass" id="password" type="password"/>
            </li>
            <li>
                <p style="clear: both;"></p>
                <input type="submit" name="submit" value="立即登录" class="input0"/>
                <input type="hidden" name="returnUrl" value="${requestScope.returnUrl}"/>
            </li>
            <li></li>
        </form>
    </ul>
</div>
<s:include value="footer.jsp"></s:include>
</body>
</html>