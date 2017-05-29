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
<script>
function cancel() {
	window.close();
}

var username_not_null = "用户名不能为空!";
var realname_not_null = "用户真实姓名不能为空!";

var pwd_not_null = "密码不能为空！";
var rePwd_not_null = "确认密码不能为空！";
var pwd_rePwd_not = "新密码与确认密码不一致！";

function checkForm(frm) {
	var msg = new Array();
	var err = false;
	
	/* if(frm.elements['username'] && frm.elements['username'].value.trim().length==0) {
		msg.push(username_not_null); 
		err = true;
	} */
	
	if(frm.elements['realname'] && frm.elements['realname'].value.trim().length==0) {
		msg.push(realname_not_null);
		err = true;
	}
	
	
	
	/* var pwd = "";
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
    <div id="tc_div" <c:choose><c:when test="${userType=='0' }"> style="height:620px;" </c:when><c:otherwise> style="height:280px;" </c:otherwise></c:choose>>
    
    
    <!-- <h4>用户信息编辑</h4> -->
    	<div class="div20px"></div><div class="div10px"></div>
        
        <div style=" padding-left:30px;">
        <form action="${ctx}/ss/Users/doSave.do" method="post" id="form" name="form" onSubmit="return checkForm(this)">
        <input type="hidden" value="${users.id}" name="id"/>
        <input type="hidden" value="0" name="isJudge"/>
        
        
        
        <c:if test="${userType!='0'}">
        <input name="username" readOnly value="<c:out value="${users.username}"/>" type="hidden" class="input_a" />
        <input name="realname" value="<c:out value="${users.realname}"/>" type="hidden" class="input_a" />
        <input name="brand" value="<c:out value="${users.brand}"/>" <c:if test="${userType!='0'}"> readOnly </c:if> type="hidden" class="input_a" />
        <input name="dealerCode" value="<c:out value="${users.dealerCode}"/>" <c:if test="${userType!='0'}"> readOnly </c:if> type="hidden" class="input_a" />
        <input name="dealerName" value="<c:out value="${users.dealerName}"/>" <c:if test="${userType!='0'}"> readOnly </c:if> type="hidden" class="input_a" />
        <input name="dealer" value="<c:out value="${users.dealer}"/>" <c:if test="${userType!='0'}"> readOnly </c:if> type="hidden" class="input_a" />
        <input name="userType" value="${users.userType }" type="hidden"/>
        <textarea name="descri" class="input_b" style="display:none;">${users.descri}</textarea>
        </c:if>
        
        
        
        
	    	<table width="96%" border="0" align="center">
	    	  <c:if test="${userType=='0'}">
	          <tr>
	            <td width="16%" align="right" valign="top">用户名</td>
	            <td width="71%"><input name="username" readOnly value="<c:out value="${users.username}"/>" type="text" class="input_a" /></td>
	            <td width="13%">&nbsp;</td>
	          </tr>
	          <tr>
	            <td width="16%" align="right" valign="top">iv-user-id</td>
	            <td width="71%"><input name="ivUserId" value="<c:out value="${users.ivUserId}"/>" type="text" class="input_a" /></td>
	            <td width="13%">&nbsp;</td>
	          </tr>
	          </c:if>
	          <c:if test="${userType=='0'}">
	          <tr>
	            <td align="right" valign="top">真实姓名</td>
	            <td><input name="realname" value="<c:out value="${users.realname}"/>" type="text" class="input_a" /></td>
	            <td>&nbsp;</td>
	          </tr>
	          </c:if>
	          <c:choose>
	          	<c:when test="${userType=='0'}">
	          		
	          		<tr>
			            <td align="right" valign="top"><input name="oldpassword" type="hidden" value="123" class="input_a" />是否修改密码</td>
			            <td><select name="updPwd" class="input_a"><option value="0">否</option><option value="1">是</option></select></td>
			            <td>&nbsp;</td>
			          </tr>
	          		
	          	</c:when>
	          	<c:otherwise>
	          		<tr>
			            <td align="right" valign="top"><input type="hidden" value="1" name="updPwd"/>原密码</td>
			            <td><input name="oldpassword" type="password" class="input_a" /></td>
			            <td>&nbsp;</td>
			          </tr>
	          	</c:otherwise>
	          </c:choose>
	          <%-- <c:if test="${userType!='0'}">
	          
	          </c:if> --%>
	          
	          <tr>
	            <td align="right" valign="top">新密码</td>
	            <td><input name="password" type="password" class="input_a" /></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">确认密码</td>
	            <td><input name="repassword" type="password" class="input_a" /></td>
	            <td>&nbsp;</td>
	          </tr>
	          
	          <c:if test="${userType=='0'}">
	          <tr>
	            <td align="right" valign="top">品牌</td>
	            <td><input name="brand" value="<c:out value="${users.brand}"/>" <c:if test="${userType!='0'}"> readOnly </c:if> type="text" class="input_a" /></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">经销商代码</td>
	            <td><input name="dealerCode" id="dealerCode" value="<c:out value="${users.dealerCode}"/>" <c:if test="${userType!='0'}"> readOnly </c:if> type="text" class="input_a"
	             onfocus="check.clear();" onkeyup="check.getDealerName();" /><br/>
	             <span style="color:red;margin-left: 10px">不确定经销商代码请输入TBD</span></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">经销商名称</td>
	            <td><input name="dealerName" id="dealerName" value="<c:out value="${users.dealerName}"/>" readOnly type="text" class="input_a" /></td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="right" valign="top">大区</td>
	            <%-- <td><input name="dealer" value="<c:out value="${users.dealer}"/>" <c:if test="${userType!='0'}"> readOnly </c:if> type="text" class="input_a" /></td> --%>
	            <td>
					<select name="dealer" class="input_a">
						${rsscCodeOptions}
					</select>
				</td>
	            <td>&nbsp;</td>
	          </tr>
	          
	          <tr>
             	<td align="right" valign="top">大区是否有效</td>
             	<td>
             		<select name="isRsscCode"  class="input_a">
             			<option value="0">否</option>
             			<option <c:if test="${users.isRsscCode=='1'}"> selected </c:if> value="1">是</option>
             		</select>
             	</td>
             	<td>&nbsp;</td>
             </tr>
	          
	          <tr>
	            <td align="right" valign="top">用户类型</td>
	            <td><%-- <input name="userType" value="${users.userType}" type="text" class="input_a" /> --%>
	            	<select name="userType" class="input_a">
	            		<c:choose>
	            			<%-- <c:when test="${users.userType=='0'}">
	            				<option value="0">管理员</option>
	            			</c:when>
	            			<c:otherwise>
	            				<c:if test="${users.userType=='1'}"><option value="1"  selected >大众人员</option></c:if>
			            		<c:if test="${users.userType=='2'}"><option value="2"  selected >经销商</option></c:if>
			            		<c:if test="${users.userType=='3'}"><option value="3"  selected >安吉人员</option></c:if>
	            			</c:otherwise> --%>
	            			<c:when test="${users.userType=='0'}">
	            				<option value="0">管理员</option>
	            			</c:when>
	            			<c:otherwise>
	            				<c:choose>
	            					<c:when test="${userType=='0'}">
	            						<option value="1" <c:if test="${users.userType=='1'}"> selected </c:if>>大众人员</option>
			            				<option value="2" <c:if test="${users.userType=='2'}"> selected </c:if>>经销商</option>
			            				<option value="3" <c:if test="${users.userType=='3'}"> selected </c:if>>安吉人员</option>
	            					</c:when>
	            					<c:otherwise>
	            						<c:if test="${users.userType=='1'}"><option value="1"  selected >大众人员</option></c:if>
			            				<c:if test="${users.userType=='2'}"><option value="2"  selected >经销商</option></c:if>
			            				<c:if test="${users.userType=='3'}"><option value="3"  selected >安吉人员</option></c:if>
	            					</c:otherwise>
	            				</c:choose>
	            				
	            			</c:otherwise>
	            		</c:choose>
	            	
	            		<%-- <c:if test="${users.userType=='0'}"><option value="0">管理员</option></c:if>
	            		<c:if test="${users.userType!='0'}></c:if>
	            		<option value="0" <c:if test="${users.userType=='0'}"> selected </c:if>>0</option>
	            		<option value="1" <c:if test="${users.userType=='1'}"> selected </c:if>>1</option>
	            		<option value="2" <c:if test="${users.userType=='2'}"> selected </c:if>>2</option>
	            		<option value="3" <c:if test="${users.userType=='3'}"> selected </c:if>>3</option> --%>
	            	</select>
	            </td>
	            <td>&nbsp;</td>
	          </tr>
	          
	          <!-- <tr>
	            <td align="right" valign="top"> 角色选择           </td>
	            <td><select name="select" class="input_f"><option>1</option></select></td>
	            <td>&nbsp;</td>
	          </tr> -->
	          <tr>
	            <td align="right" valign="top">
	            <div class="div10px"></div>备注</td>
	            <td> <div class="div10px"></div><textarea name="descri" class="input_b" >${users.descri}</textarea></td>
	            <td>&nbsp;</td>
	          </tr>
	          </c:if>
	          <tr>
	            <td>&nbsp;</td>
	            <td>
	            <div class="div15px"></div>&nbsp;&nbsp;&nbsp;
	              <input type="button" class="btn_a" value="提交" onclick="user.doEdit('${ctx}/ss/Users/doEditAjax.do')"/>
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
