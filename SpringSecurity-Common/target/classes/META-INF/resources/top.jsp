<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/ss" prefix="ss"%>

<script type="text/javascript">
<!--
var sus={
	get_id_postition:function(id){
		var t=$('#'+id).offset().top;
		var l=$('#'+id).offset().left;
		var w=$('#'+id).width();
		var h=$('#'+id).height();
		var re = new Array();
		re[0]=t;
		re[1]=l;
		re[2]=w;
		re[3]=h;
		return re;
	},
	ov:function(id){
		var re=this.get_id_postition(id);
		$('#abc').css('display','block');
		$('#abc').css('top',re[0]+re[3]+'px');
		$('#abc').css('left',re[1]);
	},
	hid:function(){
		var d = $('#abc').css('display');
		if (d=='block') {
			$('#abc').css('display','none');
		}
	},
	show:function(){
		var d = $('#abc').css('display');
		if (d=='none') {
			$('#abc').css('display','block');
		}
	},
	ajaxShow:function(){
		$.post('<c:url value="/pages/TtReal/getTopPage.do"/>',
				{},
				function(vals){
					var message = vals.message;
					var m = message.split('$');
					if (m[1]=='无'){
						$('#review1').html('');
					} else {
						$('#one').html(m[1]);
					}
					if (m[2]=='无'){
						$('#review2').html('');
					} else {
						$('#two').html(m[2]);
					}
					if (m[3]=='无'){
						$('#review3').html('');
					} else {
						$('#three').html(m[3]);
					}
					sus.ov('prompt');
				},'json');
	},
	logout:function(){
		window.location.href='${ctx}/logout';
		if (navigator.userAgent.indexOf("MSIE") > 0) {
			if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
				window.opener = null;
				window.close();
			} else {
				window.open('', '_top');
				window.top.close();
			}
		} else if (navigator.userAgent.indexOf("Firefox") > 0) {
			window.location.href = 'about:blank ';
			window.open('', '_self', '');
			window.close(); 
		} else if (navigator.userAgent.indexOf("Chrome") > 0){
			top.window.opener = top;
			top.window.open('','_self','');
			top.window.close();
		} else {
			window.opener = null;
			window.open('', '_self', '');
			window.close();
		}
	}
};

$(function(){
	$('#abc').hover(
		function(){
			var d = $('#abc').css('display');
			if (d=='none') {
				$('#abc').css('display','block');
			}
		},
		function(){
			var d = $('#abc').css('display');
			if (d=='block') {
				$('#abc').css('display','none');
			}
		}
	);
});

var sumbit={
	edit:function(){
		$.post('<c:url value="/pages/TsUser/doChange.do"/>', 
				{}, 
				function(vals) {
					var len = vals.indexOf('上海大众-用户登录');
					if (len >= 0){
						dialog.hide();
						window.location.href="<c:url value='/'/>";
					} else {
						dialog.msgBox('用户修改密码',vals,"auto","auto");
					}
				},'HTML'
		);
	},
	save:function(){
		var pwd = $('#password').val();
		if(pwd==null || pwd=='' || pwd==undefined) {
			$('#error_label').html('密码为空');
			return;
		}
		if(pwd.length > 20 ){
			$('#error_label').html('密码长度不能超出20个字');
			return;
		}
		var rpwd = $('#repassword').val();
		if(rpwd==null || rpwd=='' || rpwd==undefined) {
			$('#error_label').html('确认密码为空');
			return;
		}
		if(rpwd != pwd) {
			$('#error_label').html('两次输入密码不一致');
			return;
		} 

		var password = $('#password').val();
		var url = '<c:url value="/pages/TsUser/toSave.do"/>';
		$.ajax({
			type:'POST',
			url:url,
			data:{password:password},
			dataType:'JSON',
			error:function(XMLHttpRequest, textStatus, errorThrown){
				var responseTxt = XMLHttpRequest.responseText;
				dialog.msgAlert('消息',responseTxt);
			},
			success:function(data, textStatus){
				var isSuccess = data.isSuccess;
				if (isSuccess) {
					dialog.hide();
					dialog.msgConfirmFun('消息',data.message,'reload()');
				} else {
					//dialog.msgAlert('消息',data.message);
					$('#error_label').html(data.message);
				}
			}
		});
	}
};

//-->
</script>

<style type="text/css">
.li_hover{
	background:url("") no-repeat scroll 0 8px rgba()
}
</style>

<div id="top_box"  style="width:100%">
	<div id="top_x" style="width:100%"></div>
	<div id="top_id" style="margin-right:5px;width: 500px">
		<table width="100%" border="0">
			<tr>
				<td align="right">欢迎您：<span>${sessionScope.loginUser.username}</span>
				&nbsp;&nbsp;｜&nbsp;&nbsp;
				<a href="javascript:sus.logout()">注销</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
		</table>

	</div>

	<div class="clear"></div>

	<div id="menu_div">
		<ul>
			
			<ss:permission permission="/ss/Users/doList.do">
				<li <c:if test="${menu=='system'}">id="li_b"</c:if>>
					<table width="100%" border="0">
						<tr>
							<td width="23%"><img src="${ctx}/images/ico04.png" /></td>
							<td width="77%"><a href="${ctx }/ss/Users/doList.do" class="type_b">系统管理</a>
							</td>
						</tr>
					</table>
				</li>
			</ss:permission>
			
			<div class="clear"></div>
		</ul>

	</div>

</div>

