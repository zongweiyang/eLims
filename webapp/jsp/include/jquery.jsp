<%@page import="org.apache.struts.Globals"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="cn.labsoft.labos.utils.DateUtils"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);
request.setAttribute("now",DateUtils.getCurrDateTime());
request.getSession().setAttribute("basePath", basePath);
Locale locale = (Locale)request.getSession().getAttribute(Globals.LOCALE_KEY);
if(locale==null)
	locale = request.getLocale();
String lang= locale.getCountry();
%>
<script type="text/javascript" src="${basePath}js/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${basePath}js/jquery/jquery-1.7.min.js"></script> 
<script type="text/javascript" src="${basePath}js/jquery/tool-1.0.0.js"></script>