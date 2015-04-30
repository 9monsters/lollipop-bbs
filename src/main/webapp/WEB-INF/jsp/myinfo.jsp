<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" dir="ltr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>个人博客</title>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <link rel="shortcut icon" href="" type="image/x-icon"/>
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
                <span>我的资料</span>

                <div id="fd"></div>
            </div>
            <div class="blog_main">
                <form id="userForm" action="" method="post">
                    <table align="center" cellspacing="1" cellpadding="3" style="width:100%;" class="tableborder1">
                        <tr>
                            <td valign="middle" width="30%" style="text-align:right; padding:10px">
                                <b>用户名:</b>
                            </td>
                            <td valign="middle" class="tablebody1">
                                ${userVo.userName}
                            </td>
                        </tr>
                        <tr>
                            <td valign="middle" style="text-align:right; padding:10px">
                                <b>性&nbsp;&nbsp;&nbsp;别:</b>
                            </td>
                            <td valign="middle" class="tablebody1">
                                <s:if test="userVo.sexy == 1">
                                    <input type="radio" id="male" name="userVo.sexy" value="1" checked/>男&nbsp;
                                    <input type="radio" id="female" name="userVo.sexy" value="2"/>女&nbsp;
                                </s:if>
                                <s:else>
                                    <input type="radio" id="male" name="userVo.sexy" value="1"/>男&nbsp;
                                    <input type="radio" id="female" name="userVo.sexy" value="2" checked/>女&nbsp;
                                </s:else>
                            </td>
                        </tr>
                        <tr>
                            <td valign="middle" style="text-align:right; padding:10px">
                                <b>联系电话:</b>
                            </td>
                            <td valign="middle" class="tablebody1">
                                <input type="text" id="phone" name="userVo.phone" class="input_txt"
                                       value="${userVo.phone}"/> &nbsp; （手机或固定电话，如010-26268181或13622298413）<br/>
                            </td>
                        </tr>
                        <tr>
                            <td valign="middle" style="text-align:right; padding:10px">
                                <b>电子邮件:</b>
                            </td>
                            <td valign="middle" class="tablebody1">
                                <input type="text" id="email" name="userVo.email" class="input_txt"
                                       value="${userVo.email}"/> &nbsp; （邮件格式，如 zhongliwen1981@163.com）
                            </td>
                        </tr>
                        <tr>
                            <td valign="middle" colspan="2" style="text-align:center" class="tablebody2">
                                <input type="button" value="保 存" class="input0" onclick="save();"/>&nbsp;&nbsp;&nbsp;
                                <input type="hidden" name="userVo.id" value="${userVo.id}"/>
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

    <script type="text/javascript">
        jQuery.validator.addMethod("isTel", function (value, element) {
            var tel = /^\d{3,4}-?\d{7,9}$/;
            return this.optional(element) || (tel.test(value));
        }, "请正确填写您的电话号码");

        $(document).ready(function () {
            // 表单校验
            $("#userForm").validate({
                debug: false,
                rules: {
                    "userVo.phone": {
                        required: true,
                        maxlength: 200,
                        isTel: true
                    },
                    "userVo.email": {
                        required: true,
                        maxlength: 200,
                        email: true
                    }
                },
                messages: {
                    "userVo.phone": {
                        required: "联系电话不能为空"
                    },
                    "userVo.email": {
                        required: "电子邮箱不能为空",
                        maxlength: "邮箱长度不能超过200个字符"
                    }
                },
                focusInvalid: true,
                onkeyup: false,
                errorPlacement: function (error, element) {
                    error.appendTo(element.parent());
                }
            });
        });

        function save() {
            if (CommonUtils.initValidator({form: 'userForm'}).form()) {
                var url = "${pageContext.request.contextPath}/IndexAjaxAction!updateUser.action";
                var params = $("#userForm").serialize();
                $.post(url, params, function (data) {
                    var result = $.trim(data);
                    if (result == 'ok') {
                        alert("保存成功.");
                    } else {
                        alert(result);
                    }
                    window.location.reload();
                });
            }
        }
    </script>
</div>
</body>
</html>
