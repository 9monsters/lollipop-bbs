var listArticle;

$(function() {
	
	createGrid();
	
	//添加文章
	$("#add").click(function() {
		CommonUtils.openWindow(basePath + "/admin/addarticle_page.jspx", '添加文章', 1024, 700);
	});

	//修改文章
	$("#edit").click(function() {
		var rows = listArticle.getSelectedRows();
		if (rows.length == 0){
			$.ligerDialog.warn("请选择要修改的记录.");
			return;
		}
		if (rows.length > 1){
			$.ligerDialog.warn("对不起，每次只能修改一条记录.");
			return;
		}
		var row = listArticle.getSelectedRow();
		var id = row.id;
		var url = basePath + "/admin/ArticleAction!forwardEditArticlePage.action?articleVo.id=" + id;
		CommonUtils.openWindow(url, '修改文章', 1024, 700);
	});

	//删除文章
	$("#delete").click(function() {
		var rows = listArticle.getSelectedRows();
		if (rows.length == 0){
			$.ligerDialog.warn("请选择要删除的记录.");
			return;
		}
		if (confirm("执行该操作，关于该帖子的回复都会被删除。\r\n而且该操作是不可恢复的，您确定要继续吗？")){
			var ids = "";
			for ( var i = 0; i < rows.length; i++) {
				ids += rows[i].id + ",";
			}
			var url = basePath + "/admin/ArticleAction!deleteArticle.action";
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

	//查看回复
	$("#reply").click(function() {
		var rows = listArticle.getSelectedRows();
		if (rows.length != 1){
			$.ligerDialog.warn("请选择一条记录.");
			return;
		}
		var row = listArticle.getSelectedRow();
		window.parent.openTab(basePath + "/admin/articlereplys_page.jspx?articleId=" + row.id, "查看回复");
	});

	//查询文章
	$("#search").click(function() {
		var title = $("#title").val();
		var createTime = $("#createTime").val();
		var params = {
			"articleVo.title" : title,
			"articleVo.createTime" : createTime
		};
		listArticle.setOptions({
			parms : params
		});
		listArticle.loadData();
	});
	
	$("#reset").click(function(){
		$("#title").val("");
		$("#createTime").val("");
		var params = {
				"articleVo.title" : "",
				"articleVo.createTime" : ""
			};
		listArticle.setOptions({
			parms : params
		});
		listArticle.loadData();
	});

});

function createGrid(){
	listArticle = $("#listArticle").ligerGrid({
		checkbox : true,
		columns : [{
			display : '标题',
			name : 'title',
			align : 'center',
			minWidth : 140
		}, {
			display : '版块',
			name : 'discussionBoard',
			align : 'center',
			minWidth : 140,
			render: function(data){
				return data.discussionBoard.name;
			}
		}, {
			display : '发帖时间',
			name : 'createTime',
			align : 'center',
			minWidth : 140,
			render : function(data) {
				return data.createTime.substring(0, 10);
			}
		}, {
			display : '发帖人',
			name : 'createPerson',
			align : 'center',
			minWidth : 140,
			render: function(data){
				return data.createPerson.userName;
			}
		}, {
			display : '是否置顶',
			name : 'isTop',
			align : 'center',
			minWidth : 140,
			render: function(data){
				return data.isTop == 1 ? "是" : "否";
			}
		}],
		url : basePath + "/admin/ArticleAction!listArticles.action",
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