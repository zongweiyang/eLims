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
		<input name="labQuaManageCheckPlanVo.id" type="hidden" value="${labQuaManageCheckPlanVo.id }" />
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
																	<l:a uri="quality/managePlan/updateLabQuaManageCheckPlan.action" onclick="goAction('updateLabQuaManageCheckPlan.action');" value="lab.code.save"/>
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
												<span><s:text name="pingshenplantal"/></span>
											</div>
											<table class="FormtableCon">
												   <tr>
												<td class="r" width="150">
													<label>
														<s:text name="commtunit"/>：
													</label>
												</td>
												<td>
													<s:select list="labOrgVoList" headerKey="" headerValue="" theme="simple" cssStyle="width:150px;"   value="'${labQuaManageCheckPlanVo.orgId}|${labQuaManageCheckPlanVo.orgName }'" listKey="id+'|'+name" listValue="name" onchange="selectChange(this);"></s:select>
													<input type="hidden"  id="orgId" valType="required" msg='<s:property value="getText('slecltdepart')"/>' name="labQuaManageCheckPlanVo.orgId" value="${labQuaManageCheckPlanVo.orgId }"  />
													<input type="hidden"  id="orgName" name="labQuaManageCheckPlanVo.orgName"  value="${labQuaManageCheckPlanVo.orgName }" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="plannamed"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.name" id="name" type="text" valType="required" msg='<s:property value="getText('plannamenotempt')"/>'
														value="${labQuaManageCheckPlanVo.name}"/>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="auditsite"/>：
													</label>
												</td>
												<td>
													<input valType="required" msg='<s:property value="getText('auditaddnotem')"/>' size="40" name="labQuaManageCheckPlanVo.address" id="address"
														type="text" value="${labQuaManageCheckPlanVo.address}"/>
												</td>
											    <td class="r" width="150">
													<label>
														<s:text name="acctlescte"/>：
													</label>
												</td>
												<td>
													<input valType="required" msg='<s:property value="getText('auditzhuchinotem')"/>' size="40" name="labQuaManageCheckPlanVo.trackPeople" id="trackPeople" type="text"
														value="${labQuaManageCheckPlanVo.trackPeople}" />
												</td>
												
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="audittime"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.recTime" id="recTime" type="text" valType="required" msg='<s:property value="getText('audittimenotemep')"/>'
														value="${labQuaManageCheckPlanVo.recTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
												<td class="r" width="150">
													<label>
																<s:text name="acceptreason"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.foundation" id="foundation" type="text"
														value="${labQuaManageCheckPlanVo.foundation}" />
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													<s:text name="groupmaster"/>：
														</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.groupLeader" id="groupLeader" type="text"
														value="${labQuaManageCheckPlanVo.groupLeader}" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="grouppeople"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.groupMember" id="groupMember" type="text"
														value="${labQuaManageCheckPlanVo.groupMember}"/>
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
													<label>
																<s:text name="plancodepeo"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.planUser" id="planUser" type="text"
														value="${labQuaManageCheckPlanVo.planUser}" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="planwirtedate"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.planTime" id="planTime" type="text"
														value="${labQuaManageCheckPlanVo.planTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
													<label>
														<s:text name="pizhunren"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.checkPeople" id="checkPeople" type="text"
														value="${labQuaManageCheckPlanVo.checkPeople}" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="acceptdate"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.checkTime" id="checkTime" type="text"
														value="${labQuaManageCheckPlanVo.checkTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													<s:text name="penshenconte"/>：
														</label>
												</td>
												<td colspan="3">
													 <textarea rows="3" cols="40" name="labQuaManageCheckPlanVo.contents" id="contents" >${labQuaManageCheckPlanVo.contents}</textarea>
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
