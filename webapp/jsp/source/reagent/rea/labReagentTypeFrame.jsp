<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="Org list" content="PSPad" />
	</head>

	<frameset cols="200,*" frameborder="NO" border="0" framespacing="2">
		<frame src="<%=basePath%>reagent/labReagent/preLabReagentZtree.action"
			name="left" scrolling="auto">
		<frame
			src="<%=basePath%>reagent/labReagent/listLabReagent.action?labReagentVo.reagentTypeId=${labReagentVo.reagentTypeId}"
			name="treeMain" id="treeMain" scrolling="auto">
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>