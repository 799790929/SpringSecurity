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
		function dogroups(groupid) {
			//$$("groupid").value=groupid;
			$("#groupid").val(groupid);
		}
		
		function cancel() {
			window.close();
		}
		
		function submitForm(formid){
			//$$(formid).submit();
			$("#"+formid).submit();
		}
		
		/*分页查询*/
		/*function doPage(requestPage){
			$("#requestPage").val(requestPage);
			$("#doSearch").submit();
		}*/
	</script>
	</head>
	
	
	<body>
	<div id="tc_div" style="height:600px;">
    
    <!-- <h4>分配用户</h4> -->
    	<div class="div20px"></div><div class="div10px"></div>
  
  		<div class="tags_bg">
	  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;群组名:<div onclick="dogroups('${group.id}')" class="tags_but" style="display:inline;">${group.name }</div>
  		</div>
  		
  		<div style="padding:10px;"></div>
  		
	  	<%-- <form action="${ctx}/ss/UsersGroups/doSave.do" method="post"  id="groupusers" name="groupusers">
	  		<input type="hidden" value="${group.id }" name="groupid" id="groupid"/> --%>
	     	<%-- <div>
		     	<table>
			     	<c:forEach items="${users}" var="item" varStatus="status">
			     		<tr><td><input type="checkbox" name="userid" value="${item.id}"/>${item.username }</td></tr>
			     	</c:forEach>
		     	</table>
	     	</div> --%>
	     	
	     	
	     	
	     	
	     	
	     	
	     	<div id="content">
	     	
	     	<form action="${ctx}/ss/UsersGroups/doSave.do" method="post"  id="groupusers" name="groupusers">
	  			<input type="hidden" value="${group.id }" name="groupid" id="groupid"/>
            	<table width="100%" border="0" cellspacing="1" cellpadding="0"  style='background-color:#cdcdcd'>
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#e3e3e3">序号</td>
                        <td align="center" bgcolor="#e3e3e3">用户名</td>
                        <td align="center" bgcolor="#e3e3e3">真实姓名</td>
                        <td align="center" bgcolor="#e3e3e3">添加时间</td>
                        <!-- <td align="center" bgcolor="#e3e3e3">操作</td> -->
	                  </tr>
	                  <c:forEach items="${users}" var="item" varStatus="status">
		                  <tr bgcolor="#ffffff">
		                  	<td align="center" <c:if test="${status.count%2==0}">  bgcolor="#f6f6f6" </c:if>>${status.count}</td>
	                        <td align="left" <c:if test="${status.count%2==0}">  bgcolor="#f6f6f6" </c:if>><input type="checkbox" name="userid" value="${item.id}"/>${item.username }</td>
	                        <td align="left" <c:if test="${status.count%2==0}">  bgcolor="#f6f6f6" </c:if>>${item.realname}</td>
	                        <td align="left" <c:if test="${status.count%2==0}">  bgcolor="#f6f6f6" </c:if>>${item.createdt}</td>
	                        <%-- <td align="center" <c:if test="${status.count%2==0}">  bgcolor="#f6f6f6" </c:if>>
		                        <a href="javascript:deleteUser('${ctx}/ss/Users/doDeleteAjax.do?id=${item.id}')" class="type_c">【删除】</a>
	                        </td> --%>
		                  </tr>
	                  </c:forEach>
              	</table>
              	
              	
            </form>
			<!-- <div id="f_page"> -->
				<form class="formpage" id='doSearch' action='${ctx}/ss/UsersGroups/doSavePage.do' method='post' name='doSearch'>
					<input type='hidden' name='requestPage'  id='requestPage' value='${requestPage}'/>
					<%-- <input type='hidden' name='username' value="${username}"/> --%>
				
					<a href="javascript:void(0)" onclick="doPage(1)">首页</a>&nbsp;&nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0)" onclick='doPage("${requestPage-1}");'>上一页 </a>&nbsp;&nbsp;&nbsp;&nbsp; ${requestPage}/${pagesCount} &nbsp;&nbsp;&nbsp;&nbsp;      
					<a href="javascript:void(0)" onclick='doPage("${requestPage + 1}");'>下一页 </a>&nbsp;&nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0)" onclick='doPage("${pagesCount}")'>尾页 </a>
				</form>
			<!-- </div>  -->
			
			
			<div>
		     		<div class="div15px"></div>&nbsp;&nbsp;&nbsp;
		              <input type="button" class="btn_a" value="提交" onclick="usergroup.doSave('${ctx}/ss/UsersGroups/doSaveAjax.do')"/>&nbsp;&nbsp;
		              <input type="button" class="btn_a" value="取消" onclick="cancel()"/>
		     		<!-- <input type="submit" value="提交"/> -->
	     		</div>
			
    	 	</div>
     
     
	     	
     	
     </div>
</body>
</html>