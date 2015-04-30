$(function(){
	loadUsers();
	loadColumns();
		
	$("#save").click(function(){
		if (CommonUtils.initValidator({form:'boardForm'}).form())
		{
			var url = basePath + "/admin/BoardAction!addBoard.action";
			var params = $("#boardForm").serialize();
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
	
	// 表单校验
	$("#boardForm").validate({
		debug: false,
		rules: {
			"boardVo.boardName": {
				required: true,
				maxlength: 200
			},
			"boardVo.column.id": {
				required: true
			},
			"boardVo.moderator.id": {
				required: true
			}
		},
		messages: {
			"boardVo.boardName": {
				required: "版块名称不能为空",
				maxlength: "版块名称的长度不能超过200个字符"
			},
			"boardVo.column.id": {
				required: "栏目不能为空"
			},
			"boardVo.moderator.id": {
				required: "版主不能为空",
			}
		},
		focusInvalid: true, 
		onkeyup: false,
		errorPlacement: function(error, element) {
			error.appendTo(element.parent());
		}
	});

});

function loadUsers(){
	var url = basePath + "/admin/UserAction!listComboUsers.action";
	$.post(url, function(data){
		var json = eval('(' + data.result + ')');
		if (json.status == 'true'){
			var data = eval(json.result);
			for (i in data){
				$("#moderator").append("<option value='" + data[i].id 
						+ "'>" + data[i].name + "</option>");
			}
		}
	}, "json");
}

function loadColumns(){
	var url = basePath + "/admin/ColumnAction!listComboColumns.action";
	$.post(url, "", function(data){
		var json = eval('(' + data.result + ')');
		if (json.status == 'true'){
			var data = eval(json.result);
			for (i in data){
				$("#column").append("<option value='" + data[i].id 
						+ "'>" + data[i].name + "</option>");
			}
		}
	}, "json");
}