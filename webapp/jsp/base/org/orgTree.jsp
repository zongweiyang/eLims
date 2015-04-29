<%@ page language="java" import="java.util.*,cn.labsoft.labos.utils.tree.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<HTML>
  <head>
     <%@ include file="../../../jsp/include/common.jsp"%>
	<link rel="stylesheet" href="${basePath}utils/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${basePath}utils/ztree/jquery.ztree.core-3.5.js"></script>
  </head>
  <body>
	 <ul id="zTree" turl="${basePath}/org/labOrg/treeLabOrg.action" curl="${basePath}/jsp/include/progress_bar.jsp?url=/org/labOrg/listLabOrg.action?labOrgVo.parentId"></ul> 
  </body>
</html>