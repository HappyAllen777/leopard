<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="/WEB-INF/page/system/source.jsp"/>
<html>
<head>

<title>小伦哥后台</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="${project}/static/oss/amazeui/amazeui.min.css">
<link rel="stylesheet" href="${project}/static/oss/amazeui/admin.css">
<link rel="shortcut icon" href="${project}/static/image/common/favicon.ico" />
</head>

<body>
	<jsp:include page="/WEB-INF/page/system/header.jsp"></jsp:include>
	<div class="am-cf admin-main">
		<jsp:include page="/WEB-INF/page/system/menu.jsp"></jsp:include>
		<div class="admin-content" style="padding-top:51px">
			<iframe name="content" style="width:100%;height:100%"></iframe>
		</div>
	</div>
</body>
</html>
