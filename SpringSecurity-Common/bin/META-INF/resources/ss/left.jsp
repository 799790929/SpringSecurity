<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    <div id="left_box">
        <h3>系统管理</h3>
        	<div class="div20px"></div>
        <ul>
        	<li <c:if test="${lMenuSelect=='user'}"> id="li_b" </c:if>><a href="${ctx}/ss/Users/doList.do">用户管理</a></li>
        	<li <c:if test="${lMenuSelect=='group'}"> id="li_b" </c:if>><a href="${ctx}/ss/Groups/doList.do">群组管理</a></li>
            <li <c:if test="${lMenuSelect=='role'}"> id="li_b" </c:if>><a href="${ctx}/ss/Roles/doList.do">角色管理</a></li>
            <li <c:if test="${lMenuSelect=='resource'}"> id="li_b" </c:if>><a href="${ctx}/ss/Resources/doList.do">权限管理</a></li>      
        </ul>
        <div class="div10px"></div>
        
        
        <ul>
        	<li <c:if test="${lMenuSelect=='usergroup'}"> id="li_b" </c:if>><a href="${ctx}/ss/UsersGroups/doList.do">用户群组管理</a></li>
        	<li <c:if test="${lMenuSelect=='grouprole'}"> id="li_b" </c:if>><a href="${ctx}/ss/GroupsRoles/doList.do">群组角色管理</a></li>
            <li <c:if test="${lMenuSelect=='roleresource'}"> id="li_b" </c:if>><a href="${ctx}/ss/RolesResources/doList.do">角色权限管理</a></li>    
        </ul>
        <div class="div10px"></div>
        
        
    </div>
