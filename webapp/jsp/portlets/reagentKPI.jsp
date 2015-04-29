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
		<td width="6%" rowspan="4"><s:text name="the.week"/> </td>
		<td width="44%"><s:text name="added.reagent"/>：${reaConKPIVo.addReaInWeek}<s:text name="jian"/></td>
		<td width="6%" rowspan="4"><s:text name="the.month"/></td>
		<td width="44%"><s:text name="added.reagent"/>：${reaConKPIVo.addReaInMonth}<s:text name="jian"/></td>
	</tr>
	<tr>
		<td><s:text name="used.reagent"/>：${reaConKPIVo.conHasBeenUsedInWeek} <s:text name="jian"/></td>
		<td><s:text name="used.reagent"/>：${reaConKPIVo.conHasBeenUsedInMonth} <s:text name="jian"/></td>
	</tr>
	<tr>
		<td ><s:text name="added.consumes"/>：${reaConKPIVo.addReaInWeek}<s:text name="jian"/></td>
		<td ><s:text name="added.consumes"/>：${reaConKPIVo.addReaInMonth}<s:text name="jian"/></td>
	</tr>
	<tr>
		<td><s:text name="used.consumes"/>：${reaConKPIVo.conHasBeenUsedInWeek} <s:text name="jian"/></td>
		<td><s:text name="used.consumes"/>：${reaConKPIVo.conHasBeenUsedInMonth} <s:text name="jian"/></td>
	</tr>
</table>
</body>
</html>