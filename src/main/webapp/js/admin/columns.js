var listColumns;

$(function() {
	createGrid();
	
	//添加分论坛
	$("#add").click(function() {
		CommonUtils.openWindow(basePath + "/admin/addcolumn_page.jspx", '添加栏目版块', 1024, 700);
	});

	//修改分论坛
	$("#edit").click(function() {
		var rows = listColumns.getSelectedRows();
		if (rows.length == 0){
			$.ligerDialog.warn("请选择要修改的记录.");
			return;
		}
		if (rows.length > 1){
			$.ligerDialog.warn("对不起，每次只能修改一条记录.");
			return;
		}
		var row = listColumns.getSelectedRow();
		var id = row.id;
		var url = basePath + "/admin/ColumnAction!forwardEditColumnPage.action?columnVo.id=" + id;
		CommonUtils.openWindow(url, '修改栏目', 1024, 700);
	});

	//删除栏目
	$("#delete").click(function() {
		var rows = listColumns.getSelectedRows();
		if (rows.length == 0){
			$.ligerDialog.warn("请选择要删除的记录.");
			return;
		}
		if (confirm("执行该操作，该栏目下的所有版块及其帖子都会被删除。\r\n而且该操作是不可恢复的，您确定要继续吗？")){
			var ids = "";
			for ( var i = 0; i < rows.length; i++) {
				ids += rows[i].id + ",";
			}
			var url = basePath + "/admin/ColumnAction!deleteColumn.action";
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
		var columnName = $("#columnName").val();
		var params = {
			"columnVo.name" : columnName
		};
		listColumns.setOptions({
			parms : params
		});
		listColumns.loadData();
	});
	
	$("#reset").click(function(){
		$("#columnName").val("");
		var params = {
			"columnVo.name" : ""
		};
		listColumns.setOptions({
			parms : params
		});
		listColumns.loadData();
	});

});

function createGrid(){
	listColumns = $("#listColumns").ligerGrid({
		checkbox : true,
		columns : [{
			display : '栏目名称',
			name : 'name',
			align : 'left',
			minWidth : 140
		}, {
			display : '栏目描述',
			name : 'remark',
			align : 'left', 
			minWidth : 250,
			render: function(data){
				return "<span title='" + data.remark + "'>" + data.remark + "</span>"
			}
		}],
		url : basePath + "/admin/ColumnAction!listColumns.action",
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
