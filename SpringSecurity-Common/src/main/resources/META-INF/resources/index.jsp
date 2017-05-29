<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>SpringSecurity用户权限管理系统</title>
<link href="${basePath}styles/style.css" type="text/css" rel="stylesheet" />
<link href="${basePath}styles/second.css" type="text/css" rel="stylesheet" />
	

<%-- <link href="${basePath}scripts/My97DatePicker/skin/WdatePicker.css" type="text/css" rel="stylesheet" /> --%>
<link href="${basePath }js/jquery-ui-css/base/jquery.ui.all.css" type="text/css" rel="stylesheet"/>
<link href="${basePath}js/jQuery-contextMenu/jquery.contextMenu.css" type="text/css" rel="stylesheet"/>

<!-- JAVASCRIIPT -->
<script src="${basePath}js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}js/jquery-ui-1.9.2.min.js"></script>
<script type="text/javascript" src="${basePath}scripts/common/common.js"></script>
<%-- <script type="text/javascript" src="${basePath}scripts/jquery.PrintArea.js"></script>
<script type="text/javascript" src="${basePath}scripts/common/eTheme.js"></script> --%>
<%-- <script type="text/javascript" src="${basePath}scripts/My97DatePicker/WdatePicker.js"></script> --%>
<script src="${basePath}js/jQuery-contextMenu/jquery.ui.position.js" type="text/javascript"></script>
<script src="${basePath}js/jQuery-contextMenu/jquery.contextMenu.js" type="text/javascript"></script>
</head>

<body>
	<div style="width: 98%;margin: 0 auto;">
	<table style="width:100%">
		<c:if test="${topPath!='/WEB-INF/topblank.jsp'}">
		<tr>
			<td>
				<div style="height: 100px">
					<jsp:include page="${topPath}"></jsp:include>
				</div>
			</td>
		</tr>
		</c:if>
		<tr>
			<td>
			<jsp:include page="${boxPath }"></jsp:include>
				</td>
		</tr>
		<tr>
			<td><div>
					<jsp:include page="${footerPath }"></jsp:include>
				</div></td>
		</tr>
	</table>
</div>


</body>
</html>