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
			//$$("roleid").value=roleid;
			$("roleid").val(roleid);
		}
		
		function cancel() {
			window.close();
		}
	</script>
  </head>
  
  <body>
  
  <div id="tc_div" style="height:460px;">
    
    <!-- <h4>分配群组</h4> -->
    	<div class="div20px"></div><div class="div10px"></div>
  
  
  		<div class="tags_bg">
	  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色名:<div onclick="doroles('${role.id}')" class="tags_but" style="display:inline;">${role.name }</div>
  		</div>
  		
  		<div style="padding:10px;"></div>
  		
	     	
	     	<div id="content">
	     	<form action="${ctx}/ss/GroupsRoles/doSave.do" method="post"  id="rolegroups" name="rolegroups">
	  		<input type="hidden" value="${role.id }" name="roleid" id="roleid"/>
            	<table width="100%" border="0"cellspacing="1" cellpadding="0"  style='background-color:#cdcdcd'><!-- #e2e2e2 -->
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#e3e3e3">序号</td>
                        <td align="center" bgcolor="#e3e3e3">群组名</td>
                  </tr>
                  <c:forEach items="${groups}" var="item" varStatus="status">
	                  <tr bgcolor="#ffffff">
	                  	<td align="center" <c:if test="${status.count%2==0}">  bgcolor="#f6f6f6" </c:if>>${status.count}</td>
                        <td align="left" <c:if test="${status.count%2==0}">  bgcolor="#f6f6f6" </c:if>><input type="checkbox" name="groupid" value="${item.id}"/>${item.name }</td>
                        
	                  </tr>
                  </c:forEach>
              </table>
              </form>
              
              
              <form class="formpage" id='doSearch' action='${ctx}/ss/GroupsRoles/doSavePage.do' method='post' name='doSearch'>
					<input type='hidden' name='requestPage'  id='requestPage' value='${requestPage}'/>
				
					<a href="javascript:void(0)" onclick="doPage(1)">首页</a>&nbsp;&nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0)" onclick='doPage("${requestPage-1}");'>上一页 </a>&nbsp;&nbsp;&nbsp;&nbsp; ${requestPage}/${pagesCount} &nbsp;&nbsp;&nbsp;&nbsp;      
					<a href="javascript:void(0)" onclick='doPage("${requestPage + 1}");'>下一页 </a>&nbsp;&nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0)" onclick='doPage("${pagesCount}")'>尾页 </a>
				</form>
              
              
              <div>
	     	<div class="div15px"></div>&nbsp;&nbsp;&nbsp;
	              <input type="button" class="btn_a" value="提交" onclick="grouprole.doSave('${ctx}/ss/GroupsRoles/doSaveAjax.do')"/>
	&nbsp;&nbsp;
	<input type="button" class="btn_a" value="取消" onclick="cancel()"/>
	     	</div>
              
     </div>
	     	
     </div>
  </body>
  
  
</html>
