<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title><s:text name="login.labossystem"/></title>
<%@ include file="../jsp/include/jquery.jsp"%>
<link  href="<%=basePath%>/style/login_muti.css" media="all" rel="stylesheet" type="text/css" />
<link  href="<%=basePath%>/style/global.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>utils/elementTable/jquery-latest-pack.js"></script>
<script type="text/javascript" src="<%=basePath%>js/dwr/engine.js"></script>
<script type="text/javascript" src="<%=basePath%>dwr/interface/SysUserService.js"></script>
<script language="javascript">
	$(function(){
		$('#instantMessagingMin').click(function(){
			$(this).hide();
			$('#instantMessagingMain').show();		
		});
		$('#closeInstantMessagingMain').click(function(){
			$('#instantMessagingMain').hide();
			$('#instantMessagingMin').show();		
		});
		browserVersion();
	});
	function browserVersion(){
		if($.browser.msie){
			if($.browser.version!='8.0'){
				alert('<s:property value="getText('theme.use.ie8')"/>');
			}
			
		}
		else if($.browser.mozilla){
			if($.browser.version!='8.0'){
				alert('<s:property value="getText('theme.use.fx7')"/>');
			}
		}
		else{
			alert('<s:property value="getText('theme.use.ieorfx')"/>');
		}
	}
</script>
<script language="javascript">

<s:set name="msg" value="actionErrors"/>
<s:iterator value="#msg">
alert('${msg}');
</s:iterator>
</script>	

<script language="javascript">
<s:set name="msg" value="actionMessages"/>
<s:iterator value="#msg">
alert('${msg}');
</s:iterator>
</script>
<script type="text/javascript">

function checkUser() {
	var msg = "";
	if($("#tenantName").val()==''){
			msg+='<s:property value="getText('theme.input.username')"/>'+'\n';
			alert(msg);
			$("#tenantName").focus();
	}else{
		if($("#loginname").val() ==''){
			msg+='<s:property value="getText('theme.input.username')"/>'+'\n';
			alert(msg);
			$("#loginname").focus();
		}else{
			if($("#password").val() ==''){
				msg+='<s:property value="getText('theme.input.password')"/>'+'\n';
				alert(msg);
				$("#password").focus();
			}else{
				if($("#checkcode").val() ==''){
					msg+='<s:property value="getText('theme.input.checkma')"/>'+'\n';
					alert(msg);
					$("#checkcode").focus();
				}
			}
		}
	}
	
	return msg;
}


function submitForm(){
	if(checkUser()==""){
		if(checkIsIpassUser()==0){
			userLogin();
			document.forms[0].action="<%=basePath%>coreextend/extend/loginSystem.action";
			document.forms[0].submit();
		}else{
			var isConn = CertControl.IsKeyConnect();
			if(isConn){
				userLogin();
				document.forms[0].action="<%=basePath%>coreextend/extend/loginSystem.action";
				document.forms[0].submit();
			}else{
				alert('<s:property value="getText('theme.insert.e')"/>');
			}
		}
	}
}
DWREngine.setAsync(false); //设置DWR方法为同步方法
function checkIsIpassUser(){
	var name = $("#loginname").val();
	var result = "";
	SysUserService.getSysUserIpassInfoByLoginname(name, callBackFunction);
	function callBackFunction(returnData){
		if(returnData == 1){
			result = 1;
		}else{
			result = 0;
		}
	}
	return result;
}
</script>
<script language="javascript" type="text/javascript">
	$(document).ready(function(){ 
		if(""==$("#tenantName").val()){ 
			$("#tenantName").focus();
		}else{
			if(""==$("#loginname").val()){ 
				$("#loginname").focus();
			}else{
				$("#checkcode").focus();
			}
		}
	});
	document.onkeydown = function(evt){
		var evt = window.event?window.event:evt;
		if(evt.keyCode==13)
		{
			submitForm();
		}
	}
</script>
</head>

<body>
<object ID="CertControl"
			CLASSID="CLSID:D05EB9EB-0463-4CF2-80C6-F69D03476DDA" width="0"
			height="0"
			codebase="<%=basePath %>theme/RuiBo.CAB#version=1,0,0,1">
</object>
<div class="wrapper">

<script><!--

function gaia_onLoginSubmit() {

  
  if (window.gaiacb_onLoginSubmit) {
    return gaiacb_onLoginSubmit();
  } else {
    return true;
  }

}


