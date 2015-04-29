<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
			<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<script language="javascript" type="text/javascript"> 

</script>
	<body class="" id="mainid">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
								<div class="myworkingbox">
									<div class="myworkingboxttitle">
										<h2>
											<s:text name="person.center"></s:text>：
											<span><s:text name="update.now"/></span>
										</h2>
									</div>
									<form action="" method="post" name="labUserForm" id="form">
										<s:token></s:token>
										<input type="hidden" value="${labUserVo.id}" name="labUserVo.id" />
										<div class="TabTable">
											<div class="TabTableNAV">
												<ul>
													<li id="li01" class="">
														<a href="javascript:;" onclick="nextUri('user/labUser/preUpdateLabUser4Center.action?labUserVo.id=${labUserVo.id}');"><span><s:text name="base.info"/></span></a>
													</li>
													<li id="li02" class="">
														<a href="javascript:;" onclick="nextUri('user/labUser/showLabUser4Center1.action?labUserVo.id=${labUserVo.id}');"><span><span><s:text name="auth.info"/></span> </a>
													</li>
													<li id="li03" class="currenttab">
														<a href="javascript:;"><span><span><s:text name="attend.info"/></span> </a>
													</li>
													<li id="li04" class="">
														<a href="javascript:;" onclick="nextUri('user/labUser/showLabUser4Center3.action?labUserVo.id=${labUserVo.id}');"><span><span><s:text name="other.info"/></span> </a>
													</li>
												</ul>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="Formtable">
													<div class="Formtabletitle">
														<span><span><s:text name="auth.info"/></span>
													</div>
													<table class="FormtableCon_sform">
														
													</table>
												</div>
											</div>
										</div>
									</form>
								</div>
							</td>
						</tr>
					</table>
				<td class="MRimg"></td>
			</tr>
		</table>
	</body>
	<script></script>
</html>
