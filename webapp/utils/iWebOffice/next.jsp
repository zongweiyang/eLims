<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<html>
  <head>
   <%@ include file="../jsp/include/common.jsp"%>
    <title>My JSP 'next.jsp' starting page</title>
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
  	<span style="color: red;">保存成功,等待跳转</span>
   	<script language="javascript" type="text/javascript">
       window.parent.document.getElementById('fileId').value='<%=request.getParameter("mRecordID")%>';
       window.parent.submitvalue('quality/quaControl/addQuaControlMain.action');
       //var api = frameElement.api, W = api.opener, D = W.document; //api.opener 为载加lhgdialog.min.js文件的页面的window对象
	   //W.window.location.href= W.window.location+'&quaControlMainVo.fileId=<%=request.getParameter("mRecordID")%>';
	</script>
  </body>
</html>
