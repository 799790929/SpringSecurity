var Sys = {};
var USERAGENT = navigator.userAgent.toLowerCase();
Sys.ie = window.ActiveXObject && USERAGENT.indexOf('msie') != -1 && USERAGENT.substr(USERAGENT.indexOf('msie') + 5, 3);
Sys.firefox = USERAGENT.indexOf('firefox') != -1 && USERAGENT.substr(USERAGENT.indexOf('firefox') + 8, 3);
Sys.chrome = window.MessageEvent && !document.getBoxObjectFor && USERAGENT.indexOf('chrome') != -1 && USERAGENT.substr(USERAGENT.indexOf('chrome') + 7, 10);
Sys.opera = window.opera && opera.version();
Sys.safari = window.openDatabase && USERAGENT.indexOf('safari') != -1 && USERAGENT.substr(USERAGENT.indexOf('safari') + 7, 8);
Sys.other = !Sys.ie && !Sys.firefox && !Sys.chrome && !Sys.opera && !Sys.safari;
Sys.firefox = Sys.chrome ? 1 : Sys.firefox;

var _doc = (document.compatMode != "BackCompat") ? document.documentElement : document.body;
function $$(o){return document.getElementById(o);}

var ajax={
	_objPool: [],
	_getInstance: function ()    {
		for (var i = 0; i < this._objPool.length; i ++){
			if (this._objPool[i].readyState == 0 || this._objPool[i].readyState == 4){
				return this._objPool[i];
			}
		}
		this._objPool[this._objPool.length] = this.createxml();
		return this._objPool[this._objPool.length - 1];
	},
	createxml: function (){
		try{
			if( window.ActiveXObject ){
				for( var i = 4; i; i-- ){
					try{
						if( i == 2 ){
							xmlobje = new ActiveXObject( "Microsoft.XMLHTTP" );
						}else{
							xmlobje = new ActiveXObject( "Msxml2.XMLHTTP." + i + ".0" );
							xmlobje.setRequestHeader("Content-Type","text/html");
							xmlobje.setRequestHeader("Content-Type","gb2312");
						}
						break;
					}catch(e){
						xmlobje = false;
					}
				}
			}else if( window.XMLHttpRequest ){
				xmlobje = new XMLHttpRequest();
				if (xmlobje.overrideMimeType){
					xmlobje.overrideMimeType('text/xml');
				}
			}
		}catch(e){
			xmlobje = false;
		}
		if (xmlobje.readyState == null){
			xmlobje.readyState = 0;
			xmlobje.addEventListener("load", function (){
				xmlobje.readyState = 4;
				if (typeof xmlobje.onreadystatechange == "function"){
					xmlobje.onreadystatechange();
				}
			},  false);
		}
		return xmlobje;
	},
	Req: function (method,url,data,func){
		var xmlobje=this._getInstance();
		if (url.indexOf("?") > 0){
			url += "&randnum=" + Math.random();
		}else{
			url += "?randnum=" + Math.random();
		}
		xmlobje.open(method,url,true);
		if (method.toUpperCase()=="POST"){
			xmlobje.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		}
		xmlobje.onreadystatechange=function(){
			
			if(Sys.firefox){var states=3;}else{var states=4;}
			if(xmlobje.readyState==states){
				if(xmlobje.status==200){
					func(xmlobje,xmlobje.status);
				}
			}
		};
		xmlobje.send(data);
	},
	
	/* *
	  * 对返回的HTTP响应结果进行过滤。
	  *
	  * @public
	  * @params   {mix}   result   HTTP响应结果
	  * @return  返回过滤后的结果
	  * @type string
	  */
	  preFilter : function (result)
	  {
	    return result.replace(/\xEF\xBB\xBF/g, "");
	  }
};

