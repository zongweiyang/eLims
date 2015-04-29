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
			<input type="hidden" name="labSciLearnVo.type" value="2" id="type" />
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
																			onclick="nextUri('science/labSciLearn/listLabSciLearn.action?labSciLearnVo.type=2');"
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
																<s:text name="lecture.name"/>：
															</label>
														</td>
														<td>
															${labSciLearnVo.name}
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="lecture.type"/>：
															</label>
														</td>
														<td>
															${labSciLearnVo.learnType}
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="lecture.time"/>：
															</label>
														</td>
														<td>
															${labSciLearnVo.startTime} &nbsp;&nbsp; 至  &nbsp;&nbsp; 
																   ${labSciLearnVo.endTime}
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="lecture.site"/>：
															</label>
														</td>
														<td>
															${labSciLearnVo.place}
														</td>
													</tr>
													
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="teacher"/>：
															</label>
														</td>
														<td>
															${labSciLearnVo.speaker}
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="own.item"/>：
															</label>
														</td>
														<td>
															${labSciLearnVo.labSciScienceName}
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="host.office"/>：
															</label>
														</td>
														<td>
															<textarea readonly="readonly" rows="3" cols="36" name="labSciLearnVo.labOrgName" id="labOrgName"
																value="${labSciLearnVo.labOrgName}" size="40"
																valType="required,strLength" max="11"
																strLength-msg="主办科室不能超过64位" msg="主办科室不能为空"  onclick="getOrg();return false;" readonly="true"
																>${labSciLearnVo.labOrgName}</textarea>
																<input type="hidden" name="labSciLearnVo.labOrgId" id="labOrgId"
																value="${labSciLearnVo.labOrgId}"/>
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="attend.people"/>：
															</label>
														</td>
														<td>
															<textarea readonly="readonly" rows="3" cols="36" name="labSciLearnVo.participant" onclick="getUser();return false;" readonly="true"
																id="participant" value="${labSciLearnVo.participant}" size="40"
																valType="required,strLength" max="128" msg="参加人员不能为空" strLength-msg="参加人员长度不能超过128位">${labSciLearnVo.participant}</textarea>
														</td>
													</tr>
												<tr>
													<td class="r" width="150">
															<label>
																<s:text name="lecture.intr"/>：
															</label>
														</td>
														<td>
															<textarea readonly="readonly" rows="3" cols="36" name="labSciLearnVo.summary"
																valType="strLength" max="512"
																strLength-msg="讲座简介长度不能超过512位">${labSciLearnVo.summary}</textarea>
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="teacher.intr"/>：
															</label>
														</td>
														<td>
															<textarea readonly="readonly" rows="3" cols="36" name="labSciLearnVo.speakerIntroduced"
																valType="strLength" max="512"
																strLength-msg="主讲人简介长度不能超过512位">${labSciLearnVo.speakerIntroduced}</textarea>
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="lab.remark"/>：
															</label>
														</td>
														<td>
															<textarea readonly="readonly" rows="3" cols="36" name="labSciLearnVo.remark"
																valType="strLength" max="512"
																strLength-msg="备注长度不能超过512位">${labSciLearnVo.remark}</textarea>
														</td>
														<td class="r">
															<label>
																<s:text name="upload.attachment"/>：
															</label>
														</td>
														<td colspan="">
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
