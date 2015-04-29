<%@ page language="java" import="java.util.*,cn.labsoft.labos.utils.tree.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
  <head>
  	<%@ include file="/jsp/include/common.jsp"%>
	<link rel="stylesheet" href="${basePath}utils/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${basePath}utils/ztree/jquery.ztree.core-3.5.min.js"></script>
  </head>
  <body style="">
    <ul id="zTree" turl="${basePath}/klg/labItem/ajaxLabStandItemZtree.action" parent="treeMain" curl="${basePath}/jsp/include/progress_bar.jsp?url=/klg/labItem/listLabItem.action?labItemVo.type"></ul>
  </body>
</html>