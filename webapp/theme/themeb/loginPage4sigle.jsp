<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/jstl/fn.tld" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico"> 
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title><s:text name="login.labossystem"/></title>

<link  href="<%=basePath%>style/login_web.css" media="all" rel="stylesheet" type="text/css" />
<link  href="<%=basePath%>style/global_web.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="JavaScript" src="<%=basePath%>js/jquery/jquery-1.11.0.min.js"></script>
<script>
<s:set name="msg" value="actionErrors"/>
<s:iterator value="#msg">
alert('${msg}');
</s:iterator>
function checkUser() {
	var msg = "";
	if($("#loginname").val() ==''){
		msg+='请输入用户名!\n';
		alert(msg);
		$("#loginname").focus();
	}else{
		if($("#password").val() ==''){
			msg+='请输入密码!\n';
			alert(msg);
			$("#password").focus();
		}else{
		}
	}
	return msg;
}


function submitForm(){
	var msg = checkUser();
	if(msg.length>0){
		return false;
	}
	userLogin();
	$('form').attr('action','<%=basePath%>coreextend/extend/loginSystem.action');
	$('form').submit();
}
var cookieNameForName = "loginname";
var cookieNameForPWD = "password";
$(function(){
	var cookieValueForName = getCookie(cookieNameForName);
	var cookieValueForPWD = getCookie(cookieNameForPWD);
	if(cookieValueForName != null && cookieValueForName != "") {
		$("#loginname").val(cookieValueForName);
		$("#password").val(cookieValueForPWD);
		$("#rememberPwdId").attr('checked',true);
	}
	
	var flag1=$("#loginname").val();
	var flag2=$("#password").val();
	if(flag1==''){
		$('#loginname').focus();
	}else if(flag2==''){
		$('#password').focus();
	}
	$("#resetX").click(function(){
		$("#loginname").val("");
		$("#password").val("");
		$("#errorInfo").html("");
		$("#loginname").focus();
	})
})
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
<div class="wrapper">
<div class="loginbox">
<form action="<%=basePath%>coreextend/extend/loginSystem.action" method="post" id="loginForm" name="loginForm">
	<input type="hidden" id="token" name="labUserVo.token" value="" class="" /> 
	<input type="text" id="loginname" name="labUserVo.loginName" value="${labUserVo.loginName }" class="usernameinput" /> 
	<input type="password" id="password" name="labUserVo.pwd" value="${labUserVo.pwd}" class="userpasswordinput" /> 
	<div class="baocunmima" style="">
		<input type="checkbox" id="rememberPwdId"/>
		<s:text name="login.savepassword" />
	</div>
	<input class="denglubtn" name="signIn" value="" type="button" onclick="submitForm();"  />
	<input class="chongzhibtn" value="" id="resetX" type="buuton" />
	<p class="errorinfo" id="errorInfo">${messageInfo}</p>
</form>
<div class="footer">
	<span><s:text name="theme.copyright"></s:text>：<a target="_blank" href="http://www.labsoft.cn">瑞铂软件</a></span>
	<span>Tel：029-88440668</span>
</div>
</div>
</div>

<script>
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
	document.cookie = name + "=" + cval + ";expires="+ exp.toGMTString();
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
function userLogin() {
	var loginName = $('#loginname').val();
	var password =$('#password').val();
	var cheRem = $('#rememberPwdId').prop('checked');
	if(cheRem) {
		setCookie(cookieNameForName, loginName);
		setCookie(cookieNameForPWD, password);
	} else {
		delCookie(cookieNameForName);
		delCookie(cookieNameForPWD);
	}
}
</script>

</body>
