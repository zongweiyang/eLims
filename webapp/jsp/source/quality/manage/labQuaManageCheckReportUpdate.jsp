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
       
       function showReport(flag){
				var tempId=$('#tempId').val();
				if(tempId==''){
					alert('<s:property value="getText('modleseletconfig')"/>');
					return false;
				}
				var height = window.screen.height-250;
				var url  = '${basePath}quality/manage/addLabReport4Bus.action?labReportVo.editType=1&labReportVo.path=${labQuaManageCheckVo.reportPath}&labReportVo.busInsId=${labQuaManageCheckVo.id}&labReportVo.id='+tempId+'&labReportVo.isNew='+flag;
				$.dialog({
					id:'id',
					content:'url:'+url,
					title:'<s:property value="getText('pageprint')"/>',
					opacity:0.4,
					width:1050,
					height:height,
					max:false,
					min:false,
					lock:true
				 });
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaManageCheckPlanForm" id="form">
		<input name="labQuaManageCheckVo.id" type="hidden" value="${labQuaManageCheckVo.id }" />
		<input type="hidden" name="labQuaManageCheckVo.reportPath" id="reportPath" value="${labQuaManageCheckVo.reportPath }" />
		<input type="hidden" name="labQuaManageCheckVo.problem" id="problem" value="${labQuaManageCheckVo.problem}" />
		<input type="hidden" name="labQuaManageCheckVo.measure" id="measure" value="${labQuaManageCheckVo.measure}" />
		<input type="hidden" name="labQuaManageCheckVo.measureCheck" id="measureCheck" value="${labQuaManageCheckVo.measureCheck}" />
		<input name="labQuaManageCheckVo.recordPeople" id="recordPeople" type="hidden" value="${labQuaManageCheckVo.recordPeople}" />
		<input name="labQuaManageCheckVo.recordTime" id="recordTime" type="hidden" value="${labQuaManageCheckVo.recordTime}"/>
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
																	<l:a uri="quality/manage/updateLabQuaManageCheckReport.action" onclick="goAction('updateLabQuaManageCheckReport.action?labQuaManageCheckVo.auditResult=0');" value="lab.code.save"/>
																</td>
																<!-- <td>
																	<l:a uri="quality/manage/updateLabQuaManageCheckReport.action" onclick="goAction('updateLabQuaManageCheckReport.action?labQuaManageCheckVo.auditResult=1');" value="save.commit"/>
																</td> -->
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
													<input type="hidden"  id="orgId" name="labQuaManageCheckVo.orgId" value="${labQuaManageCheckVo.orgId }"  />
													<input type="hidden"  id="orgName" name="labQuaManageCheckVo.orgName"  value="${labQuaManageCheckVo.orgName }" />
												</td>
												<td class="r" width="150">
													<label>
														评审计划：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.quaManageCheckPlanName}
													<input name="labQuaManageCheckVo.quaManageCheckPlanName" id="quaManageCheckPlanName" type="hidden"
														value="${labQuaManageCheckVo.quaManageCheckPlanName}" />
														<input name="labQuaManageCheckVo.quaManageCheckPlanId" id="quaManageCheckPlanId" type="hidden"
														value="${labQuaManageCheckVo.quaManageCheckPlanId}" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														评审时间：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.recTime}
													<input name="labQuaManageCheckVo.recTime" id="recTime" type="hidden"
														value="${labQuaManageCheckVo.recTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											    <td class="r" width="150">
													<label>
														评审地点：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.address}
													<input name="labQuaManageCheckVo.address" id="address"
														type="hidden" value="${labQuaManageCheckVo.address}"/>
												</td>
												
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													组&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长：
														</label>
												</td>
												<td>
													${labQuaManageCheckVo.groupLeader}
													<input name="labQuaManageCheckVo.groupLeader" id="groupLeader" type="hidden"
														value="${labQuaManageCheckVo.groupLeader}" />
												</td>
												<td class="r" width="150">
													<label>
														组&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;员：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.groupMember}
													<input name="labQuaManageCheckVo.groupMember" id="groupMember" type="hidden"
														value="${labQuaManageCheckVo.groupMember}"/>
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
													<label>
														编&nbsp;&nbsp;制&nbsp;&nbsp;人：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.reportPeople}
													<input name="labQuaManageCheckVo.reportPeople" id="reportPeople" type="hidden"
														value="${labQuaManageCheckVo.reportPeople}" />
												</td>
												<td class="r" width="300">
													<label>
														<s:text name="codedates"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.reportTime}
													<input name="labQuaManageCheckVo.reportTime" id="reportTime" type="hidden"
														value="${labQuaManageCheckVo.reportTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
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
													<input name="labQuaManageCheckVo.checkPeople" id="checkPeople" type="hidden"
														value="${labQuaManageCheckVo.checkPeople}" />
												</td>
												<td class="r" width="300">
													<label>
														批准日期：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.checkTime}
													<input name="labQuaManageCheckVo.checkTime" id="checkTime" type="hidden"
														value="${labQuaManageCheckVo.checkTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													管理评审记录：
														</label>
												</td>
												<td colspan="3">
													 <textarea rows="2" readonly="readonly" cols="40" name="labQuaManageCheckVo.record" id="record" >${labQuaManageCheckVo.record}</textarea>
												</td>
											</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="report.preview"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>   
											</div>
											<table class="FormtableCon">
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="report.info"/>：
														</label>
													</td>
													<td width="100">
														<s:select list="#request.tempList" id="tempId"
															listKey="id" listValue="title" theme="simple"
															name="labQuaManageCheckVo.reportTempId"></s:select>
													</td>
													<td>
														<a id="reportPatha" class="zPushBtn"
															href="javascript:void(0);"
															onclick="showReport('');"><img
																height="20" width="20" src="${basePath}/img/dayin.gif" /><b><s:text name="reportprint"/></b>
														</a>
														<s:if test="${fn:length(labQuaManageCheckVo.reportPath)>0}">
															<a class="zPushBtn"
																href="javascript:void(0);"
																onclick="if(confirm('<s:property value="getText('regeneteatesd')"/>')){showReport('1');}"><img
																	height="20" width="20" src="${basePath}/img/dayin.gif" /><b><s:text name="re.generate"/></b>
															</a>
														</s:if>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="archiinfo"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
												    <td class="r" width="150">
													       <label>
																<s:text name="lab.yes"/>否存档：
															</label>
													</td>
													<td colspan="3" > 
														<s:if test="${labQuaManageCheckVo.isFile == '' || labQuaManageCheckVo.isFile == null}">
															<s:radio cssStyle="border:0 " theme="simple" list="#{'0':getText('lab.yes'),'1':getText('lab.no')}" value="'1'" name="labQuaManageCheckVo.isFile"></s:radio>
														</s:if>
														<s:else>
															<s:radio cssStyle="border:0 " theme="simple" list="#{'0':getText('lab.yes'),'1':getText('lab.no')}" value="'${labQuaManageCheckVo.isFile}'" name="labQuaManageCheckVo.isFile"></s:radio>
														</s:else>
													</td>
												</tr>
												<tr>
												    <td class="r" width="150">
													       <label>
														存档说明：
															</label>
													</td>
													<td colspan="3">
														 <textarea rows="3" cols="40" name="labQuaManageCheckVo.remark" id="remark" >${labQuaManageCheckVo.remark}</textarea>
													</td>
												</tr>
												<tr>
												    <td class="r" width="150">
														<label>
															归档人：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaManageCheckVo.filePeople" id="filePeople" type="text"
															value="${labQuaManageCheckVo.filePeople}" />
													</td>
													<td class="r" width="270">
														<label>
															归档日期：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaManageCheckVo.fileTime" id="fileTime" type="text"
															value="${labQuaManageCheckVo.fileTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
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
