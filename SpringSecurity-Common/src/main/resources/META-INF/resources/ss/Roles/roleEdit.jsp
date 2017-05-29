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
    <title>ss系统</title>
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
	
	/* if(frm.elements['scope'] && frm.elements['scope'].value.trim().length==0) {
		msg.push(scope_not_null);
		err = true;
	} */
	
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
