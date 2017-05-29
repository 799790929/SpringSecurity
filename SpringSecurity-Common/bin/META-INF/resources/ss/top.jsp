<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div id="top_box">
		<div id="top_x"><img src="${ctx}/images/logo.jpg" /></div>
        <div id="top_id">
        	<table width="100%" border="0">
              <tr>
                <td align="right">欢迎您：<span>admin</span></td>
                <td align="center"><a href="#"><img src="${ctx}/images/xx_img.jpg" /></a></td>
                <td><a href="#">注销</a></td>
              </tr>
            </table>

        </div>
        
        <div class="clear"></div>
      	
        <div id="menu_div">
        	<ul>
            	<li id="li_b">
                <table width="100%" border="0">
                  <tr>
                    <td width="23%"><img src="${ctx}/images/ico01.png" /></td>
                    <td width="77%"><a href="#" class="type_b">报表功能</a></td>
                  </tr>
                </table>
               </li>
               
                <li>
                <table width="100%" border="0">
                  <tr>
                    <td width="23%"><img src="${ctx}/images/ico02.png" /></td>
                    <td width="77%"><a href="#" class="type_b">实时数据</a></td>
                  </tr>
                </table> </li>
                <li>
                <table width="100%" border="0">
                  <tr>
                    <td width="23%"><img src="${ctx}/images/ico03.png" /></td>
                    <td width="77%"><a href="#" class="type_b">历史数据</a></td>
                  </tr>
                </table> </li>
                <li>
                <table width="100%" border="0">
                  <tr>
                    <td width="23%"><img src="${ctx}/images/ico04.png" /></td>
                    <td width="77%"><a href="#" class="type_b">系统设置</a></td>
                  </tr>
                </table>
                </li>
                <li>
                 <table width="100%" border="0">
                  <tr>
                    <td width="23%"><img src="${ctx}/images/ico05.png" /></td>
                    <td width="77%"><a href="#" class="type_b">系统管理</a></td>
                  </tr>
                </table>
                </li>
                <div class="clear"></div>
            </ul>
        	
        </div>
    
    </div>
