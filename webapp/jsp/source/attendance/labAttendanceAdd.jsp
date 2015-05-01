<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
		<script>
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
		 	$(function(){
		 		var msg='${labAttendanceVo.auditResult}';
		 		if(msg=='Y'){
		 			W.flushThisWin();
		 			api.close();
		 			alert('<s:property value="getText('buchansuccess')"/>');
		 		}
		 	});
		</script>
	</head>
	<body>

		<form action="" method="post" name="form">
			<input name="labAttendanceVo.userId" value="${labAttendanceVo.userId}" type="hidden" />
			<input name="labAttendanceVo.id" value="${labAttendanceVo.id}" type="hidden" />
			<table id="bodyTable" class="bodytable_pop" cellspacing="0" cellpadding="0" border="0" style="min-height: 50px; width: 95%">
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox_pop" style="min-height: 50px; width: 100%; padding-top: 10px;">
										<table class="listTable_pop" cellspacing="0" cellpadding="0">
											<tr>
												<td>
													<label>
														<s:text name="theme.curr.user"/>：
													</label>
												</td>
												<td>
													${labAttendanceVo.userName}
												</td>
												<td>
													<label>
														<s:text name="attent.date"/>：
													</label>
												</td>
												<td>
													<input type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'%y-%M-{%d-1}'})" name="labAttendanceVo.workDate" value="${labAttendanceVo.workDate}" id="workDate" onchange="goAction('attendance/labAttendance/preAddLabAttendance4History.action');"/>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="wordday.time"/>：
													</label>
												</td>
												<td>
													${labAttendanceVo.standTimeAM}
												</td>
												<td>
													<label>
														<s:text name="cark.time"/>：
													</label>
												</td>
												<td>
													<s:if test="${fn:length(labAttendanceVo.startTime)>0}">
														${labAttendanceVo.startTime}
														<input type="hidden" value="${labAttendanceVo.startTime}" id="startTime" />
													</s:if>
													<s:else>
														<input type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'HH:mm',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endTime\')}'})" name="labAttendanceVo.startTime" value="${labAttendanceVo.startTime}" id="startTime" />
													</s:else>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="after.time"/>：
													</label>
												</td>
												<td>
													${labAttendanceVo.standTimePM}
												</td>
												<td>
													<label>
														<s:text name="cark.time"/>：
													</label>
												</td>
												<td>
													<s:if test="${fn:length(labAttendanceVo.endTime)>0}">
														${labAttendanceVo.endTime}
														<input type="hidden" value="${labAttendanceVo.endTime}" id="endTime" />
													</s:if>
													<s:else>
														<input type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'HH:mm',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startTime\')}'})" name="labAttendanceVo.endTime" value="${labAttendanceVo.endTime}" id="endTime" />
													</s:else>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														说明：
													</label>
												</td>
												<td colspan="3">
													<textarea style="width: 100%;height: 56px;overflow:hidden;" name="labAttendanceVo.remark">${labAttendanceVo.remark}</textarea>
												</td>
											</tr>
										</table>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<s:if test="${fn:length(labAttendanceVo.startTime)>0&&fn:length(labAttendanceVo.endTime)>0}">
																		<l:a uri="attendance/labAttendance/addLabAttendance4History.action"  value="page.confirm" disabled="true"/>
																	</s:if>
																	<s:else>
																		<l:a uri="attendance/labAttendance/addLabAttendance4History.action" onclick="goAction('attendance/labAttendance/addLabAttendance4History.action');" value="page.confirm" />
																	</s:else>
																</td>
															</tr>
														</table>
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