function gaia_setFocus() {
  var f = null;
  if (document.getElementById) { 
    f = document.getElementById("gaia_loginform");
  } else if (window.gaia_loginform) { 
    f = window.gaia_loginform;
  } 
  if (f) {
    if (f.Email && (f.Email.value == null || f.Email.value == "")) {
      f.Email.focus();
    } else if (f.Passwd) {
      f.Passwd.focus();
    } 
  }
}
--></script>

<style>
	.browerHref{
		color:red;font-weight:bold;font-size:14px;
	}
	.browerHref:hover{
		TEXT-DECORATION:underline;color:red;
	}
</style>

<form action="<%=basePath%>coreextend/extend/loginSystem.action" method="post" id="loginForm" name="loginForm">
<div class="loginBox">
<p class="udunjiance" id="msgTxt" style="color:#f60;"><s:text name="theme.logining"></s:text></p>
<!--<s:text name="login.loginsystem"/>-->
<!--<s:text name="login.username"/>-->
<input type="text" id="tenantName" name="tenantName" class="unitnameinput" style="height:20px;line-height:20px;width:200px;font-sie:14px;padding-left:2px;" />
<input type="text" id="loginname" name="sysUserVo.loginname" class="usernameinput" style="height:20px;line-height:20px;width:200px;font-sie:14px;padding-left:2px;" /> 
<!--<s:text name="login.password"/>-->
<input type="password" id="password" name="sysUserVo.password" value="" class="userpasswordinput" style="height:20px;line-height:20px;width:200px;font-sie:14px;padding-left:2px;" /> 
<!--<s:text name="login.checkcode"/>-->
<div class="yanzhenginput"><input type="text" name="checkcode"  id="checkcode" style="border: 1px solid #64A380;height:20px;line-height:20px;width:60px;font-sie:14px;padding-left:5px;" /><span id="checkCodeImgValue" style="padding:2px 20px;background:#ccc;font-family:verdana,arial;font-size:16px;letter-spacing:3px;font-style:italic;font-weight:bold;margin-left:5px;"></span><!-- <img name="" src="RandImg" value="" width="60px" height="21px" style="margin-left:5px;vertical-align:middle;" alt="" /> --></div>
<div class="baocunmima"><input style="font-size:12px;" type="checkbox" name="rememberPwd" id="rememberPwdId"/><s:text name="login.savepassword" /></div>

<input class="denglubtn" name="signIn" value="" type="button" onclick="submitForm();" style="font-size:13px;" />
<input class="chongzhibtn" value="" type="reset" style="font-size:13px;" />

<input type="hidden" name="asts" id="asts" value="" />
<input type="hidden" id="uKeySn" name="sysUserVo.usbKeySn"/>
<input type="hidden" id="uKeyCertSn" name="sysUserVo.usbKeyCertSn"/>
</form>
</div>
<div class="footer" style="">
<div><s:text name="theme.recom.browse"></s:text>：<a href="http://windows.microsoft.com/zh-CN/internet-explorer/products/ie-8/ie8-how-to" class="browerHref">IE8</a>、<a href="http://firefox.com.cn/" class="browerHref">FireFox7</a><s:text name="theme.upper"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="theme.recom.fenbian"></s:text></div>
<span><!--<img style="vertical-align:middle;border:1px solid #fff;margin-right:10px;" src="<%=basePath%>img/medxview.gif"/>-->版权所有：西安瑞铂软件科技有限公司</span><span>Tel：029-88440668</span><span></span>
</div>
</div>

<script type="text/javascript">
function getCookie(name) {
   var search = name + "="
   if(document.cookie.length > 0) {
      offset = document.cookie.indexOf(search)
      if(offset != -1) {
         offset += search.length
         end = document.cookie.indexOf(";", offset)
         if(end == -1) end = document.cookie.length
         return unescape(document.cookie.substring(offset, end))
      }
      else return ""
   }
}

function delCookie(name)
{
var exp = new Date();
exp.setTime(exp.getTime() - 1);
var cval = getCookie(name);
document.cookie = name + "=" + cval + "; expires="+ exp.toGMTString();
}

