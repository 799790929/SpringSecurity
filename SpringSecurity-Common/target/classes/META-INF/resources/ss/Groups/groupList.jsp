<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<script>
function deleteGroup_2(url){
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
				//window.location.href="${ctx}/ss/Groups/doList.do";
			} else {
				dialog.msgAlert('提示',v);
			}
	    }  
	}); 
}
function deleteGroup(url) {
	dialog.msgConfirmFun('警告','确定删除','deleteGroup_2(\''+url+'\')');
		/*ajax.Req("get",url,"",function(o){
			var v = o.responseText;
			if(v == 'success') {
				alert("删除成功");
				window.location.href="${ctx}/ss/Groups/doList.do";
			} else {
				alert(v);
			}
		});*/
}

function addGroupPage(url) {
	window.open(url,'newwindow','height=500,width=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no,channelmode=yes');

}





var group={
	doSavePage:function(url){
		//window.open('<c:url value="/pages/TtCarInfo/gotoPage.do"/>?pageFlag=2&pkTtCarInfo='+id);
		$.post(url,'', 
				function(vals) {
					var len = vals.indexOf('上海-用户登录');
					if (len >= 0){
						dialog.hide();
						window.location.href="<c:url value='/'/>";
					} else {
							dialog.msgBox('群组信息添加',vals,"auto","auto");
					}
				}
				,'HTML');
	},
	doSave:function(url){
		var form = $('#form');
		if(!checkForm($('#form')[0])) {
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

var name_not_null = "群组名不能为空!";
var name_out_length="群组名不能超出20个字";
var descri_out_length="备注不能超出200个字";
function checkForm(frm) {
	var msg = new Array();
	var err = false;
	var height=140;
	
	if(frm.elements['name'] && frm.elements['name'].value.trim().length==0) {
		msg.push(name_not_null); 
		err = true;
		height += 30;
	}else{
		$('#name').val(frm.elements['name'].value.trim());
	}
	

	if(frm.elements['descri'].value.trim().length>200){
		msg.push(descri_out_length);
		err = true;
		height += 30;
	}
	if(!(frm.elements['descri'] && frm.elements['descri'].value.trim().length==0)){
		$('#descri').val(frm.elements['descri'].value.trim());
	}
	
	if(frm.elements['name'].value.trim().length>20){
		msg.push(name_out_length);
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
        	
          
          <div id="main_h4">群组列表</div>
          <div id="main_btn">
          	<a href="javascript:group.doSavePage('${ctx}/ss/Groups/doSavePage.do')" class="type_a"><div id="btn_2">+新增群组</div></a>
          </div>
            
            <div id="content">
            	<table width="100%" border="0"cellspacing="1" cellpadding="0"  style='background-color:#cdcdcd'><!-- #e2e2e2 -->
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#e3e3e3">序号</td>
                        <td align="center" bgcolor="#e3e3e3">群组名</td>
                        <td align="center" bgcolor="#e3e3e3">操作</td>
                  </tr>
                  <c:forEach items="${groupList}" var="item" varStatus="status">
	                  <tr bgcolor="#ffffff">
	                  	<td align="center" bgcolor="#f6f6f6">${status.count}</td>
                        <td align="left" bgcolor="#f6f6f6"><c:out value="${item.name}"/></td>
                        <td align="center" bgcolor="#f6f6f6">
                        	<a href="javascript:deleteGroup('${ctx}/ss/Groups/doDeleteAjax.do?id=${item.id}')" class="type_c">【删除】</a>
                        </td>
	                  </tr>
                  </c:forEach>
              </table>
     </div>
            
       </div>
