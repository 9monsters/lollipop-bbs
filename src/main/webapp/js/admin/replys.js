var listReply;
var isFor, isAnother;
var CustomersData = {
	Rows : [{
		"id" : "1",
		"title" : "期末考试通知",
		"content" : "测试回复。。。",
		"replyTime" : "2013-02-02 00:00:00",
		"replyPerson" : "李四"
	}],
	Total : 3
};

$(function() {
	listReply = $("#listReply").ligerGrid({
		checkbox : true,
		columns : [{
			display : '回复标题',
			name : 'title',
			align : 'left',
			minWidth : 140
		}, {
			display : '内容',
			name : 'content',
			align : 'left',
			minWidth : 250
		}, {
			display : '回复时间',
			name : 'replyTime',
			align : 'center',
			minWidth : 140
		}, {
			display : '回复人',
			name : 'replyPerson',
			align : 'center',
			minWidth : 140
		}],
		data : CustomersData,
		height : "99%",
		width : "99%",
		pageSize : 30
	});
	
	//添加回复
	$("#add").click(function() {
		CommonUtils.openWindow("back_add_article_reply.html", '添加回复', 1024, 700);
	});

	//修改回复
	$("#edit").click(function() {
		CommonUtils.openWindow("back_edit_article_reply.html", '修改回复', 1024, 700);
	});

	//删除回复
	$("#delete").click(function() {
		alert("删除成功.");
	});

	//查询回复内容
	$("#search").click(function() {
				
	});

});

function openEditWin(type, isFor) {
	CommonUtils.openWindow("", '标题', 1024, 700);
}