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
    <title>ss系统</title>
<link href="${ctx}/styles/style.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/styles/second.css" type="text/css" rel="stylesheet" />
<script>
function cancel() {
	window.close();
}

var username_not_null = "用户名不能为空!";
var username_out_length="用户名不能超出50个字";
var realname_not_null = "用户真实姓名不能为空!";
var realname_out_length="用户真实姓名不能超出50个字";

var pwd_not_null = "密码不能为空！";
var pwd_out_length="密码不能超出50个字";
var rePwd_not_null = "确认密码不能为空！";
var pwd_rePwd_not = "新密码与确认密码不一致！";

function checkForm(frm) {
	var msg = new Array();
	var err = false;
	
	if(frm.elements['username'] && frm.elements['username'].value.trim().length==0) {
		msg.push(username_not_null); 
		err = true;
	}
	if(frm.elements['username'].value.trim().length>50){
		msg.push(username_out_length);
		err = true;
		height += 30;
	}
	if(frm.elements['realname'] && frm.elements['realname'].value.trim().length==0) {
		msg.push(realname_not_null);
		err = true;
	}

	if(frm.elements['realname'].value.trim().length>50){
		msg.push(realname_out_length);
		err = true;
		height += 30;
	}	

	
	var pwd = "";
	if(frm.elements['password'] && frm.elements['password'].value.trim().length==0) {
		msg.push(pwd_not_null);
		err = true;
	} else {
		pwd = frm.elements['password'].value;
	}
	var rePwd = "";
	if(frm.elements['repassword'] && frm.elements['repassword'].value.trim().length==0) {
		msg.push(rePwd_not_null);
		err = true;
	} else {
		rePwd = frm.elements['repassword'].value;
	}
	if(pwd!="" && rePwd!="" && pwd!=rePwd) {
		msg.push(pwd_rePwd_not);
		err = true;
	}
	if(frm.elements['password'].value.trim().length>50){
		msg.push(pwd_out_length);
		err=true;
		height += 30;
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
    <div id="tc_div" style="height:570px;">
    
    
    <!-- <h4>用户信息编辑</h4> -->
    	<div class="div20px"></div><div class="div10px"></div>
        
        <div style=" padding-left:30px;">
        <form action="${ctx}/ss/Users/doSave.do" method="post" id="form" name="form" onSubmit="return checkForm(this)">
	    	<table width="96%" border="0" align="center">
	          <tr>
	            <td width="16%" align="right" valign="top">用户名</td>
	            <td width="71%"><input name="username" type="text" class="input_a" id="username"/></td>
	            <td width="13%">&nbsp;</td>
	          </tr>
	          <tr>
	            <td width="16%" align="right" valign="top">iv-user-id</td>
	            <td width="71%"><input name="ivUserId" type="text" class="input_a" id="ivUserId"/></td>
	            <td width="13%">&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">真实姓名</td>
	            <td><input name="realname" type="text" class="input_a" id="realname"/></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">密码</td>
	            <td><input name="password" type="password" class="input_a" id="password"/></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">确认密码</td>
	            <td><input name="repassword" type="password" class="input_a" id="repassword"/></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">
	            <div class="div10px"></div>备注</td>
	            <td> <div class="div10px"></div><textarea name="descri" class="input_b" id="descri"></textarea></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td>
	            <div class="div15px"></div>&nbsp;&nbsp;&nbsp;
	              <input type="button" class="btn_a" value="提交" onclick="user.doSave('${ctx}/ss/Users/doSaveAjax.do')"/>
	&nbsp;&nbsp;
	<input type="button" class="btn_a" value="取消" onclick="cancel()"/></td>
	            <td>&nbsp;</td>
	          </tr>
	        </table>
        </form>

          </div>
          
</div>
  </body>
</html>
