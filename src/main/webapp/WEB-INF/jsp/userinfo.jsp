<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" dir="ltr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>${userVo.userName}的个人博客</title>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <link rel="shortcut icon" href="" type="image/x-icon"/>
    <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/theme/blue.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.8.2.min.js"></script>
</head>
<body>
<div id="header">
    <div id="user_nav">
        <s:if test="#session.user != null">
            <a href="${pageContext.request.contextPath}/IndexAction!forwardMyInfoPage.action" title="查看我的博客首页"
               class="welcome">欢迎${sessionScope.user.userName}</a>
            <a href="javascript:logout();" class="nobg">退出</a>
        </s:if>
        <s:else>
            <span style="color:black;">欢迎您：游客！请先 <a href="${pageContext.request.contextPath}/login_page.jspx"
                                                    style="font-weight:bold">登录</a> 或 <a
                    href="${pageContext.request.contextPath}/regist_page.jspx" style="font-weight:bold">注册</a></span>
        </s:else>
    </div>
</div>
<div id="page">
    <div id="branding" class="clearfix">
        <div id="blog_name">
            <h1><a href="head_my_info.html">${userVo.userName}的个人博客</a></h1>
        </div>
        <div id='fd'></div>
        <div id="blog_navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/">首页</a></li>
                <li>
                    <a href="${pageContext.request.contextPath}/IndexAction!forwardUserBlogPage.action?userVo.id=${userVo.id}">博客</a>
                </li>
                <li class='blog_navbar_for'><a
                        href="${pageContext.request.contextPath}/IndexAction!forwardUserPage.action?userVo.id=${userVo.id}"><strong>关于我</strong></a>
                </li>
            </ul>
        </div>
    </div>
    <div id="content" class="clearfix">
        <div id="main">
            <div class="blog_main_title">
                <span>关于我</span>

                <div id="fd"></div>
            </div>
            <div class="blog_main">
                <table width="100%" cellSpacing="2" cellPadding="2">
                    <tr>
                        <th width="25%">昵称</th>
                        <td>${userVo.userName}</td>
                    </tr>
                    <tr>
                        <th>加入时间</th>
                        <td><s:date name="userVo.registTime" format="yyyy-MM-dd"/></td>
                    </tr>
                    <tr>
                        <th class="material_classify_bottom">性别</th>
                        <td class="material_classify_bottom">
                            <s:if test="userVo.sexy == 1">
                                <img title="男" src="${pageContext.request.contextPath}/images/male.gif">
                            </s:if>
                            <s:else>
                                <img title="女" src="${pageContext.request.contextPath}/images/female.gif">
                            </s:else>
                        </td>
                    </tr>
                    <tr>
                        <th class="material_classify_top">论坛文章</th>
                        <td class="material_classify_top">${totalOfArticle}</td>
                    </tr>
                </table>
            </div>
        </div>
        <!-- 左边栏目 -->
        <div id="local">
            <div class="local_top"></div>
            <div id="blog_actions">
                <ul>
                    <li>用户: ${userVo.userName}</li>
                    <li>性别: <s:if test="userVo.sexy == 1">
                        <img title="男" src="${pageContext.request.contextPath}/images/male.gif">
                    </s:if>
                        <s:else>
                            <img title="女" src="${pageContext.request.contextPath}/images/female.gif">
                        </s:else></li>
                </ul>
            </div>
            <div class="local_bottom"></div>
        </div>
    </div>

    <div id="footer" class="clearfix">
        <div id="copyright">
            <hr/>
            <s:include value="footer.jsp"></s:include>
        </div>
    </div>

    <script type="text/javascript">
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
</div>
</body>
</html>