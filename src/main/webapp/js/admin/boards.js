var listBoards;

$(function() {
	createGrid();
	loadColumns();
	
	//添加分论坛
	$("#add").click(function() {
		CommonUtils.openWindow(basePath + "/admin/addboard_page.jspx", '添加论坛版块', 1024, 700);
	});

	//修改分论坛
	$("#edit").click(function() {
		var rows = listBoards.getSelectedRows();
		if (rows.length == 0){
			$.ligerDialog.warn("请选择要修改的记录.");
			return;
		}
		if (rows.length > 1){
			$.ligerDialog.warn("对不起，每次只能修改一条记录.");
			return;
		}
		var row = listBoards.getSelectedRow();
		var bid = row.id;
		var url = basePath + "/admin/BoardAction!forwardEditBoardPage.action?boardVo.id=" + bid;
		CommonUtils.openWindow(url, '修改版块', 1024, 700);
	});

	//删除分论坛
	$("#delete").click(function() {
		var rows = listBoards.getSelectedRows();
		if (rows.length == 0){
			$.ligerDialog.warn("请选择要删除的记录.");
			return;
		}
		if (confirm("执行该操作，该版块下的所有帖子都会被删除。\r\n而且该操作是不可恢复的，您确定要继续吗？")){
			var ids = "";
			for ( var i = 0; i < rows.length; i++) {
				ids += rows[i].id + ",";
			}
			var url = basePath + "/admin/BoardAction!deleteBoard.action";
			var params = "ids=" + ids;
			$.post(url, params, function(data){
				if (data.result == 'ok'){
					alert("删除成功.");
					createGrid();
				}else{
					alert(data.result);
				}
			}, "json");
		}
	});
	
	$("#search").click(function(){
		var boardName = $("#boardName").val();
		var columnId = $("#column").val();
		var params = {
			"boardVo.name" : boardName,
			"boardVo.column.id" : columnId
		};
		listBoards.setOptions({
			parms : params
		});
		listBoards.loadData();
	});
	
	$("#reset").click(function(){
		$("#boardName").val("");
		$("#column").val("");
		var params = {
			"boardVo.name" : "",
			"boardVo.column.id" : ""
		};
		listBoards.setOptions({
			parms : params
		});
		listBoards.loadData();
	});

});

function createGrid(){
	listBoards = $("#listBoards").ligerGrid({
		checkbox : true,
		columns : [{
			display : '版块名称',
			name : 'name',
			align : 'center',
			minWidth : 140
		}, {
			display : '栏目',
			name : 'column',
			align : 'center',
			minWidth : 140,
			render: function(data){
				return data.column.name;
			}
		}, {
			display : '版主',
			name : 'moderator',
			align : 'center',
			minWidth : 140,
			render: function(data){
				return data.moderator.userName;
			}
		}],
		url : basePath + "/admin/BoardAction!listBoards.action",
		root : 'rows',
		record : 'total',
		height : '99%',
		width : '99%',
		pageSize : 15
	});
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

/**
 * 窗口回调方法
 */
function parentCallBack() {
	createGrid();
}
