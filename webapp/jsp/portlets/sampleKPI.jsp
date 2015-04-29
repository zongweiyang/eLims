<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<html>
<head>
<title><s:text name="page.info"/></title>

</head>
<body> 
<table class="myworkingboxttable_pl" cellspacing="0" cellpadding="0" >
	<tr>
		<td width="6%" rowspan="4"><s:text name="the.week"/></td>
		<td width="44%"><s:text name="added.item"/>：${sampleVo.weekNew}<s:text name="ge"/></td>
		<td width="6%" rowspan="4"><s:text name="the.month"/></td>
		<td width="44%"><s:text name="added.item"/>：${sampleVo.monthNew}<s:text name="ge"/></td>
	</tr>
	<tr>
		<td><s:text name="finished.item"/>：${sampleVo.weekFinished}<s:text name="ge"/></td>
		<td><s:text name="finished.item"/>：${sampleVo.monthFinished}<s:text name="ge"/></td>
	</tr>
	<tr>
		<td><s:text name="needed.item"/>：${sampleVo.weekNeeded}<s:text name="ge"/></td>
		<td><s:text name="needed.item"/>：${sampleVo.monthNeeded}<s:text name="ge"/></td>
	</tr>
	<tr>
		<td><s:text name="charged.mount"/>：${sampleVo.weekCharge}<s:text name="theme.yuan"/></td>
		<td><s:text name="charged.mount"/>：${sampleVo.monthCharge}<s:text name="theme.yuan"/></td>
	</tr>
</table>
</body>
						</html>