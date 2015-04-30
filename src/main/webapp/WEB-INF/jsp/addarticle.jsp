<%@ page language="java" import="java.util.List,com.just.lollipop.bbs.vo.UserVo" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程论坛毕业设计-发布话题</title>
<meta name="keywords" content="" />
<link rel="SHORTCUT ICON" href="favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.8.2.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/kindeditor/kindeditor-min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/lib/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/lib/jquery-validation-1.9.0/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/common/js/common.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#submit").click(function(){
			if (CommonUtils.initValidator({form:'articleForm'}).form())
			{
				var content = editor.html();
				if (content == ""){
					alert("请输入内容.");
					return;
				}
				var url = "${pageContext.request.contextPath}/IndexAjaxAction!addArticle.action";
				var params = $("#articleForm").serialize();
				params += "&articleVo.content=" + encodeURI(encodeURI(content));
				$.post(url, params, function(data){
	    			var result = $.trim(data);
	    			if (result == "ok"){
	    				alert("发布成功!");
	    				window.opener.location.reload();
	    				window.close();
	    			}else{
	    				alert(data);
	    			}
	    		});
			}
		});
	});
</script>
</head>
<body>
	<div id="top" class="header">
		<table width="100%">
			<tr>
				<td width="200" style="padding-top:16px">
					<img src="${pageContext.request.contextPath}/images/logo.png" />
				</td>
			</tr>
		</table>
	</div>
	<div class="mainbox">
		<table class="topic border" style="width:100%; border-bottom-color: currentColor; border-bottom-width: 0px; border-bottom-style: none;" cellSpacing="0" cellPadding="0">
			<tr>
				<th>
					<h1>发表帖子</h1>
				</th>
			</tr>
		</table>
		<form id="articleForm">
			<table class="tablebox border" cellSpacing="0" cellPadding="0" style="width:100%;">
				<tr id="upload_input">
					<td style="background: rgb(250, 253, 255); border-bottom-color: rgb(228, 231, 236); border-bottom-width: 1px; border-bottom-style: solid;" class="infoleft leftcolor" vAlign="middle" align="left">
						&nbsp;&nbsp;用户名
					</td>
					<td style="background: rgb(250, 253, 255); padding: 5px; border-bottom-color: rgb(228, 231, 236); border-bottom-width: 1px; border-bottom-style: solid;">
						${sessionScope.user.userName}
					</td>
				</tr>
				<tr id="upload_input">
					<td style="background: rgb(250, 253, 255); border-bottom-color: rgb(228, 231, 236); border-bottom-width: 1px; border-bottom-style: solid;" class="infoleft leftcolor" vAlign="middle" align="left">
						<font color=red>*</font>主题标题：
					</td>
					<td style="background: rgb(250, 253, 255); padding: 5px; border-bottom-color: rgb(228, 231, 236); border-bottom-width: 1px; border-bottom-style: solid;">
						<input id="title" class="FormClass" name="articleVo.title" size="45">
						<span id="mode_chk"></span>
						&nbsp;（不得超过 200 个汉字）<span id="topic_chk"></span>
					</td>
				</tr>
				<tr>
					<td style="background: rgb(250, 253, 255); padding: 10px 20px;" class="infoleft leftcolor" vAlign="top" align="left">
						<font color=red>*</font>帖子内容：
					</td>
					<td style="margin: 0px; padding: 0px;">
						<textarea name="content" style="width: 100%; height: 200px; visibility: hidden;"></textarea>
					</td>
				</tr>
				<tr>
					<td style="text-align: center; padding:5px;" class="tablebody2" colspan="2">
						<input id="submit" class="input0" value="  发 表  " type="button" />&nbsp;
						<input class="input0" onclick="window.close();" value="  取 消  " type="button" />
						<input type="hidden" name="articleVo.discussionBoard.id" value="${param.boardId}" />
					</td>
				</tr>
				</tbody>
			</table>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>	
</html>
<script type="text/javascript">
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline', 'removeformat', '|',
					'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|',
					'link' ]
		});
	});
	
	// 表单校验
	$("#articleForm").validate({
		debug: false,
		rules: {
			"articleVo.title": {
				required: true,
				maxlength: 200
			}
		},
		messages: {
			"articleVo.title": {
				required: "标题不能为空",
				maxlength: "标题长度不能超过200个字符"
			}
		},
		focusInvalid: true, 
		onkeyup: false,
		errorPlacement: function(error, element) {
			error.appendTo(element.parent());
		}
	});
</script>