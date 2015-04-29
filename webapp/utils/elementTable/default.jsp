<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>

<head>
<title>Salesforce - Professional Edition</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="jquery-latest-pack.js"></script>
<script type="text/javascript" src="thickbox-compressed.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" media="screen" />

</head>
<body class="st1" style="overflow-y:auto;">

<p><a class="thickbox" title="add a caption to title attribute / or leave blank" href="images/single.jpg"><img alt="Image 2" src="images/single_t.jpg"/></a></p>
              <a href="<%=basePath %>admin/sysUser/frameSysUserX.action?placeValuesBeforeTB_=savedValues&TB_iframe=true&height=600&width=800&modal=true" title="这是一个iframe" class="thickbox">iFrame Modal被打开的演示</a>
              <a href="elementtable.html?keepThis=true&TB_iframe=true&height=600&width=800" title="这是一个html" class="thickbox">例子2</a>

</body>
</html>



















































