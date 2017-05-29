<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大众车辆监管系统</title>
<link href="${ctx}/styles/style.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/styles/second.css" type="text/css" rel="stylesheet" />
</head>

<body>
	
<div id="box">

    <!-- <iframe scrolling="no" frameborder="0" src="top.html" height="132px" width="100%"></iframe> -->
    <jsp:include page="${top}" flush="true"></jsp:include>
    <!-- <div id="left_P">
    <div id="left_box">
        <h3>系统管理</h3>
        	<div class="div20px"></div>
        <ul>
        	<li><a href="#">用户管理</a></li>
            <li id="li_b"><a href="#">角色管理</a></li>
            <li><a href="#">权限管理</a></li>      
        </ul>
        <div class="div10px"></div>
    </div>
     </div> -->
     <div id="left_P">
     	<jsp:include page="${left}" flush="true"></jsp:include>
     </div>
     
     <div id="right_P">
     	<jsp:include page="${context}" flush="true"></jsp:include>
<!-- <div id="right_box">
        	<h3>角色管理</h3>
          <div id="search_div">角色名
              <input name="" type="text" class="input_a" /><input type="button" class="btn_a" value="查询" />
          </div>
          
          <div id="main_h4">角色列表</div>
          <div id="main_btn">
          	<a href="#" class="type_a"><div id="btn_2">+新增角色</div></a>
          </div>
            
            <div id="content">
            	<table width="100%" border="0"cellspacing="1" cellpadding="0"  style='background-color:#e2e2e2'>
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#e3e3e3">序号</td>
                        <td align="center" bgcolor="#e3e3e3">角色名</td>
                        <td align="center" bgcolor="#e3e3e3">备注</td>
                        <td align="center" bgcolor="#e3e3e3">添加时间</td>
                        <td align="center" bgcolor="#e3e3e3">操作</td>
                  </tr>
                      <tr bgcolor="#ffffff">
                        <td align="center">1</td>
                        <td align="center">超级管理员</td>
                        <td align="center">&nbsp;</td>
                        <td align="center">2014.09.09 14：22：56</td>
                        <td align="center"><a href="#">【编辑】</a> <a href="#" class="type_c">【删除】</a></td>
                      </tr>
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#f6f6f6">2</td>
                        <td align="center" bgcolor="#f6f6f6">监管员</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">2014.09.09 14：22：56</td>
                        <td align="center" bgcolor="#f6f6f6"><a href="#">【编辑】</a> <a href="#" class="type_c">【删除】</a></td>
                      </tr>
                      <tr bgcolor="#ffffff">
                        <td align="center">3</td>
                        <td align="center">经销商</td>
                        <td align="center">&nbsp;</td>
                        <td align="center">2014.09.09 14：22：56</td>
                        <td align="center"><a href="#">【编辑】</a> <a href="#" class="type_c">【删除】</a></td>
                      </tr>
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                      </tr>
                      <tr bgcolor="#ffffff">
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                      </tr>
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                      </tr>
                    <tr bgcolor="#ffffff">
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                  </tr>
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                        <td align="center" bgcolor="#f6f6f6">&nbsp;</td>
                      </tr>
                      <tr bgcolor="#ffffff">
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                      </tr>
              </table>
				<div id="f_page">首页&nbsp;&nbsp;&nbsp;&nbsp; 上一页&nbsp;&nbsp;&nbsp;&nbsp; 1/3 &nbsp;&nbsp;&nbsp;&nbsp;      下一页&nbsp;&nbsp;&nbsp;&nbsp; 尾页 </div>
     </div>
            
       </div> -->
     </div>
     
     <div class="clear"></div>
               

 <!-- <iframe scrolling="no" frameborder="0" src="footer.html" height="27px" width="100%""></iframe> -->
 <jsp:include page="${footer}" flush="true"></jsp:include>
 
</div>
</body>
</html>

