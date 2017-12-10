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
    <title>1210</title>
<link href="${ctx}/styles/style.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/styles/second.css" type="text/css" rel="stylesheet" />
<script>
function cancel() {
	window.close();
}



var name_not_null = "权限名不能为空!";
var url_not_null = "权限路径不能为空!";
function checkForm(frm) {
	var msg = new Array();
	var err = false;
	
	if(frm.elements['name'] && frm.elements['name'].value.trim().length==0) {
		msg.push(name_not_null); 
		err = true;
	}
	
	if(frm.elements['url'] && frm.elements['url'].value.trim().length==0) {
		msg.push(url_not_null);
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
    <div id="tc_div" style="height:300px;">
    
    
    <!-- <h4>角色信息编辑</h4> -->
    	<div class="div20px"></div><div class="div10px"></div>
        
        <div style=" padding-left:30px;">
        <form action="${ctx}/ss/Resources/doSave.do" method="post" id="form" name="form" onSubmit="return checkForm(this)">
	    	<table width="96%" border="0" align="center">
	          <tr>
	            <td width="16%" align="right" valign="top">权限名</td>
	            <td width="71%"><input name="name" type="text" class="input_a" id="name"/></td>
	            <td width="13%">&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">权限路径</td>
	            <td><input name="url" type="text" class="input_a" id="url"/></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">
	            <div class="div10px"></div>备注</td>
	            <td> <div class="div10px"></div><textarea name="memo" class="input_b" id="memo"></textarea></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td>
	            <div class="div15px"></div>&nbsp;&nbsp;&nbsp;
	              <input type="button" class="btn_a" value="提交" onclick="resource.doSave('${ctx}/ss/Resources/doSaveAjax.do')"/>
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
