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
          
          <div id="main_h4">用户群组列表</div>
          <div id="main_btn">
          	<a href="#" class="type_a"><div id="btn_2">+新增用户群组</div></a>
          </div>
            
            <div id="content">
            	<table width="100%" border="0"cellspacing="1" cellpadding="0"  style='background-color:#cdcdcd'>
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#e3e3e3">序号</td>
                        <td align="center" bgcolor="#e3e3e3">用户名</td>
                        <td align="center" bgcolor="#e3e3e3">群组名</td>
                        <td align="center" bgcolor="#e3e3e3">操作</td>
                  </tr>
                  <c:forEach items="${userGroupList}" var="item" varStatus="status">
	                  <tr bgcolor="#ffffff">
	                  	<td align="center" >${status.count}</td>
                        <td align="center" >${item.userName}</td>
                        <td align="center" >${item.groupName}</td>
                        <td align="center" >
                        	<a href="#">【编辑】</a> <a href="#" class="type_c">【删除】</a>
                        </td>
	                  </tr>
                  </c:forEach>
              </table>
				<div id="f_page">首页&nbsp;&nbsp;&nbsp;&nbsp; 上一页&nbsp;&nbsp;&nbsp;&nbsp; 1/3 &nbsp;&nbsp;&nbsp;&nbsp;      下一页&nbsp;&nbsp;&nbsp;&nbsp; 尾页 </div>
     </div>
            
       </div>