var MessageBox={
	maxZIndex:1000,
	i:0,
	mark:null,
	_obj:[],
	_ini:function(v)
	{
		if(v==1){
			for(var i=0;i<this._obj.length;i++)
			{
				if(this._obj[i].style.display=="none"){
					this.i=i;
					return this._obj[i];
				}
			}
			this.i=this._obj.length;
			this._obj[this._obj.length]=this._CreateWindow(i);
			return this._obj[this._obj.length-1];
			
		}else{
			if(this._obj[0])
				return this._obj[0];
			else
				this._obj[0]=this._CreateWindow(i);
				return this._obj[0];
		}
	},

	_CreateWindow:function()
	{
		if(!$$("ajax"+this.i)){
			var oab = document.createElement("div");
			oab.id = "ajax"+this.i;
			document.body.appendChild(oab);
		}
		//oab.style.overflow="visible";

		return oab;
	},

	Msg:function (t,v)
	{
		var agr=arguments;
		var showCloseButton = arguments[4];
		var w,h;
		if(agr[3]==1){
			var WD=this._ini(1);
		}else {
			var WD=this._ini(0);
		}

		if(agr[2]==1)
		{
			this.Mark(this.maxZIndex - 1, WD.id.substring("ajax".length));
		}else{
			this.classMark(WD.id.substring("ajax".length));
		}
		
		var windowSN = this._msg(t,v,WD);
		if (undefined != showCloseButton && 0 == showCloseButton) $$("msg_close"+windowSN).style.display="none";
		else  $$("msg_close"+windowSN).style.display="";
		
		return windowSN;
		
	},


	_msg:function (t,v,o)
	{
		var agr=arguments;
		var sn = o.id.substring("ajax".length);
		
		if ("0" == sn) sn = "";
		
		o.style.position="absolute";
		o.style.zIndex=this.maxZIndex++;
		o.style.border="0px";
		o.style.backgroundColor="";

		o.style.top="0px";
		o.style.left="0px";
		o.style.padding="0px";
		var m="";

		m+="<div id=\"back_ap_div" + sn + "\" style=\"border:1px solid #5FAEE4;padding:0px;background:#5FAEE4;left:5px;top:5px;position:absolute; \"></div>";
		m+="<div id=\"fly_message" + sn + "\" style=\"color:#fff;position:absolute;top:6px;left:6px;border:1px solid #A2CEF0;\">";
		if(t==''){
			
		}else{
			m+="<div id=\"msg_title" + sn + "\" style=\"color:#fff;background:url(images/messagebox_title_bg.jpg) #6BB1E7 repeat-y;cursor:move;padding:0 5px;font-weight:bold;font-size:14px;height:28px;line-height:28px;\">"+t+"</div>";
			m+="<div id=\"msg_close" + sn + "\" onclick=\"if(MessageBox.hid(this)){MessageBox.classMark('"+ sn +"');}\" style=\"position:absolute;top:0px;background:url(images/messagebox_close.gif) ;width:20px;height:20px;cursor:pointer;z-index:1000;\"></div>";
		}
		m+="<div id=\"message" + sn + "\" style=\"width:400px;\">"+v+"</div>";
		m+="</div>";

		o.style.display="block";
		o.innerHTML=m;

		mw=$$('message' + sn).offsetWidth;
		mh=$$('message' + sn).offsetHeight;
		if(t==''){
			mh=mh-28;
		}else{
			$$('msg_title' + sn).style.width=(mw-10)+"px";
			$$('msg_close' + sn).style.left=(mw-25)+"px";
		}
		o.style.width=mw+12+"px";
		o.style.height=mh+28+12+"px";
		
		$$('fly_message' + sn).style.width=mw+"px";
		$$('fly_message' + sn).style.height=mh+28+"px";
		$$('back_ap_div' + sn).style.width=mw+2+"px";
		$$('back_ap_div' + sn).style.height=mh+31+"px";

		var sctop=	document.documentElement.scrollTop||document.body.scrollTop;
		fly_top=sctop+_doc.clientHeight/2-o.offsetHeight/2;
		if ((_doc.clientHeight/2-o.offsetHeight/2)<0) fly_top=sctop;
		fly_left=_doc.scrollLeft+_doc.clientWidth/2-o.offsetWidth/2;
		if (fly_left<0) fly_left="0";
		o.style.top=fly_top+"px";
		o.style.left=fly_left+"px";

		return sn;
	},
	Mark:function (zIndex, sn)
	{
		if(!zIndex) zIndex = this.maxZIndex++;
		if(!sn) sn = "";
		var maskObj=$$("mark_all" + sn);
		if(!maskObj){
			maskObj = document.createElement("div");
			maskObj.id="mark_all" + sn;
			document.body.appendChild(maskObj);
		}

		maskObj.style.display="block";
		maskObj.style.background="#000";
		maskObj.style.width=_doc.scrollWidth+"px";
		maskObj.style.height=_doc.scrollHeight+"px";
		maskObj.style.position="absolute";

		maskObj.style.filter="alpha";
		maskObj.style.top=0;
		maskObj.style.left=0;
		maskObj.style.zIndex=zIndex;

		if(document.all){
			maskObj.filters.alpha.opacity=60;
		}else{
			maskObj.style.MozOpacity=0.6;
			maskObj.style.opacity=0.6;
		}
	},
	classMark:function(sn)
	{
		if (!sn || "" == sn) {
			sn = "0";
			var maskObj = $$("mark_all" + sn);
			if (maskObj) {
				maskObj.style.display = "none";
			}
			sn = "";
			var maskObj = $$("mark_all" + sn);
			if (maskObj) {
				maskObj.style.display = "none";
			}
		} else {
			var maskObj = $$("mark_all" + sn);
			if (maskObj) {
				maskObj.style.display = "none";
			}
		}
	},
	hid:function (o)
	{	
		var sn = o.id.substring("msg_close".length);
		var callBackFunc = eval("window.beforMsgCloseFunc" + sn);
		if (callBackFunc != undefined && callBackFunc != null && false == callBackFunc()) return false;
		o.parentNode.parentNode.style.display="none";
		if(this.mark&&this.mark.style.display!="none") this.mark.style.display="none";
		if (callBackFunc != undefined && callBackFunc != null ) eval("window.beforMsgCloseFunc" + sn + " = function(){return true;}");
		return true;
	}
};

