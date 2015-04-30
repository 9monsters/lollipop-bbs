var listUser;

$(function() {
	createGrid();
	
	$("#add").click(function() {
		CommonUtils.openWindow(basePath + "/admin/adduser_page.jspx", '添加用户', 1024, 700);
	});

	$("#edit").click(function() {
		var rows = listUser.getSelectedRows();
		if (rows.length == 0){
			$.ligerDialog.warn("请选择要修改的记录.");
			return;
		}
		if (rows.length > 1){
			$.ligerDialog.warn("对不起，每次只能修改一条记录.");
			return;
		}
		var row = listUser.getSelectedRow();
		var uid = row.id;
		var url = basePath + "/admin/UserAction!forwardEditUserPage.action?userVo.id=" + uid;
		CommonUtils.openWindow(url, '修改用户', 1024, 700);
	});

	$("#search").click(function(){
		var userName = $("#userName").val();
		var status = $("#status").val();
		var params = {
			"userVo.userName" : userName,
			"userVo.status" : status
		};
		listUser.setOptions({
			parms : params
		});
		listUser.loadData();
	});
	
	$("#reset").click(function(){
		$("#userName").val("");
		$("#status").val(0);
		var params = {
			"userVo.userName" : "",
			"userVo.status" : 0
		};
		listUser.setOptions({
			parms : params
		});
		listUser.loadData();
	});

});

function createGrid(){
	listUser = $("#listUser").ligerGrid({
		checkbox : true,
		columns : [{
			display : '用户名',
			name : 'userName',
			align : 'center',
			minWidth : 140
		}, {
			display : '性别',
			name : 'sexy',
			align : 'center',
			minWidth : 140,
			render : function(data) {
				return data.sexy == 1 ? "男" : "女";
			}
		}, {
			display : '联系电话',
			name : 'phone',
			align : 'center',
			minWidth : 140
		}, {
			display : '电子邮件',
			name : 'email',
			align : 'center',
			minWidth : 200
		}, {
			display : '注册时间',
			name : 'registTime',
			align : 'center',
			minWidth : 140,
			render : function(data) {
				return data.registTime.substring(0, 10);
			}
		}, {
			display : '用户角色',
			name : 'role',
			align : 'center',
			minWidth : 140,
			render : function(data) {
				if (data.role == 1){
					return "普通会员";
				}else if (data.role == 2){
					return "版主";
				}else if (data.role == 3){
					return "管理员";
				}
				return '';
			}
		}, {
			display : '用户状态',
			name : 'status',
			align : 'center',
			minWidth : 140,
			render : function(data) {
				return data.status == 1 ? "<font color=green>激活</font>" : "<font color=red>冻结</font>";
			}
		}],
		url : basePath + "/admin/UserAction!listUsers.action",
		root : 'rows',
		record : 'total',
		height : '99%',
		width : '99%',
		pageSize : 15
	});
}

/**
 * 窗口回调方法
 */
function parentCallBack() {
	createGrid();
}