<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript" src="jquery-latest-pack.js"></script>
	<script type="text/javascript" src="thickbox-compressed.js"></script>
	<link rel="stylesheet" href="thickbox.css" type="text/css" media="screen" />
  </head>
 
  <body>
  	<p>说明：通常txtListContentIds为隐藏域，也是要保持到数据库中的id字符串；txtListContentStrs为显示给调用者看的值；这两个值可以根据自己vo中的属性改变；
  	</p>
  	<p>Name:<textarea id="txtListContentStrs"></textarea></p>
  	<p>
  	Id:<textarea id="txtListContentIds"></textarea><a href="<%=basePath %>jsp/chooseUser/sysUserFrameX.jsp?strs=txtListContentStrs&ids=txtListContentIds&jg=&gw=&claceValuesBeforeTB_=savedValues&TB_iframe=true&height=540&width=700&modal=true" title="这是一个iframe" class="thickbox"><img src="<%=basePath %>img/lims_user_icon.gif" /></a>
  	</p>
  	
  </body>
</html>
