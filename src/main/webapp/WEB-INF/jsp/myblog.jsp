<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" dir="ltr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>个人博客</title>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon"/>
    <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/theme/blue.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>
</head>
<style>
    .input_txt {
        border-bottom: 1px solid #BDC5CA;
        border-right: 1px solid #BDC5CA;
        border-top: 1px solid #6F787E;
        border-left: 1px solid #6F787E;
        padding: 3px 2px;
        font-size: 12px;
    }

    a {
        text-decoration: none;
    }
</style>
<body>
<div id="header">
    <div id="user_nav">
        <a href="${pageContext.request.contextPath}/IndexAction!forwardMyInfoPage.action" title="查看我的博客首页"
           class="welcome">欢迎${userVo.userName}</a>
        <a href="${pageContext.request.contextPath}/IndexAction!logout.action" class="nobg">退出</a>
    </div>
</div>

<div id="page">
    <div id="branding" class="clearfix">
        <div id="blog_name">
            <h1><a href="head_my_blog.html">我的博客</a></h1>
        </div>
        <div id='fd'></div>
    </div>
    <div id="content" class="clearfix">
        <div id="main">
            <div class="blog_main_title">
                <span>我的博客文章列表</span>

                <div id="fd"></div>
            </div>
            <div class="blog_main">
                <table class="admin">
                    <thead>
                    <tr>
                        <td width="30%">标题</td>
                        <td width="30%">版块</td>
                        <td width="20%">发表时间</td>
                        <td width="10%">回复</td>
                        <td width="10%">管理</td>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="result.items">
                        <tr id="">
                            <td><a title="${title}"
                                   href="${pageContext.request.contextPath}/IndexAction!forwardArticlePage.action?articleVo.id=${id}"
                                   target="_blank">${title}</a></td>
                            <td>${discussionBoard.name}</td>
                            <td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>${totalReply}</td>
                            <td>
                                <a title="编辑"
                                   href="${pageContext.request.contextPath}/IndexAction!forwardEditArticlePage.action?articleVo.id=${id}"
                                   target="_blank">
                                    <img alt="编辑" src="${pageContext.request.contextPath}/images/btn_edit.png">
                                </a>
                                <a title="删除" onclick="deleteBlog(${id});" href="javascript:void(-1);">
                                    <img alt="删除" src="${pageContext.request.contextPath}/images/btn_delete.png">
                                </a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <div style="width: 100%; overflow: hidden; float: left;">
                <form id="pageForm" action="${pageContext.request.contextPath}/IndexAction!forwardMyBlogPage.action"
                      method="post"
                      onsubmit="return validate();">
                    <table style="width: auto;" border="0" cellSpacing="2" cellPadding="0" align="center">
                        <tr>
                            <td><a href="javascript:lastPage();">上一页</a></td>
                            <td>符合您条件的共有<font color="red">${result.totalCount}</font>条 ，第<font
                                    color="red">${result.page}</font> 页/共 <font color="red">${result.totalPage}</font> 页
                            </td>
                            <td><a href="javascript:nextPage();">下一页</a></td>
                            <td align="right">
                                <input class="TextBox" id="currentPage" name="currentPage" size="4" type="text"/>
                                <input class="input0" title="填写翻转的分页，然后点击查看！" value="GO" type="submit"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <!-- 左边栏目 -->
        <div id="local">
            <div class="local_top"></div>
            <div id="blog_actions">
                <ul>
                    <li>用户: ${userVo.userName}</li>
                    <li>性别:
                        <s:if test="userVo.sexy == 1">
                            <img alt="Icon_minigender_1" src="${pageContext.request.contextPath}/images/male.gif"
                                 title="男"/>
                        </s:if>
                        <s:else>
                            <img alt="Icon_minigender_1" src="${pageContext.request.contextPath}/images/female.gif"
                                 title="女"/>
                        </s:else>

                    </li>
                </ul>
            </div>
            <div class="local_bottom"></div>
        </div>

        <div id="local">
            <ul class="icons">
                <li class="new_blog"><a href="${pageContext.request.contextPath}/">网站首页</a></li>
                <li class="new_blog"><a href="${pageContext.request.contextPath}/IndexAction!forwardMyInfoPage.action">个人资料</a>
                </li>
                <li class="category"><a href="${pageContext.request.contextPath}/IndexAction!forwardMyBlogPage.action">博客管理</a>
                </li>
                <li class="comment"><a href="${pageContext.request.contextPath}/mypass_page.jspx">修改密码</a></li>
            </ul>
        </div>
    </div>

    <div id="footer" class="clearfix">
        <div id="copyright">
            <hr/>
            <s:include value="footer.jsp"></s:include>
        </div>
    </div>
</body>
</html>
<script type="text/javascript">
    function deleteBlog(articleId) {
        if (confirm("如果你删除该主题帖子，它的所有回复也会被同时删除，您确定要继续吗？")) {
            var url = "${pageContext.request.contextPath}/IndexAjaxAction!deleteArticle.action";
            $.post(url, "articleVo.id=" + articleId, function (data) {
                var result = $.trim(data);
                if (result == "ok") {
                    alert("删除成功.");
                } else {
                    alert(data);
                }
                window.location.reload();
            });
        }
    }

    function validate() {
        var pageNum = $("#currentPage").val();
        var regex = /^[0-9]*[1-9][0-9]*$/;
        if (!regex.test(pageNum)) {
            alert("非法参数！");
            $("#currentPage").val("").focus();
            return false;
        }
    }

    //下一页
    function nextPage() {
        var currentPage = "${result.page}";
        var totalPage = "${result.totalPage}";
        currentPage = parseInt(currentPage) + 1;
        if (currentPage > totalPage) {
            alert("已经是最后一页了.");
            return;
        }
        $("#currentPage").val(currentPage);
        document.getElementById("pageForm").submit();
    }

    //上一页
    function lastPage() {
        var currentPage = "${result.page}";
        currentPage = parseInt(currentPage) - 1;
        if (currentPage < 1) {
            alert("已经是第一页了.");
            return;
        }
        $("#currentPage").val(currentPage);
        document.getElementById("pageForm").submit();
    }
</script>