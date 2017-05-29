<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div id="right_box">
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
            
       </div>
