<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
			function checkLab(obj){
				var thisVal = $(obj).val();
				var name=$(obj).val();;
				var oo=name.split('|');
				$('#orgId').val(oo[0]);
				$('#orgName').val(oo[1]);
			}
			function checkNum(obj){
				var val=$(obj).val();
				if(isNaN(val)){
					alert('<s:property value="getText('pleseinpunumber')"/>');
					$(obj).val(0);
				}
			}
			function showTable(obj){
				var test=$('#isTest').val();
				if(test=='Y'){
					$(obj).html('[&nbsp;<font color="blue"><s:text name="genetask"/></font>&nbsp;]');
					$('#isTest').val('N');
				}else{
					$(obj).html('[&nbsp;<font color="blue">取消任务</font>&nbsp;]');
					$('#isTest').val('Y');
				}
				$(obj).parent().next().toggle();
			}
		</script>
	</head>

	<body class="" id="mainid">
		<form action="" method="post" name="labQuaProficiencyPlanForm" id="form">
			<input type="hidden" value="${labQuaProficiencyPlanVo.isTest}" name="labQuaProficiencyPlanVo.isTest" id="isTest"/>
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
																	<l:a uri="back" value="msg.back" />
																</td>
																<td>
																	<l:a uri="quality/proficiencyPlan/addLabQuaProficiencyPlan.action"
																		onclick="goAction('addLabQuaProficiencyPlan.action?labQuaProficiencyPlanVo.auditResult=1');"
																		value="lab.code.save" />
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
												<span><s:text name="plan.info"/>：</span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="theme.depart"/>：
														</label>
													</td>
													<td>
														<s:select headerKey="" headerValue="请选择" list="labOrgList"
															theme="simple" listKey="id+'|'+name" listValue="name"
															cssStyle="width:273px;" onchange="checkLab(this);"></s:select>
														<input type="hidden" id="orgId"
															valType="required" msg="请选择单位"
															name="labQuaProficiencyPlanVo.unitOrgId"
															value="${labQuaProficiencyPlanVo.unitOrgId }" />
														<input type="hidden" id="orgName"
															name="labQuaProficiencyPlanVo.unitOrgName"
															value="${labQuaProficiencyPlanVo.unitOrgName }" />
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="plannamed"/>：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaProficiencyPlanVo.name"
															id="name" valType="required" msg='<s:property value="getText('plannamenotempt')"/>' type="text"
															value="${labQuaProficiencyPlanVo.name}" />
													</td>
												</tr>
												<tr>
													<td class="r">
														<label>
															<s:text name="duibiyanzhengtype"/>：
														</label>
													</td>
													<td>
														<input size="40"
															name="labQuaProficiencyPlanVo.proficiencyType"
															id="proficiencyType" valType="required" msg='<s:property value="getText('sytoeduibinitem')"/>'
															type="text"
															value="${labQuaProficiencyPlanVo.proficiencyType}" />
													</td>
													<td class="r">
														<label>
															<s:text name="jishuashishidate"/>：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaProficiencyPlanVo.planTime"
															id="planTime" type="text"
															value="${labQuaProficiencyPlanVo.planTime}"
															valType="required" msg='<s:property value="getText('jishuashnotempyt')"/>' class="Wdate"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
													</td>
												</tr>
												<tr>
													<td class="r">
														<label>
															<s:text name="fundbudget"/>：
														</label>
													</td>
													<td colspan="3">
														<input size="40" name="labQuaProficiencyPlanVo.payMoney"
															id="payMoney" type="text"
															value="${labQuaProficiencyPlanVo.payMoney}"
															valType="required" msg='<s:property value="getText('fundfeeenotem')"/>'
															onblur="checkNum(this);" />
													</td>
												</tr>
												<tr>
													<td class="r">
														<label>
															<s:text name="planman"/>：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaProficiencyPlanVo.contPeople"
															id="contPeople" type="text"
															value="${labQuaProficiencyPlanVo.contPeople}" />
													</td>
													<td class="r">

														<label>
															<s:text name="plandatedd"/>：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaProficiencyPlanVo.contDate"
															id="contDate" type="text"
															value="${labQuaProficiencyPlanVo.contDate}" class="Wdate"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
													</td>
												</tr>
												<tr>
													<td class="r">
														<label>
															<s:text name="pizhunren"/>：
														</label>
													</td>
													<td>
														<input size="40"
															name="labQuaProficiencyPlanVo.auditPeople"
															id="auditPeople" type="text"
															value="${labQuaProficiencyPlanVo.auditPeople}" />
													</td>
													<td class="r">

														<label>
															<s:text name="acceptdate"/>：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaProficiencyPlanVo.auditDate"
															id="auditDate" type="text"
															value="${labQuaProficiencyPlanVo.auditDate}"
															class="Wdate"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
													</td>
												</tr>
												<tr>
													<td class="r">
														<label>
															<s:text name="duibiandyaoq"/>：
														</label>
													</td>
													<td colspan="3">
														<textarea rows="3" cols="40"
															name="labQuaProficiencyPlanVo.contents" id="contents"
															valType="required" msg='<s:property value="getText('sytoeduibinitem')"/>'>${labQuaProficiencyPlanVo.contents}</textarea>
													</td>
												</tr>
												<tr>
													<td class="r">
														<label>
															<s:text name="lab.remark"/>：
														</label>
													</td>
													<td colspan="3">
														<textarea name="labQuaProficiencyPlanVo.remark" cols="40"
															rows="3" id="remark"></textarea>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="checkatsk"/></span>
												<label style="padding-left: 10px;" onclick="showTable(this)">
													[&nbsp;
													<font color="blue"><s:text name="genetask"/></font>&nbsp;]
												</label>
											</div>
											<div style="display: none;">
												<table class="FormtableCon">
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="lecture.item"/>：
															</label>
														</td>
														<td>
															<input size="40"
																name="labQuaProficiencyPlanVo.sampRegisterTitle"
																id="sampRegisterTitle" type="text"
																value="${labQuaProficiencyPlanVo.sampRegisterTitle}" />
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="task.no"/>：
															</label>
														</td>
														<td>
															<input size="40" name="labQuaProficiencyPlanVo.sampRegisterNo"
																value="${labQuaProficiencyPlanVo.sampRegisterNo}" id="sampRegisterNo" readonly="readonly" style="background-color: #eee"/>
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="call.people"/>：
															</label>
														</td>
														<td>
															<input size="40" name="labQuaProficiencyPlanVo.sampRegisterUser"
																id="sampRegisterUser" value="${labQuaProficiencyPlanVo.sampRegisterUser}"/>
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="theme.phone"/>：
															</label>
														</td>
														<td>
															<input size="40" name="labQuaProficiencyPlanVo.sampRegisterTel"
																id="sampRegisterTel" type="text"  valType="phone"  phone-msg="电话格式不准确"
																value="${labQuaProficiencyPlanVo.sampRegisterTel}" />
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="sam.number"/>：
															</label>
														</td>
														<td colspan="3">
															<input  valType="number" msg='<s:property value="getText('samnumbernotco')"/>' size="40" valType="number"  name="labQuaProficiencyPlanVo.sampRegisterNum"
																id="sampRegisterNum" type="text"
																value="${labQuaProficiencyPlanVo.sampRegisterNum}"/>&nbsp;&nbsp;
															<l:a href="#" uri="quality/proficiencyPlan/addLabQuaProficiencyPlan.action?labQuaProficiencyPlanVo.auditResult=0" value="generateconten" />
														</td>
													</tr>
												</table>
											</div>
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
