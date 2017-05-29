<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<script type="text/javascript" >
	function dogroups(groupid) {
		//$$("groupid").value=groupid;
		$("#groupid").val(groupid);
		var url = "${ctx}/ss/UsersGroups/doList.do?groupid=" + groupid + "";
		window.location.href=url;
	}
	
	function relax(userid) {
		//var groupid = $$("groupid").value;
		
			//window.location.href=url;
		dialog.msgConfirmFun("消息","确认解除关系?","relaxConfirm('"+userid+"')");
		
	}
	
	function relaxConfirm(userid) {
		var groupid = $("#groupid").val();
		var url = "${ctx}/ss/UsersGroups/doDeleteAjax.do?userid=" + userid + "&groupid=" + groupid + "";
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
					//window.location.href="${ctx}/ss/UsersGroups/doList.do?groupid=" + groupid;
				} else {
					dialog.msgAlert("提示",v);
				}
		    }  
		}); 
	}
	
	function addPage(url) {
		window.open(url,'newwindow','height=500,width=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no,channelmode=yes');
		
	}
	
	
	
	
	
	
	
	
	
	
	
	var usergroup={
		doSavePage:function(url){
			//window.open('<c:url value="/pages/TtCarInfo/gotoPage.do"/>?pageFlag=2&pkTtCarInfo='+id);
			$.post(url,'', 
					function(vals) {
						dialog.msgBox('分配用户',vals,"auto","auto");
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
				data:$('#groupusers').serialize(),
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
	
	/*function dogroups(groupid) {
		//$$("groupid").value=groupid;
		$("#groupid").val(groupid);
	}*/
	
	function cancel() {
		dialog.hide();
	}
	
	function submitForm(formid){
		//$$(formid).submit();
		$("#"+formid).submit();
	}
	
	/*分页查询*/
	function doPage(requestPage){
		$("#requestPage").val(requestPage);
		var url = $('#doSearch').attr('action');
		var groupid = $('#groupid').val();
		url += "?requestPage="+requestPage + "&groupid="+groupid;
		$.post(url,'', 
					function(vals) {
						dialog.msgBox('分配用户',vals,"auto","auto");
					}
					,'HTML');
		//$("#doSearch").submit();
	}
</script>
	
     	
  		<div id="right_box">
        	<!-- <h3>车辆库存</h3> -->
        	 <div id="main_h4">用户群组列表</div>
        	 <div id="qh_div">
              	<c:forEach items="${groups}" var="item" varStatus="status">
	  				<div   onclick="dogroups('${item.id}')"  <c:choose><c:when test="${item.id==group.id }"> id="qh1"  </c:when><c:otherwise>  id="qh2" </c:otherwise></c:choose> >
	  				<c:out value="${item.name }"/>
	  				</div>
	  			</c:forEach>
	  			 <div class="clear"></div>
            </div>
        	
          
         
          <div id="main_btn">
          	<a href="javascript:usergroup.doSavePage('${ctx }/ss/UsersGroups/doSavePage.do?groupid=${group.id}')" class="type_a"><div id="btn_2">+分配用户</div></a>
          </div>
            <input type="hidden" value="${group.id }" name="groupid" id="groupid"/>
            <div id="content">
            	<table  width="100%" border="0"cellspacing="1" cellpadding="0"  style='background-color:#cdcdcd'>
		     		<tr bgcolor="#ffffff">
		     			<td width="200"  align="center" bgcolor="#e3e3e3">序号</td>
		     			<td width="200"  align="center" bgcolor="#e3e3e3">用户姓名</td>
		     			<td width="200"  align="center" bgcolor="#e3e3e3">操作</td>
		     		</tr>
			     	<c:forEach items="${users}" var="item" varStatus="status">
			     		<tr bgcolor="#ffffff">
			     			<td align="center" bgcolor="#f6f6f6">${status.count}</td>
			     			<td align="left" bgcolor="#f6f6f6"><c:out value="${item.username}"/></td>
			     			<td align="center" bgcolor="#f6f6f6"><a href="javascript:void(0)" onclick="relax('${item.id}')" class="type_c">解除关系</a></td>
			     		</tr>
			     	</c:forEach>
		     	</table>
            	<%-- <table width="100%" border="0"cellspacing="1" cellpadding="0"  style='background-color:#e2e2e2'>
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#e3e3e3">序号</td>
                        <td align="center" bgcolor="#e3e3e3">用户名</td>
                        <td align="center" bgcolor="#e3e3e3">真实姓名</td>
                        <td align="center" bgcolor="#e3e3e3">角色</td>
                        <td align="center" bgcolor="#e3e3e3">备注</td>
                        <td align="center" bgcolor="#e3e3e3">添加时间</td>
                        <td align="center" bgcolor="#e3e3e3">操作</td>
                  </tr>
                  <c:forEach items="${userList}" var="item" varStatus="status">
	                  <tr>
	                  	<td align="center">${status.count}</td>
                        <td align="center">${item.username}</td>
                        <td align="center">${item.username}</td>
                        <td align="center">${item.username}</td>
                        <td align="center">&nbsp;</td>
                        <td align="center">${item.createdt}</td>
                        <td align="center">
	                        <!-- <a href="#">【编辑】</a>  -->
	                        <a href="javascript:deleteUser('${ctx}/ss/Users/doDeleteAjax.do?id=${item.id}')" class="type_c">【删除】</a>
                        </td>
	                  </tr>
                  </c:forEach>
              </table> --%>
				<!-- <div id="f_page">首页&nbsp;&nbsp;&nbsp;&nbsp; 上一页&nbsp;&nbsp;&nbsp;&nbsp; 1/3 &nbsp;&nbsp;&nbsp;&nbsp;      下一页&nbsp;&nbsp;&nbsp;&nbsp; 尾页 </div> -->
     </div>
            
       </div>
