<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<%-- <link href="<c:url value="/styles/public.css"/>" type="text/css" rel="stylesheet"> --%>
<script type="text/javascript" src="<%=basePath%>scripts/public.js"></script>
<script type="text/javascript" >
		function doroles(roleid) {
			$("#roleid").val(roleid);
		}
	</script>

  		<div class="tags_bg">
	  		<%-- <c:forEach items="${roles}" var="item" varStatus="status">
	  			<div onclick="doroles('${item.id}')" class="tags_but" style="display:inline;">${item.name }</div>
	  		</c:forEach> --%>
	  		<div onclick="doroles('${role.id}')" class="tags_but" style="display:inline;">${role.name }</div>
  		</div>
  		
  		<div style="padding:10px;"></div>
  		
  		<!-- <div>
  			<div style="display:inline;">分配资源</div>
  			<div style="display:inline;">移除资源</div>
  		</div> -->
  		
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