/*
*模拟ajax提交表单
*/
function ajaxpost(formid,recall){
	var ajaxframeid = 'ajaxframe';
	var handleResult=function(){
		s = $$(ajaxframeid).contentWindow.document.body.innerHTML;
		ajaxframe.loading = 0;
		recall(s);
		document.body.removeChild($$(ajaxframeid));
	}
	if(!$$('ajaxframe')){
		if (Sys.ie) {
			try{
				coverObj = document.createElement('<iframe name="' + ajaxframeid + '" id="' + ajaxframeid + '"></iframe>');
			}catch(e){
				coverObj = document.createElement('iframe');
				coverObj.id = ajaxframeid;
				coverObj.name = ajaxframeid;
			}
		} else {
			coverObj = document.createElement('iframe');
			coverObj.id = ajaxframeid;
			coverObj.name = ajaxframeid;
		}
		coverObj.style.display = 'none';
		coverObj.loading = 1;
		document.body.appendChild(coverObj);
		addEvent( coverObj, 'load', handleResult );
	}
	$$(formid).target = ajaxframeid;
	$$(formid).action += '&ajax=1';
	$$(formid).submit();
	return false;
}

/*
*模拟ajax提交表单
*/
function ajaxpostfile(formid,data,recall){
	var ajaxframeid = 'ajaxframe';
	var handleResult=function(){
		s = $$(ajaxframeid).contentWindow.document.body.innerHTML;
		ajaxframe.loading = 0;
		recall(s);
		document.body.removeChild($$(ajaxframeid));
	}
	if(!$$('ajaxframe')){
		if (Sys.ie) {
			try{
				coverObj = document.createElement('<iframe name="' + ajaxframeid + '" id="' + ajaxframeid + '"></iframe>');
			}catch(e){
				coverObj = document.createElement('iframe');
				coverObj.id = ajaxframeid;
				coverObj.name = ajaxframeid;
			}
		} else {
			coverObj = document.createElement('iframe');
			coverObj.id = ajaxframeid;
			coverObj.name = ajaxframeid;
		}
		coverObj.style.display = 'none';
		coverObj.loading = 1;
		document.body.appendChild(coverObj);
		addEvent( coverObj, 'load', handleResult );
	}
	$$(formid).target = ajaxframeid;
	$$(formid).encoding="multipart/form-data";
	$$(formid).submit();
	return false;
}

function addEvent(obj, type, fn) {
	if (obj.attachEvent) {
		obj['e' + type + fn] = fn;
		obj[type + fn] = function() {
			obj['e' + type + fn](window.event);
		}
		obj.attachEvent('on' + type, obj[type + fn]);
	} else {
		obj.addEventListener(type, fn, false);
	}
}