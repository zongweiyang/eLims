<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
<script>
function submitvalue(actionstr){
	$('form').attr('action','<%=basePath%>'+actionstr);
	$('form').submit();
}
function showProcess(id){
	$.dialog({
		id:'id',
		content:'url:'+'<%=basePath%>/jsp/common/workflow/ins/processFrame.jsp?busId='+id,
		title:'<s:property value="getText('task.progress')"/>',
		opacity:0.4,
		width:1100, 
		height:500,
		lock:true,
		max:false,
		min:false
	});
}
		</script>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labSamReportFrom" id="labSamReportFrom">
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
												<span><s:text name="top.index"/></span>
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
																	<label>
																		<s:text name="task.no"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSamReportVo.busNo"
																		 value="${labSamReportVo.busNo}" />
																</td>
																<td>
																	<label>
																		<s:text name="report.no"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSamReportVo.reportNo"
																		 value="${labSamReportVo.reportNo}" />
																</td>
																<td>
																	<label>
																		<s:text name="lec.cust.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSamReportVo.labCustomerName"
																		 value="${labSamReportVo.labCustomerName}" />
																</td>
																<td></td>
															</tr>
															<tr>
																<td>
																	<label>
																		<s:text name="comp.report.date"/>：
																	</label>
																</td>
																<td colspan="3">
																	<input type="text" name="labSamReportVo.startDate"
																		value="${labSamReportVo.startDate}" class="Wdate"
																		size="12" id="startDate" 
																		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});" />
																	~
																	<input type="text" name="labSamReportVo.endDate"
																		value="${labSamReportVo.endDate}" class="Wdate"
																		size="12" id="endDate" 
																		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});" />
																</td>
																<td>
																	<label>
																		<s:text name="state.status"/>：
																	</label>
																</td>
																<td>
																	<s:select theme="simple" list="#request.funStepList"
																		listKey="stepId" listValue="stepName" headerKey=""
																		headerValue="-全部-" name="labSamReportVo.status"
																		id="status"
																		onchange="submitvalue('samreport/labSamReport/listLabSamReport4Sign.action');">
																	</s:select>
																</td>
																<td>
																	<l:a uri="${SessionContainer.lastUrl}"
																		onclick="submitAction();" value="fun.query" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="1"
											cellpadding="0">
											<thead>
												<tr>
													<th  property="busNo"><s:text name="task.no"/></th>
													<th property="reportNo"><s:text name="report.no"/></th>
													<th property="labCustomerName"><s:text name="lec.cust.name"/></th>
													<th property="reportType"><s:text name="report.performance"/></th>
													<th property="sampType"><s:text name="report.type"/></th>
													<th property="finishDate"><s:text name="comp.report.date"/></th>
													<th width="100"><s:text name="progressing"/></th>
													<th>
														<s:text name="lab.code.ops"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td>${busNo}</td>
																<td><a href="<%=basePath%>samreport/labSamReport/showLabSamReport.action?labSamReportVo.id=${id}">${reportNo}</a></td>
																<td>${labCustomerName}</td>
																<td>${reportType}</td>
																<td>${sampType}</td>
																<td>${finishDate}</td>
																<td class="l" style="padding-left: 20px;">
																	<a href="javascript:;" onclick="showProcess('${busId}');return false">${status}
																	<s:if test="${isBack=='Y'}">
																		<img src="${basePath}/img/tishi_icon.gif" width="12" height="12"/>
																	</s:if>
																	</a>
																</td>
																<td class="c">
																	<s:if test="${isOper=='Y'}">
																		<l:a href="#"
																		uri="samreport/labSamReport/preUpdateLabSamReport4Sign.action?labSamReportVo.id=${id}"
																		value="flow.check" />
																	</s:if>
																	<s:else>
																		<l:a href="#" uri="samreport/labSamReport/showLabSamReport.action?labSamReportVo.id=${id}" value="look.check" />
																	</s:else>
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="8" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"/></font>
														</td>
													</tr>
												</s:else>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=labSamReportFrom" flush="true" /></td>
							</tr>
						</table>
					</td>
					<td class="MRimg"></td>
				</tr>
				<tr>
					<td class="FLimg"></td>
					<td class="FMimg"></td>
					<td class="FRimg"></td>
				</tr>
			</table>
		</form>
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
