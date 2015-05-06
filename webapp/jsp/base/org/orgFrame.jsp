<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/include/common.jsp"%>
   <title></title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
   <meta name="Org list" content="PSPad" />
</head>

<frameset cols="220,*" frameborder="1" border="1" framespacing="2">
  <frame src="<%=basePath%>org/labOrg/preTreeLabOrg.action" name="docTreeLeft" id="docTreeLeft" scrolling="auto">
  <frame src="<%=basePath%>org/labOrg/listLabOrg.action?labOrgVo.parentId=${labOrgVo.parentId}" name="docTreeMain" id="docTreeMain" scrolling="auto">  
</frameset>

<noframes><body>
</body></noframes>
</html>
