<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  	<base href="<%=basePath%>">
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="areport/areport.js"></script>
	<script>
		$(function() {
			$('body').areport();
		});
	</script>
  </head>
  
  <body>
    <input type="hidden" id="editType" value="${labReportVo.editType}">
  	<input type="hidden" id="reportId" value="${labReportVo.id}">
  	<div id="page-data" style="display:none;">
  		${labReportVo.file}
  	</div>
  </body>
</html>
