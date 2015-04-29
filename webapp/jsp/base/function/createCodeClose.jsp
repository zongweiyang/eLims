<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<meta http-equiv="Pragma" contect="no-cache" />

		<%@ include file="/jsp/include/common.jsp"%>
		<style>
html,body {
	width: 100%;
	height: 100%;
	overflow: hidden;
}

.myworkingboxttable {
	margin: 0;
}

.myworkingbox {
	min-height: 310px;
}

.TabTableBOX01 b {
	min-height: 200px;
}

.myworkingboxttable td input {
	width: 200px
}

.myworkingboxttable .butt {
	height: 25px;
	width: 93px;
}
</style>
	</head>
	<script>
	var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	$(function(){
		  	api.close();
		  	W.refresh();
	})
</script>
	<body class="" id="mainid">
	</body>
</html>
