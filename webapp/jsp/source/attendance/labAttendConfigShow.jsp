<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
* {
	font-size: 9pt;
	line-height: 20px
}

.title {
	font-size: 16px;
	color: #0000FF;
	font-weight: bold
}

.con {
	margin-left: 15px;
}

.STYLE1 {
	color: #FF0000
}

.STYLE2 {
	color: #0000FF
}

.STYLE3 {
	color: #009900
}
</style>



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
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName}：
								<span><s:text name="look.check"/></span>
							</h2>
						</div>
						<form action="" method="post" name="mouldForm">
							<div class="TabTable">
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
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span>${funName}<s:text name="information"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td width="100">
												<label>
													<s:text name="titleed"/>：
												</label>
											</td>
											<td width="300">
												${labAttendConfigVo.title}
											</td>
											<td width="100">
												<label>
													<s:text name="weekday"/>：
												</label>
											</td>
											<td id="workDay">
												<s:if test="${fn:indexOf(labAttendConfigVo.workDay,'1')>=0}">
													<s:text name="monday"/>
												</s:if>
												<s:if test="${fn:indexOf(labAttendConfigVo.workDay,'2')>=0}">
													<s:text name="quesday"/>
												</s:if>
												<s:if test="${fn:indexOf(labAttendConfigVo.workDay,'3')>=0}">
													<s:text name="thirday"/>
												</s:if>
												<s:if test="${fn:indexOf(labAttendConfigVo.workDay,'4')>=0}">
													<s:text name="fourday"/>
												</s:if>
												<s:if test="${fn:indexOf(labAttendConfigVo.workDay,'5')>=0}">
													<s:text name="firday"/>
												</s:if>
												<s:if test="${fn:indexOf(labAttendConfigVo.workDay,'6')>=0}">
													<s:text name="satarday"/>
												</s:if>
												<s:if test="${fn:indexOf(labAttendConfigVo.workDay,'7')>=0}">
													<s:text name="sunday"/>
												</s:if>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="wordday.time"/>：
												</label>
											</td>
											<td>
												${labAttendConfigVo.startTime}
											</td>
											<td>
												<label>
													<s:text name="after.time"/>：
												</label>
											</td>
											<td>
												${labAttendConfigVo.endTime}
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="begindatedo"/>：
												</label>
											</td>
											<td>
												${labAttendConfigVo.startDay}
											</td>
											<td>
												<label>
													<s:text name="enddodate"/>：
												</label>
											</td>
											<td>
												${labAttendConfigVo.endDay}
											</td>

										</tr>
										<tr>
											<td>
												<label>
													<s:text name="lab.remark"/>：
												</label>
											</td>
											<td colspan="3">
												<textarea disabled="disabled" name="labAttendConfigVo.remark" cols="60" rows="3" id="remark">${labAttendConfigVo.remark}</textarea>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</form>
					</div>
				<td class="MRimg"></td>
			</tr>
			<tr>
				<td class="FLimg"></td>
				<td class="FMimg"></td>
				<td class="FRimg"></td>
			</tr>
		</table>
		<div class="clear"></div>
	</body>
</html>
