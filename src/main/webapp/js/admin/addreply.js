var editor;
	
$(document).ready(function(){
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
	
	$("#save").click(function(){
		var content = editor.html();
		if ($.trim(content) == '')
		{
			alert("请输入回复内容.");
			return;
		}
		var url = basePath + "/admin/ArticleAction!addReply.action";
		var params = $("#replyForm").serialize();
		params += "&articleVo.content=" + encodeURI(encodeURI(content));
		$.post(url, params, function(data){
			if (data.result == 'ok'){
				alert("添加成功.");
				window.opener.parentCallBack();
				window.close();
			}else{
				alert(data.result);
			}
		}, "json");
	});

	$("#cancel").click(function(){
		window.close();
	});
	
});