<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
-->
<div id="box" style="width:100%">
<table style="width:100%" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top" style="width:150px;background-color: #efefef">
			<div id="left_P">
				<jsp:include page="${leftPath }"></jsp:include>
			</div>
		</td>
		<td style="width:2px;overflow: hidden;"></td>
		<td >
			<div id="right_P"  style="width:100%">
			<div id="right_box">
				<jsp:include page="${rightPath }"></jsp:include>
				</div>
			</div>
		</td>
	<tr>
</table>

</div>
<!--
</body>
</html>
-->