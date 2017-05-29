<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> -->
<html>
  <head>
    <base href="<%=basePath%>">
    <title>大众车辆监管系统</title>
<link href="${ctx}/styles/style.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/styles/second.css" type="text/css" rel="stylesheet" />
<script>
function cancel() {
	window.close();
}

var name_not_null = "角色名不能为空!";
var scope_not_null = "数据范围不能为空!";
function checkForm(frm) {
	var msg = new Array();
	var err = false;
	
	if(frm.elements['name'] && frm.elements['name'].value.trim().length==0) {
		msg.push(name_not_null); 
		err = true;
	}
	
	if(frm.elements['scope'] && frm.elements['scope'].value.trim().length==0) {
		msg.push(scope_not_null);
		err = true;
	}
	
	if(err) {
		message = msg.join("\n");
		dialog.msgAlert(message);
	}
	
	return !err;
}
</script>

  </head>
  
  <body>
    <div id="tc_div" style="height:460px;">
    
    
    <!-- <h4>角色信息编辑</h4> -->
    	<div class="div20px"></div><div class="div10px"></div>
        
        <div style=" padding-left:30px;">
        <form action="${ctx}/ss/Roles/doSave.do" method="post" id="form" name="form" onSubmit="return checkForm(this)">
        <input type="hidden" value="${roles.id}" name="id"/>
	    	<table width="96%" border="0" align="center">
	          <tr>
	            <td width="16%" align="right" valign="top">角色名</td>
	            <td width="71%"><input name="name" value="<c:out value="${roles.name}"/>" readOnly type="text" class="input_a" /></td>
	            <td width="13%">&nbsp;</td>
	          </tr>
	           <tr>
	            <td width="16%" align="right" valign="top">所属类型</td>
	            <td width="71%">
	            <c:choose>
	            	<c:when test="${roles.descri eq '管理员' }">
	            		<select  class="input_a" name="descri">
	            			<option value="管理员" selected="selected">管理员</option>
	            		</select>
	            	</c:when>
	            	<c:otherwise>
	            		<select  class="input_a" name="descri">
						     <option value="安吉物流员工" <c:if test="${roles.descri=='安吉物流员工'}"> selected </c:if>>安吉物流员工</option>
							 <option value="经销商" <c:if test="${roles.descri=='经销商'}"> selected </c:if>>经销商</option>
							 <option value="安吉物流领导" <c:if test="${roles.descri=='安吉物流领导'}"> selected </c:if>>安吉物流领导</option>
							 <option value="大众计划员" <c:if test="${roles.descri=='大众计划员'}"> selected </c:if>>大众计划员</option>
							 <option value="大众品牌经理" <c:if test="${roles.descri=='大众品牌经理'}"> selected </c:if>>大众品牌经理</option>
					        <option value="大众科长" <c:if test="${roles.descri=='大众科长'}"> selected </c:if>>大众科长</option>
					      </select>
	            	</c:otherwise>
	            </c:choose> 
	            </td>
	            <td width="13%">&nbsp;</td>
	          </tr>
	          <%-- <tr>
	            <td align="right" valign="top">
	            <div class="div10px"></div>备注</td>
	            <td> <div class="div10px"></div><textarea name="descri" class="input_b" >${roles.descri}</textarea></td>
	            <td>&nbsp;</td>
	          </tr> --%>
	          
	          <tr><td></td><td colspan="2" style="color:red;">首页设置，请咨询相关开发人员</td></tr>
	          <tr>
	            <td align="right" valign="top">查询首页</td>
	            <td><input name="searchFirstPage" value="<c:out value="${roles.searchFirstPage}"/>" type="text" class="input_a" id="searchFirstPage"/></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">系统管理首页</td>
	            <td><input name="systemFirstPage" value="<c:out value="${roles.systemFirstPage}"/>" type="text" class="input_a" id="systemFirstPage"/></td>
	            <td>&nbsp;</td>
	          </tr>
	          
	          <tr>
	            <td align="right" valign="top">品牌</td>
	            <td><input name="brand" value="<c:out value="${roles.brand}"/>" type="text" class="input_a"/></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">经销商代码</td>
	            <td><input name="dealerCode" value="<c:out value="${roles.dealerCode}"/>" type="text" class="input_a"/></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">经销商名称</td>
	            <td><input name="dealerName"  value="<c:out value="${roles.dealerName}"/>" type="text" class="input_a"/></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">大区</td>
	            <td><input name="dealer" value="<c:out value="${roles.dealer}"/>" type="text" class="input_a"/></td>
	            <td>&nbsp;</td>
	          </tr>
	          
	          <tr>
	            <td align="right" valign="top">地图显示</td>
	            <td><%-- <input name="scope" value="${roles.scope}" type="text" class="input_a"/> --%>
	            	<select name="scope" class="input_a">
		            	<option value="1" <c:if test="${roles.scope=='1'}"> selected </c:if>>大众</option>
		            	<option value="2" <c:if test="${roles.scope=='2'}"> selected </c:if>>经销商</option>
		            </select>
	            </td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td>
	            <div class="div15px"></div>&nbsp;&nbsp;&nbsp;
	              <input type="button" class="btn_a" value="提交" onclick="rolek.doEdit('${ctx}/ss/Roles/doEditAjax.do')"/>
	&nbsp;&nbsp;
	<input type="button" class="btn_a" value="取消"  onclick="cancel()"/></td>
	            <td>&nbsp;</td>
	          </tr>
	        </table>
        </form>

          </div>
          
</div>
  </body>
</html>
