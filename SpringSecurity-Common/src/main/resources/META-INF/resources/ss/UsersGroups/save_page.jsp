<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<%-- <link href="<c:url value="/styles/public.css"/>" type="text/css" rel="stylesheet"> --%>
<script type="text/javascript" src="<%=basePath%>scripts/public.js"></script>
<script type="text/javascript" >
		function dogroups(groupid) {
			//$$("groupid").value=groupid;
			$("#groupid").val(groupid);
		}
	</script>
  
  		<div class="tags_bg">
	  		<%-- <c:forEach items="${groups}" var="item" varStatus="status">
	  			<div onclick="dogroups('${item.id}')" class="tags_but" style="display:inline;">${item.name }</div>
	  		</c:forEach> --%>
	  		<div onclick="dogroups('${group.id}')" class="tags_but" style="display:inline;">${group.name }</div>
  		</div>
  		
  		<div style="padding:10px;"></div>
  		
  		<!-- <div>
  			<div style="display:inline;">分配用户</div>
  			<div style="display:inline;">移除用户</div>
  		</div> -->
  		
	  	<form action="${ctx}/ss/UsersGroups/doSave.do" method="post"  id="groupusers" name="groupusers">
	  		<input type="hidden" value="${group.id }" name="groupid" id="groupid"/>
	     	<div>
		     	<table>
			     	<c:forEach items="${users}" var="item" varStatus="status">
			     		<tr><td><input type="checkbox" name="userid" value="${item.id}"/>${item.username }</td></tr>
			     	</c:forEach>
		     	</table>
	     	</div>
	     	<div>
	     		<input type="submit" value="提交"/>
	     	</div>
     	</form>
