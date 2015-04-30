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
		<form action="" method="post" name="sampTestAllotFrom" id="sampTestAllotFrom">
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
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="task.no"/>：
																	</label>
																</td>
																<td>
																	<input id="name" name="labSamTestVo.taskNo" value="${labSamTestVo.taskNo}" />
																</td>
																<td>
																	<label>
																		<s:text name="state.status"/>：
																	</label>
																</td>
																<td>
																	<s:select theme="simple" list="#request.funStepList"
																		listKey="stepId" listValue="stepName" headerKey=""
																		headerValue='<s:property value="getText('alldata')"/>' name="labSamTestVo.status"
																		id="status"
																		onchange="submitvalue('samtest/labSamTask/listLabSamTask4Allot.action');">
																	</s:select>
																</td>
																<td>
																	<l:a uri="${pageResult.action}" onclick="submitAction();" value="fun.query" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a  uri="samtest/labSamTask/preUpdateSamTask4AllotBeach.action" onclick="submitvalue('samtest/labSamTask/preUpdateSamTask4AllotBeach.action');"  img="img/query.gif" value="batch.assign" />
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
													<th class="w50">
														<input type="checkbox" id="allCheckBox" key="labSamTestVo.ids" />
													</th>
													<th property="no"><s:text name="task.no"/></th>
													<th><s:text name="checking.item"/></th>
													<th><s:text name="sam.number"/></th>
													<th property="taskType"><s:text name="checking.type"/></th>
													<th property="finishDate"><s:text name="comp.report.date"/></th>
													<th ><s:text name="progressing"/></th>
													<th width="100">
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
																<td class="c">
																	<input type="checkbox" name="labSamTestVo.ids" value="${id}" />
																</td>
																<td class="c">${no}</td>
																<td>${itemNames}</td>
																<td>${sampNum}</td>
																<td>${taskType}</td>
																<td class="c">${finishDate}</td>
																<td class="l" style="padding-left: 20px;">
																	<a href="javascript:;" onclick="showProcess('${id}');return false">${status}
																	<s:if test="${isBack=='Y'}">
																		<img src="${basePath}/img/tishi_icon.gif" width="12" height="12"/>
																	</s:if>
																	</a>
																</td>
																<td class="c">
																	<s:if test="${isOper=='Y'}">
																		<l:a href="#" uri="samtest/labSamTask/preUpdateLabSamTask4Allot.action?labSamTestVo.taskId=${id}" value="assigned" />
																	</s:if>
																	<s:else>
																		<font><s:text name="assigned"/></font>
																	</s:else>
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="9" align="center" valign="middle">
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
										page="/jsp/include/page.jsp?formName=sampTestAllotFrom" flush="true" /></td>
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