function setCookie(name,value,expiredays)
{
var exdate;
if(typeof(expiredays)=="undefined") {
	exdate = new Date(2099,12,31);
} else {
	exdate=new Date();
	exdate.setDate(exdate.getDate() + expiredays);
}
document.cookie = name+ "=" +escape(value)+ ";expires="+exdate.toGMTString();
}
var coolieNameForTenantName = "tenantName";
var cookieNameForName = "loginName";
var cookieNameForPWD = "password";
var cookieValueForTenantName = getCookie(coolieNameForTenantName);
var cookieValueForName = getCookie(cookieNameForName);
var cookieValueForPWD = getCookie(cookieNameForPWD);
if(cookieValueForName != null && cookieValueForName != "") {
	$("#tenantName").val(cookieValueForTenantName);
	$("#loginname").val(cookieValueForName);
	$("#password").val(cookieValueForPWD);
	$("input[name=rememberPwd]").attr({"checked":"true"});
}

function userLogin() {
    var tenantName = $("#tenantName").val();
	var loginName = $("#loginname").val();
	var password = $("#password").val();
	var cheRem = window.document.loginForm.rememberPwd.checked;
	
	if(($("input[name=rememberPwd]:checked").eq(0).attr("checked"))||($("input[name=rememberPwd]:checked").eq(0).attr("checked")=="checked")) {
		setCookie(coolieNameForTenantName, tenantName);
		setCookie(cookieNameForName, loginName);
		setCookie(cookieNameForPWD, password);
	} else {
		setCookie(coolieNameForTenantName);
		delCookie(cookieNameForName);
		delCookie(cookieNameForPWD);
	}
}



</script>
<!-- start -->
<script type="text/javascript" language="JavaScript">
	var check;
	function eLogin() {
	window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.logining')"/>';},2000);
		try{
			//测试e盾驱动和安全控件安装是否正确
			var test = CertControl.TestControl();
			if (!test) {
				window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.right.e')"/>';},2000);
				return false;
			}
			//测试设备是否连接电脑
			var isConn = CertControl.IsKeyConnect();
			if (isConn) {
				window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.checked.e')"/>'},2000);
				//设备初始化
				var initRes = CertControl.InitKey();
				window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.init.e')"/>';},2000);
				if (!initRes) {
					window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.inite.fail')"/>';},2000);
					return false;
				}else{
					window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.inite.success')"/>';},2000);
				}
				//读取设备序列号
				var uksn = CertControl.GetDeviceSN();
				window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.read.e')"/>';},2000);
				if (uksn == "") {
					window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.reade.fail')"/>';},2000);
					return false;
				}else{
					window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.reade.success')"/>';},2000);
				}
				//读取签名证书
				var cert = CertControl.GetSignCert();
				window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.get.e')"/>';},2000);
				if (cert == "") {
					window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.gete.fail')"/>';},2000);
					return false;
				}else{
					window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.gete.success')"/>';},2000);
				}
				//访问签名服务器
				var result = CertControl.OnlineVerifySign(cert, uksn);
				window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.visit.e')"/>';},2000);
				//释放控件资源
				CertControl.KeyDisconnect(); 
				if(result == 0){
					window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.net.check')"/>';},2000);
					return false;
				}else{
					SysUserService.getSysUserIpassInfoByUksn(uksn, callBackFunction);
					function callBackFunction(returnData){
						if(returnData == '0'){
							document.getElementById("loginname").focus();
							window.clearInterval(check);
							window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.e.unbind')"/>';},2000);
							return;
						}else{
							window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.e.checked')"/>';},2000);
							document.getElementById("loginname").value=returnData;
							
							var cookieNameForName = "loginName";
							var cookieNameForPWD = "password";
							var cookieValueForName = getCookie(cookieNameForName);
							var cookieValueForPWD = getCookie(cookieNameForPWD);
							if(cookieValueForName != null && cookieValueForName != "") {
								document.getElementById("checkcode").focus();
							}else{
								
							}
							window.clearInterval(check);
							
							return;
						}
					}
				}
			} else {
				//window.setTimeout(function(){document.all.msgTxt.innerHTML="请您正确插入e盾！";},2000);
				return false;
			}
		}catch(e){
			window.setTimeout(function(){document.all.msgTxt.innerHTML='<s:property value="getText('theme.e.safeplugin')"/>';},2000);
		}
	}
	 window.onload = function(){
       	//check = window.setInterval("eLogin()",1000);//设置该页面每秒钟执行一次checkTimeout()方法。
       	$.ajax({
				url:'<%=basePath%>coreextend/extend/RandImg',
				type:'post',
				dataType:'text',
				error: function(){
					alert('<s:property value="getText('theme.net.fail')"/>');
				},
				success: function(text){
					$("#checkCodeImgValue").html(text);
				}
		});
       	
     }
</script>
<!-- end -->
</body>
























