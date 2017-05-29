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
<%-- <link href="<c:url value="/styles/public.css"/>" type="text/css" rel="stylesheet"> --%>
<script type="text/javascript" src="<%=basePath%>scripts/public.js"></script>
<script type="text/javascript" >
		function doroles(roleid) {
			//$$("roleid").value=roleid;
			$("#roleid").val(roleid);
			
			var url = "${ctx}/ss/RolesResources/doList.do?roleid=" + roleid + "";
			window.location.href=url;
		}
		
		function relax(resourceid) {
			//var roleid = $$("roleid").value;
			
			//window.location.href=url;
			/* if(confirm("确认解除关系?")) {
				
			} */
			dialog.msgConfirmFun("消息","确认解除关系?","relaxConfirm('"+resourceid+"')");
		}
		
		function relaxConfirm(resourceid){
			var roleid = $("#roleid").val();
			var url = "${ctx}/ss/RolesResources/doDeleteAjax.do?roleid=" + roleid + "&resourceid=" + resourceid + "";
			
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
						//window.location.href="${ctx}/ss/RolesResources/doList.do?roleid=" + roleid;
					} else {
						dialog.msgAlert("提示",v);
					}
			    }  
			}); 
		}
		
		function addPage(url) {
			window.open(url,'newwindow','height=500,width=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no,channelmode=yes');
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		var roleresource={
			doSavePage:function(url){
				//window.open('<c:url value="/pages/TtCarInfo/gotoPage.do"/>?pageFlag=2&pkTtCarInfo='+id);
				$.post(url,'', 
						function(vals) {
							dialog.msgBox('分配权限',vals,"auto","auto");
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
					data:$('#roleresources').serialize(),
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
			$("#roleid").val(roleid);
		}*/
		
		function cancel() {
			dialog.hide();
		}
		
		function doRefreshMetaSource(url) {
			$.ajax({
				type:'POST',
				url:url,
				//data:$('#form').serialize(),
				dataType:'text',
				success:function(data){
					dialog.msgAlert('提示',data);
					/* var success = data.success;
					if (success) {
						dialog.hide();
						dialog.msgConfirmFun('消息',data.message,'reload()');
					} else {
						dialog.msgAlert('消息',data);
					} */
				}
			});
		}
		
		/*分页查询*/
	function doPage(requestPage){
		$("#requestPage").val(requestPage);
		var url = $('#doSearch').attr('action');
		var roleid = $('#roleid').val();
		url += "?requestPage="+requestPage + "&roleid="+roleid;
		$.post(url,'', 
					function(vals) {
						dialog.msgBox('分配权限',vals,"auto","auto");
					}
					,'HTML');
		//$("#doSearch").submit();
	}
	</script>
	
	
  		<%-- <div class="tags_bg">
	  		<c:forEach items="${roles}" var="item" varStatus="status">
	  			<div onclick="doroles('${item.id}')" class="tags_but"                 <c:choose><c:when test="${item.id==role.id }">  style="display:inline;background:#eee;color:#fff;"  </c:when><c:otherwise>  style="display:inline;" </c:otherwise></c:choose> >${item.name }</div>
	  		</c:forEach>
  		</div>
  		
  		<div style="padding:10px;"></div>
  		
  		<div>
  			<div style="display:inline;" onclick=""><a href="${ctx }/ss/RolesResources/doSavePage.do?roleid=${role.id}">分配权限</a></div>
  		</div>
  		
  		<input type="hidden" value="${role.id }" name="roleid" id="roleid"/>
  		
     	<div>
	     	<table border="1">
	     		<tr><td width="200">权限名</td><td width="400">url</td><td width="200">操作</td></tr>
		     	<c:forEach items="${resources}" var="item" varStatus="status">
		     		<tr><td>${item.name }</td><td>${item.url }</td><td><a href="javascript:void(0)" onclick="relax('${item.id}')">解除关系</a></td></tr>
		     	</c:forEach>
	     	</table>
     	</div> --%>
     	
     	
     	<div id="right_box">
        	<!-- <h3>车辆库存</h3> -->
        	<div id="main_h4">角色权限列表</div>
        	 <div id="qh_div">
              	<c:forEach items="${roles}" var="item" varStatus="status">
	  				<div   onclick="doroles('${item.id}')"  <c:choose><c:when test="${item.id==role.id }"> id="qh1"  </c:when><c:otherwise>  id="qh2" </c:otherwise></c:choose> >
	  				<c:out value="${item.name }"/>
	  				</div>
	  			</c:forEach>
	  			 <div class="clear"></div>
            </div>
        	
<!--           <div id="search_div"> -->
<%--           	<c:forEach items="${roles}" var="item" varStatus="status"> --%>
<%-- 	  			<div onclick="doroles('${item.id}')" class="tags_but"                 <c:choose><c:when test="${item.id==role.id }">  style="font-size: 16px;display:inline;background:#000;color:#fff;"  </c:when><c:otherwise>  style="font-size: 16px;display:inline;" </c:otherwise></c:choose> ><strong>${item.name }</strong></div> --%>
<%-- 	  		</c:forEach> --%>
<!--             群组名<input name="" type="text" class="input_a" /><input type="button" class="btn_a" value="查询" /> -->
<!--           </div> -->
          
          
          <div id="main_btn">
          	<a href="javascript:void(0)" class="type_a" onclick="doRefreshMetaSource('${ctx }/ss/RolesResources/ajaxRefreshMetaSource.do')"><div id="btn_2">刷新缓存</div></a>
          	<%-- <a href="javascript:addPage('${ctx }/ss/RolesResources/doSavePage.do?roleid=${role.id}')" class="type_a"><div id="btn_2">+分配权限</div></a> --%>
          	<a href="javascript:roleresource.doSavePage('${ctx }/ss/RolesResources/doSavePage.do?roleid=${role.id}')" class="type_a"><div id="btn_2">+分配权限</div></a>
          </div>
            
           <input type="hidden" value="${role.id }" name="roleid" id="roleid"/>
            
            <div id="content">
            	<table width="100%" border="0"cellspacing="1" cellpadding="0"  style='background-color:#cdcdcd'>
            		<tr bgcolor="#ffffff">
            			<td width="200"  align="center" bgcolor="#e3e3e3">序号</td>
            			<td width="200"  align="center" bgcolor="#e3e3e3">权限名</td>
            			<td width="200"  align="center" bgcolor="#e3e3e3">操作</td>
            		</tr>
			     	<c:forEach items="${resources}" var="item" varStatus="status">
			     		<tr bgcolor="#ffffff">
			     			<td align="center" bgcolor="#f6f6f6">${status.count}</td>
			     			<td align="left" bgcolor="#f6f6f6"><c:out value="${item.name}"/></td>
			     			<td align="center" bgcolor="#f6f6f6">
			     				<a href="javascript:void(0)" onclick="relax('${item.id}')" class="type_c">解除关系</a>
			     			</td>
			     		</tr>
			     	</c:forEach>
            	
                      <%-- <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#e3e3e3">序号</td>
                        <td align="center" bgcolor="#e3e3e3">群组名</td>
                        <td align="center" bgcolor="#e3e3e3">操作</td>
                  </tr>
                  <c:forEach items="${groupList}" var="item" varStatus="status">
	                  <tr>
	                  	<td align="center">${status.count}</td>
                        <td align="center">${item.name}</td>
                        <td align="center">
                        	<!-- <a href="#">【编辑】</a> --> 
                        	<a href="javascript:deleteGroup('${ctx}/ss/Groups/doDeleteAjax.do?id=${item.id}')" class="type_c">【删除】</a>
                        </td>
	                  </tr>
                  </c:forEach> --%>
              </table>
				<!-- <div id="f_page">首页&nbsp;&nbsp;&nbsp;&nbsp; 上一页&nbsp;&nbsp;&nbsp;&nbsp; 1/3 &nbsp;&nbsp;&nbsp;&nbsp;      下一页&nbsp;&nbsp;&nbsp;&nbsp; 尾页 </div> -->
     </div>
            
       </div>
