<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<link href="<%=basePath %>css/global.css" media="all" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/common.css" media="all" rel="stylesheet" type="text/css" />
<style>
.errorblock {margin-top:100px;margin-left:100px;position:relative;width:450px;height:180px;background:url(../../img/login_error_img.png) no-repeat left top;}
.errorblock a {position:absolute;left:218px;top:120px;text-decoration:none;color:#000;font-size:14px;font-weight:bold;}
.errorblock a:hover {text-decoration:underline;color:#333;font-weight:bold;} 
</style>
<script   language='javascript'>
alert("您已经安全退出，请重新登录！");
window.parent.parent.location.href='<%=basePath%>coreextend/extend/login.action';
</script>
</head>
<body>

<div class="systemerror">
<s:if test="${errorForm.gotoUrl==''}">
<div class="errorblock">
</div>
</s:if>

</div>
</body>
</html>