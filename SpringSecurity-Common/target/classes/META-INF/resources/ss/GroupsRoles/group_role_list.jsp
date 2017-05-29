<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.sf.ss.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>
.tags_but{
	cursor:pointer;
}
</style>
<script type="text/javascript" src="<%=basePath%>scripts/public.js"></script>
<script type="text/javascript" >
		function doroles(roleid) {
			//$$("roleid").value=roleid;
			$("#roleid").val(roleid);
			
			var url = "${ctx}/ss/GroupsRoles/doList.do?roleid=" + roleid + "";
			window.location.href=url;
		}
		
		function relax(groupid) {
			//var roleid = $$("roleid").value;
			
			//window.location.href=url;
			/* if(confirm("确认解除关系?")) {
				
				
			} */
			dialog.msgConfirmFun("消息","确认解除关系?","relaxConfirm('"+groupid+"')");
		}
		
		function relaxConfirm(groupid) {
			var roleid = $("#roleid").val();
			var url = "${ctx}/ss/GroupsRoles/doDeleteAjax.do?roleid=" + roleid + "&groupid=" + groupid + "";
			$.ajax({     
			    url:url,     
			    type:'get',   
			    async : true, //默认为true 异步     
			    error:function(){     
			    	dialog.msgAlert('提示','error');     
			    },     
			    success:function(v){     
			    	if(v == 'success') {
			    		dialog.msgAlertFun("提示","解除成功","reload()");
						//window.location.href="${ctx}/ss/GroupsRoles/doList.do?roleid=" + roleid;
					} else {
						dialog.msgAlert("提示",v);
					}
			    }  
			});
		}
		
		function addPage(url) {
			window.open(url,'newwindow','height=500,width=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no,channelmode=yes');
			
		}
		
		
		
		
		
		
		
		
		
		var grouprole={
			doSavePage:function(url){
				//window.open('<c:url value="/pages/TtCarInfo/gotoPage.do"/>?pageFlag=2&pkTtCarInfo='+id);
				$.post(url,'', 
						function(vals) {
							dialog.msgBox('分配群组',vals,"auto","auto");
						}
						,'HTML');
			},
			doSave:function(url){
				/*var form = $('#groupusers');
				if(!checkForm($('#groupusers')[0])) {
					return;
				}*/
			
				$.ajax({
					type:'POST',
					url:url,
					data:$('#rolegroups').serialize(),
					dataType:'JSON',
					success:function(data, textStatus){
						var success = data.success;
						if (success) {
							dialog.hide();
							dialog.msgConfirmFun('消息',data.message,'reload()');
						} else {
							dialog.msgAlert('消息',data);
						}
					}
				});
			}
		};
		
		/*function doroles(roleid) {
			//$$("roleid").value=roleid;
			$("roleid").val(roleid);
		}*/
		
		function cancel() {
			dialog.hide();
		}
		
		/*分页查询*/
	function doPage(requestPage){
		$("#requestPage").val(requestPage);
		var url = $('#doSearch').attr('action');
		var roleid = $('#roleid').val();
		url += "?requestPage="+requestPage + "&roleid="+roleid;
		$.post(url,'', 
					function(vals) {
						dialog.msgBox('分配群组',vals,"auto","auto");
					}
					,'HTML');
		//$("#doSearch").submit();
	}
	</script>
	
	
     	
     	
     	
     	<div id="right_box">
        	<div id="main_h4">群组角色列表</div>
        	 <div id="qh_div">
              	<c:forEach items="${roles}" var="item" varStatus="status">
	  				<div   onclick="doroles('${item.id}')"  <c:choose><c:when test="${item.id==role.id }"> id="qh1"  </c:when><c:otherwise>  id="qh2" </c:otherwise></c:choose> >
	  				<c:out value="${item.name }"/></div>
	  			</c:forEach>
	  			 <div class="clear"></div>
            </div>
          
          
          
          
          <div id="main_btn">
          	<a href="javascript:grouprole.doSavePage('${ctx }/ss/GroupsRoles/doSavePage.do?roleid=${role.id}')" class="type_a"><div id="btn_2">+分配群组</div></a>
          </div>
            
            <input type="hidden" value="${role.id }" name="roleid" id="roleid"/>
            
            <div id="content">
            	<table width="100%" border="0"cellspacing="1" cellpadding="0"  style='background-color:#cdcdcd'>
            		<tr bgcolor="#ffffff">
            			<td width="200"  align="center" bgcolor="#e3e3e3">序号</td>
            			<td width="200"  align="center" bgcolor="#e3e3e3">群组名</td>
            			<td width="200"  align="center" bgcolor="#e3e3e3">操作</td>
            		</tr>
			     	<c:forEach items="${groups}" var="item" varStatus="status">
			     		<tr bgcolor="#ffffff">
			     			<td align="center" bgcolor="#f6f6f6">${status.count}</td>
			     			<td align="left" bgcolor="#f6f6f6"><c:out value="${item.name}"/></td>
			     			<td align="center" bgcolor="#f6f6f6">
			     				<a href="javascript:void(0)" onclick="relax('${item.id}')" class="type_c">解除关系</a>
			     			</td>
			     		</tr>
			     	</c:forEach>
            	
              </table>
     </div>
            
       </div>
