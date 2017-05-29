<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script>
function deleteUser_2(url){
	dialog.hide();
	$.ajax({     
	    url:url,     
	    type:'get',   
	    async : true, //默认为true 异步     
	    error:function(){     
	    	dialog.msgAlert('error');     
	    },     
	    success:function(v){ 
	    	if(v == 'success') {
				dialog.msgAlertFun("提示","删除成功",'reload()');
				//window.location.href="${ctx}/ss/Users/doList.do";
			} else {
				dialog.msgAlert("提示",v);
				//alert(v);
			}
	    }  
	});
}
function deleteUser(url) {
	dialog.msgConfirmFun('警告','确定删除','deleteUser_2(\''+url+'\')');
	/*if(dialog.msgConfirm("警告","确定删除吗?")) {
		$.ajax({     
		    url:url,     
		    type:'get',   
		    async : true, //默认为true 异步     
		    error:function(){     
		    	dialog.msgAlert('error');     
		    },     
		    success:function(v){ 
		    	alert(v);
		    	if(v == 'success') {
					dialog.msgAlert("提示","删除成功");
					window.location.href="${ctx}/ss/Users/doList.do";
				} else {
					dialog.msgAlert("提示",v);
					//alert(v);
				}
		    }  
		}); 
		
	}*/
	/* if(confirm("确定删除吗?")) {
		$.ajax({     
		    url:url,     
		    type:'get',   
		    async : true, //默认为true 异步     
		    error:function(){     
		    	dialog.msgAlert('error');     
		    },     
		    success:function(v){     
		    	if(v == 'success') {
					dialog.msgAlert("删除成功");
					window.location.href="${ctx}/ss/Users/doList.do";
				} else {
					dialog.msgAlert(v);
					//alert(v);
				}
		    }  
		}); 
		
	} */
}
function addUserPage(url) {
	window.open(url,'newwindow','height=500,width=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no,channelmode=yes');

	//window.showModalDialog('${ctx}/ss/Users/doSavePage.do','newwindow','');
	/*var returnValue = window.showModalDialog(url, 'newwindow','');
    //for chrome
    if (returnValue == undefined) {
        returnValue = window.returnValue;
    }

    alert(returnValue);*/
}

/*分页查询*/
function doPage(requestPage){
	/*$$("requestPage").value=requestPage;
	$$("doSearch").submit();*/
	$("#requestPage").val(requestPage);
	$("#doSearch").submit();
}








