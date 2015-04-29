<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/jstl/c.tld"%>
<%@ taglib uri="/WEB-INF/tld/jstl/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tld/jstl/fmt.tld" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>

		<title><s:text name="page.info"/></title>
		<style>
.myworkingboxttable {
	width: 100%; *
	width: 97%;
	margin: 0;
}
</style>
	</head>
	<body>
		<table class="myworkingboxttable_pl" cellspacing="0" cellpadding="0"
			style="_WIDTH: 96%; _margin: 0; _overflow: hidden;">
			<tr>
				<th class="c" width="30">
					<img src="<%=basePath%>img/icon_drag.gif" />
				</th>
				<th class="c" width="60">
					<s:text name="typed"/>
				</th>
				<th class="c">
					<s:text name="theme.alarm.msg"/>
				</th>
			</tr>
			<s:if test="alarmMessageList!=null">
				<s:set name="alllist" value="alarmMessageList" />
				<s:iterator value="#alllist" status="st">
					<tr>
						<td class="c">
							${index }
						</td>
						<td class="c">
							<font color="#FFCC00">${type }</font>
						</td>
						<td>
							<font color="#FFCC00">${fn:substring(message,0,50)}</font>
						</td>
					</tr>
				</s:iterator>
			</s:if>
		</table>
	</body>
</html>