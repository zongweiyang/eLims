<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
<%@ include file="../jsp/include/common.jsp"%>
<link rel="icon" href="favicon.ico" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:text name="login.labossystem"/></title>

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
</head>
<frameset rows="104,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="<%=basePath%>coreextend/extend/topframe.action?labUserVo.roleId=${SessionContainer.roleId }" name="topFrame" scrolling="No" noresize="noresize" id="TopFrame"/>
  <frameset id="bottomid" cols="190,*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=basePath%>coreextend/extend/leftframe.action?labUserVo.id=${SessionContainer.userId }" name="leftFrame" scrolling="No" noresize="noresize" id="LeftFrame"/>
    <frame src="<%=basePath+request.getParameter("url")%>" name="workarea" scrolling="auto" id="MianFrame"/>
  </frameset>
</frameset>

</html>