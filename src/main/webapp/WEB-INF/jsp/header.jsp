<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>论坛程序设计</title>
</head>
<body>
<div class="header">
    <table width="100%">
        <tr>
            <td width="200" style="padding-top:16px">
                <img src="${pageContext.request.contextPath}/images/logo.png"/>
            </td>
            <td align="right">
                <s:if test="#session.user != null">
                    <div id="user_nav" style="width:400px;">
                        欢迎 ${sessionScope.user.userName}&nbsp;|&nbsp;
                        <a href="#" onmouseover="openMenu(this)">控制面板</a>&nbsp;|&nbsp;
                        <div class="quick_menu" style="width:70px;display:none;" onmouseout="closeMyApp(this)">
                            <a href="${pageContext.request.contextPath}/IndexAction!forwardMyInfoPage.action"
                               title="查看我的博客首页">个人信息</a>
                            <a href="${pageContext.request.contextPath}/IndexAction!forwardMyBlogPage.action"
                               title="管理我的博客">我的博客</a>
                            <a href="${pageContext.request.contextPath}/mypass_page.jspx" title="修改密码">修改密码</a>
                            <s:if test="#session.user.role > 1">
                                <a href="${pageContext.request.contextPath}/admin/index_page.jspx"
                                   title="管理我的博客">后台管理</a>
                            </s:if>
                        </div>
                        <a href="javascript:logout();" class="nobg">退出</a>
                    </div>
                </s:if>
                <s:else>
                    欢迎您：游客！请先 <a href="${pageContext.request.contextPath}/login_page.jspx"
                                 style="font-weight:bold">登录</a> 或 <a
                        href="${pageContext.request.contextPath}/regist_page.jspx" style="font-weight:bold">注册</a>
                </s:else>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    function openMenu(self) {
        var left = $(self).offset().left;
        var top = $(self).offset().top + 22;
        $(self).next().css("left", left).css("top", top).show();
    }

    function closeMyApp(obj) {
        var mouseX = getX(getEvent());
        var left = obj.style.left;
        left = parseInt(left.substr(0, left.length - 2));
        if (mouseX < left || mouseX > left + 70) {
            $(obj).hide();
        }
    }

    function getEvent() {
        if (document.all) {
            return window.event;
        }
        func = getEvent.caller;
        while (func != null) {
            var arg0 = func.arguments[0];
            if (arg0) {
                if ((arg0.constructor == Event || arg0.constructor == MouseEvent) || (typeof(arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
                    return arg0;
                }
            }
            func = func.caller;
        }
        return null;
    }

    function getX(e) {
        e = e || window.event;
        return e.x || e.clientX - 30;
    }

    function logout() {
        var url = "${pageContext.request.contextPath}/IndexAjaxAction!logout.action";
        $.post(url, function (data) {
            var result = $.trim(data);
            if (result == 'ok') {
                alert("注销成功.");
                window.location.reload();
            } else {
                alert(data);
            }
        });
    }
</script>
</body>
</html>