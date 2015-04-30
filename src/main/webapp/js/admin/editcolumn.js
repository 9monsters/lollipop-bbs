$(function(){
		
	$("#save").click(function(){
		if (CommonUtils.initValidator({form:'columnForm'}).form())
		{
			var url = basePath + "/admin/ColumnAction!updateColumn.action";
			var params = $("#columnForm").serialize();
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
	
	// 表单校验
	$("#columnForm").validate({
		debug: false,
		rules: {
			"columnVo.name": {
				required: true,
				maxlength: 200
			},
			"columnVo.remark": {
				maxlength: 200
			}
		},
		messages: {
			"columnVo.name": {
				required: "栏目名称不能为空",
				maxlength: "版块名称的长度不能超过200个字符"
			},
			"columnVo.remark": {
				maxlength: "栏目描述不能超过200个字符"
			}
		},
		focusInvalid: true, 
		onkeyup: false,
		errorPlacement: function(error, element) {
			error.appendTo(element.parent());
		}
	});

});