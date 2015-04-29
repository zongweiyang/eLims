<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<!DOCTYPE HTML>
<html>
  <body>
  	<s:if test="${labReportVo.type=='1'}">
  		<jsp:include page="jspTempContent.jsp"></jsp:include>
  	</s:if>
  	<s:elseif test="${labReportVo.type=='2'}">
  		<jsp:include page="excelTempContent.jsp"></jsp:include>
  	</s:elseif>
  </body>
</html>
