<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link href="<c:url value="/styles/public.css"/>" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=basePath%>scripts/public.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/alert.js"></script>
  </head>
  
  <body>
    <table width="100%">
		<tr style="height:800px;">
			<td id="meneTd" width="171" valign="top">
				<div id="lmenu" class="lmenu">
						 <div class="vmenu_title">
						  <h2>用户群组角色资源维护</h2>
						 </div>
						<ul class="menu">
							<li onClick="menulist.showlist(this);"  <c:if test="${sfSelectIndex=='users' }"> style="background:#eee;color:#fff;" </c:if>>
								<span></span>
								<span class="icon_small_1 icon_set18" onClick=""><a href="${ctx }/ss/Users/list.do?sfSelectIndex='users'">用户维护</a></span>
							</li>
							<li onClick="menulist.showlist(this);"  <c:if test="${sfSelectIndex=='groups' }"> style="background:#eee;color:#fff;" </c:if>>
								<span class=""></span>
								<span class="icon_small_1 icon_set19" onClick=""><a href="${ctx }/ss/Groups/list.do?sfSelectIndex='groups'">群组维护</a></span>
							</li>
							<li onClick="menulist.showlist(this);"  <c:if test="${sfSelectIndex=='roles' }"> style="background:#eee;color:#fff;" </c:if>>
								<span class=""></span>
								<span class="icon_small_1 icon_set19" onClick=""><a href="${ctx }/ss/Roles/list.do?sfSelectIndex='roles'" >权限维护</a></span>
							</li>
							<li onClick="menulist.showlist(this);"  <c:if test="${sfSelectIndex=='resources' }"> style="background:#eee;color:#fff;" </c:if>>
								<span class=""></span>
								<span class="icon_small_1 icon_set19" onClick=""><a href="${ctx }/ss/Resources/list.do?sfSelectIndex='resources'" >资源维护</a></span>
							</li>
						</ul>
				
				
				
						<div class="vmenu_title">
						  <h2>权限管理</h2>
						 </div>
						<ul class="menu">
							<li onClick="menulist.showlist(this);"  <c:if test="${sfSelectIndex=='groupusers' }"> style="background:#eee;color:#fff;" </c:if>>
								<span></span>
								<span class="icon_small_1 icon_set18" onClick=""><a href="${ctx }/ss/UsersGroups/initPage.do?sfSelectIndex='groupusers'">用户群组维护</a></span>
							</li>
							<li onClick="menulist.showlist(this);"  <c:if test="${sfSelectIndex=='rolegroups' }"> style="background:#eee;color:#fff;" </c:if>>
								<span class=""></span>
								<span class="icon_small_1 icon_set19" onClick=""><a href="${ctx }/ss/GroupsRoles/initPage.do">群组权限维护</a></span>
							</li>
							<li onClick="menulist.showlist(this);"  <c:if test="${sfSelectIndex=='roleresources' }"> style="background:#eee;color:#fff;" </c:if>>
								<span class=""></span>
								<span class="icon_small_1 icon_set19" onClick=""><a href="${ctx }/ss/RolesResources/initPage.do" >权限资源维护</a></span>
							</li>
						</ul>
				</div>
			</td>
			<td valign="top" id="menuright">
				<jsp:include page="${mainPage }"></jsp:include>
			</td>
		</tr>
	</table>
  </body>
</html>
