<%@ page contentType="text/html;charset=UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>

<head>

<link rel="shortcut icon" href="favicon.ico"> 
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>LabOS-实验室运营系统</title>
<link  href="<%=basePath%>/style/global.css" media="all" rel="stylesheet" type="text/css" />
<link  href="<%=basePath%>/style/login.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>dwr/interface/SysUserService.js"></script>
<script type="text/javascript" src="<%=basePath%>utils/dwr/engine.js"></script>
>
<script type="text/javascript" language="JavaScript" src="<%=basePath%>js/CCITCertCtrl.js"></script>
<script type="text/javascript">
//function formSubmit() {
//	var username = document.getElementById('loginname').value;
//	var password = document.getElementById('password').value;
//	if(username ==''){
//		alert('请输入用户名');
//		return false;
//	}
//	if(password ==''){
//		alert('请输入密码');
//		return false;
//	}
//}
function checkUser() {
	var msg = "";
	if($("#loginname").val() ==''){
		msg+='请输入用户名!\n';
	}
	if($("#password").val() ==''){
		msg+='请输入密码!\n';
	}
	return msg;
}

function checkUKey(){
	var msg = "";
	if($("#uKeySn").val()=='' || $("#uKeySn").val()== null){
		msg+='获取UKey设备序号错误!\n';
	}
	if($("#uKeyCertSn").val()=='' || $("#uKeyCertSn").val()==null){
		msg+='获取UKey证书序号错误!\n';
	}
	return msg;
}

function checkUnits(){
	var msg= "";
	msg+=checkUser();
	if(msg.length > 0){
		alert(msg);
		return "error";
	}else{
		var isipass = getUserIpassInfo();
		if(isipass == 1){
			setInterval("initUKey();",1000);
			msg+=checkUKey();
			if(msg.length > 0){
				alert(msg);
				return "error";
			}else{
				return "ipass";
			}
		}else{
			return "normal";
		}
	}
}

function initUKey(){
	var connect = isUKConnect();
	if(connect==1){
		init(0); //现在没有去服务端验证参数为0
		getUKeyInfo();
	}else{
		$("#uKeySn").val(""); //重置设备序列号
		$("#uKeyCertSn").val(""); //重置证书序列号
	}
}

function getUKeyInfo(){
	$("#uKeySn").val(readUKSN()); //获取设备序列号
	$("#uKeyCertSn").val(getCertSN(getSignCert())); //获取证书序列号
}

//function intiUKeyInfo(){
//	var initTimer = setInterval("getUKeyInfo();",1000);
//	if($("#uKeySn").val()!='' && $("#uKeySn").val()!=null && $("#uKeyCertSn").val()!='' && $("#uKeyCertSn").val()!=null){
//		clearInterval(initTimer);
//	}
//}

function submitForm(){
	var result = checkUnits();
	if(result=="normal"){
		document.forms[0].action="<%=basePath%>coreextend/extend/loginSystem.action";
		document.forms[0].submit();
	}
	if(result=="ipass"){
		document.forms[0].action="<%=basePath%>ipass/identityAuthentication.action";
		document.forms[0].submit();
	}
}
DWREngine.setAsync(false); //设置DWR方法为同步方法
function getUserIpassInfo(){
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
		$("#loginname").focus();
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
<body class="loginbody">
<div class="Wrapper">
	<div id="Faux">
		<div class="loginbox">
			<form action="<%=basePath%>coreextend/extend/loginSystem.action" method="post" id="loginForm">
				<input class="usernameinput" type="text" id="loginname" name="labUserVo.loginname" value=""/>
				<input class="userpwinput" type="password" id="password" name="labUserVo.password" value="" />
				<input class="loginbtn" type="button" onclick="submitForm();"/>
				<input class="loginoutbtn" type="button" onclick="submitForm();"/>
			<input type="hidden" id="uKeySn" name="labUserVo.usbKeySn"/>
				<input type="hidden" id="uKeyCertSn" name="labUserVo.usbKeyCertSn"/>
			</form>
		</div>
	</div>
</div>
</body>
























