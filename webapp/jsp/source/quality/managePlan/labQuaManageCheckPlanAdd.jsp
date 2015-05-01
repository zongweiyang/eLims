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
																	<l:a uri="quality/managePlan/addLabQuaManageCheckPlan.action" onclick="goAction('addLabQuaManageCheckPlan.action');" value="lab.code.save"/>
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
												<span>管理评审计划表</span>
											</div>
											<table class="FormtableCon">
												   <tr>
												<td class="r" width="150">
													<label>
														<s:text name="commtunit"/>：
													</label>
												</td>
												<td>
													<s:select list="labOrgVoList" headerKey="" cssStyle="width:273px;" headerValue="--请选择--"
													name="labQuaManageCheckPlanVo.orgSearch" id="orgSearch" theme="simple"
													listKey="id+'|'+name" listValue="name" onchange="selectChange(this);"></s:select>
													<input size="40" type="hidden"  id="orgId" valType="required" msg="请选择单位" name="labQuaManageCheckPlanVo.orgId" value="${labQuaManageCheckPlanVo.orgId }"  />
													<input size="40" type="hidden"  id="orgName" name="labQuaManageCheckPlanVo.orgName"  value="${labQuaManageCheckPlanVo.orgName }" />
												</td>
												<td class="r" width="150">
													<label>
														计划名称：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.name" id="name" type="text" valType="required" msg="计划名称不能为空"
														value="${labQuaManageCheckPlanVo.name}" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														评审地点：
													</label>
												</td>
												<td>
													<input valType="required" msg="评审地点不能为空" size="40" name="labQuaManageCheckPlanVo.address" id="address"
														type="text" value="${labQuaManageCheckPlanVo.address}"/>
												</td>
											    <td class="r" width="150">
													<label>
														评审主持人：
													</label>
												</td>
												<td>
													<input valType="required" msg="评审主持人不能为空" size="40" name="labQuaManageCheckPlanVo.trackPeople" id="trackPeople" type="text"
														value="${labQuaManageCheckPlanVo.trackPeople}" />
												</td>
												
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														评审时间：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.recTime" id="recTime" type="text" valType="required" msg="评审时间不能为空"
														value="${labQuaManageCheckPlanVo.recTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
												<td class="r" width="150">
													<label>
														评审依据：
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
													组&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长：
														</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.groupLeader" id="groupLeader" type="text"
														value="${labQuaManageCheckPlanVo.groupLeader}" />
												</td>
												<td class="r" width="150">
													<label>
														组&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;员：
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
														计划编制日期：
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
														批&nbsp;&nbsp;准&nbsp;&nbsp;人：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaManageCheckPlanVo.checkPeople" id="checkPeople" type="text"
														value="${labQuaManageCheckPlanVo.checkPeople}" />
												</td>
												<td class="r" width="150">
													<label>
														批准日期：
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
													评审内容：
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
