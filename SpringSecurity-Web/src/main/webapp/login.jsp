<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.web.WebAttributes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上海-用户登录</title>
<link href='<c:url value="/styles/login.css"/>' type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="box">
		<form action="login" method="post">
			<div id="login">
				<div class="hh">
					<input name="username" type="text" class="input">
				</div>
				<div class="hh" style="margin-top: 24px; margin-bottom: 30px;">
					<input name="password" type="password" class="input">
				</div>
				<div class="hh" style="height: 20px;">
					<table width="122%" border="0">
						<tr>
							<td width="25%"><!--<input name="" type="checkbox" value="" />&nbsp;&nbsp;下次自动登录--></td>
							<td width="75%">
								<!--<a href="#">忘记密码</a>-->
								<a><span style="color:red">
								<%=session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) %>
				<%if(session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) != null){ %>
					用户名或密码不正确
					
				<%} %>
				</span></a>
							</td>
						</tr>
					</table>
	
				</div>
				<div class="hh">
					<input name="提交" type="submit" id="btn" value="登录" />
				</div>
			</div>
		</form>
	</div>
	
	<div id="footer">
		com.byron版权所有    系统支持热线:021_64822292
	</div>
</body>
</html>