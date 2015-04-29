<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
			function selectChange(obj){
       		var value=$(obj).val();
			var oo=value.split('|');
			$('#orgId').val(oo[0]);
			$('#orgName').val(oo[1]);
       }
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaManageCheckRecordForm" id="form">
		<input name="labQuaManageCheckVo.quaManageCheckPlanName" id="quaManageCheckPlanName" type="hidden" value="${labQuaManageCheckVo.quaManageCheckPlanName}" />
		<input name="labQuaManageCheckVo.quaManageCheckPlanId" id="quaManageCheckPlanId" type="hidden" value="${labQuaManageCheckVo.quaManageCheckPlanId}" />
		<input name="labQuaManageCheckVo.recTime" id="recTime" type="hidden" value="${labQuaManageCheckPlanVo.recTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
		<input name="labQuaManageCheckVo.groupLeader" id="groupLeader" type="hidden" value="${labQuaManageCheckPlanVo.groupLeader}" />
		<input name="labQuaManageCheckVo.groupMember" id="groupMember" type="hidden" value="${labQuaManageCheckPlanVo.groupMember}"/>
		 <input type="hidden" name="labQuaManageCheckVo.record" id="record" value="${labQuaManageCheckPlanVo.contents}" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="admin.add"/></span>
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
																	<l:a uri="back" value="msg.back"/>  
																</td>
																<td>
																	<l:a uri="quality/managePlan/addLabQuaManageCheckRecord.action" onclick="goAction('addLabQuaManageCheckRecord.action');" value="lab.code.save"/>
																</td>
																<!-- <td>
																	<l:a uri="quality/manage/addLabQuaManageCheckRecord.action" onclick="goAction('addLabQuaManageCheckRecord.action?labQuaManageCheckVo.auditResult=1');" value="save.commit"/>
																</td>-->
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="base.info"/></span>
											</div>
											<table class="FormtableCon">
											<tr>
											    <td class="r" width="200">
													<label>
														<s:text name="commtunit"/>：
													</label>
												</td>
												<td class="l" width="500">
													${labQuaManageCheckPlanVo.orgName }
													<input type="hidden"  id="orgId" name="labQuaManageCheckVo.orgId" value="${labQuaManageCheckPlanVo.orgId }"  />
													<input type="hidden"  id="orgName" name="labQuaManageCheckVo.orgName"  value="${labQuaManageCheckPlanVo.orgName }" />
												</td>
												<td class="r" width="150">
													<label>
														评审计划名称：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.name}
												</td>
											</tr>
											<tr>
												<td class="r" width="100">
													<label>
														评审地点：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.address}
													<input size="40" name="labQuaManageCheckVo.address" id="address" type="hidden" value="${labQuaManageCheckPlanVo.address}"/>
												</td>
											    <td class="r" width="150">
													<label>
														评审主持人：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.trackPeople}
													<input size="40"  name="labQuaManageCheckVo.trackPeople" id="trackPeople" type="hidden"
														value="${labQuaManageCheckPlanVo.trackPeople}" />
												</td>
											</tr>
											<tr>
												<td class="r" width="100">
													<label>
														评审日期：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.recTime}
													<input size="40" name="labQuaManageCheckVo.recTime" id="recTime" type="hidden" value="${labQuaManageCheckPlanVo.recTime}"  class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
												<td class="r" width="150">
													<label>
														评审依据：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.foundation}
												</td>
												
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													组&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长：
														</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.groupLeader}
												</td>
												<td class="r" width="150">
													<label>
														组&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;员：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.groupMember}
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													批&nbsp;&nbsp;准&nbsp;&nbsp;人：
														</label>
												</td>
												<td>
													${labQuaManageCheckVo.checkPeople}
												</td>
												<td class="r" width="150">
													<label>
														批准时间：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.checkTime}
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													评审内容：
														</label>
												</td>
												<td colspan="3">
													 <textarea readonly="readonly" rows="2" cols="40" name="labQuaManageCheckPlanVo.contents" id="contents" >${labQuaManageCheckPlanVo.contents}</textarea>
												</td>
											</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="record.info"/></span>
											</div>
											<table class="FormtableCon">
											<tr>
											    <td class="r" width="200">
													<label>
														编&nbsp;&nbsp;制&nbsp;&nbsp;人：
													</label>
												</td>
												<td class="l" width="500">
													<input size="40"  name="labQuaManageCheckVo.reportPeople" id="reportPeople" type="text"
														value="${labQuaManageCheckVo.reportPeople}" />
												</td>
												<td class="r" width="150">
													<label>
														编制日期：
													</label>
												</td>
												<td>
													<input size="40"  name="labQuaManageCheckVo.reportTime" id="reportTime" type="text"
														value="${labQuaManageCheckVo.reportTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
													<input size="40"  name="labQuaManageCheckVo.checkPeople" id="checkPeople" type="hidden"
														value="${labQuaManageCheckVo.checkPeople}" />
													<input size="40"  name="labQuaManageCheckVo.checkTime" id="checkTime" type="hidden"
														value="${labQuaManageCheckVo.checkTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
											<tr>
											    <td class="r" width="150">
												       <label>
													评审中发现的问题和结论：
														</label>
												</td>
												<td colspan="3">
													 <textarea rows="3" cols="40" name="labQuaManageCheckVo.problem" id="problem" >${labQuaManageCheckVo.problem}</textarea>
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													实施纠正/改进的措施：
														</label>
												</td>
												<td colspan="3">
													 <textarea rows="3" cols="40" name="labQuaManageCheckVo.measure" id="measure" >${labQuaManageCheckVo.measure}</textarea>
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													对纠正/改进措施的检查：
														</label>
												</td>
												<td colspan="3">
													 <textarea rows="3" cols="40" name="labQuaManageCheckVo.measureCheck" id="measureCheck" >${labQuaManageCheckVo.measureCheck}</textarea>
												</td>
											</tr>
											
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
