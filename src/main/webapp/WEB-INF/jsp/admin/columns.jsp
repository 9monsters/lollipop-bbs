<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理平台-栏目管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/easyui/themes/default/easyui.css"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/easyui/themes/icon.css"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/search.css"/>

<link href="${pageContext.request.contextPath}/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet"type="text/css" />

<link href="${pageContext.request.contextPath}/lib/ligerUI/skins/Gray/css/dialog.css" rel="stylesheet"type="text/css" />

<link href="${pageContext.request.contextPath}/css/ui-lightness/jquery-ui-1.8.23.custom.css"  rel="stylesheet"type="text/css"  />

<link href="${pageContext.request.contextPath}/common/css/global.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/jquery/jquery-ui-1.8.23.custom.min.js" type="text/javascript"></script> 

<script src="${pageContext.request.contextPath}/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/jquery/TC.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/lib/easyui/jquery.easyui.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/common/js/common.js" type="text/javascript"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/lib/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/columns.js"></script>
<script type="text/javascript">
	var basePath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<div id="layout1">
		<div position="center" title="">
			<div class="easyui-panel">
				<div id='toolbar'>
					<div class="easyui-panel" title="操作">
						<div class="toolbar"
							style='padding-bottom: 5px; padding-top: 5px; padding-left: 5px;'>
							<div class="l-cuz-btn_" id="add">
								<div class="l-cuz-btn-l_"></div>
								<div class="l-cuz-btn-r_"></div>
								<div class="l-cuz-btn-inner_">添加</div>
							</div>
							<div class="l-cuz-btn_" id="edit">
								<div class="l-cuz-btn-l_"></div>
								<div class="l-cuz-btn-r_"></div>
								<div class="l-cuz-btn-inner_">修改</div>
							</div>
							<div class="l-cuz-btn_" id="delete">
								<div class="l-cuz-btn-l_"></div>
								<div class="l-cuz-btn-r_"></div>
								<div class="l-cuz-btn-inner_">删除</div>
							</div>
						</div>
					</div>
				</div>
				<div id='search_panel'
					style="padding-top: 2px; padding-bottom: 2px;">
					<div id="search_">
						<div id="search_main">
							<table>
								<tbody>
									<tr>
										<th>栏目名称</th>
										<td><input id="columnName" class="l-text" /></td>
										<td>
											<div class="l-cuz-btn_" id="search">
												<div class="l-cuz-btn-l_"></div>
												<div class="l-cuz-btn-r_"></div>
												<div class="l-cuz-btn-inner_">查询</div>
											</div>
										</td>
										<td>
											<div class="l-cuz-btn_" id="reset">
												<div class="l-cuz-btn-l_"></div>
												<div class="l-cuz-btn-r_"></div>
												<div class="l-cuz-btn-inner_">清空</div>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div id="listColumns"></div>
		</div>
	</div>
</body>
</html>