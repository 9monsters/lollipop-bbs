var sysMenus = {
	"actionErrors" : [],
	"actionMessages" : [],
	"errorMessages" : [],
	"errors" : {},
	"fieldErrors" : {},
	"fnVo" : null,
	"functions" : [
			{
				"funName" : "论坛管理平台",
				"rolesFuncitons" : [],
				"pId" : 0,
				"url" : "",
				"status" : 0,
				"funCode" : "member",
				"_url" : "",
				"name" : "论坛管理平台",
				"functions" : [],
				"sortId" : 0,
				"id" : 1,
				"isLeaf" : 0,
				"funType" : 0
			},
			{
				"isLeaf" : 1,
				"name" : "用户管理",
				"pId" : 1,
				"funType" : 0,
				"funName" : "用户管理",
				"_url" : "users_page.jspx",
				"funCode" : "article",
				"functions" : [],
				"status" : 0,
				"id" : 11,
				"sortId" : 0,
				"url" : "",
				"function" : {
					"rolesFuncitons" : [],
					"functions" : [],
					"id" : 21,
					"sortId" : 0,
					"funType" : 0,
					"status" : 0
				},
				"rolesFuncitons" : []
			},
			{
				"isLeaf" : 1,
				"name" : "栏目管理",
				"pId" : 1,
				"funType" : 0,
				"funName" : "栏目管理",
				"_url" : "columns_page.jspx",
				"funCode" : "column",
				"functions" : [],
				"status" : 0,
				"id" : 12,
				"sortId" : 0,
				"url" : "",
				"function" : {
					"rolesFuncitons" : [],
					"functions" : [],
					"id" : 21,
					"sortId" : 0,
					"funType" : 0,
					"status" : 0
				},
				"rolesFuncitons" : []
			},
			{
				"isLeaf" : 1,
				"name" : "版块管理",
				"pId" : 1,
				"funType" : 0,
				"funName" : "版块管理",
				"_url" : "boards_page.jspx",
				"funCode" : "board",
				"functions" : [],
				"status" : 0,
				"id" : 13,
				"sortId" : 0,
				"url" : "",
				"function" : {
					"rolesFuncitons" : [],
					"functions" : [],
					"id" : 21,
					"sortId" : 0,
					"funType" : 0,
					"status" : 0
				},
				"rolesFuncitons" : []
			},
			{
				"isLeaf" : 1,
				"name" : "文章管理",
				"pId" : 1,
				"funType" : 0,
				"funName" : "文章管理",
				"_url" : "articles_page.jspx",
				"funCode" : "article",
				"functions" : [],
				"status" : 0,
				"id" : 14,
				"sortId" : 0,
				"url" : "",
				"function" : {
					"rolesFuncitons" : [],
					"functions" : [],
					"id" : 21,
					"sortId" : 0,
					"funType" : 0,
					"status" : 0
				},
				"rolesFuncitons" : []
			}
			],
	"ids" : null,
	"locale" : "zh_CN",
	"pageNo" : 1,
	"pagesize" : 10,
	"rows" : null,
	"start" : 0,
	"sysFunctionsById" : "JSON",
	"texts" : null,
	"total" : 0
};

var curMenu = null, zTree_Menu = null;
var setting = {
	view : {
		showLine : false,
		showIcon : false,
		selectedMulti : false,
		dblClickExpand : false,
		addDiyDom : addDiyDom
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onClick : menuClick
	}
};

function menuClick(event, treeId, treeNode) {
	if (!treeNode._url) {
		return;
	}
	tab.addTabItem({
		url : basePath + "/admin/" + treeNode._url,
		text : treeNode.name,
		tabid : treeNode.id
	});			
}

function addDiyDom(treeId, treeNode) {
	var spaceWidth = 5;
	var switchObj = $("#" + treeNode.tId + "_switch"), icoObj = $("#"
			+ treeNode.tId + "_ico");
	switchObj.remove();
	icoObj.before(switchObj);

	if (treeNode.level > 1) {
		var spaceStr = "<span style='display: inline-block;width:"
				+ (spaceWidth * treeNode.level) + "px'></span>";
		switchObj.before(spaceStr);
	}
}

function beforeClick(treeId, treeNode) {
	if (treeNode.level == 0 || treeNode._url == "") {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.expandNode(treeNode);
		return false;
	}
	return true;
}

function createFunctionTree() {
	$("#indexTree").parent().append("<div class='l-tree-loading'></div>");
	$("#indexTree").empty();
	var treeObj = $("#treeDemo");
	if (supperType == "1")
		$.fn.zTree.init(treeObj, setting, supplier.functions);
	else
		$.fn.zTree.init(treeObj, setting, sysMenus.functions);
	zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
	treeObj.addClass("showIcon");
}

function openEditWin(type, isFor) {
	CommonUtils.openWindow("", "", 1024, 700);
}

var tab;
$(document).ready(function() {
	$("#layout1").ligerLayout({
		leftWidth : 200,
		topHeight : 80
	});
	$("#tab1").ligerTab({
		height : '100%'
	});
	tab = $("#tab1").ligerGetTabManager();
	createFunctionTree();
});

function openTab(url, title){
	var tab1 = $("#tab1").ligerGetTabManager();
	tab1.addTabItem({
		url : url,
		text : title,
		tabid : 0
	});	
}