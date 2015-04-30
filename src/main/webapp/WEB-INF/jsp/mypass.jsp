<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" dir="ltr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>个人博客</title>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/theme/blue.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/jquery.validate.min.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/jquery.metadata.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/messages_cn.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/common/js/common.js" type="text/javascript"></script>
    <style>
        .input_txt {
            border-bottom: 1px solid #BDC5CA;
            border-right: 1px solid #BDC5CA;
            border-top: 1px solid #6F787E;
            border-left: 1px solid #6F787E;
            padding: 3px 2px;
            font-size: 12px;
        }

        input.error, select.error {
            border: 1px solid red;
        }

        label.error {
            background: url("${pageContext.request.contextPath}/images/unchecked.gif") no-repeat 0px 0px;
            padding-left: 16px;
            padding-bottom: 2px;
            font-weight: bold;
            color: #EA5200;
        }
    </style>
</head>
<body>
<div id="header">
    <div id="user_nav">
        <a href="${pageContext.request.contextPath}/IndexAction!forwardMyInfoPage.action" title="查看我的博客首页"
           class="welcome">欢迎${sessionScope.user.userName}</a>
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
                <span>修改密码</span>

                <div id="fd"></div>
            </div>
            <div class="blog_main">
                <form id="passForm" action="" method="post">
                    <table align="center" cellspacing="1" cellpadding="3" style="width:100%;" class="tableborder1">
                        <tr>
                            <td valign="middle" width="30%" style="text-align:right; padding:10px">
                                <b><font color=red>*</font>旧密码:</b>
                            </td>
                            <td valign="middle">
                                <input type="password" id="oldPass" name="userVo.oldPass" class="input_txt"/> &nbsp;
                                （密码长度必须为6-12位）
                            </td>
                        </tr>
                        <tr>
                            <td valign="middle" width="30%" style="text-align:right; padding:10px">
                                <b><font color=red>*</font>新密码:</b>
                            </td>
                            <td valign="middle">
                                <input type="password" id="userPass" name="userVo.userPass" class="input_txt"/> &nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td valign="middle" width="30%" style="text-align:right; padding:10px">
                                <b><font color=red>*</font>密码确认:</b>
                            </td>
                            <td valign="middle">
                                <input type="password" id="reUserPass" name="reUserPass" class="input_txt"/> &nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td valign="middle" colspan="2" style="text-align:center;" class="tablebody2">
                                <input type="button" value="修改密码" class="input0" onclick="updatePass();"/>&nbsp;&nbsp;&nbsp;
                                <input type="hidden" name="userVo.id" value="${sessionScope.user.id}"/>
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
                    <li>用户: ${sessionScope.user.userName}</li>
                    <li>性别:
                        <s:if test="#session.user.sexy == 1">
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

    <script type="text/javascript">
        function updatePass() {
            if (CommonUtils.initValidator({form: 'passForm'}).form()) {
                var url = "${pageContext.request.contextPath}/IndexAjaxAction!changePass.action";
                var params = $("#passForm").serialize();
                $.post(url, params, function (data) {
                    var result = $.trim(data);
                    if (result == 'ok') {
                        alert("修改成功.");
                    } else {
                        alert(result);
                    }
                    window.location.reload();
                });
            }
        }

        $(document).ready(function () {
            $("#oldPass").focus();

            // 表单校验
            $("#passForm").validate({
                debug: false,
                rules: {
                    "userVo.oldPass": {
                        required: true,
                        rangelength: [6, 12]
                    },
                    "userVo.userPass": {
                        required: true,
                        rangelength: [6, 12]
                    },
                    "reUserPass": {
                        required: true,
                        rangelength: [6, 12],
                        equalTo: "#userPass"
                    }
                },
                messages: {
                    "userVo.oldPass": {
                        required: "密码不能为空",
                        rangelength: "密码长度必须为6-12位"
                    },
                    "userVo.userPass": {
                        required: "密码不能为空",
                        rangelength: "密码长度必须为6-12位"
                    },
                    "reUserPass": {
                        required: "确认密码不能为空",
                        rangelength: "密码长度必须为6-12位",
                        equalTo: "2次输入的密码不相同"
                    }
                },
                focusInvalid: true,
                onkeyup: false,
                errorPlacement: function (error, element) {
                    error.appendTo(element.parent());
                }
            });
        });
    </script>
</div>
</body>
</html>