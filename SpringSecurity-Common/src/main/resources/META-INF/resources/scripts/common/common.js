/**
 * @author Administrator
 */
var dateOperation={
	getYearFirstDay:function(){
		var dts=new Date();
		var date=dts.getYear();
		date=date+'-01-01';
		return date;
	},
	getYearLastDay:function(){
		var dts=new Date();
		var date=dts.getYear();
		date=date+'-12-31';
		return date;
	},
	getMonthLastDay:function(year,month){
		var m = 0;
		var y = 0;
		if(isNaN(year)){
			y = parseInt(year);
		}else{
			y = year;
		}
		
		if(isNaN(month)){
			if(month.length==1){
				m = parseInt(month);
			}else if(month.length==2){
				var f = month.substring(0,1);
				var s = month.substring(1,2);
				if('0'==f){
					m = parseInt(s);
				}else{
					m = parseInt(month);
				}
			}
		}else{
			m=month;
		}
		
		var date = new Date(y,m,0);
		var day = date.getDate();
		if(day<10){
			day='0'+day;
		}
		return year+'-'+month+'-'+day;
	}
};
var datepicker={
	mindate:function(id,interval){
		var date='';
		var distance = 0;
		if(isNaN(interval)){
			distance = parseInt(interval);
		}else{
			distance = interval;
		}
		
		if(id===null||id==undefined||id==''){
			date = dateOperation.getYearFirstDay();
		}else{
			var dt=$("#"+id).val();
			if(dt===null||dt==''||dt==undefined){
				date = dateOperation.getYearFirstDay();
			}else{
				var year = dt.substring(0,4);
				var y = parseInt(year)+distance;
				date = y+"-01-01";
			}
		}
		return date;
	},
	maxdate:function(id,interval){
		var date='';
		var distance = 0;
		if(isNaN(interval)){
			distance = parseInt(interval);
		}else{
			distance = interval;
		}
		
		if(id===null||id==undefined||id==''){
			date = dateOperation.getYearLastDay();
		}else{
			var dt=$("#"+id).val();
			if(dt===null||dt==''||dt==undefined){
				date = dateOperation.getYearLastDay();
			}else{
				var year = dt.substring(0,4);
				var y = parseInt(year)+distance;
				date = y+"-12-31";
			}
		}
		return date;
	}
};

var num_fun={
	rightNum:function(val){
		var str = '';
		var len = val.indexOf('.');
		if (len >= 0) {
			str = val.substring(len,val.length);
		}
		return str;
	}
};

function $$(o){return document.getElementById(o);}
function reload(){window.location.reload();}

