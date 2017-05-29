<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/ss" prefix="ss"%>
<!--
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${basePath}styles/style.css" type="text/css" rel="stylesheet" />
</head>

<body>
-->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<div id="left_box">
        <h3>系统管理</h3>
        	<div class="div20px"></div>
        <ul>
        	
            <!-- <li><a href="#">用户设置</a></li>
            <li><a href="#">角色设置</a></li>
            <li><a href="#">权限设置</a></li> -->
            
            <ss:permission permission="/ss/Users/doList.do">
            	<li <c:if test="${lMenuSelect=='user'}"> id="li_b" </c:if>><a href="${ctx}/ss/Users/doList.do">用户管理</a></li>
            </ss:permission>
        	<ss:permission permission="/ss/Groups/doList.do">
        		<li <c:if test="${lMenuSelect=='group'}"> id="li_b" </c:if>><a href="${ctx}/ss/Groups/doList.do">群组管理</a></li>
        	</ss:permission>
        	<ss:permission permission="/ss/Roles/doList.do">
        		<li <c:if test="${lMenuSelect=='role'}"> id="li_b" </c:if>><a href="${ctx}/ss/Roles/doList.do">角色管理</a></li>
        	</ss:permission>
        	<ss:permission permission="/ss/Resources/doList.do">
        		<li <c:if test="${lMenuSelect=='resource'}"> id="li_b" </c:if>><a href="${ctx}/ss/Resources/doList.do">权限管理</a></li>
        	</ss:permission>
                  
			<ss:permission permission="/ss/UsersGroups/doList.do">
				<li <c:if test="${lMenuSelect=='usergroup'}"> id="li_b" </c:if>><a href="${ctx}/ss/UsersGroups/doList.do">用户群组管理</a></li>
			</ss:permission>
			<ss:permission permission="/ss/GroupsRoles/doList.do">
				<li <c:if test="${lMenuSelect=='grouprole'}"> id="li_b" </c:if>><a href="${ctx}/ss/GroupsRoles/doList.do">群组角色管理</a></li>
			</ss:permission>
        	<ss:permission permission="/ss/RolesResources/doList.do">
        		<li <c:if test="${lMenuSelect=='roleresource'}"> id="li_b" </c:if>><a href="${ctx}/ss/RolesResources/doList.do">角色权限管理</a></li>
        	</ss:permission>
        </ul>
        <div class="div10px"></div>
    </div>
<!--
</body>
</html>
-->