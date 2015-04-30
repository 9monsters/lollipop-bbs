<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	alert("登录超时，请重新登录!");
	window.parent.location.href = "${pageContext.request.contextPath}/admin/login_page.jsp";
</script>
