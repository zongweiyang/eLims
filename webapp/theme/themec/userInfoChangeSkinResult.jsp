<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<html>
		<head>
			<%@ include file="../jsp/include/common.jsp"%>
			<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
			<title>SaaS For LIMS - LabSoft</title>


			<style>
.TabTable {
	padding-top: 0;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
		</head>
		<script language=javascript> 
			
</script>
		<body>



			<div class="myworkingbox">
				<div class="myworkingboxttitle">
					<h2>
						<s:text name="theme.changeskin.info"></s:text>：
						<span></span>
					</h2>
				</div>
				<form action="update1.action" method="post" name="stUserform">
					<input type="hidden" name="vo.id" id="id" value="${vo.id}" />
					<input type="hidden" name="vo.stOrgId" id="stOrgId"
						value="${vo.stOrgId}" />
					<div class="TabTable">
						<div class="buttonbar">
							<div class="button01">
								<div class="buttonl"></div>
								<div class="buttonm">
									<a href="javascript:void();" onclick="parent.location.href='<%=basePath %>coreextend/extend/loginSystem.action?labUserVo.loginname=${session.SessionContainer.loginname }&labUserVo.password=${session.SessionContainer.password }';return false;"><s:text name="theme.back"></s:text></a>
								</div>
								<div class="buttonr"></div>
							</div>
						</div>
						<div class="TabTableBOX01 b" id="Tab01">
							<div class="tabtablebox">
								<div class="tabtableboxtitle">
									<span><s:text name="theme.change.skin"></s:text></span>
								</div>
								<br>
									<s:text name="theme.changeskin.success"></s:text>							
								<br>
				</form>
			</div>
			

			</div>
		</body>
		<script language="javascript" type="text/javascript">

</script>
	</html>