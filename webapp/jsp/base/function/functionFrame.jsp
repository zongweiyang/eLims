<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="Org list" content="PSPad" />
	</head>
	<frameset cols="200,*" frameborder="1" border="1" framespacing="2">
		<frame id="funTree" src="<%=basePath%>function/labFunction/treeLabFunction.action" name="docTreeLeft" scrolling="auto" frameborder="1">
		<frame  src="<%=basePath%>function/labFunction/listLabFunction.action?labFunctionVo.parentId=${labFunctionVo.parentId}" name="docTreeMain" id="docTreeMain" scrolling="auto">
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>