<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>课程论坛毕业设计-用户注册</title>
    <meta name="keywords" content=""/>
    <link rel="SHORTCUT ICON" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css"/>
    <script src="${pageContext.request.contextPath}/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/jquery.validate.min.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/jquery.metadata.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/messages_cn.js"
            type="text/javascript"></script>
    <script type="text/javascript">
        var tip = "${tip}";
        if (tip == "ok") {
            alert("注册成功.");
            window.location.href = "${pageContext.request.contextPath}/login_page.jspx";
        } else if (tip != "") {
            alert(tip);
        }
    </script>
</head>
<body>
<div class="header">
    <table width="100%">
        <tr>
            <td width="200" style="padding-top:16px">
                <img src="${pageContext.request.contextPath}/images/logo.png"/>
            </td>
        </tr>
    </table>
</div>
<div class="mainbox">
    <div class="nav">
        <img src="${pageContext.request.contextPath}/images/forum_readme.gif"/>
        <a href="${pageContext.request.contextPath}/IndexAction!index.action">课程论坛首页</a> >> 注册会员
    </div>
    <form id="registForm" action="${pageContext.request.contextPath}/IndexAction!regist.action" method="post">
        <table align="center" cellspacing="1" cellpadding="3" style="width:100%;" class="tableborder1">
            <tr>
                <th align="center" valign="middle" height="25" colspan="2">注册会员（请填写会员信息）</th>
            </tr>
            <tr>
                <td valign="middle" class="tablebody1" width="30%" style="text-align:right; padding:10px">
                    <b><font color=red>*</font>用户名:</b>
                </td>
                <td valign="middle" class="tablebody1">
                    <input type="text" name="userVo.userName" value="${userVo.userName}"/> &nbsp; （请输入用户名称）
                </td>
            </tr>
            <tr>
                <td valign="middle" class="tablebody1" style="text-align:right; padding:10px">
                    <b><font color=red>*</font>密&nbsp;&nbsp;&nbsp;码:</b>
                </td>
                <td valign="middle" class="tablebody1">
                    <input type="password" id="pwd1" name="userVo.userPass"/> &nbsp; （密码长度必须为6-12位）
                </td>
            </tr>
            <tr>
                <td valign="middle" class="tablebody1" style="text-align:right; padding:10px">
                    <b><font color=red>*</font>确认密码:</b>
                </td>
                <td valign="middle" class="tablebody1">
                    <input type="password" id="pwd2" name="rePass"/> &nbsp;
                </td>
            </tr>
            <tr>
                <td valign="middle" class="tablebody1" style="text-align:right; padding:10px">
                    <b><font color=red>*</font>性&nbsp;&nbsp;&nbsp;别:</b>
                </td>
                <td valign="middle" class="tablebody1">
                    <s:if test="userVo.sexy == 1">
                        <input type="radio" id="male" name="userVo.sexy" value="1" checked>男&nbsp;
                        <input type="radio" id="female" name="userVo.sexy" value="2">女&nbsp;
                    </s:if>
                    <s:elseif test="userVo.sexy == 2">
                        <input type="radio" id="male" name="userVo.sexy" value="1">男&nbsp;
                        <input type="radio" id="female" name="userVo.sexy" value="2" checked>女&nbsp;
                    </s:elseif>
                    <s:else>
                        <input type="radio" id="male" name="userVo.sexy" value="1">男&nbsp;
                        <input type="radio" id="female" name="userVo.sexy" value="2">女&nbsp;
                    </s:else>
                </td>
            </tr>
            <tr>
                <td valign="middle" class="tablebody1" style="text-align:right; padding:10px">
                    <b><font color=red>*</font>联系电话:</b>
                </td>
                <td valign="middle" class="tablebody1">
                    <input type="text" id="phone" name="userVo.phone" value="${userVo.phone}"> &nbsp;
                    （手机或固定电话，如010-26268181或13622298413）
                </td>
            </tr>
            <tr>
                <td valign="middle" class="tablebody1" style="text-align:right; padding:10px">
                    <b><font color=red>*</font>电子邮件:</b>
                </td>
                <td valign="middle" class="tablebody1">
                    <input type="text" id="email" name="userVo.email" value="${userVo.email}"
                           validate="{required:true,email:true}"> &nbsp; （邮件格式，如 zhongliwen1981@163.com）
                </td>
            </tr>
            <tr>
                <td valign="middle" colspan="2" style="text-align:center" class="tablebody2">
                    <input type="submit" value="注 册" class="input0"/>&nbsp;&nbsp;&nbsp;
                    <input type="reset" value="重 置" class="input0"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="ad"></div>
<div class="copyright">
		<span style="text-align:center;">Copyright &copy;2013 - 2020
			&nbsp;&nbsp;&nbsp;Powered By Lollipop  <a href="" target="_blank">Version 1.0.0</a></span>
</div>
<script type="text/javascript">
    jQuery.validator.addMethod("isTel", function (value, element) {
        var tel = /^\d{3,4}-?\d{7,9}$/;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写您的电话号码");

    $(document).ready(function () {
        // 表单校验
        $("#registForm").validate({
            debug: false,
            rules: {
                "userVo.userName": {
                    required: true,
                    maxlength: 50
                },
                "userVo.userPass": {
                    required: true,
                    rangelength: [6, 12]
                },
                "rePass": {
                    required: true,
                    rangelength: [6, 12],
                    equalTo: "#pwd1"
                },
                "userVo.sexy": {
                    required: true,
                    maxlength: 200
                },
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
                "userVo.userName": {
                    required: "用户名不能为空",
                    maxlength: "用户名长度不能超过50个字符"
                },
                "userVo.userPass": {
                    required: "密码不能为空",
                    rangelength: "密码长度必须为6-12位"
                },
                "rePass": {
                    required: "确认密码不能为空",
                    rangelength: "密码长度必须为6-12位",
                    equalTo: "2次输入的密码不相同"
                },
                "userVo.sexy": {
                    required: "性别不能为空"
                },
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
</script>
</body>
</html>