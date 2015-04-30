var listReplys;

$(function() {
	
	createGrid();
	
	//添加文章
	$("#add").click(function() {
		CommonUtils.openWindow(basePath + "/admin/addreply_page.jspx?articleId=" + articleId, '添加回复', 1024, 700);
	});

	//修改文章
	$("#edit").click(function() {
		var rows = listReplys.getSelectedRows();
		if (rows.length == 0){
			$.ligerDialog.warn("请选择要修改的记录.");
			return;
		}
		if (rows.length > 1){
			$.ligerDialog.warn("对不起，每次只能修改一条记录.");
			return;
		}
		var row = listReplys.getSelectedRow();
		var id = row.id;
		var url = basePath + "/admin/ArticleAction!forwardEditReplyPage.action?articleVo.id=" + id;
		CommonUtils.openWindow(url, '修改回复', 1024, 700);
	});

	//删除文章
	$("#delete").click(function() {
		var rows = listReplys.getSelectedRows();
		if (rows.length == 0){
			$.ligerDialog.warn("请选择要删除的记录.");
			return;
		}
		if (confirm("该操作是不可恢复的，您确定要继续吗？")){
			var ids = "";
			for ( var i = 0; i < rows.length; i++) {
				ids += rows[i].id + ",";
			}
			var url = basePath + "/admin/ArticleAction!deleteReply.action";
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

});

function createGrid(){
	listReplys = $("#listReplys").ligerGrid({
		checkbox : true,
		columns : [{
			display : '回复标题',
			name : 'themeArticle',
			align : 'center',
			minWidth : 140,
			render: function(data){
				return data.themeArticle.title;
			}
		}, {
			display : '回复内容',
			name : 'content',
			align : 'left',
			minWidth : 250,
			render : function(data) {
				if (data.content.length > 50){
					return "<span title='" + data.content + "'>" 
						+ data.content.substring(0, 50) + "</span>";
				}else{
					return data.content;
				}
				
			}
		}, {
			display : '回复时间',
			name : 'createTime',
			align : 'center',
			minWidth : 140,
			render : function(data) {
				return data.createTime.substring(0, 10);
			}
		}, {
			display : '回复人',
			name : 'createPerson',
			align : 'center',
			minWidth : 140,
			render: function(data){
				return data.createPerson.userName;
			}
		}],
		url : basePath + "/admin/ArticleAction!listArticleReplys.action?articleVo.id=" + articleId,
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