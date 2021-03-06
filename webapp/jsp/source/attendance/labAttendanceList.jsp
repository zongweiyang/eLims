<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

#dayShow td {
	height: 56px;
	width: 100px;
	padding-top: 0px;
}
#dayShow td p {
	text-align: center;
}
#dayShow td .remark{
	text-align:left;
	vertical-align:bottom;
}
#dayShow td .day{
	font-weight: bold;
	font-size: 18px;
	text-align:left;
	vertical-align:top;
	margin-top: opx;
}
</style>
		<script>
		function submitvalue(actionstr){
			$('form').attr('action','<%=basePath%>'+actionstr);
			$('form').submit();
		}
		function showMsg(url){
			var url  = '${basePath}'+url;
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'<s:property value="getText('todayatten')"/>',
				opacity:0.4,
				max: false,                  
		        min: false,
				width:300,
				height:200,
				lock:true
			 });
		}
		function showMsg4After(url){
			var url  = '${basePath}'+url;
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'<s:property value="getText('attenbuchan')"/>',
				opacity:0.4,
				max: false,                  
		        min: false,
				width:500,
				height:300,
				lock:true
			 });
		}
		function flushThisWin(){
			window.location.href=window.location.href;
		}
		</script>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labAttendanceFrom" id="labAttendanceFrom">
			<input name="labAttendanceVo.userId" value="${labAttendanceVo.userId}" type="hidden" />
			<input name="labAttendanceVo.orgId" value="${labAttendanceVo.orgId}" type="hidden" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
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
																	<s:text name="month"/>份：
																	<input type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM',alwaysUseStartDate:true})" name="labAttendanceVo.workMonth" value="${labAttendanceVo.workMonth}" onchange="submitvalue('attendance/labAttendance/listLabAttendance.action')" />
																</td>
																<td>
																	<l:a uri="attendance/labAttendance/addLabAttendance.action" onclick="showMsg('attendance/labAttendance/addLabAttendance.action');" value="att.in" />
																</td>
																<td>
																	<l:a uri="attendance/labAttendance/updateLabAttendance.action" onclick="showMsg('attendance/labAttendance/updateLabAttendance.action');" value="att.out" />
																</td>
																<td>
																	<l:a uri="attendance/labAttendance/preAddLabAttendance4History.action" onclick="showMsg4After('attendance/labAttendance/preAddLabAttendance4History.action');" value="att.repair" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="TabTableBOX01 b" id="Tab01">
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="atd.info"/></span>
												</div>
												<table class="FormtableCon_sform">
													<tr>
														<th>
															<s:text name="act.atd"/>（<s:text name="dayday"/>）
														</th>
														<th>
															<s:text name="lated"/>（<s:text name="times"/>）
														</th>
														<th>
															<s:text name="be.out"/>（<s:text name="times"/>）
														</th>
														<th>
															<s:text name="save.atd"/>（<s:text name="times"/>）
														</th>
														<th>
															<s:text name="lat.card"/>（<s:text name="times"/>）
														</th>
													</tr>
													<tr>
														<td class="c">
															<font color="#009933">${countList[0]}</font>
														</td>
														<td class="c">
															<font color="red">${countList[1]}</font>
														</td>
														<td class="c">
															<font color="red">${countList[2]}</font>
														</td>
														<td class="c">
															<font color="red">${countList[3]}</font>
														</td>
														<td class="c">
															<font color="red">${countList[4]}</font>
														</td>
													</tr>
												</table>
												<table class="FormtableCon_sform" id="dayShow">
													<tr>
														<th>
															<s:text name="monday"/>
														</th>
														<th>
															<s:text name="quesday"/>
														</th>
														<th>
															<s:text name="thirday"/>
														</th>
														<th>
															<s:text name="fourday"/>
														</th>
														<th>
															<s:text name="firday"/>
														</th>
														<th>
															<s:text name="satarday"/>
														</th>
														<th>
															<s:text name="sunday"/>
														</th>
													</tr>
													<tr>
														<s:iterator value="attendList" status="st">
															<s:if test="${isGood=='Y'}">
																<td style="background-color: green;" title='<s:property value="getText('addban')"/>'>
																	<div class="day">${day}</div>
																	<p>${startTime} ~ ${endTime}&nbsp;</p>
																	<div class="remark">${remark}&nbsp;</div>
																</td>
															</s:if>
															<s:elseif test="${week==6||week==7}">
																<td style="background-color: #E0E0E0;">
																	<s:if test="${day!=''}">
																		<div class="day">${day}</div>
																		<p>&nbsp;</p>
																		<div class="remark">&nbsp;</div>
																	</s:if>
																</td>
															</s:elseif>
															<s:elseif test="${noWork=='Y'}">
																<td style="background-color: red;" title='<s:property value="getText('kuanggong')"/>'>
																	<div class="day">${day}</div>
																	<p>&nbsp;</p>
																	<div class="remark">&nbsp;</div>
																</td>
															</s:elseif>
															<s:elseif test="${startFlag=='Y'||endFlag=='Y'}">
																<td style="background-color: yellow;" title='<s:property value="getText('chidaozaotui')"/>'>
																	<div class="day">${day}</div>
																	<p>${startTime} ~ ${endTime}&nbsp;</p>
																	<div class="remark">${remark}&nbsp;</div>
																</td>
															</s:elseif>
															<s:elseif test="${afterFlag=='Y'}">
																<td style="background-color: #99FF99;" title='<s:property value="getText('att.repair')"/>'>
																	<div class="day">${day}</div>
																	<p>${startTime} ~ ${endTime}&nbsp;</p>
																	<div class="remark">${remark}&nbsp;</div>
																</td>
															</s:elseif>
															<s:elseif test="${isOk=='Y'}">
																<td style="background-color:#00CC00;"  title='<s:property value="getText('common')"/>'>
																	<div class="day">${day}</div>
																	<p>${startTime} ~ ${endTime}&nbsp;</p>
																	<div class="remark">${remark}&nbsp;</div>
																</td>
															</s:elseif>
															<s:else>
																<td>
																	<s:if test="${day>0}">
																		<div class="day">${day}</div>
																		<p>&nbsp;</p>
																	<div class="remark">&nbsp;</div>
																	</s:if>
																</td>
															</s:else>
															<s:if test="${(st.index+1)%7==0}">
													</tr>
													<tr>
														</s:if>
														</s:iterator>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</td>
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
	</body>
</html>
