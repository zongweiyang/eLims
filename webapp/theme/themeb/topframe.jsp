<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/jsp/include/common.jsp"  %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div id="Header">
	<table class="Headertoolsbar" cellspacing="0" cellpadding="0" border="0">
	  <tr>
	    <td class="left"><img src="<%=basePath%>${session.SessionContainer.logoUrl}" style="position:absolute;top:0;left:0;"/></td>
	    <td class="right">
	      <div class="headertool">
	        <ul class="topicon">
	       		<li>
	        		<a class="ti_loginout" href="<%=basePath%>coreextend/extend/logout.action" target="_top"></a>
	        	</li>
	        </ul>
	      </div>
	    </td>
	  </tr>
	</table>
</div>
</body>
</html>