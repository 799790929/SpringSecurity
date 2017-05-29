<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>">
    <title>大众车辆监管系统</title>
<link href="${ctx}/styles/style.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/styles/second.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" >
		function doroles(roleid) {
			$("#roleid").val(roleid);
		}
		
		function cancel() {
			window.close();
		}
	</script>
	</head>
	<body>
	 <div id="tc_div" style="height:460px;">
    
    <!-- <h4>分配权限</h4> -->
    	<div class="div20px"></div><div class="div10px"></div>

  		<div class="tags_bg">
	  		<%-- <c:forEach items="${roles}" var="item" varStatus="status">
	  			<div onclick="doroles('${item.id}')" class="tags_but" style="display:inline;">${item.name }</div>
	  		</c:forEach> --%>
	  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色名:<div onclick="doroles('${role.id}')" class="tags_but" style="display:inline;">${role.name }</div>
  		</div>
  		
  		<div style="padding:10px;"></div>
  		
  		<!-- <div>
  			<div style="display:inline;">分配权限</div>
  			<div style="display:inline;">移除权限</div>
  		</div> -->
  		
	  	<%-- <form action="${ctx}/ss/RolesResources/doSave.do" method="post"  id="roleresources" name="roleresources">
	  		<input type="hidden" value="${role.id }" name="roleid" id="roleid"/>
	     	<div>
		     	<table>
			     	<c:forEach items="${resources}" var="item" varStatus="status">
			     		<tr><td><input type="checkbox" name="resourceid" value="${item.id}"/>${item.name }</td></tr>
			     	</c:forEach>
		     	</table>
	     	</div> --%>
	     	
	     	
	     	
	     	
	     	
	     	
	     	<div id="content">
	     	<form action="${ctx}/ss/RolesResources/doSave.do" method="post"  id="roleresources" name="roleresources">
	  		<input type="hidden" value="${role.id }" name="roleid" id="roleid"/>
            	<table width="100%" border="0"cellspacing="1" cellpadding="0"  style='background-color:#cdcdcd'>
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#e3e3e3">序号</td>
                        <td align="center" bgcolor="#e3e3e3">权限名</td>
                        <td align="center" bgcolor="#e3e3e3">权限路径</td>
                        <!-- <td align="center" bgcolor="#e3e3e3">操作</td> -->
                  </tr>
                  <c:forEach items="${resources}" var="item" varStatus="status">
	                  <tr bgcolor="#ffffff">
	                  	<td align="center" <c:if test="${status.count%2==0}">  bgcolor="#f6f6f6" </c:if>>${status.count}</td>
                        <td align="left" <c:if test="${status.count%2==0}">  bgcolor="#f6f6f6" </c:if>><input type="checkbox" name="resourceid" value="${item.id}"/>${item.name }</td>
                        <td align="left" <c:if test="${status.count%2==0}">  bgcolor="#f6f6f6" </c:if>>${item.url}</td>
                        <%-- <td align="center" <c:if test="${status.count%2==0}">  bgcolor="#f6f6f6" </c:if>>
                        	<a href="javascript:deleteResource('${ctx}/ss/Resources/doDeleteAjax.do?id=${item.id}')" class="type_c">【删除】</a>
                        </td> --%>
	                  </tr>
                  </c:forEach>
              </table>
              </form>
              
              
              <form class="formpage" id='doSearch' action='${ctx}/ss/RolesResources/doSavePage.do' method='post' name='doSearch'>
					<input type='hidden' name='requestPage'  id='requestPage' value='${requestPage}'/>
					<%-- <input type='hidden' name='username' value="${username}"/> --%>
				
					<a href="javascript:void(0)" onclick="doPage(1)">首页</a>&nbsp;&nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0)" onclick='doPage("${requestPage-1}");'>上一页 </a>&nbsp;&nbsp;&nbsp;&nbsp; ${requestPage}/${pagesCount} &nbsp;&nbsp;&nbsp;&nbsp;      
					<a href="javascript:void(0)" onclick='doPage("${requestPage + 1}");'>下一页 </a>&nbsp;&nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0)" onclick='doPage("${pagesCount}")'>尾页 </a>
				</form>
				
              
              <div>
	     	<div class="div15px"></div>&nbsp;&nbsp;&nbsp;
	              <input type="button" class="btn_a" value="提交" onclick="roleresource.doSave('${ctx}/ss/RolesResources/doSaveAjax.do')"/>
	&nbsp;&nbsp;
	<input type="button" class="btn_a" value="取消" onclick="cancel()"/>
	     		<!-- <input type="submit" value="提交"/> -->
	     	</div>
     	
              
				<!-- <div id="f_page">首页&nbsp;&nbsp;&nbsp;&nbsp; 上一页&nbsp;&nbsp;&nbsp;&nbsp; 1/3 &nbsp;&nbsp;&nbsp;&nbsp;      下一页&nbsp;&nbsp;&nbsp;&nbsp; 尾页 </div> -->
     </div>
	     	
	     	
	     	
	     	
	     	
	     	
     </div>
</body>