$(function(){
		
	$("#save").click(function(){
		if (CommonUtils.initValidator({form:'userForm'}).form())
		{
			var url = basePath + "/admin/UserAction!addUser.action";;
			var params = $("#userForm").serialize();
			$.post(url, params, function(data){
				if (data.result == 'ok'){
					alert("添加成功.");
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
	
	jQuery.validator.addMethod("isTel", function (value, element) {  
		var tel = /^\d{3,4}-?\d{7,9}$/; 
		return this.optional(element) || (tel.test(value));  
	}, "请正确填写您的电话号码");
	
	// 表单校验
	$("#userForm").validate({
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
			"rePassword": {
				required: true,
				rangelength: [6, 12],
				equalTo: "#userPass" 
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
			"rePassword": {
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
		errorPlacement: function(error, element) {
			error.appendTo(element.parent());
		}
	});

});