<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script>
function deleteResource_2(url){
	dialog.hide();
	$.ajax({     
	    url:url,     
	    type:'get',   
	    async : true, //默认为true 异步     
	    error:function(){     
	    	dialog.msgAlert('提示','error');     
	    },     
	    success:function(v){     
	    	if(v == 'success') {
	    		dialog.msgAlertFun("提示","删除成功",'reload()');
				//window.location.href="${ctx}/ss/Resources/doList.do";
			} else {
				dialog.msgAlert('提示',v);
			}
	    }  
	}); 
	
}
function deleteResource(url) {
	dialog.msgConfirmFun('警告','确定删除','deleteResource_2(\''+url+'\')');
		/*ajax.Req("get",url,"",function(o){
			var v = o.responseText;
			if(v == 'success') {
				alert("删除成功");
				window.location.href="${ctx}/ss/Resources/doList.do";
			} else {
				alert(v);
			}
		});*/

}

function addResourcePage(url) {
	window.open(url,'newwindow','height=500,width=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no,channelmode=yes');

}















var resource={
	doSavePage:function(url){
		//window.open('<c:url value="/pages/TtCarInfo/gotoPage.do"/>?pageFlag=2&pkTtCarInfo='+id);
		$.post(url,'', 
				function(vals) {
					dialog.msgBox('权限信息添加',vals,"auto","auto");
				}
				,'HTML');
	},
	doSave:function(url){
		var form = $('#form');
		if(!checkForm($('#form')[0],"save")) {
			return;
		}
	
		$.ajax({
			type:'POST',
			url:url,
			data:$('#form').serialize(),
			dataType:'JSON',
			success:function(data, textStatus){
				var success = data.success;
				if (success) {
					dialog.hide();
					dialog.msgConfirmFun('消息',data.message,'reload()');
				} else {
					dialog.msgAlert('消息',data.message);
				}
			}
		});
	},
	doEditPage:function(url) {
		$.post(url,'', 
				function(vals) {
					dialog.msgBox('权限信息编辑',vals,"auto","auto");
				}
				,'HTML');
	},
	doEdit:function(url){
		var form = $('#form');
		if(!checkForm($('#form')[0],"edit")) {
			return;
		}
		$.ajax({
			type:'POST',
			url:url,
			data:$('#form').serialize(),
			dataType:'JSON',
			success:function(data, textStatus){
				var success = data.success;
				if (success) {
					dialog.hide();
					dialog.msgConfirmFun('消息',data.message,'reload()');
				} else {
					dialog.msgAlert('消息',data.message);
				}
			}
		});
	}
};

function cancel() {
	dialog.hide();
}

var name_not_null = "权限名不能为空!";
var name_out_length="权限名不能超过20个字";
var url_not_null = "权限路径不能为空!";
var memo_out_length="备注不能超出200个字";
var url_out_length ="权限路径不能超过100个字";
function checkForm(frm,type) {
	var msg = new Array();
	var err = false;
	var height =140;
	
	if(frm.elements['name'] && frm.elements['name'].value.trim().length==0) {
		msg.push(name_not_null); 
		err = true;
		height += 30;
	}else{
		$('#name').val(frm.elements['name'].value.trim());
	}

	if(frm.elements['name'].value.trim().length>20){
		msg.push(name_out_length);
		err = true;
		height += 30;
	}
	if(frm.elements['memo'].value.trim().length>200){
		msg.push(memo_out_length);
		err = true;
		height += 30;
	}
	if(!(frm.elements['memo'] && frm.elements['memo'].value.trim().length==0)){
		$('#memo').val(frm.elements['memo'].value.trim());
	}
	if(type && type=='save') {
		if(frm.elements['url'] && frm.elements['url'].value.trim().length==0) {
			msg.push(url_not_null);
			err = true;
			height += 30;
		}else{
			$('#url').val(frm.elements['url'].value.trim());
		}
	}
	if(frm.elements['url'].value.trim().length>100){
		msg.push(url_out_length);
		err = true;
		height += 30;
	}
	if(err) {
		message = msg.join("<br/>");
		dialog_2.msgAutoAlert_2('提示',message,height);
	}
	
	return !err;
}
</script>

<div id="right_box">
        
          <div id="main_h4">权限列表</div>
          <div id="main_btn">
          	<a href="javascript:resource.doSavePage('${ctx}/ss/Resources/doSavePage.do')" class="type_a"><div id="btn_2">+新增权限</div></a>
          </div>
            
            <div id="content">
            	<table width="100%" border="0"cellspacing="1" cellpadding="0"  style='background-color:#cdcdcd'>
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#e3e3e3">序号</td>
                        <td align="center" bgcolor="#e3e3e3">权限名</td>
                        <td align="center" bgcolor="#e3e3e3">权限路径</td>
                        <td align="center" bgcolor="#e3e3e3">操作</td>
                  </tr>
                  <c:forEach items="${resourceList}" var="item" varStatus="status">
	                  <tr bgcolor="#ffffff">
	                  	<td align="center" bgcolor="#f6f6f6"><c:out value="${status.count}"/></td>
                        <td align="left" bgcolor="#f6f6f6"><c:out value="${item.name}"/></td>
                        <td align="left" bgcolor="#f6f6f6"><c:out value="${item.url}"/></td>
                        <td align="center" bgcolor="#f6f6f6">
                        	<a href="javascript:resource.doEditPage('${ctx}/ss/Resources/doEditPage.do?id=${item.id}')">【编辑】</a>
                        	&nbsp;&nbsp;
                        	<a href="javascript:deleteResource('${ctx}/ss/Resources/doDeleteAjax.do?id=${item.id}')" class="type_c">【删除】</a>
                        </td>
	                  </tr>
                  </c:forEach>
              </table>
     </div>
            
       </div>
