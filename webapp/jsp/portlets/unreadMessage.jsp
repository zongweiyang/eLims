<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
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
.ui-widget-content a {
	color: #3399CC;
}

a:HOVER {
	color: red;
}

.myworkingboxttable {
	width: 100%; *
	width: 97%;
	margin: 0;
}
</style>
	</head>
	<body>
		<table class="myworkingboxttable_pl" cellspacing="0" cellpadding="0">
			<tr>
				<th class="c" width="30">
					<img src="<%=basePath%>img/icon_drag.gif" />
				</th>
				<th class="c" width="75">
					<s:text name="msg.short.sender"/>
				</th>
				<th class="c" width="90">
					<s:text name="theme.date"/>
				</th>
				<th class="c">
					<s:text name="msg.subject"/>
				</th>
			</tr>
			<s:if test="unReadMessageList!=null">
				<s:set name="alllist" value="unReadMessageList" />
				<s:iterator value="#alllist" status="st">
					<tr>
						<td class="c">
							${st.index+1 }
						</td>
						<td class="c">
							${senderName}
						</td>
						<td class="c">
							${createTime}
						</td>
						<td>
							<a
								href="<%=basePath%>/message/messageMain/updateLabMsg4Readed.action?labMsgDetailVo.id=${id}&labMsgDetailVo.mainMsgID=${mainMsgID}">
								${subject} </a>
						</td>
					</tr>
				</s:iterator>
			</s:if>
		</table>
	</body>
</html>