<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<link href="<%=basePath %>css/global.css" media="all" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/common.css" media="all" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="systemerror">
<s:if test="${errorForm.gotoUrl==''}">
<script   language='javascript'>
setTimeout("{window.parent.location.href='<%=basePath%>'}",3000);
</script>
<p><h2>很抱歉!</h2><s:property value="errorForm.displayErrorName"/></p>
<p><a href="#" ><span id="timer">3秒</span>秒后返回登录页面<s:property value="errorForm.unCatch" /></a></p>
<SCRIPT language=JavaScript>
<!--
var maxtime=4;

maxtime = 4;

function CountDown(){
if(maxtime>=0){

seconds = maxtime-1;
msg =seconds+"秒";
document.getElementById("timer").innerHTML = msg;
--maxtime;

} 

}
timer = setInterval("CountDown()",1000);
//-->
                  </SCRIPT>
</s:if>
<s:if test="${errorForm.gotoUrl!=''}">
<script   language='javascript'>
setTimeout("{location.href='<%=basePath %><s:property value='errorForm.gotoUrl'/>'}",6000);
</script>
<p><h2>很抱歉!</h2><s:property value="errorForm.displayErrorName"/></p>
<p><a href="<%=basePath %><s:property value='errorForm.gotoUrl'/>"> <span id="timer">5秒</span>后返回之前访问的页面<s:property value="errorForm.unCatch" /></a></p>


<SCRIPT language=JavaScript>
<!--
var maxtime=6;

maxtime = 6;

function CountDown(){
if(maxtime>=0){

seconds = maxtime-1;
msg =seconds+"秒";
document.getElementById("timer").innerHTML = msg;
--maxtime;

} 

}
timer = setInterval("CountDown()",1000);
//-->
                  </SCRIPT>
</s:if>
</div>
</body>

</html>
