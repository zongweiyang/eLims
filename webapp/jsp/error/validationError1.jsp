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
<script type='text/javascript'>
　　var i=5;
　　function getTime(){
　　document.getElementById('num').innerHTML="<font color='red'>"+i+"</font>";
　　i-=1;
　　var x=setTimeout('getTime()',1000)
　　if(i<=0){
　　clearTimeout(x);
　　}
　　}
　　window.onload=getTime;
</script>
　<body>
     <s:fielderror cssStyle="color: red"></s:fielderror><br />
    <div class="foot">【<a href="javascript:history.back();">点击这里返回<s:text name="page.before"/></a>】</div>
</body>