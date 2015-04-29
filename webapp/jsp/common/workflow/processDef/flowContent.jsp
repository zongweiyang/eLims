<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ include file="/jsp/include/common.jsp"%>
<html>
	<head>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

.TabTable .TabTableBOX01 {
	display: none;
	_height: 220px;
	min-height: 220px;
	width: 96.5%;
	background: #ECFCFF;
	border: 1px solid #499EB3;
	margin-left: 10px;
	margin-bottom: 10px;;
	padding: 10px 10px 0 10px
}

.TabTable .TabTableBOX01.b {
	display: block;
	_height: 220px;
	min-height: 220px;
	width: 96.5%;
	background: #ECFCFF;
	border: 1px solid #499EB3;
	margin-left: 10px;
	margin-bottom: 10px;;
	padding: 10px 10px 0 10px
}
html {
	_overflow-x: hidden;
}
</style>
	</head>
	<Script>
	function goToNextAction(url){
		$('form').attr('action','${basePath}'+url);
		$('form').submit();
	}
	
	function submitforform(){
		document.content.save();
		$('form').submit();
	}
</script>
	<body class="" id="mainid">
		<form method="post" name="form" action="listLabWfProcess.action">
			<input type="hidden" name="labWfProcessVo.id" value="${labWfProcessVo.id}" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												<s:text name="prc.manage"/>：
												<span><s:text name="modeling"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back" />
																</td>
																<td>
																	<l:a uri="workflow/process/preUpdateLabWfProcess4Content.action" img="img/filesave.gif" onclick="goToNextAction('workflow/process/preUpdateLabWfProcess4Content.action?labWfProcessVo.initStatus=1');return false;" value="reinit" />
																</td>
																<td>
																	<l:a uri="workflow/process/listLabWfProcess.action" img="img/filesave.gif" onclick="submitforform();return false;" value="lab.code.save" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
										<span style="background-color:#ccc;"><font color="red"><s:text name="use.ie8"/></font></span>	
											<div id="contentFrame">
												<iframe name="content" id="content"
													src="<%=basePath%>workflow/process/preUpdateLabWfProcess4Flow.action?labWfProcessVo.id=${labWfProcessVo.id}&labWfProcessVo.initStatus=${labWfProcessVo.initStatus}"
													onload="document.all.content.style.height=document.content.document.body.clientHeight"
													scrolling="no" frameborder="0" width="100%" height="500">
												</iframe>
											</div>
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
