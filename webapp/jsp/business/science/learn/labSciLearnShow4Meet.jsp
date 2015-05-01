<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
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
	<body class="" id="mainid">
		<form action="" method="post" name="studentForm">
			<input type="hidden" name="labSciLearnVo.type" value="1" id="type" />
			<input type="hidden" name="labSciLearnVo.id" value="${labSciLearnVo.id}" id="id" />
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
												${funName}：
												<span><s:text name="look.check"/></span>
											</h2>
										</div>
										<div class="TabTable">
											<div class="FUNCIONBARNEW">
												<table>
													<tr>
														<td class="blockTd"
															style="padding: 6px 10px; vertical-align: center;">
															<table cellspacing="0" cellpadding="0" border="0">
																<tr>
																	<td>
																		<l:a uri="science/labSciLearn/listLabScience.action"
																			onclick="nextUri('science/labSciLearn/listLabSciLearn.action?labSciLearnVo.type=1');"
																			value="msg.back" />
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
														<td class="r" width="150">
															<label>
																<s:text name="conference.name"/>：
															</label>
														</td>
														<td>
															${labSciLearnVo.name}
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="conference.type"/>：
															</label>
														</td>
														<td>
															${labSciLearnVo.learnType}
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="conference.time"/>：
															</label>
														</td>
														<td>
															${labSciLearnVo.startTime}
														</td>
														<td  class="r" width="150" >
															<label>
																<s:text name="conference.site"/>：
														</label>
														</td>
														<td>
															${labSciLearnVo.place}
														</td>
													</tr>
													<tr>
														<td  class="r" width="150" >
															<label>
																<s:text name="own.item"/>：
															</label>
														</td>
														<td colspan="3">
															${labSciLearnVo.labSciScienceName}
														</td>
													</tr>
													<tr>
														<td  class="r" width="150" >
															<label>
																<s:text name="attend.office"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40" name="labSciLearnVo.labOrgName" id="labOrgName"
																value="${labSciLearnVo.labOrgName}" size="40"
																valType="required,strLength" max="11"
																strLength-msg="参加科室不能超过64位" msg="参加科室不能为空"
																readonly="true">${labSciLearnVo.labOrgName}</textarea>
																<input type="hidden" name="labSciLearnVo.labOrgId" id="labOrgId"
																value="${labSciLearnVo.labOrgId}"/>
														</td>
														<td  class="r" width="150" >
															<label>
																<s:text name="attend.people"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40" name="labSciLearnVo.participant" readonly="true"
																id="participant"
																valType="required,strLength" max="128" msg="参加人员不能为空" strLength-msg="参加人员长度不能超过128位">${labSciLearnVo.participant}</textarea>
														</td>
													</tr>
													<tr>
														<td  class="r" width="150" >
															<label>
																<s:text name="conference.jianjie"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40" name="labSciLearnVo.summary"
																valType="strLength" max="512"
																strLength-msg="会议简介长度不能超过512位">${labSciLearnVo.summary}</textarea>
														</td>
														<td  class="r" width="150" >
															<label>
																<s:text name="lab.remark"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40" name="labSciLearnVo.remark"
																valType="strLength" max="512"
																strLength-msg="备注长度不能超过512位">${labSciLearnVo.remark}</textarea>
														</td>
													</tr>
													<tr>
														<td class="r">
															<label>
																<s:text name="upload.attachment"/>：
															</label>
														</td>
														<td colspan="3">
																<s:if test="${fn:length(uplodeList)>0}">
															<s:iterator status="st2" value="#request.uplodeList" id="">
																<span> <a href="${path }" id="fileId">${name
																		} </a></span>
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
														<div id="upfiles"></div>
														</td>
													</tr>
												</table>
											</div>
										</div>
										</form>
									</div>
								<td class="MRimg">

								</td>
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