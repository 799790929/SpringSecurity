<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<script language="javascript">
var msg = "${msg}";
if(msg == 'success') {
	dialog.msgAlert("角色添加成功!");window.close();window.opener.location.reload();
} else {
	dialog.msgAlert("角色添加失败!");//window.close();window.opener.location.reload();
}

</script>
