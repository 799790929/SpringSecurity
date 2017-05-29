<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<script type="text/javascript" src="<%=basePath%>scripts/public.js"></script>
<script type="text/javascript" >
		function doroles(roleid) {
			$("#roleid").val(roleid);
		}
	</script>

  		<div class="tags_bg">
	  		
	  		<div onclick="doroles('${role.id}')" class="tags_but" style="display:inline;">${role.name }</div>
  		</div>
  		
  		<div style="padding:10px;"></div>
  		
	  	<form action="${ctx}/ss/RolesResources/doSave.do" method="post"  id="roleresources" name="roleresources">
	  		<input type="hidden" value="${role.id }" name="roleid" id="roleid"/>
	     	<div>
		     	<table>
			     	<c:forEach items="${resources}" var="item" varStatus="status">
			     		<tr><td><input type="checkbox" name="resourceid" value="${item.id}"/>${item.name }</td></tr>
			     	</c:forEach>
		     	</table>
	     	</div>
	     	<div>
	     		<input type="submit" value="提交"/>
	     	</div>
     	</form>
