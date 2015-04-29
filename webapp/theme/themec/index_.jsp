<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>

<head>

<link rel="shortcut icon" href="favicon.ico"> 
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title><s:text name="login.labossystem"/></title>
<link  href="<%=basePath%>/style/login.css" media="all" rel="stylesheet" type="text/css" />
<link  href="<%=basePath%>/style/global.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>utils/elementTable/jquery-latest-pack.js"></script>
<script type="text/javascript" src="<%=basePath%>utils/dwr/engine.js"></script>
>
<script type="text/javascript" src="<%=basePath%>dwr/interface/SysUserService.js"></script>
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
				alert('<s:property value="getText('theme.insert.e')"/>'+'\n');
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
		if(""==$("#loginname").val()){ 
			$("#loginname").focus();
		}else{
			$("#checkcode").focus();
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
<table align="center" border="0" cellpadding="0" cellspacing="0" width="95%">
  <tbody><tr valign="top">
  <td width="1%">

  </td>
  <td valign="top" width="99%">
  <table cellpadding="2" width="100%">
  <tbody><tr valign="bottom">
  <td>
  <div align="right">&nbsp;
  </div>
  </td>
  </tr>
  <tr>
  <td nowrap="nowrap">

  </td>
  </tr>
  </tbody></table>
  </td>
  </tr>
</tbody></table>
<table border="0" cellpadding="1" cellspacing="0" width="260" style="margin:0 auto;margin-top:120px;">
  <tbody><tr>
  <td id="gaia_loginbox_td" align="center" valign="top">
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
-->
</script>



<form action="<%=basePath%>coreextend/extend/loginSystem.action" method="post" id="loginForm" name="loginForm">
<div id="gaia_loginbox" style="margin-top:150px;">
<table class="form-noindent" border="0" cellpadding="5" cellspacing="3" width="100%">
  <tbody><tr>
  <td id="jinshuyin" valign="top" nowrap="nowrap">
  <div class="loginBox">
  <table id="gaia_table" align="center" border="0" cellpadding="1" cellspacing="0">
  <tbody><tr>
<td class="smallfont" colspan="2" align="center">
  <span style="font-size:13px;"><s:text name="login.loginsystem"/></span> 
    <h2> </h2></td>
</tr>
<tr>
  <td colspan="2" align="center"><p id="msgTxt" style="color:#F00;"><s:text name="theme.logining"></s:text></p></td>
</tr>


<tr>
  <td nowrap="nowrap">
  <div align="right">
  <span class="gaia le lbl" style="font-size:13px;"><s:text name="login.username"/></span></div></td>
  <td>
  <input type="text" id="loginname" name="labUserVo.loginname" style="border: 1px solid #000;height:20px;line-height:20px;width:128px;font-sie:12px;padding-left:5px;" class="gaia le val" >  </td>
</tr>


<tr>
  <td align="right" nowrap="nowrap">
  <span class="gaia le lbl" style="font-size:13px;"><s:text name="login.password"/></span></td>
  <td>
  <input id="password" name="labUserVo.password" value="" style="border: 1px solid #000;height:20px;line-height:20px;width:128px;font-sie:12px;padding-left:5px;" class="gaia le val" type="password">  </td>
</tr>
<tr>
  <td align="right" nowrap="nowrap"><span class="gaia le lbl"  style="font-size:13px;"><s:text name="login.checkcode"/></span></td>
  <td><input type="text" name="checkcode"  id="checkcode" style="border: 1px solid #000;height:20px;line-height:20px;width:60px;font-sie:12px;padding-left:5px;" class="gaia le val"/><img name="" src="RandImg" width="60px" height="21px" style="margin-left:5px;vertical-align:middle;" alt="" /></td>
</tr>
<tr>
  <td>  </td>
  <td align="left" style="font-size:12px;"> 
  <input style="font-size:12px;" type="checkbox" name="rememberPwd" id="rememberPwdId"/><s:text name="login.savepassword" />
   </td>
</tr>
<tr>
  <td>  </td>
  <td align="left">
  <input class="gaia le button" name="signIn" value="<s:text name="login.login" />" type="button" onclick="submitForm();" style="font-size:13px;" />
  <input class="loginoutbtn" value="<s:text name="login.reset" />" type="reset" style="font-size:13px;" /></td>
</tr>

  </tbody></table>
  </div>
  </td>
  </tr>
</tbody></table>
</div>
<input name="asts" id="asts" value="" type="hidden">
<input type="hidden" id="uKeySn" name="labUserVo.usbKeySn"/>
<input type="hidden" id="uKeyCertSn" name="labUserVo.usbKeyCertSn"/>
</form>

  </td>

  </tr>
</tbody></table>
<div class="footer" style="">
<span><!--<img style="vertical-align:middle;border:1px solid #fff;margin-right:10px;" src="<%=basePath%>img/medxview.gif"/>-->版权所有：西安瑞铂软件科技有限公司</span><span>Tel：029-88440668</span><span></span>
</div>
</div>
<br><br>

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

var cookieNameForName = "loginName";
var cookieNameForPWD = "password";
var cookieValueForName = getCookie(cookieNameForName);
var cookieValueForPWD = getCookie(cookieNameForPWD);
if(cookieValueForName != null && cookieValueForName != "") {
	window.document.getElementById("loginname").value = cookieValueForName;
	window.document.getElementById("password").value = cookieValueForPWD;
	window.document.loginForm.rememberPwd.checked = true;
}

function userLogin() {
	var loginName = window.document.getElementById("loginname").value;
	var password = window.document.getElementById("password").value;
	var cheRem = window.document.loginForm.rememberPwd.checked;
	if(cheRem) {
		setCookie(cookieNameForName, loginName);
		setCookie(cookieNameForPWD, password);
	} else {
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
	}
	  window.onload = function(){
	  	alert();
       	check = window.setInterval("eLogin()",1000);//设置该页面每秒钟执行一次checkTimeout()方法。
     }
</script>
<!-- end -->


</body>

