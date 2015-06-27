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

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><s:text name="login.labossystem"/></title>
 <link href="<%=basePath%>style/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<%=basePath%>style/signin.css" rel="stylesheet">
<script type="text/javascript" language="JavaScript" src="<%=basePath%>js/jquery/jquery-1.11.0.min.js"></script>
<script>
<s:set name="msg" value="actionErrors"/>
<s:iterator value="#msg">
alert('${msg}');
</s:iterator>
function checkUser() {
	var msg = "";
	if($("#loginname").val() ==''){
		msg+='<s:property value="getText('input.username')"/>';
		alert(msg);
		$("#loginname").focus();
	}else{
		if($("#password").val() ==''){
			msg+='<s:property value="getText('input.password')"/>';
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
	$('form').attr('action','<%=basePath%>admin/coreextend/extend/loginSystem.action');
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

 <div class="container">
      <h3 class="form-signin-heading"><s:text name="newlogintitle"></s:text></h3>
      <form class="form-signin" role="form" action="<%=basePath%>admin/coreextend/extend/loginSystem.action" method="post" id="loginForm" name="loginForm">
        <input type="hidden" id="token" name="labUserVo.token" value="" class="" /> 
        <input type="text" id="loginname" name="labUserVo.loginName" value="${labUserVo.loginName }" class="form-control" placeholder='<s:property value="getText('newloginusername')"/>' required autofocus>
        <input type="password" id="password" name="labUserVo.pwd" value="${labUserVo.pwd}" class="form-control" placeholder='<s:property value="getText('newloginpassword')"/>' required>
        <label class="checkbox">
          <input type="checkbox" value="remember-me"  id="rememberPwdId"><s:text name="login.savepassword" />
        </label>
        <div class="row">
        <div class="col-md-6">
       	 		<button class="btn btn-lg btn-primary btn-block" name="signIn" value="" type="button" onclick="submitForm();" ><s:text name="newloginloginbtn"></s:text></button>
        </div>
  		<div class="col-md-6">
       			<button class="btn btn-lg btn-primary btn-block" type="reset"><s:text name="newloginresetbtn"></s:text></button>
  		</div>
        </div>
      </form>
     <%--  <!--   <div class="row" style="padding:;">
        <div class="col-md-6" style="text-align: right;"> -->
					<a href="<%=basePath%>langAdmin.action?request_locale=zh_CN" onclick="window.top.location.reload();" >CN</a>    
      <!--   </div>
  		<div class="col-md-6" style="text-align: left;"> -->
					<a href="<%=basePath%>langAdmin.action?request_locale=en_US" onclick="window.top.location.reload();">EN</a>     
  		<!-- </div>
        </div> --> --%>
        <p class="errorinfo" id="errorInfo">${messageInfo}</p>
    </div> <!-- /container -->
<!-- 
<div class="footer">
	<span>版权所有：<a target="_blank" href="http://www.labsoft.cn">瑞铂软件</a></span>
	<span>Tel：029-88440668</span>
</div>
 -->
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
