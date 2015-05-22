<%@page import="org.apache.struts.Globals"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	String url = request.getParameter("url");
	Locale locale = (Locale)request.getSession().getAttribute(Globals.LOCALE_KEY);
	String dataLoadingCN = "数据加载中，请稍后....";
	String dataLoadingUS = "Data loading, please wait ....";
	String dataLoading = dataLoadingCN;
	if(locale!=null){
		String localeStr = locale.getCountry();
		if(localeStr.equals("US")){
			dataLoading = dataLoadingUS;
		}
	}
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
				<span id="spnMsg"><font size="1"><%=dataLoading %></font></span>
			</div>
		</div>
	</body>
	</script>