$(function(){
		
	$("#save").click(function(){
		if (CommonUtils.initValidator({form:'userForm'}).form())
		{
			var url = basePath + "/admin/UserAction!updateUser.action";;
			var params = $("#userForm").serialize();
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
	
	$("#resetPassword").click(function(){
		if (confirm("您确定重置密码吗？")){
			var url = basePath + "/admin/UserAction!resetPassword.action";;
			var params = "userVo.id=" + $("#id").val();
			$.post(url, params, function(data){
				if (data.result == 'ok'){
					alert("密码重置为123456.请妥善保管好！");
				}else{
					alert(data.result);
				}
			}, "json");
		}
	});
	
	$("#freeze").click(function(){
		var url = basePath + "/admin/UserAction!deleteUser.action";;
		var params = "userVo.id=" + $("#id").val();
		$.post(url, params, function(data){
			if (data.result == 'ok'){
				window.opener.parentCallBack();
				window.location.reload();
			}else{
				alert(data.result);
			}
		}, "json");
	});
	
	$("#activate").click(function(){
		var url = basePath + "/admin/UserAction!activateUser.action";;
		var params = "userVo.id=" + $("#id").val();
		$.post(url, params, function(data){
			if (data.result == 'ok'){
				window.opener.parentCallBack();
				window.location.reload();
			}else{
				alert(data.result);
			}
		}, "json");
	});
	
	$("#cancel").click(function(){
		window.close();
	});
	
	jQuery.validator.addMethod("isTel", function (value, element) {  
		var tel = /^\d{3,4}-?\d{7,9}$/; 
		return this.optional(element) || (tel.test(value));  
	}, "请正确填写您的电话号码");
	
	// 表单校验
	$("#userForm").validate({
		debug: false,
		rules: {
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
		errorPlacement: function(error, element) {
			error.appendTo(element.parent());
		}
	});

});