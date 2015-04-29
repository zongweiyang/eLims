<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.labsoft.labos.framework.common.sesseionutils.SessionContainer"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/jstl/c.tld"%>
<%@ taglib uri="/WEB-INF/tld/jstl/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tld/jstl/fmt.tld" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../jsp/include/jquery.jsp"%>
		<link rel=stylesheet href="<%=basePath%>admin/skin/css/common.css" type="text/css">
		<link rel=stylesheet href="<%=basePath%>style/global.css" type="text/css">
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

.bold {
	font-weight: bold;
	font-size: 25px;
}
</style>
		<script type="text/javascript">		
	</script>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form theme="simple" action="" method="post" name="homeForm">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
											<s:text name="admin.home.page"/>
											</h2>
										</div>
										<div id="columns"></div>
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td class="MRimg"></td>
				</tr>
				<tr>
					<td class="FLimg"></td>
					<td class="FMimg"></td>
					<td class="FRimg"></td>
				</tr>
			</table>
		</form>
	</body>
</html>