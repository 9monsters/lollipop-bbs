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
	
	loadBoard();

	$("#save").click(function(){
		if (CommonUtils.initValidator({form:'articleForm'}).form())
		{
			var content = editor.html();
			var url = basePath + "/admin/ArticleAction!updateArticle.action";
			var params = $("#articleForm").serialize();
			params += "&articleVo.content=" + encodeURI(encodeURI(content));
			$.post(url, params, function(data){
				if (data.result == 'ok'){
					alert("修改成功.");
					window.opener.parentCallBack();
					window.close();
				}else{
					alert(data.result);
				}
			}, "json");
		}
	});

	$("#cancel").click(function(){
		window.close();
	});
	
	$("#discussionBoard").change(function(){
		if (parseInt(this.value) == 0){
			alert("请选择版块.");
			$(this).val("");
		}
	});

	// 表单校验
	$("#articleForm").validate({
		debug: false,
		rules: {
			"articleVo.title": {
				required: true,
				maxlength: 200
			},
			"articleVo.discussionBoard.id": {
				required: true
			}
		},
		messages: {
			"articleVo.title": {
				required: "标题不能为空",
				maxlength: "标题长度不能超过200个字符"
			},
			"articleVo.discussionBoard.id": {
				required: "版块不能为空"
			}
		},
		focusInvalid: true, 
		onkeyup: false,
		errorPlacement: function(error, element) {
			error.appendTo(element.parent());
		}
	});
});

function loadBoard(){
	var url = basePath + "/admin/BoardAction!listComboBoards.action";
	$.post(url, "", function(data){
		var json = eval('(' + data.result + ')');
		if (json.status == 'true'){
			var data = eval(json.result);
			for (i in data){
				if (data[i].id == discussionBoardId){
					$("#discussionBoard").append("<option value='" + data[i].id 
							+ "' selected>" + data[i].name + "</option>");
				}else{
					$("#discussionBoard").append("<option value='" + data[i].id 
							+ "'>" + data[i].name + "</option>");
				}
			}
		}
	}, "json");
}