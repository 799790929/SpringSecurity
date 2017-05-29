<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div id="right_box">
        	<!-- <h3>车辆库存</h3> -->
          <!-- <div id="search_div">
            权限名<input name="" type="text" class="input_a" /><input type="button" class="btn_a" value="查询" />
          </div> -->
          
          <div id="main_h4">角色资源列表</div>
          <div id="main_btn">
          	<a href="#" class="type_a"><div id="btn_2">+新增角色资源</div></a>
          </div>
            
            <div id="content">
            	<table width="100%" border="0"cellspacing="1" cellpadding="0"  style='background-color:#e2e2e2'>
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#e3e3e3">序号</td>
                        <td align="center" bgcolor="#e3e3e3">角色名</td>
                        <td align="center" bgcolor="#e3e3e3">资源名</td>
                        <td align="center" bgcolor="#e3e3e3">操作</td>
                  </tr>
                  <c:forEach items="${roleResourceList}" var="item" varStatus="status">
	                  <tr>
	                  	<td align="center">${status.count}</td>
                        <td align="center">${item.roleName}</td>
                        <td align="center">${item.resourceName}</td>
                        <td align="center"><a href="#">【编辑】</a> <a href="#" class="type_c">【删除】</a></td>
	                  </tr>
                  </c:forEach>
              </table>
				<div id="f_page">首页&nbsp;&nbsp;&nbsp;&nbsp; 上一页&nbsp;&nbsp;&nbsp;&nbsp; 1/3 &nbsp;&nbsp;&nbsp;&nbsp;      下一页&nbsp;&nbsp;&nbsp;&nbsp; 尾页 </div>
     </div>
            
       </div>
