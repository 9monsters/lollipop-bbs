function CommonUtils() {
}
/**
 * 打开窗体
 * 
 * @param url
 * @param winName
 * @param width
 * @param height
 */
CommonUtils.openWindow = function(url, winName, width, height) {
	var openWindow;
	var top = (window.screen.height - height) / 2;
	var left = (window.screen.width - width) / 2;
	openWindow = window
			.open(url,
					winName,
					'height='
							+ height
							+ ', width='
							+ width
							+ ', top='
							+ top
							+ ', left='
							+ left
							+ ', toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=n o, status=no');
//	if(openWindow){
//		openWindow.focus();// 让窗口获取焦点
//		openWindows.push(openWindow);
//	}
	return openWindow;
}
/**
 * 初始化校验器
 * 
 * @param cfg
 *            {form : 'InofForm'} //optional
 * @return
 * @example <code>
 //初始化（也可以传递一个具体的Form ID作为参数）
 var validator = null;
 $(function() {
 validator = initValidator();
 }); 

 //form提交时
 if(!validator.form())return;
 </code>
 */
CommonUtils.initValidator = function(cfg) {
	$.metadata.setType("attr", "validate");
	var form = (cfg && cfg.form) ? "#" + cfg.form : "form";
	var validator = $(form)
			.validate(
					{
						onfocusout : function(element) {
							if (!this.checkable(element)
									&& (element.name in this.submitted || !this
											.optional(element))) {
								if (this.element(element)) {
									$(element).removeClass(
											"x-form-text-invalid");
								} else {
									$(element).addClass("x-form-text-invalid");
								}
							}
						},
						onkeyup : function(element) {
							if (element.name in this.submitted
									|| element == this.lastElement) {
								if (this.element(element)) {
									$(element).removeClass(
											"x-form-text-invalid");
								} else {
									$(element).addClass("x-form-text-invalid");
								}
							}
						},
						errorPlacement : function(lable, element) {
							element.addClass("x-form-text-invalid");
							lable
									.css({
										"color" : "red",
										"padding-left" : "18px",
										"background" : 'url("../../images/unchecked.gif") no-repeat 0px 0px'
									});
							if (element.parent().get(0).tagName == "TD") {
								element.parent().append("<div></div>");
								lable.appendTo(element.parent());
							} else {
								lable.appendTo(element.parent().parent());
							}
						},
						submitHandler : function() {
							$("input", $(form)).removeClass(
									"x-form-text-invalid");
							$("textarea", $(form)).removeClass(
									"x-form-text-invalid");
						}
					});
	//$(form).ligerForm();
	return validator;
}
/**
 * 基于jquery组件同步取数据，要使用该方法，必须导入 TC.js
 * 
 * @param url
 * @return
 */
CommonUtils.getData = function(url) {
	var tmp;
	$.json(url, function(json) {
		tmp = json;
	}, true);
	return tmp;
}
CommonUtils.close = function() {
	parent.opener.openWindow.close();
}
/**
 * 根据浏览器的不同选择alert方式
 */
CommonUtils.alert = function(data) {
	var alertType;
	if ($.browser.msie || $.browser.compatible) {// IE
		alertType = alert(data);
	} else if ($.browser.opera) {// opera
		alertType = $.ligerDialog.alert(data);
	} else if ($.browser.gecko) {// mozilla
		alertType = $.ligerDialog.alert(data);
	} else {// others
		alertType = alert(data);
	}
	return alertType;
}




/**
 * 获取随机数
 */
r = function() {
	return Math.floor(Math.random() * 99999999 + 1);// 0-23
}

$(function() {
	$(".l-cuz-btn_").mouseover(function() {
		$(this).addClass("l-cuz-btn-over_");
	}).mouseout(function() {
		$(this).removeClass("l-cuz-btn-over_");
	});
});

/**
 * 获取3位随机数
 */
r2 = function(){
	var no = Math.floor(Math.random() * 999 + 1);// 100-999
	if(r2>=1000 || r2<=100){
		return r2();
	}
	return no+"";
}

getEnterNo = function(){
	var no = r2();
	var date = new Date();
	return date.getTime() + "" + no;
}

function openTab(name, zTree) {
	var nodes = zTree.getNodes();
	for ( var i = 0; i < nodes.length; i++) {
		var node = nodes[i];
		checkNodeEq(zTree, node, name);
	}
}

function checkNodeEq(zTree, node, name) {
	if (node.name == name) {
		// alert("#"+node.tId+"_span");
		// $("#"+node.tId+"_span").click();
		zTree.selectNode(node);
		parent.menuClick(window.event, zTree.setting.treeId, node);
		return true;
	}
	if (node.children && node.children.length >= 1) {
		for ( var i = 0; i < node.children.length; i++) {
			var node1 = node.children[i];
			if (checkNodeEq(zTree, node1, name))
				break;
		}
	}
	return false;
}

function request(paras) {
	var url = location.href;
	var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
	var paraObj = {}
	for (i = 0; j = paraString[i]; i++) {
		paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j
				.indexOf("=") + 1, j.length);
	}
	var returnValue = paraObj[paras.toLowerCase()];
	if (typeof (returnValue) == "undefined") {
		return "";
	} else {
		return returnValue;
	}
}

function getRdValu(name){
	return $("input[\'name=\""+name+"\'\"][checked]").val();
}
function isHnairEmail(str){
	var reg =/^([a-zA-Z0-9_-])+@hnair.com+/;
	return reg.test(str);
}
