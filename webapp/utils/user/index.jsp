<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.6.1.min.js"></script>
    <script>var basePath = '<%=basePath%>';</script>
    <script>var surveyType='ALL';</script>
	<script type="text/javascript" src="<%=basePath%>js/lhgcore.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/lhgdialog.min.js"></script>
    <%
String isShowTryOutReport=(String)session.getAttribute("isShowTryOutReport");
if("true".equals(isShowTryOutReport)){
%>
    <script type="text/javascript" src="<%=basePath%>js/survey/userSurvey.js"></script>
    <%
}
    %>
    <style>
    	body{
    		margin:0px;padding:0px;
    	}
    </style>
  </head>
  
  <body>
  </body>
</html>
