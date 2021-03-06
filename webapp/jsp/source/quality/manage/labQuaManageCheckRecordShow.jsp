<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
			function checkUnit(obj){
				var thisVal = $(obj).val();
				var name=$(obj).val();;
				var oo=name.split('|');
				$('#OrgId').val(oo[0]);
				$('#OrgName').val(oo[1]);
			}
			function checkLab(obj){
				var thisVal = $(obj).val();
				var name=$(obj).val();;
				var oo=name.split('|');
				$('#labId').val(oo[0]);
				$('#labName').val(oo[1]);
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaCusVisitForm" id="form">
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
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
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
											    <td class="r" width="150">
													<label>
														<s:text name="commtunit"/>：
													</label>
												</td>
												<td class="l" width="300">
													${labQuaManageCheckVo.orgName }
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="auditplanname"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.quaManageCheckPlanName}
												</td>
											</tr>
											<tr>
												<td class="r" width="100">
													<label>
														<s:text name="auditsite"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.address}
												</td>
											    <td class="r" width="150">
													<label>
														<s:text name="acctlescte"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.trackPeople}
												</td>
											</tr>
											<tr>
												<td class="r" width="100">
													<label>
																<s:text name="acctptdate"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.recTime}
												</td>
												<td class="r" width="150">
													<label>
																<s:text name="acceptreason"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.foundation}
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
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="grouppeople"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.groupMember}
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													<s:text name="pizhunren"/>：
														</label>
												</td>
												<td>
													${labQuaManageCheckVo.checkPeople}
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="pizhuNtime"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.checkTime}
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													<s:text name="penshenconte"/>：
														</label>
												</td>
												<td colspan="3">
													 <textarea readonly="readonly" rows="2" cols="40" name="labQuaManageCheckVo.record" id="record" >${labQuaManageCheckVo.record}</textarea>
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
											    <td class="r" width="150">
													<label>
														<s:text name="coderepeople"/>：
													</label>
												</td>
												<td class="l" width="300">
													${labQuaManageCheckVo.reportPeople}
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="codedates"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.reportTime}
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													<s:text name="finderroreancol"/>：
														</label>
												</td>
												<td colspan="3">
													 <textarea readonly="readonly" rows="3" cols="40" name="labQuaManageCheckVo.problem" id="problem" >${labQuaManageCheckVo.problem}</textarea>
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													实施纠正/改进的措施：
														</label>
												</td>
												<td colspan="3">
													 <textarea readonly="readonly" rows="3" cols="40" name="labQuaManageCheckVo.measure" id="measure" >${labQuaManageCheckVo.measure}</textarea>
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													<s:text name="jiuzhengcheck"/>：
														</label>
												</td>
												<td colspan="3">
													 <textarea readonly="readonly" rows="3" cols="40" name="labQuaManageCheckVo.measureCheck" id="measureCheck" >${labQuaManageCheckVo.measureCheck}</textarea>
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
