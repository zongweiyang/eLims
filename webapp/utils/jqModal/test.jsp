<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@ include file="../../jsp/include/common.jsp"%>
    <title>My JSP 'test.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <a href="javascript:void();" onclick="javascript:showID('http://www.baidu.com','400','100');">Click Me</a>
    
    <a href="javascript:void();" onclick="javascript:showID('http://www.baidu.com','800','450');">Click Me</a>
    
    <jsp:include page="jqModalSetsize.jsp" flush="false"></jsp:include>
  </body>
</html>