var dialog={
	msgContent:function(title,cont){
		var main ="<div id=\"dialog_content\" style=\"width:100%;\"> "+cont+"</div>";
		if(!$$("dialog_win")){
			var oab = document.createElement("div");
			oab.id = "dialog_win";
			if(title!=null) oab.title=title;
			document.body.appendChild(oab);
			oab.innerHTML=main;
		}else{
			$$("dialog_win").innerHTML=main;
		}
	},
	msgAlert:function(title,cont){
		var con = '<div id="tc_div_msg" style="height: 140px;">'+
			'<div class="div20px"></div>'+
			'<div class="div10px"></div>'+
			'<div style="padding-left: 30px;">'+
				'<table width="96%" border="0" align="center">'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td colspan="2">'+cont+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td>'+
						'<div class="div15px"></div> &nbsp;&nbsp;&nbsp;'+ 
						'<input id="car_inf_add" type="button" style="margin-left : 40px " class="btn_a" value="确定" onclick="dialog.hide();"/> &nbsp;&nbsp;'+ 
					'</td>'+
					'<td>&nbsp;</td>'+
				'</tr>'+
			'</table>'+
		'</div>'+
	'</div>';
		this.msgContent(title,con);
		$("#dialog_win").dialog({
			modal:true,
			autoOpen:true,
			resizable: false,
			width:'auto'
		});
	},
	msgAutoAlert:function(title,cont,height){
		var con = '<div id="tc_div_msg" style="height: '+ height +'px;">'+
			'<div class="div20px"></div>'+
			'<div class="div10px"></div>'+
			'<div style="padding-left: 30px;">'+
				'<table width="96%" border="0" align="center">'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td colspan="2">'+cont+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td>'+
						'<div class="div15px"></div> &nbsp;&nbsp;&nbsp;'+ 
						'<input id="car_inf_add" type="button" style="margin-left : 40px " class="btn_a" value="确定" onclick="dialog.hide();"/> &nbsp;&nbsp;'+ 
					'</td>'+
					'<td>&nbsp;</td>'+
				'</tr>'+
			'</table>'+
		'</div>'+
	'</div>';
		this.msgContent(title,con);
		$("#dialog_win").dialog({
			modal:true,
			autoOpen:true,
			resizable: false,
			width:'auto'
		});
	},
	msgAlertFun:function(title,cont,fun){
		var con = '<div id="tc_div_msg" style="height: 180px;width:300px;">'+
			'<div class="div20px"></div>'+
			'<div class="div10px"></div>'+
			'<div style="padding-left: 30px;">'+
				'<table width="96%" border="0" align="center">'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td colspan="2">'+cont+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td>'+
						'<div class="div15px"></div> &nbsp;&nbsp;&nbsp;'+ 
						'<input id="car_inf_add" type="button" class="btn_a" value="确定" onclick="'+fun+'"/> &nbsp;&nbsp;'+ 
					'</td>'+
					'<td>&nbsp;</td>'+
				'</tr>'+
			'</table>'+
		'</div>'+
	'</div>';
		this.msgContent(title,con);
		$("#dialog_win").dialog({
			modal:true,
			autoOpen:true,
			resizable: false,
			width:'auto'
		});
	},
	msgConfirm:function(title,cont){
		var con = '<div id="tc_div_msg" style="height: 140px;">'+
			'<div class="div20px"></div>'+
			'<div class="div10px"></div>'+
			'<div style="padding-left: 30px;">'+
				'<table width="96%" border="0" align="center">'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td colspan="2">'+cont+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td>'+
						'<div class="div15px"></div> &nbsp;&nbsp;&nbsp;'+ 
						'<input id="car_inf_add" type="button" class="btn_a" value="确定" onclick="dialog.hide();"/> &nbsp;&nbsp;'+ 
						'<input type="button" class="btn_a" value="取消"  onclick="dialog.hide();"/>'+
					'</td>'+
					'<td>&nbsp;</td>'+
				'</tr>'+
			'</table>'+
		'</div>'+
	'</div>';
		this.msgContent(title,con);
		$("#dialog_win").dialog({
			modal:true,
			autoOpen:true,
			resizable: false,
			width:'auto'
		});
	},
	/**
	 * 点击'确定'可以操作某一方法,请不要忘记关闭窗口
	 * @param {Object} title
	 * @param {Object} con
	 * @param {Object} fun
	 */
	msgConfirmFun:function(title,cont,fun){
		var con = '<div id="tc_div_msg" style="height: 140px;">'+
			'<div class="div20px"></div>'+
			'<div class="div10px"></div>'+
			'<div style="padding-left: 30px;">'+
				'<table width="96%" border="0" align="center">'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td colspan="2">'+cont+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td>'+
						'<div class="div15px"></div> &nbsp;&nbsp;&nbsp;'+ 
						'<input id="car_inf_add" type="button" class="btn_a" value="确定" onclick="'+fun+'"/> &nbsp;&nbsp;'+ 
						'<input type="button" class="btn_a" value="取消"  onclick="dialog.hide();"/>'+
					'</td>'+
					'<td>&nbsp;</td>'+
				'</tr>'+
			'</table>'+
		'</div>'+
	'</div>';
		this.msgContent(title,con);
		$("#dialog_win").dialog({
			modal:true,
			autoOpen:true,
			resizable: false,
			width:'auto'
		});
	},
	msgBox:function(title,cont,width,height){
		this.msgContent(title,cont);
		$("#dialog_win").dialog({
			modal:true,
			autoOpen:true,
			resizable: false,
			width:width,
			height:height
		});
	},
	hide:function(){
		$("#dialog_win").dialog('close');
	}
};


