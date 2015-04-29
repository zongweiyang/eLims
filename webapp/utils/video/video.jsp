<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
<div>支持wmv</div>
<OBJECT id="WMP" height="600" width="800" classid="clsid:6BF52A52-394A-11D3-B153-00C04F79FAA6" VIEWASTEXT>
<PARAM NAME="URL" VALUE="<%=basePath %>utils/video/Video_2012-05-08_165750.wmv">
<PARAM NAME="rate" VALUE="1">
<PARAM NAME="balance" VALUE="0">
<PARAM NAME="currentPosition" VALUE="0">
<PARAM NAME="defaultFrame" VALUE="">
<PARAM NAME="playCount" VALUE="1" >
<PARAM NAME="autoStart" VALUE="-1">
<PARAM NAME="currentMarker" VALUE="0">
<PARAM NAME="invokeURLs" VALUE="-1">
<PARAM NAME="baseURL" VALUE="">
<PARAM NAME="volume" VALUE="50">
<PARAM NAME="mute" VALUE="0">
<PARAM NAME="uiMode" VALUE="full">
<PARAM NAME="stretchToFit" VALUE="0">
<PARAM NAME="windowlessVideo" VALUE="0">
<PARAM NAME="enabled" VALUE="-1">
<PARAM NAME="enableContextMenu" VALUE="-1">
<PARAM NAME="fullScreen" VALUE="0">
<PARAM NAME="SAMIStyle" VALUE="">
<PARAM NAME="SAMILang" VALUE="">
<PARAM NAME="SAMIFilename" VALUE="">
<PARAM NAME="captioningID" VALUE="">
<PARAM NAME="enableErrorDialogs" VALUE="0">
<PARAM NAME="_cx" VALUE="9260">
<PARAM NAME="_cy" VALUE="7938">
</OBJECT>
  </body>
</html>
