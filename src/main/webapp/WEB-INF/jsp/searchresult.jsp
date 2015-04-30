<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>课程论坛毕业设计-搜索结果</title>
    <meta name="keywords" content=""/>
    <link rel="SHORTCUT ICON" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.8.2.min.js"></script>
    <style>
        .search li {
            border-bottom: 1px #B3B3B3 dashed;
            list-style: none;
            padding: 10px 0;
            width: 98%;
            line-height: 150%;
            float: left
        }

        .index {
            float: left;
            display: block;
            font-size: 14px;
            margin: 0 5px;
        }

        .content {
            text-indent: 3em;
        }
    </style>
</head>
<body>
<s:include value="header.jsp"></s:include>
<div class="mainbox">
    <div class="nav">
        <div style="float:left;">
            <img src="${pageContext.request.contextPath}/images/forum_readme.gif"/>
            <a href="${pageContext.request.contextPath}/IndexAction!index.action">课程论坛首页</a>&nbsp;>>&nbsp;搜索结果
        </div>
        <div class="notice">
            <form id="searchForm" action="${pageContext.request.contextPath}/IndexAction!search.action"
                  method="post" onsubmit="return check();">
                <input type="text" id="keyword" name="keyword" value="${keyword}" size="35"/>
                <input type="submit" value=" 搜 索 "/>
            </form>
        </div>
    </div>
    <table align="center" class="tableborder1" id="iSearch" cellSpacing="1" cellPadding="3" style="width:100%;">
        <tr>
            <td id="SearchMain1" class="tablebody1" vAlign="top" width="50%">
                <div style="margin: 0px; width: 100%; height: 24px; line-height: 24px; border-bottom-color: rgb(204, 204, 204); border-bottom-width: 1px; border-bottom-style: solid; float: left; background-color: rgb(238, 238, 238);">
                    &nbsp;&nbsp;
                    本次搜索共找到约 <font color="#ff0000">${result.totalCount}</font> 条相关帖子记录，共耗时<font
                        color=green>${totalTime}</font>秒。
                </div>
                <br/><br/>

                <div style="margin: 8px;">
                    <form id="batch" method="post" name="batch" action="">
                        <ul class="search">
                            <s:iterator value="result.items" status="item">
                                <li>
                                    <span class="index">${item.index+1}.</span>
                                    <a href="${pageContext.request.contextPath}/IndexAction!forwardArticlePage.action?articleVo.id=${id}"
                                       target="_blank" style="font-size: 15px;">
                                        <s:property value="%{title}" escape="false"/>
                                    </a><br/><br/>

                                    <div class="content">
                                        <s:property value="%{content}" escape="false"/>
                                    </div>
                                    <div style="color:green; margin:8px 20px;">
                                        作者：
                                        <font color="green"><s:property value="%{createPerson.userName}"/></font>&nbsp;&nbsp;
                                        回复：<s:property value="%{totalReply}"/>&nbsp;&nbsp;→&nbsp;&nbsp;
                                        <font color="green"><s:property value="%{discussionBoard.name}"/></font>
                                        <font color="gray"><s:date name="%{createTime}" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;</font>
                                    </div>
                                </li>
                            </s:iterator>
                        </ul>
                    </form>
                    <br/>

                    <div style="width: 100%; overflow: hidden; float: left;">
                        <form id="pageForm" action="${pageContext.request.contextPath}/IndexAction!search.action"
                              method="post"
                              onsubmit="return validate();">
                            <input type="hidden" name="keyword" value="${keyword}"/>
                            <table style="width: auto;" border="0" cellSpacing="2" cellPadding="0" align="center">
                                <tr>
                                    <td><a href="javascript:lastPage();">上一页</a></td>
                                    <td>符合您条件的共有<font color="red">${result.totalCount}</font>条 ，第<font
                                            color="red">${result.page}</font> 页/共 <font
                                            color="red">${result.totalPage}</font> 页
                                    </td>
                                    <td><a href="javascript:nextPage();">下一页</a></td>
                                    <td align="right">
                                        <input class="TextBox" id="currentPage" name="currentPage" size="4"
                                               type="text"/>
                                        <input class="input0" title="填写翻转的分页，然后点击查看！" value="GO" type="submit"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </td>
            <td style="border-left-color: rgb(222, 222, 222); border-left-width: 3px; border-left-style: solid; display: none;"
                id="SearchMain2" class="tablebody1" height="100%" vAlign="top" width="50%"></td>
        </tr>
    </table>
</div>
<s:include value="footer.jsp"></s:include>
<script type="text/javascript">
    function check() {
        var keyword = $.trim($("#keyword").val());
        if (keyword == "") {
            alert("请输入搜索关键字.");
            return false;
        }
        return true;
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
</body>
</html>