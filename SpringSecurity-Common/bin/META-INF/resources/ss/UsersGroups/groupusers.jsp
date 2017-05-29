<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@page import="cn.sf.ss.model.*" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

<script type="text/javascript" >
		function dogroups(groupid) {
			//$$("groupid").value=groupid;
			$("#groupid").val(groupid);
			var url = "${ctx}/ss/UsersGroups/initPage.do?groupid=" + groupid + "";
			window.location.href=url;
		}
		
		function relax(userid) {
			var groupid = $$("groupid").value;
			var url = "${ctx}/ss/UsersGroups/doDelete.do?userid=" + userid + "&groupid=" + groupid + "";
			window.location.href=url;
		}
	</script>
  </head>
  
  <body>
  		<div class="tags_bg">
	  		<c:forEach items="${groups}" var="item" varStatus="status">
	  			<div onclick="dogroups('${item.id}')" class="tags_but"    <c:if test="${item.id==group.id }"> style="background:#eee;color:#fff;" </c:if>>${item.name }</div>
	  		</c:forEach>
  		</div>
  		
  		<div style="padding:10px;"></div>
  		
  		<div>
  			<div style="display:inline;" onclick=""><a href="${ctx }/ss/UsersGroups/doSavePage.do?groupid=${group.id}">分配用户</a></div>
  		</div>
  		
	  	<input type="hidden" value="${group.id }" name="groupid" id="groupid"/>
	  	
     	<div>
	     	<table border="1">
	     		<tr><td width="200">用户姓名</td><td width="200">操作</td></tr>
		     	<c:forEach items="${users}" var="item" varStatus="status">
		     		<tr><td>${item.username }</td><td><a href="javascript:void(0)" onclick="relax('${item.id}')">解除关系</a></td></tr>
		     	</c:forEach>
	     	</table>
     	</div>
  </body>
  
  
</html>