var user={
	doSavePage:function(url){
		//window.open('<c:url value="/pages/TtCarInfo/gotoPage.do"/>?pageFlag=2&pkTtCarInfo='+id);
		$.post(url,'', 
				function(vals) {
					var len = vals.indexOf('上海大众-用户登录');
					if (len >= 0){
						window.location.href="<c:url value='/'/>";
					} else {
						dialog.msgBox('用户信息添加',vals,"auto","auto");
					}
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
					var len = vals.indexOf('上海大众-用户登录');
					if (len >= 0){
						window.location.href="<c:url value='/'/>";
					} else {
						var userType = "${userType}";
						if(userType=='0') {
							dialog.msgBox('用户信息编辑',vals,"auto","auto");
						} else {
							dialog.msgBox('用户信息编辑',vals,"auto","auto");
						}
					}
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

var username_not_null = "用户名不能为空!";
var username_out_length="用户名不能超出20个字";
var realname_not_null = "用户真实姓名不能为空!";
var realname_out_length="用户真实姓名不能超出20个字";

var oldpwd_not_null = "原密码不能为空！";
var pwd_not_null = "密码不能为空！";
var pwd_out_length="密码不能超出20个字";
var rePwd_not_null = "确认密码不能为空！";
var pwd_rePwd_not = "新密码与确认密码不一致！";
var brand_out_length  ="品牌不能超出20个字";
var dealerCode_out_length ="经销商代码不能超出20个字";
var dealerName_out_length ="经销商名称不能超出20个字";
var dealer_out_length="大区不能超出20个字";
var descri_out_length="备注不能超出200个字";
function checkForm(frm,type) {
	var msg = new Array();
	var err = false;
	var height = 140;
	
	if(type && type=='save') {
		if(frm.elements['username'] && frm.elements['username'].value.trim().length==0) {
			
			msg.push(username_not_null); 
			err = true;
			height += 30;
		}else{
			$('#username').val(frm.elements['username'].value.trim());
		}
		
		if(frm.elements['username'].value.trim().length>20){
			msg.push(username_out_length);
			err = true;
			height += 30;
		}
	}
	if(frm.elements['realname'] && frm.elements['realname'].value.trim().length==0) {
		msg.push(realname_not_null);
		err = true;
		height += 30;
	}else{
		$('#realname').val(frm.elements['realname'].value.trim());
	}
	
	if(frm.elements['dealer'].value.trim().length>20){
		msg.push(dealer_out_length);
		err = true;  
		height += 30;
	}
	if(!(frm.elements['dealer'] && frm.elements['dealer'].value.trim().length==0)){
		$('#dealer').val(frm.elements['dealer'].value.trim());
	}
	
	if(frm.elements['brand'].value.trim().length>20){
		msg.push(brand_out_length);
		err = true;
		height += 30;
	}
	if(!(frm.elements['brand'] && frm.elements['brand'].value.trim().length==0)){
		$('#brand').val(frm.elements['brand'].value.trim());
	}

	if(frm.elements['dealerName'].value.trim().length>20){
		msg.push(dealerName_out_length);
		err = true;
		height += 30;
	}
	if(!(frm.elements['dealerName'] && frm.elements['dealerName'].value.trim().length==0)){
		$('#dealerName').val(frm.elements['dealerName'].value.trim());
	}

	if(frm.elements['dealerCode'].value.trim().length>20){
		msg.push(dealerCode_out_length);
		err = true;
		height += 30;
	}
	if(!(frm.elements['dealerCode'] && frm.elements['dealerCode'].value.trim().length==0)){
		$('#dealerCode').val(frm.elements['dealerCode'].value.trim());
	}

	if(frm.elements['descri'].value.trim().length>200){
		msg.push(descri_out_length);
		err = true;
		height += 30;
	}
	if(!(frm.elements['descri'] && frm.elements['descri'].value.trim().length==0)){
		$('#descri').val(frm.elements['descri'].value.trim());
	}
	

	if(frm.elements['realname'].value.trim().length>20){
		msg.push(realname_out_length);
		err = true;
		height += 30;
	}	
	
	if(type && type=='edit') {
		if(frm.elements['oldpassword'] && frm.elements['oldpassword'].value.trim().length==0){
			msg.push(oldpwd_not_null);
			err = true;
			height += 30;
		}
	}
	
	var updPwd = frm.elements['updPwd'];
	if(updPwd && updPwd.value=='0') {
		/* var pwd = "";
		if(frm.elements['password'] && frm.elements['password'].value.trim().length==0) {
			msg.push(pwd_not_null);
			err = true;
			height += 30;
		} else {
			$('#password').val(frm.elements['password'].value.trim());
			pwd = frm.elements['password'].value;
		}
		var rePwd = "";
		if(frm.elements['repassword'] && frm.elements['repassword'].value.trim().length==0) {
			msg.push(rePwd_not_null);
			err = true;
			height += 30;
		} else {
			$('#repassword').val(frm.elements['repassword'].value.trim());
			rePwd = frm.elements['repassword'].value;
		}
		if(pwd!="" && rePwd!="" && pwd!=rePwd) {
			msg.push(pwd_rePwd_not);
			err = true;
			height += 30;
		}
		if(frm.elements['password'].value.trim().length>20){
			msg.push(pwd_out_length);
			err=true;
			height += 30;
		} */
	} else {
		var pwd = "";
		if(frm.elements['password'] && frm.elements['password'].value.trim().length==0) {
			msg.push(pwd_not_null);
			err = true;
			height += 30;
		} else {
			$('#password').val(frm.elements['password'].value.trim());
			pwd = frm.elements['password'].value;
		}
		var rePwd = "";
		if(frm.elements['repassword'] && frm.elements['repassword'].value.trim().length==0) {
			msg.push(rePwd_not_null);
			err = true;
			height += 30;
		} else {
			$('#repassword').val(frm.elements['repassword'].value.trim());
			rePwd = frm.elements['repassword'].value;
		}
		if(pwd!="" && rePwd!="" && pwd!=rePwd) {
			msg.push(pwd_rePwd_not);
			err = true;
			height += 30;
		}
		if(frm.elements['password'].value.trim().length>20){
			msg.push(pwd_out_length);
			err=true;
			height += 30;
		}
	}
	
	
	if(err) {
		message = msg.join("<br/>");
		dialog_2.msgAutoAlert_2('提示',message,height);
	}
	
	return !err;
}

var check={
	getDealerName:function(){
		var pointCode = $('#dealerCode').val();
		pointCode = $.trim(pointCode);
		$('#dealerCode').val(pointCode);
		$.post('<c:url value="/pages/TsPoint/getDealerName.do"/>' ,
				{pointCode:pointCode} ,
				function(vals){
					var isSuccess = vals.isSuccess;
					if (isSuccess) {
						//$('#isDealer_l').html(vals.message);
						$('#dealerName').val(vals.message);
					} else {
						//$('#isDealer_l').html('请重新填写经销商代码');
						$('#dealerName').val('');
					}
				},'JSON');
	},
	clear:function(){
		$('#error_label').html('');
	}
};
</script>

<div id="right_box">
        	<!-- <h3>车辆库存</h3> -->
        	<c:if test="${isSuper eq 1 }">
          <div id="search_div">
	          <form class="formpage" action='${ctx}/ss/Users/doList.do' method='post'>
	            用户名<input name="username" type="text" class="input_a" value="<c:out value="${username}"/>" /><input type="submit" class="btn_a" value="查询" />
	          </form>
          </div>
          </c:if>
          
          <div id="main_h4">用户列表</div>
          <div id="main_btn">
          	<%-- <a href="${ctx}/ss/Users/doSavePage.do" class="type_a"><div id="btn_2">+新增用户</div></a> --%>
          	<%-- <a href="javascript:addUserPage('${ctx}/ss/Users/doSavePage.do')" class="type_a"> --%>
          	<%-- <a href="javascript:window.showModalDialog('${ctx}/ss/Users/doSavePage.do','newwindow','');" class="type_a"> --%>
          	<%-- <a href="javascript:window.open('${ctx}/ss/Users/doSavePage.do','newwindow','height=500,width=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no,channelmode=yes')" class="type_a"> --%>
          	<c:if test="${isSuper eq 1 }">
	          	<a href="javascript:user.doSavePage('${ctx}/ss/Users/doSavePage.do')" class="type_a">
	          		<div id="btn_2">+新增用户</div>
	          	</a>
          	</c:if>
          </div>
            
            <div id="content">
            	<table width="100%" border="0" cellspacing="1" cellpadding="0"  style='background-color:#cdcdcd'>
                      <tr bgcolor="#ffffff">
                        <td align="center" bgcolor="#e3e3e3">序号</td>
                        <td align="center" bgcolor="#e3e3e3">用户名</td>
                        <td align="center" bgcolor="#e3e3e3">真实姓名</td>
                        <td align="center" bgcolor="#e3e3e3">品牌</td>
                        <td align="center" bgcolor="#e3e3e3">经销商代码</td>
                        <td align="center" bgcolor="#e3e3e3">经销商名称</td>
                        <td align="center" bgcolor="#e3e3e3">大区</td>
                        <td align="center" bgcolor="#e3e3e3">用户类型</td>
                        <td align="center" bgcolor="#e3e3e3">备注</td>
                        <td align="center" bgcolor="#e3e3e3">添加时间</td>
                        <td align="center" bgcolor="#e3e3e3">操作</td>
                  </tr>
                  <c:forEach items="${userList}" var="item" varStatus="status">
	                  <tr bgcolor="#ffffff">
	                  	<td align="center" bgcolor="#f6f6f6">${status.count}</td>
                        <td align="left" bgcolor="#f6f6f6"><c:out value="${item.username}"/></td>
                        <td align="left" bgcolor="#f6f6f6"><c:out value="${item.realname}"/></td>
                        <td align="left" bgcolor="#f6f6f6"><c:out value="${item.brand}"/></td>
                        <td align="left" bgcolor="#f6f6f6"><c:out value="${item.dealerCode}"/></td>
                        <td align="left" bgcolor="#f6f6f6"><c:out value="${item.dealerName}"/></td>
                        <td align="left" bgcolor="#f6f6f6">${item.dealer}</td>
                        <td align="left" bgcolor="#f6f6f6">${item.userType}</td>
                        <td align="left" bgcolor="#f6f6f6">${item.descri}</td>
                        <td align="left" bgcolor="#f6f6f6">
                        <fmt:formatDate value="${item.createdt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <%-- ${item.createdt} --%></td>
                        <td align="center" bgcolor="#f6f6f6">
                        	<a href="javascript:user.doEditPage('${ctx}/ss/Users/doEditPage.do?id=${item.id}')" >【编辑】</a>
                        	&nbsp;&nbsp;
                        	<c:if test="${isSuper eq 1 }">
	                        <a href="javascript:deleteUser('${ctx}/ss/Users/doDeleteAjax.do?id=${item.id}')" class="type_c">【删除】</a>
	                        </c:if>
                        </td>
	                  </tr>
                  </c:forEach>
              </table>
				<div id="f_page">
					<form class="formpage" id='doSearch' action='${ctx}/ss/Users/doList.do' method='post' name='doSearch'>
						<input type='hidden' name='requestPage'  id='requestPage' value='<c:out value="${requestPage}"/>"'/>
						<input type='hidden' name='username' value="<c:out value="${username}"/>"/>
					
						<c:choose>
							<c:when test="${requestPage le 1}">
								首页&nbsp;&nbsp;&nbsp;&nbsp;
								上一页&nbsp;&nbsp;&nbsp;&nbsp; 
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0)" onclick="doPage(1)">首页</a>&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="javascript:void(0)" onclick='doPage("${requestPage-1}");'>上一页 </a>&nbsp;&nbsp;&nbsp;&nbsp; 
							</c:otherwise>
						</c:choose>
						${requestPage}/<c:choose><c:when test="${pagesCount le 1 }">1</c:when><c:otherwise>${pagesCount}</c:otherwise></c:choose>
						 &nbsp;&nbsp;&nbsp;&nbsp;
						<c:choose>
							<c:when test="${requestPage ge pagesCount}">
								下一页&nbsp;&nbsp;&nbsp;&nbsp;
								尾页
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0)" onclick='doPage("${requestPage + 1}");'>下一页 </a>&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="javascript:void(0)" onclick='doPage("${pagesCount}")'>尾页 </a>
							</c:otherwise>
						</c:choose>
					</form>
				</div> 
     </div>
            
       </div>
