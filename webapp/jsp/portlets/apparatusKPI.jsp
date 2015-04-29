<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<html>
<head>
<title><s:text name="page.info"/></title>
<style>

</style>
</head>
<body> 
<table class="myworkingboxttable_pl" cellspacing="0" cellpadding="0" align="left" style="width:55%">
	<tr>
		<td><s:text name="zero.check"/></td>
	</tr>
	<tr>
		<td><s:text name="month.check"/></td>
	</tr>
	<tr>
		<td><s:text name="three.check"/></td>
	</tr>
</table>
<img src="<%=basePath %>img/hegelv.gif"/>
</body>
						</html>