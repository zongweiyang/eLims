<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	String url = request.getParameter("url");
	try{	
		if(url.indexOf("?")>-1){
			url = url.replace("^","&");
		}else{
			String beforeWH=url.substring(0,url.indexOf("^"));
			String other=url.substring(url.indexOf("^")+1,url.length()).replace("^","&");
			url = beforeWH+"?"+other.replace("^","&");
		}
	}catch(Exception e){
		url = url.replace("^","&");
	}
%>

<html>
	<head>
		<script type="text/javascript">
		function loadDataFDB(){
			window.location.href = '<%=basePath%><%=url%>';
		}
	</script>
	</head>
	<body onLoad="loadDataFDB();">
		<div>
			<div id="tbMsg" align="center"
				style="margin-top: 200px; display: block;">
				<img src="<%=basePath%>/img/loading_16x16.gif"
					align="absmiddle" />
				&nbsp;
				<span id="spnMsg"><font size="1"><s:text name="data.loading"/></font></span>
			</div>
		</div>
	</body>
	</script>