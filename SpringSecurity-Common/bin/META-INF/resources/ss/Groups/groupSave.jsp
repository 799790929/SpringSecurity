<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div id="right_box">
          
          <div id="main_h4">新增群组</div>
          <div id="main_btn">
          	<a href="javascript:history.go(-1)" class="type_a"><div id="btn_2">返回</div></a>
          </div>
            
            <div id="content">
            	<form action="${ctx}/ss/Groups/doSave.do" method="post" id="form" name="form">
	            	<table width="100%" border="0"cellspacing="1" cellpadding="0"  style='background-color:#e2e2e2'>
                      <tr>
                      	<td align="center" bgcolor="#e3e3e3">群组名</td><td><input type="text" name="name"/></td>
                      </tr>
                      <!-- <tr>
                      	<td align="center" bgcolor="#e3e3e3">密码</td><td><input type="text" name="password"/></td>
                      </tr> -->
                       <tr>
                      	<td align="center" bgcolor="#e3e3e3">备注</td><td><input type="text" name="remark"/></td>
                      </tr>
                      <tr>
                      	<td align="center" bgcolor="#e3e3e3"><input type="submit" value="提交"/></td><td></td>
                      </tr>
	              </table>
              </form>
     </div>
            
       </div>