var dialog_2={
	msgContent_2:function(title,cont){
		var main ="<div id=\"dialog_content_2\" style=\"width:100%;\"> "+cont+"</div>";
		if(!$$("dialog_win_2")){
			var oab = document.createElement("div");
			oab.id = "dialog_win_2";
			if(title!=null) oab.title=title;
			document.body.appendChild(oab);
			oab.innerHTML=main;
		}else{
			$$("dialog_win_2").innerHTML=main;
		}
	},
	msgBox_2:function(title,cont,width,height){
		this.msgContent_2(title,cont);
		$("#dialog_win_2").dialog({
			modal:true,
			autoOpen:true,
			resizable: false,
			width:width,
			height:height
		});
	},
	hide_2:function(){
		$("#dialog_win_2").dialog('close');
	},
	msgAlert_2:function(title,cont){
		var con = '<div id="tc_div_msg" style="height: 140px;">'+
			'<div class="div20px"></div>'+
			'<div class="div10px"></div>'+
			'<div style="padding-left: 30px;">'+
				'<table width="96%" border="0" align="center">'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td colspan="2">'+cont+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td>'+
						'<div class="div15px"></div> &nbsp;&nbsp;&nbsp;'+ 
						'<input id="car_inf_add" type="button" style="margin-left : 40px " class="btn_a" value="确定" onclick="dialog_2.hide_2();"/> &nbsp;&nbsp;'+ 
					'</td>'+
					'<td>&nbsp;</td>'+
				'</tr>'+
			'</table>'+
		'</div>'+
	'</div>';
		this.msgContent_2(title,con);
		$("#dialog_win_2").dialog({
			modal:true,
			autoOpen:true,
			resizable: false,
			width:'auto'
		});
	},
	msgAutoAlert_2:function(title,cont,height){
		var con = '<div id="tc_div_msg" style="height: '+ height +'px;">'+
			'<div class="div20px"></div>'+
			'<div class="div10px"></div>'+
			'<div style="padding-left: 30px;">'+
				'<table width="96%" border="0" align="center">'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td colspan="2">'+cont+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td>'+
						'<div class="div15px"></div> &nbsp;&nbsp;&nbsp;'+ 
						'<input id="car_inf_add" type="button" style="margin-left : 40px " class="btn_a" value="确定" onclick="dialog_2.hide_2();"/> &nbsp;&nbsp;'+ 
					'</td>'+
					'<td>&nbsp;</td>'+
				'</tr>'+
			'</table>'+
		'</div>'+
	'</div>';
		this.msgContent_2(title,con);
		$("#dialog_win_2").dialog({
			modal:true,
			autoOpen:true,
			resizable: false,
			width:'auto'
		});
	},
	msgAlertFun_2:function(title,cont,fun){
		var con = '<div id="tc_div_msg_2" style="height: 180px;width:300px;">'+
			'<div class="div20px"></div>'+
			'<div class="div10px"></div>'+
			'<div style="padding-left: 30px;">'+
				'<table width="96%" border="0" align="center">'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td colspan="2">'+cont+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td>&nbsp;</td>'+
					'<td>'+
						'<div class="div15px"></div> &nbsp;&nbsp;&nbsp;'+ 
						'<input id="car_inf_add_2" type="button" class="btn_a" value="确定" onclick="'+fun+'"/> &nbsp;&nbsp;'+ 
					'</td>'+
					'<td>&nbsp;</td>'+
				'</tr>'+
			'</table>'+
		'</div>'+
	'</div>';
		this.msgContent_2(title,con);
		$("#dialog_win_2").dialog({
			modal:true,
			autoOpen:true,
			resizable: false,
			width:'auto'
		});
	}
};

String.prototype.trim=function(){ 
	return this.replace(/(^\s*)|(\s*$)/g, "");
};