<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%@ include file="/jsp/include/common.jsp"%>
<html>
<head>
<title><s:text name="action.to.title"></s:text></title>
</head>
    <body>
		<div>
			<div id="tbMsg" align="center" style="margin-top: 200px; display: block;">
				<img src="${basePath}img/loading_16x16.gif" align="absmiddle" />
				<span id="spnMsg"><font size="1"><s:text name="data.loading"></s:text></font></span>
			</div>
		</div>
		<form action="${nextAction}" name="form" method="post">
			<input type="hidden" name="fromPage" value="next.jsp"/>
		    <s:iterator value="#request.paramList" id="id">
		    	<input type="hidden" name="${id[0]}" value="${id[1]}"/>
		    </s:iterator> 
	    </form>
</body>
</html>
<script>
	document.forms[0].submit();
</script>