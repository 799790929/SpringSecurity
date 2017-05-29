<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<%-- <link href="<c:url value="/styles/public.css"/>" type="text/css" rel="stylesheet"> --%>
<script type="text/javascript" src="<%=basePath%>scripts/public.js"></script>
<script type="text/javascript" >
		function doroles(roleid) {
			//$$("roleid").value=roleid;
			$("roleid").val(roleid);
		}
	</script>
  </head>
  
  <body>
  		<div class="tags_bg">
	  		
	  		<div onclick="doroles('${role.id}')" class="tags_but" style="display:inline;">${role.name }</div>
  		</div>
  		
  		<div style="padding:10px;"></div>
  		
  		
	  	<form action="${ctx}/ss/GroupsRoles/doSave.do" method="post"  id="rolegroups" name="rolegroups">
	  		<input type="hidden" value="${role.id }" name="roleid" id="roleid"/>
	     	<div>
		     	<table>
			     	<c:forEach items="${groups}" var="item" varStatus="status">
			     		<tr><td><input type="checkbox" name="groupid" value="${item.id}"/>${item.name }</td></tr>
			     	</c:forEach>
		     	</table>
	     	</div>
	     	<div>
	     		<input type="submit" value="提交"/>
	     	</div>
     	</form>
  </body>
  
  
</html>
