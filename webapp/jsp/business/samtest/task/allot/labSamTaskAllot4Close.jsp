<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
* {
	font-size: 9pt;
	line-height: 20px
}

.title {
	font-size: 16px;
	color: #0000FF;
	font-weight: bold
}

.con {
	margin-left: 15px;
}

.STYLE1 {
	color: #FF0000
}

.STYLE2 {
	color: #0000FF
}

.STYLE3 {
	color: #009900
}
</style>



		<style>
.TabTable {
	padding-top: 0;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

.Formtable {
	border: none;
}

.FormtableCon input {
	width: 200px;
}

.FormtableCon textarea {
	width: 600px;
}

.FormtableCon select {
	width: 200px;
}

.myworkingboxttable th {
	background: #F5F5F5;
	color: #000000;
	border-top: #C0C0C0 1px solid;
	border-left: #C0C0C0 1px solid;
	border-right: #C0C0C0 1px solid;
}
</style>
	</head>
	<script language=javascript>
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	    function closeMe(){
			api.close();
	    }
       	function submitx(){
       		closeMe();
       	}
     	$(function(){
     		api.close();
		  	W.refresh();
     	});
	</script>
	<body>
	</body>
</html>
