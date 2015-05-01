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
		<form action="" method="post" name="labQuaManageCheckPlanForm" id="form">
		<input name="labQuaManageCheckVo.id" type="hidden" value="${labQuaManageCheckVo.id }" />
		<input name="labQuaManageCheckVo.reportNo" type="hidden" value="${labQuaManageCheckVo.reportNo }" />
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
												<span><s:text name="lab.code.modify"/></span>
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
																	<l:a uri="quality/manage/updateLabQuaManageCheckRecord.action" onclick="goAction('updateLabQuaManageCheckRecord.action?labQuaManageCheckVo.auditResult=0');" value="lab.code.save"/>
																</td>
																<td>
																	<l:a uri="quality/manage/updateLabQuaManageCheckRecord.action" onclick="goAction('updateLabQuaManageCheckRecord.action?labQuaManageCheckVo.auditResult=1');" value="save.commit"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="manageauditlist"/>：</span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>   
											</div>
											<table class="FormtableCon">
												   <tr>
												<td class="r" width="150">
													<label>
														<s:text name="commtunit"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.orgName }
													<input size="40" type="hidden"  id="orgId" name="labQuaManageCheckVo.orgId" value="${labQuaManageCheckVo.orgId }"  />
													<input size="40" type="hidden"  id="orgName" name="labQuaManageCheckVo.orgName"  value="${labQuaManageCheckVo.orgName }" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="auditplanded"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.quaManageCheckPlanName}
													<input size="40" name="labQuaManageCheckVo.quaManageCheckPlanName" id="quaManageCheckPlanName" type="hidden"
														value="${labQuaManageCheckVo.quaManageCheckPlanName}" />
														<input size="40" name="labQuaManageCheckVo.quaManageCheckPlanId" id="quaManageCheckPlanId" type="hidden"
														value="${labQuaManageCheckVo.quaManageCheckPlanId}" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="audittime"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.recTime}
													<input size="40" name="labQuaManageCheckVo.recTime" id="recTime" type="hidden"
														value="${labQuaManageCheckVo.recTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											    <td class="r" width="150">
													<label>
														<s:text name="auditsite"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.address}
													<input size="40" name="labQuaManageCheckVo.address" id="address"
														type="hidden" value="${labQuaManageCheckVo.address}"/>
												</td>
												
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													<s:text name="groupmaster"/>：
														</label>
												</td>
												<td>
													${labQuaManageCheckVo.groupLeader}
													<input size="40" name="labQuaManageCheckVo.groupLeader" id="groupLeader" type="hidden"
														value="${labQuaManageCheckVo.groupLeader}" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="grouppeople"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.groupMember}
													<input size="40" name="labQuaManageCheckVo.groupMember" id="groupMember" type="hidden"
														value="${labQuaManageCheckVo.groupMember}"/>
												</td>
											</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span>管理评审<s:text name="record.info"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>   
											</div>
											<table class="FormtableCon">
											<tr>
											    <td class="r" width="150">
												       <label>
													<s:text name="manageaccpreco"/>：
														</label>
												</td>
												<td colspan="3">
													 <textarea rows="3" cols="40" name="labQuaManageCheckVo.record" id="record" >${labQuaManageCheckVo.record}</textarea>
												</td>
											</tr>
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
											<tr>
											    <td class="r" width="150">
													<label>
														<s:text name="coderepeople"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckVo.recordPeople" id="recordPeople" type="text"
														value="${labQuaManageCheckVo.recordPeople}" />
												</td>
												<td class="r" width="300">
													<label>
														<s:text name="codedates"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckVo.recordTime" id="recordTime" type="text"
														value="${labQuaManageCheckVo.recordTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
													<label>
														<s:text name="pizhunren"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckVo.checkPeople" id="checkPeople" type="text"
														value="${labQuaManageCheckVo.checkPeople}" />
												</td>
												<td class="r" width="300">
													<label>
														<s:text name="acceptdate"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckVo.checkTime" id="checkTime" type="text"
														value="${labQuaManageCheckVo.checkTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
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
